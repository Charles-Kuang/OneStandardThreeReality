<template>
    <div class="app-container">
      <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
        <el-form-item label="检查日期">
          <el-date-picker
            v-model="daterangeCheckDate"
            style="width: 240px"
            value-format="yyyy-MM-dd"
            type="daterange"
            range-separator="-"
            start-placeholder="开始日期"
            end-placeholder="结束日期"
          ></el-date-picker>
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
            v-hasPermi="['system:check:add']"
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
            v-hasPermi="['system:check:edit']"
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
            v-hasPermi="['system:check:remove']"
          >删除</el-button>
        </el-col>
        <el-col :span="1.5">
          <el-button
            type="warning"
            plain
            icon="el-icon-download"
            size="mini"
            @click="handleExport"
            v-hasPermi="['system:check:export']"
          >导出</el-button>
        </el-col>
        <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
      </el-row>
  
      <el-table v-loading="loading" :data="checkList" @selection-change="handleSelectionChange">
        <el-table-column type="selection" width="55" align="center" />
        <el-table-column label="检查ID" align="center" prop="checkId" />
        <el-table-column label="房屋标准地址" align="center" prop="stdAddress" :show-overflow-tooltip='true' />
        <el-table-column label="检查日期" align="center" prop="checkDate" width="180">
          <template slot-scope="scope">
            <span>{{ parseTime(scope.row.checkDate, '{y}-{m}-{d}') }}</span>
          </template>
        </el-table-column>
        <el-table-column label="检查人" align="center" prop="inspectorName" />
        <el-table-column label="危险类别" align="center" prop="dangerType">
          <template slot-scope="scope">
            <dict-tag :options="dict.type.danger_type" :value="scope.row.dangerType"/>
          </template>
        </el-table-column>
        <el-table-column label="检查类别" align="center" prop="checkType">
          <template slot-scope="scope">
            <dict-tag :options="dict.type.check_type" :value="scope.row.checkType"/>
          </template>
        </el-table-column>
        <el-table-column label="检查结果" align="center" prop="checkResult">
          <template slot-scope="scope">
            <dict-tag :options="dict.type.check_manage_result" :value="scope.row.checkResult"/>
          </template>
        </el-table-column>
        <el-table-column label="处理日期" align="center" prop="manageDate" width="180">
          <template slot-scope="scope">
            <span>{{ parseTime(scope.row.manageDate, '{y}-{m}-{d}') }}</span>
          </template>
        </el-table-column>
        <el-table-column label="处理结果" align="center" prop="manageResult">
          <template slot-scope="scope">
            <dict-tag :options="dict.type.check_manage_result" :value="scope.row.manageResult"/>
          </template>
        </el-table-column>
        <el-table-column label="备注" align="center" prop="remark" />
        <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
          <template slot-scope="scope">
            <el-button
              size="mini"
              type="text"
              icon="el-icon-edit"
              @click="handleUpdate(scope.row)"
              v-hasPermi="['system:check:edit']"
            >修改</el-button>
            <el-button
              size="mini"
              type="text"
              icon="el-icon-delete"
              @click="handleDelete(scope.row)"
              v-hasPermi="['system:check:remove']"
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
  
      <!-- 添加或修改房屋日常检查对话框 -->
      <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
        <el-form ref="form" :model="form" :rules="rules" label-width="80px">
          <el-form-item v-if="form.checkId === null" label="检查日期" prop="checkDate">
            <el-date-picker clearable
              v-model="form.checkDate"
              type="date"
              value-format="yyyy-MM-dd"
              placeholder="请选择检查日期">
            </el-date-picker>
          </el-form-item>
          <el-form-item v-if="form.checkId != null" label="处理日期" prop="manageDate">
            <el-date-picker clearable
              v-model="form.manageDate"
              type="date"
              value-format="yyyy-MM-dd"
              placeholder="请选择处理日期">
            </el-date-picker>
          </el-form-item>
          <el-form-item label="检查人证件类型" prop="inspectorIdType">
            <el-select v-model="form.inspectorIdType" placeholder="请选择检查人证件类型" :disabled="checkDisabled">
              <el-option
                v-for="dict in dict.type.host_id_type"
                :key="dict.value"
                :label="dict.label"
                :value="dict.value"
              ></el-option>
            </el-select>
          </el-form-item>
          <el-form-item label="检查人证件号码" prop="inspectorId">
            <el-input v-model="form.inspectorId" placeholder="请输入检查人证件号码" :disabled="checkDisabled"/>
          </el-form-item>
          <el-form-item label="危险类别" prop="dangerType">
            <el-select v-model="form.dangerType" placeholder="请选择危险类别" :disabled="checkDisabled">
              <el-option
                v-for="dict in dict.type.danger_type"
                :key="dict.value"
                :label="dict.label"
                :value="dict.value"
              ></el-option>
            </el-select>
          </el-form-item>
          <el-form-item label="检查类别" prop="checkType">
            <el-select v-model="form.checkType" placeholder="请选择检查类别" :disabled="checkDisabled">
              <el-option
                v-for="dict in dict.type.check_type"
                :key="dict.value"
                :label="dict.label"
                :value="dict.value"
              ></el-option>
            </el-select>
          </el-form-item>
          <el-form-item label="检查结果" prop="checkResult">
            <el-select v-model="form.checkResult" placeholder="请选择检查结果" :disabled="checkDisabled">
              <el-option
                v-for="dict in dict.type.check_manage_result"
                :key="dict.value"
                :label="dict.label"
                :value="dict.value"
              ></el-option>
            </el-select>
          </el-form-item>
          <el-form-item v-if="form.checkId != null" label="处理结果" prop="manageResult">
            <el-select v-model="form.manageResult" placeholder="请选择处理结果">
              <el-option
                v-for="dict in dict.type.check_manage_result"
                :key="dict.value"
                :label="dict.label"
                :value="dict.value"
              ></el-option>
            </el-select>
          </el-form-item>
          <el-form-item label="备注" prop="remark">
            <el-input v-model="form.remark" placeholder="请输入备注" />
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
  import { listCheck, getCheck, delCheck, addCheck, updateCheck } from "@/api/standard/check";
  
  export default {
    name: "Check",
    dicts: ['check_type', 'danger_type', 'check_manage_result', 'host_id_type'],
    data() {
      return {
        // 遮罩层
        loading: true,
        //是否可选
        manageDisabled: true,
        checkDisabled: false,
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
        // 房屋日常检查表格数据
        checkList: [],
        // 弹出层标题
        title: "",
        // 是否显示弹出层
        open: false,
        // 备注时间范围
        daterangeCheckDate: [],
        // 查询参数
        queryParams: {
          pageNum: 1,
          pageSize: 10,
          checkDate: null,
        },
        // 表单参数
        form: {},
        // 表单校验
        rules: {
          checkDate: [
            { required: true, message: "检查日期不能为空", trigger: "blur" }
          ],
          inspectorId: [
            { required: true, message: "检查人证件号码不能为空", trigger: "blur"},
            { message: "检查人证件号码长度最多18位", max: 18, trigger: "blur" }
          ],
          inspectorIdType: [
            { required: true, message: "检查人证件类型不能为空", trigger: "change" }
          ],
          checkResult: [
            { required: true, message: "检查结果不能为空", trigger: "change" }
          ],
          dangerType: [
          { required: true, message: "危险类别不能为空", trigger: "change" }
          ]
        }
      };
    },
    created() {
      this.getList();
    },
    methods: {
      /** 查询房屋日常检查列表 */
      getList() {
        this.loading = true;
        this.houseId = this.$route.params.houseId;
        this.queryParams.params = {};
        this.queryParams.houseId = this.houseId;
        if (null != this.daterangeCheckDate && '' != this.daterangeCheckDate) {
          this.queryParams.params["beginCheckDate"] = this.daterangeCheckDate[0];
          this.queryParams.params["endCheckDate"] = this.daterangeCheckDate[1];
        }
        listCheck(this.queryParams).then(response => {
          this.checkList = response.rows;
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
          checkId: null,
          houseId: null,
          checkDate: null,
          inspectorId: null,
          dangerType: null,
          checkType: null,
          checkResult: null,
          manageResult: null,
          remark: null
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
        this.daterangeCheckDate = [];
        this.resetForm("queryForm");
        this.handleQuery();
      },
      // 多选框选中数据
      handleSelectionChange(selection) {
        this.ids = selection.map(item => item.checkId)
        this.single = selection.length!==1
        this.multiple = !selection.length
      },
      /** 新增按钮操作 */
      handleAdd() {
        this.reset();
        this.open = true;
        this.title = "添加房屋日常检查";
        this.manageDisabled = true;
        this.checkDisabled = false;
      },
      /** 修改按钮操作 */
      handleUpdate(row) {
        this.reset();
        const checkId = row.checkId || this.ids;
        this.manageDisabled = false;
        this.checkDisabled = true;
        getCheck(checkId).then(response => {
          this.form = response.data;
          this.open = true;
          this.title = "修改房屋日常检查";
        });
      },
      /** 提交按钮 */
      submitForm() {
        this.form.houseId = this.houseId;
        this.$refs["form"].validate(valid => {
          if (valid) {
            if (this.form.checkId != null) {
              updateCheck(this.form).then(response => {
                this.$modal.msgSuccess("修改成功");
                this.open = false;
                this.getList();
              });
            } else {
              addCheck(this.form).then(response => {
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
        const checkIds = row.checkId || this.ids;
        this.$modal.confirm('是否确认删除房屋日常检查编号为"' + checkIds + '"的数据项？').then(function() {
          return delCheck(checkIds);
        }).then(() => {
          this.getList();
          this.$modal.msgSuccess("删除成功");
        }).catch(() => {});
      },
      /** 导出按钮操作 */
      handleExport() {
        this.download('system/check/export', {
          ...this.queryParams
        }, `check_${new Date().getTime()}.xlsx`)
      }
    }
  };
  </script>
  