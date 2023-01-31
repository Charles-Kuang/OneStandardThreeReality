<template>
    <div class="app-container">
      <el-row :gutter="10" class="mb8">
        <el-col :span="1.5">
          <el-button
            type="primary"
            plain
            icon="el-icon-plus"
            size="mini"
            @click="handleAdd"
            v-hasPermi="['system:details:add']"
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
            v-hasPermi="['system:details:edit']"
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
            v-hasPermi="['system:details:remove']"
          >删除</el-button>
        </el-col>
        <el-col :span="1.5">
          <el-button
            type="warning"
            plain
            icon="el-icon-download"
            size="mini"
            @click="handleExport"
            v-hasPermi="['system:details:export']"
          >导出</el-button>
        </el-col>
        <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
      </el-row>
  
      <el-table v-loading="loading" :data="detailsList">
        <el-table-column label="单位名称" align="center" prop="deptName" />
        <el-table-column label="单位类别" align="center" prop="deptType">
          <template slot-scope="scope">
            <dict-tag :options="dict.type.dept_type" :value="scope.row.deptType"/>
          </template>
        </el-table-column>
        <el-table-column label="经营状况" align="center" prop="businessStatus">
          <template slot-scope="scope">
            <dict-tag :options="dict.type.business_status" :value="scope.row.businessStatus"/>
          </template>
        </el-table-column>
        <el-table-column label="经营项目" align="center" prop="businessProject" />
        <el-table-column label="副营项目" align="center" prop="viceProject" />
        <el-table-column label="等级评定" align="center" prop="rating">
          <template slot-scope="scope">
            <dict-tag :options="dict.type.rating" :value="scope.row.rating"/>
          </template>
        </el-table-column>
        <el-table-column label="面积" align="center" prop="area" />
        <el-table-column label="民警负责人警号" align="center" prop="policeRepresentativeId" />
        <el-table-column label="民警负责人姓名" align="center" prop="policeRepresentativeName" />
        <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
          <template slot-scope="scope">
            <el-button
              size="mini"
              type="text"
              icon="el-icon-edit"
              @click="handleUpdate(scope.row)"
              v-hasPermi="['system:details:edit']"
            >修改</el-button>
            <el-button
              size="mini"
              type="text"
              icon="el-icon-delete"
              @click="handleDelete(scope.row)"
              v-hasPermi="['system:details:remove']"
            >删除</el-button>
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
  
      <!-- 添加或修改单位类别关系对话框 -->
      <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
        <el-form ref="form" :model="form" :rules="rules" label-width="80px">
          <el-form-item label="单位类别" prop="deptType"  v-if="form.detailId == null">
            <el-select v-model="form.deptType" placeholder="请选择单位类别">
              <el-option
                v-for="dict in dict.type.dept_type"
                :key="dict.value"
                :label="dict.label"
                :value="dict.value"
              ></el-option>
            </el-select>
          </el-form-item>
          <el-form-item label="经营状况" prop="businessStatus">
            <el-select v-model="form.businessStatus" placeholder="请选择经营状况">
              <el-option
                v-for="dict in dict.type.business_status" 
                :key="dict.value"
                :label="dict.label"
                :value="dict.value"
              ></el-option>
            </el-select>
          </el-form-item>
          <el-form-item label="经营项目" prop="businessProject">
            <el-input v-model="form.businessProject" placeholder="请输入经营项目" />
          </el-form-item>
          <el-form-item label="副营项目" prop="viceProject">
            <el-input v-model="form.viceProject" placeholder="请输入副营项目" />
          </el-form-item>
          <el-form-item label="等级评定" prop="rating">
            <el-select v-model="form.rating" placeholder="请选择等级评定">
              <el-option
                v-for="dict in dict.type.rating" 
                :key="dict.value"
                :label="dict.label"
                :value="dict.value"
              ></el-option>
            </el-select>
          </el-form-item>
          <el-form-item label="面积" prop="area">
            <el-input v-model="form.area" placeholder="请输入面积" />
          </el-form-item>
          <el-form-item label="民警负责人警号" prop="policeRepresentativeId">
            <el-input v-model="form.policeRepresentativeId" placeholder="请输入民警负责人警号" />
          </el-form-item>
          <el-form-item label="民警负责人姓名" prop="policeRepresentativeName">
            <el-input v-model="form.policeRepresentativeName" placeholder="请输入民警负责人姓名" />
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
  import { listDetails, getDetails, delDetails, addDetails, updateDetails } from "@/api/standard/details";
  

  export default {
    name: "Details",
    dicts: ['business_status', 'rating', 'dept_type'],
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
        // 单位类别关系表格数据
        detailsList: [],
        // 弹出层标题
        title: "",
        // 是否显示弹出层
        open: false,
        // 查询参数
        queryParams: {
          pageNum: 1,
          pageSize: 10,
        },
        // 表单参数
        form: {},
        // 表单校验
        rules: {
          deptType: [
             { required: true, message: "单位经营类别不能为空", triiger: "change"}
          ],
          area: [
            { pattern: /^[+]?(0|([1-9]\d*))?\.?(0|([1-9]\d*))?$/, message: "请输入正确数字", trigger: "blur"}
          ]
        }
      };
    },
    created() {
      this.getList();
    },
    methods: {
      /** 查询单位类别关系列表 */
      getList() {
        this.loading = true;
        this.deptId = this.$route.params && this.$route.params.deptId;
        this.queryParams.params = {};
        this.queryParams.deptId = this.deptId;
        this.form.deptId = this.deptId;

        listDetails(this.queryParams).then(response => {
          this.detailsList = response.rows;
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
          detailId: null,
          deptType: null,
          businessStatus: null,
          businessProject: null,
          viceProject: null,
          rating: null,
          area: null,
          policeRepresentativeId: null,
          policeRepresentativeName: null
        };
        this.resetForm("form");
        this.form.deptId = this.deptId;
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
      /** 新增按钮操作 */
      handleAdd() {
        this.reset();
        this.open = true;
        this.title = "添加单位类别关系";
      },
      /** 修改按钮操作 */
      handleUpdate(row) {
        this.reset();
        const detailId = row.detailId || this.ids
        getDetails(detailId).then(response => {
          this.form = response.data;
          this.open = true;
          this.title = "修改单位类别关系";
        });
      },
      /** 提交按钮 */
      submitForm() {
        this.$refs["form"].validate(valid => {
          if (valid) {
            if (this.form.detailId != null) {
              updateDetails(this.form).then(response => {
                this.$modal.msgSuccess("修改成功");
                this.open = false;
                this.getList();
              });
            } else {
              for(let i=0; i<this.detailsList.length; i++) {
                if(this.detailsList[i].deptType == this.form.deptType) {
                  this.$message.error("该单位类别已存在");
                  return;
                }
              }
              addDetails(this.form).then(response => {
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
        const detailIds = row.detailId || this.ids;
        this.$modal.confirm('是否确认删除该数据项？').then(function() {
          return delDetails(detailIds);
        }).then(() => {
          this.getList();
          this.$modal.msgSuccess("删除成功");
        }).catch(() => {});
      },
      /** 导出按钮操作 */
      handleExport() {
        this.download('system/details/export', {
          ...this.queryParams
        }, `details_${new Date().getTime()}.xlsx`)
      }
    }
  };
  </script>
  