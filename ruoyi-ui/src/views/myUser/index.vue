<template>
    <div class="app-container">
      <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
        <el-form-item label="用户账号" prop="userName">
          <el-input
            v-model="queryParams.userName"
            placeholder="请输入用户账号"
            clearable
            @keyup.enter.native="handleQuery"
          />
        </el-form-item>
        <el-form-item label="用户类型" prop="userType">
          <el-select v-model="queryParams.userType" placeholder="请选择用户类型" clearable>
            <el-option
              v-for="dict in dict.type.std_user_type"
              :key="dict.value"
              :label="dict.label"
              :value="dict.value"
            />
          </el-select>
        </el-form-item>
        <el-form-item label="手机号码" prop="phonenumber">
          <el-input
            v-model="queryParams.phonenumber"
            placeholder="请输入手机号码"
            clearable
            @keyup.enter.native="handleQuery"
          />
        </el-form-item>
        <el-form-item label="用户性别" prop="sex">
          <el-select v-model="queryParams.sex" placeholder="请选择用户性别" clearable>
            <el-option
              v-for="dict in dict.type.sys_user_sex"
              :key="dict.value"
              :label="dict.label"
              :value="dict.value"
            />
          </el-select>
        </el-form-item>
        <el-form-item label="最小积分" prop="minPoints">
            <el-input
              v-model="queryParams.minPoints"
              placeholder="请输入最小积分"
              clearable
              style="width: 240px"
              @keyup.enter.native="handleQuery"
            />
          </el-form-item>
          <el-form-item label="最大积分" prop="maxPoints">
            <el-input
              v-model="queryParams.maxPoints"
              placeholder="请输入最大积分"
              clearable
              style="width: 240px"
              @keyup.enter.native="handleQuery"
            />
          </el-form-item>
        <el-form-item>
          <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
          <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
        </el-form-item>
      </el-form>
  
      <el-row :gutter="10" class="mb8">
        <el-col :span="1.5">
          <el-button
            type="primary"
            plain
            icon="el-icon-plus"
            size="mini"
            @click="handleAdd"
            v-hasPermi="['standard:user:add']"
          >新增</el-button>
        </el-col>
        <el-col :span="1.5">
          <el-button
            type="success"
            plain
            icon="el-icon-edit"
            size="mini"
            :disabled="single"
            @click="handleUpdate"
            v-hasPermi="['standard:user:edit']"
          >修改</el-button>
        </el-col>
        <el-col :span="1.5">
          <el-button
            type="danger"
            plain
            icon="el-icon-delete"
            size="mini"
            :disabled="multiple"
            @click="handleDelete"
            v-hasPermi="['standard:user:remove']"
          >删除</el-button>
        </el-col>
        <el-col :span="1.5">
          <el-button
            type="warning"
            plain
            icon="el-icon-download"
            size="mini"
            @click="handleExport"
            v-hasPermi="['standard:user:export']"
          >导出</el-button>
        </el-col>
        <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
      </el-row>
  
      <el-table v-loading="loading" :data="userList" @selection-change="handleSelectionChange">
        <el-table-column type="selection" width="55" align="center" />
        <el-table-column label="用户ID" align="center" prop="userId" />
        <el-table-column label="用户账号" align="center" prop="userName" />
        <el-table-column label="用户类型" align="center" prop="userType">
          <template slot-scope="scope">
            <dict-tag :options="dict.type.std_user_type" :value="scope.row.userType"/>
          </template>
        </el-table-column>
        <el-table-column label="手机号码" align="center" prop="phonenumber" />
        <el-table-column label="用户性别" align="center" prop="sex">
          <template slot-scope="scope">
            <dict-tag :options="dict.type.sys_user_sex" :value="scope.row.sex"/>
          </template>
        </el-table-column>
        <el-table-column label="帐号状态" align="center" prop="status" >
          <template slot-scope="scope">
            <dict-tag :options="dict.type.sys_normal_disable" :value="scope.row.status"/>
          </template>
        </el-table-column>
        <el-table-column label="创建时间" align="center" prop="createTime" width="180">
          <template slot-scope="scope">
            <span>{{ parseTime(scope.row.createTime, '{y}-{m}-{d}') }}</span>
          </template>
        </el-table-column>
        <el-table-column label="账号积分" align="center" prop="accountPoints" />
        <el-table-column label="身份证号" align="center" prop="personId" />
        <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
          <template slot-scope="scope">
            <el-button
              size="mini"
              type="text"
              icon="el-icon-edit"
              @click="handleUpdate(scope.row)"
              v-hasPermi="['standard:user:edit']"
            >修改</el-button>
            <el-button
              size="mini"
              type="text"
              icon="el-icon-delete"
              @click="handleDelete(scope.row)"
              v-hasPermi="['standard:user:remove']"
            >删除</el-button>
            <el-button
              size="mini"
              type="text"
              icon="el-icon-delete"
              @click="handleResetPwd(scope.row)"
              v-hasPermi="['standard:user:resetPwd']"
            >重置密码</el-button>
          </template>
        </el-table-column>
      </el-table>
      
      <pagination
        v-show="total>0"
        :total="total"
        :page.sync="queryParams.pageNum"
        :limit.sync="queryParams.pageSize"
        @pagination="getList"
      />
  
      <!-- 添加或修改用户信息对话框 -->
      <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
        <el-form ref="form" :model="form" :rules="rules" label-width="80px">
          <el-form-item label="用户账号" prop="userName">
            <el-input v-model="form.userName" placeholder="请输入用户账号" />
          </el-form-item>
          <el-form-item label="用户类型" prop="userType">
            <el-select v-model="form.userType" placeholder="请选择用户类型">
              <el-option
                v-for="dict in dict.type.std_user_type"
                :key="dict.value"
                :label="dict.label"
                :value="dict.value"
              ></el-option>
            </el-select>
          </el-form-item>
          <el-form-item label="手机号码" prop="phonenumber">
            <el-input v-model="form.phonenumber" placeholder="请输入手机号码" />
          </el-form-item>
          <el-form-item label="用户性别" prop="sex">
            <el-select v-model="form.sex" placeholder="请选择用户性别">
              <el-option
                v-for="dict in dict.type.sys_user_sex"
                :key="dict.value"
                :label="dict.label"
                :value="dict.value"
              ></el-option>
            </el-select>
          </el-form-item>
          <el-form-item label="帐号状态" prop="status">
            <el-select v-model="form.status" placeholder="请选择帐号状态">
              <el-option
                v-for="dict in dict.type.sys_normal_disable"
                :key="dict.value"
                :label="dict.label"
                :value="dict.value"
              ></el-option>
            </el-select>
          </el-form-item>
          <el-form-item label="积分" prop="accountPoints">
            <el-input v-model="form.accountPoints" placeholder="请输入积分" />
          </el-form-item>
          <el-form-item label="身份证号" prop="personId" v-if="add">
            <el-input v-model="form.personId" placeholder="请输入身份证号" />
          </el-form-item>
        </el-form>
        <div slot="footer" class="dialog-footer">
          <el-button type="primary" @click="submitForm">确 定</el-button>
          <el-button @click="cancel">取 消</el-button>
        </div>
      </el-dialog>
    </div>
  </template>
  
  <script>
  import { listUser, getUser, delUser, addUser, updateUser } from "@/api/standard/myUser";
  import { resetUserPwd } from "@/api/system/user";

  export default {
    name: "User",
    dicts: ['std_user_type', 'sys_user_sex', 'sys_normal_disable'],
    data() {
      return {
        // 遮罩层
        loading: true,
        // 选中数组
        ids: [],
        // 非单个禁用
        single: true,
        // 非多个禁用
        multiple: true,
        // 显示搜索条件
        showSearch: true,
        // 总条数
        total: 0,
        // 用户信息表格数据
        userList: [],
        // 弹出层标题
        title: "",
        // 是否显示弹出层
        open: false,
        //添加或修改
        add: false,
        // 查询参数
        queryParams: {
          pageNum: 1,
          pageSize: 10,
          userName: null,
          userType: null,
          phonenumber: null,
          sex: null,
          password: null,
          status: null,
          accountPoints: null,
          minPoints: undefined,
          maxPoints: undefined
        },
        // 表单参数
        form: {},
        // 表单校验
        rules: {
          userName: [
            { required: true, message: "用户账号不能为空", trigger: "blur" }
          ],
          userType: [
            { required: true, message: "用户类型不能为空", trigger: "change" }
          ],
          phonenumber: [
            { required: true, message: "手机号码不能为空", trigger: "blur" }
          ],
          sex: [
            { required: true, message: "用户性别不能为空", trigger: "change" }
          ],
          password: [
            { required: true, message: "密码不能为空", trigger: "blur" }
          ],
          personId: [
            { required: true, message: "$comment不能为空", trigger: "blur" }
          ]
        }
      };
    },
    created() {
      this.getList();
    },
    methods: {
      /** 查询用户信息列表 */
      getList() {
        this.loading = true;
        listUser(this.queryParams).then(response => {
          this.userList = response.rows;
          this.total = response.total;
          this.loading = false;
        });
      },
      // 取消按钮
      cancel() {
        this.open = false;
        this.reset();
      },
      // 表单重置
      reset() {
        this.form = {
          userId: null,
          deptId: null,
          userName: null,
          nickName: null,
          userType: null,
          email: null,
          phonenumber: null,
          sex: null,
          avatar: null,
          password: null,
          status: "0",
          delFlag: null,
          loginIp: null,
          loginDate: null,
          createBy: null,
          createTime: null,
          updateBy: null,
          updateTime: null,
          remark: null,
          accountPoints: null,
          personId: null
        };
        this.resetForm("form");
      },
      /** 搜索按钮操作 */
      handleQuery() {
        this.queryParams.pageNum = 1;
        this.getList();
      },
      /** 重置按钮操作 */
      resetQuery() {
        this.resetForm("queryForm");
        this.handleQuery();
      },
      // 多选框选中数据
      handleSelectionChange(selection) {
        this.ids = selection.map(item => item.userId)
        this.single = selection.length!==1
        this.multiple = !selection.length
      },
      /** 新增按钮操作 */
      handleAdd() {
        this.reset();
        this.open = true;
        this.add = true;
        this.title = "添加用户信息";
      },
      /** 修改按钮操作 */
      handleUpdate(row) {
        this.reset();
        const userId = row.userId || this.ids
        getUser(userId).then(response => {
          this.form = response.data;
          this.open = true;
          this.add = false;
          this.title = "修改用户信息";
        });
      },
      /** 提交按钮 */
      submitForm() {
        this.$refs["form"].validate(valid => {
          if (valid) {
            if (this.form.userId != null) {
              updateUser(this.form).then(response => {
                this.$modal.msgSuccess("修改成功");
                this.open = false;
                this.getList();
              });
            } else {
              addUser(this.form).then(response => {
                this.$modal.msgSuccess("新增成功");
                this.open = false;
                this.getList();
              });
            }
          }
        });
      },
      /** 删除按钮操作 */
      handleDelete(row) {
        const userIds = row.userId || this.ids;
        this.$modal.confirm('是否确认删除用户信息编号为"' + userIds + '"的数据项？').then(function() {
          return delUser(userIds);
        }).then(() => {
          this.getList();
          this.$modal.msgSuccess("删除成功");
        }).catch(() => {});
      },
      /** 重置密码按钮操作 */
      handleResetPwd(row) {
        this.$prompt('请输入"' + row.userName + '"的新密码', "提示", {
          confirmButtonText: "确定",
          cancelButtonText: "取消",
          closeOnClickModal: false,
          inputPattern: /^.{5,20}$/,
          inputErrorMessage: "用户密码长度必须介于 5 和 20 之间"
        }).then(({ value }) => {
            resetUserPwd(row.userId, value).then(response => {
              this.$modal.msgSuccess("修改成功，新密码是：" + value);
            });
          }).catch(() => {});
      },
      /** 导出按钮操作 */
      handleExport() {
        this.download('standard/myUser/export', {
          ...this.queryParams
        }, `user_${new Date().getTime()}.xlsx`)
      }
    }
  };
  </script>
  