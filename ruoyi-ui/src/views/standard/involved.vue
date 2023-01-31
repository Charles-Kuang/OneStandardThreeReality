<template>
    <div class="app-container">
      <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
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
            v-hasPermi="['system:involved:add']"
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
            v-hasPermi="['system:involved:edit']"
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
            v-hasPermi="['system:involved:remove']"
          >删除</el-button>
        </el-col>
        <el-col :span="1.5">
          <el-button
            type="warning"
            plain
            icon="el-icon-download"
            size="mini"
            @click="handleExport"
            v-hasPermi="['system:involved:export']"
          >导出</el-button>
        </el-col>
        <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
      </el-row>
  
      <el-table v-loading="loading" :data="involvedList" @selection-change="handleSelectionChange">
        <el-table-column type="selection" width="55" align="center" />
        <el-table-column label="案件编号" align="center" prop="involvedId" />
        <el-table-column label="涉案主体类别" align="center" prop="involvedEntityType" />
        <el-table-column label="涉案主体名称" align="center" prop="involvedEntityName" />
        <el-table-column label="涉案日期" align="center" prop="caseDate" width="180">
          <template slot-scope="scope">
            <span>{{ parseTime(scope.row.caseDate, '{y}-{m}-{d}') }}</span>
          </template>
        </el-table-column>
        <el-table-column label="涉案类别" align="center" prop="caseType">
          <template slot-scope="scope">
            <dict-tag :options="dict.type.involved_type" :value="scope.row.caseType"/>
          </template>
        </el-table-column>
        <el-table-column label="案件名称" align="center" prop="caseName" />
        <el-table-column label="危害程度" align="center" prop="dangerDegree">
          <template slot-scope="scope">
            <dict-tag :options="dict.type.danger_type" :value="scope.row.dangerDegree"/>
          </template>
        </el-table-column>
        <el-table-column label="详细地址" align="center" prop="stdAddress" />
        <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
          <template slot-scope="scope">
            <el-button
              size="mini"
              type="text"
              icon="el-icon-edit"
              @click="handleUpdate(scope.row)"
              v-hasPermi="['system:involved:edit']"
            >修改</el-button>
            <el-button
              size="mini"
              type="text"
              icon="el-icon-delete"
              @click="handleDelete(scope.row)"
              v-hasPermi="['system:involved:remove']"
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
  
      <!-- 添加或修改主体涉案对话框 -->
      <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
        <el-form ref="form" :model="form" :rules="rules" label-width="80px">
          <el-form-item label="涉案日期" prop="caseDate">
            <el-date-picker clearable
              v-model="form.caseDate"
              type="date"
              value-format="yyyy-MM-dd"
              placeholder="请选择涉案日期">
            </el-date-picker>
          </el-form-item>
          <el-form-item label="涉案类别" prop="caseType">
            <el-select v-model="form.caseType" placeholder="请选择涉案类别">
              <el-option
                v-for="dict in dict.type.involved_type"
                :key="dict.value"
                :label="dict.label"
                :value="dict.value"
              ></el-option>
            </el-select>
          </el-form-item>
          <el-form-item label="案件名称" prop="caseName">
            <el-input v-model="form.caseName" placeholder="请输入案件名称" />
          </el-form-item>
          <el-form-item label="危害程度" prop="dangerDegree">
            <el-select v-model="form.dangerDegree" placeholder="请选择危害程度">
              <el-option
                v-for="dict in dict.type.danger_type"
                :key="dict.value"
                :label="dict.label"
                :value="dict.value"
              ></el-option>
            </el-select>
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
  import { listInvolved, getInvolved, delInvolved, addInvolved, updateInvolved } from "@/api/standard/involved";
  
  export default {
    name: "Involved",
    dicts: ['danger_type', 'involved_type'],
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
        // 主体涉案表格数据
        involvedList: [],
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
          caseDate: [
            { required: true, message: "涉案日期不能为空", trigger: "blur" }
          ],
          caseType: [
            { required: true, message: "涉案类别不能为空", trigger: "change" }
          ],
          caseName: [
            { required: true, message: "案件名称不能为空", trigger: "blur" }
          ],
          dangerDegree: [
            { required: true, message: "危害程度不能为空", trigger: "change" }
          ]
        }
      };
    },
    created() {
      this.getList();
    },
    methods: {
      /** 查询主体涉案列表 */
      getList() {
        this.loading = true;
        this.involvedEntityType = this.$route.params.involvedEntityType;
        this.involvedEntityId = this.$route.params.involvedEntityId;

        this.queryParams.involvedEntityType = this.involvedEntityType;
        this.queryParams.involvedEntityId = this.involvedEntityId;
        listInvolved(this.queryParams).then(response => {
          this.involvedList = response.rows;
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
          involvedId: null,
          involvedEntityType: null,
          involvedEntityId: null,
          caseDate: null,
          caseType: null,
          caseName: null,
          dangerDegree: null
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
        this.ids = selection.map(item => item.involvedId)
        this.single = selection.length!==1
        this.multiple = !selection.length
      },
      /** 新增按钮操作 */
      handleAdd() {
        this.reset();
        this.open = true;
        this.title = "添加主体涉案";
      },
      /** 修改按钮操作 */
      handleUpdate(row) {
        this.reset();
        const involvedId = row.involvedId || this.ids
        const involvedEntityType = this.involvedEntityType
        getInvolved(involvedEntityType, involvedId).then(response => {
          this.form = response.data;
          this.open = true;
          this.title = "修改主体涉案";
        });
      },
      /** 提交按钮 */
      submitForm() {
        this.form.involvedEntityType = this.involvedEntityType;
        this.form.involvedEntityId = this.involvedEntityId;
        
        this.$refs["form"].validate(valid => {
          if (valid) {
            if (this.form.involvedId != null) {
              updateInvolved(this.form).then(response => {
                this.$modal.msgSuccess("修改成功");
                this.open = false;
                this.getList();
              });
            } else {
              addInvolved(this.form).then(response => {
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
        const involvedIds = row.involvedId || this.ids;
        this.$modal.confirm('是否确认删除主体涉案编号为"' + involvedIds + '"的数据项？').then(function() {
          return delInvolved(involvedIds);
        }).then(() => {
          this.getList();
          this.$modal.msgSuccess("删除成功");
        }).catch(() => {});
      },
      /** 导出按钮操作 */
      handleExport() {
        this.download('system/involved/export', {
          ...this.queryParams
        }, `involved_${new Date().getTime()}.xlsx`)
      }
    }
  };
  </script>
  