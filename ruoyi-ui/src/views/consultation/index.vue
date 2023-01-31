<template>
    <div class="app-container">
      <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
        <el-form-item label="被咨询者" prop="consultationHostName">
          <el-input
            v-model="queryParams.consultationHostName"
            placeholder="请输入被咨询者账号"
            clearable
            @keyup.enter.native="handleQuery"
          />
        </el-form-item>
        <el-form-item label="咨询者" prop="consultationClientName">
          <el-input
            v-model="queryParams.consultationClientName"
            placeholder="请输入咨询者账号"
            clearable
            @keyup.enter.native="handleQuery"
          />
        </el-form-item>
        <el-form-item label="开始时间" prop="startTime">
          <el-date-picker clearable
            v-model="dateRange"
            style="width: 240px"
            type="daterange"
            range-separator="-"
            value-format="yyyy-MM-dd"
            start-placeholder="开始时间"
            end-placeholder="结束时间"
          ></el-date-picker>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
          <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
        </el-form-item>
      </el-form>
  
      <el-row :gutter="10" class="mb8">
        <el-col :span="1.5">
        </el-col>
        <el-col :span="1.5">
          <el-button
            type="success"
            plain
            icon="el-icon-edit"
            size="mini"
            :disabled="single"
            @click="handleUpdate"
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
          >删除</el-button>
        </el-col>
        <el-col :span="1.5">
          <el-button
            type="warning"
            plain
            icon="el-icon-download"
            size="mini"
            @click="handleExport"
          >导出</el-button>
        </el-col>
        <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
      </el-row>
  
      <el-table v-loading="loading" :data="consultationList" @row-click="getRecord" @selection-change="handleSelectionChange">
        <el-table-column type="selection" width="55" align="center" />
        <el-table-column label="咨询会话id" align="center" prop="consultationId" />
        <el-table-column label="被咨询者账号" align="center" prop="consultationHostName" />
        <el-table-column label="咨询者账号" align="center" prop="consultationClientName" />
        <el-table-column label="会话开始时间" align="center" prop="startTime" width="180">
          <template slot-scope="scope">
            <span>{{ parseTime(scope.row.startTime) }}</span>
          </template>
        </el-table-column>
        <el-table-column label="会话上次活跃时间" align="center" prop="lastActiveTime" width="180">
          <template slot-scope="scope">
            <span>{{ parseTime(scope.row.lastActiveTime) }}</span>
          </template>
        </el-table-column>
        <el-table-column label="会话结束时间" align="center" prop="endTime" width="180">
          <template slot-scope="scope">
            <span>{{ parseTime(scope.row.endTime) }}</span>
          </template>
        </el-table-column>
        <el-table-column label="咨询评分" align="center" prop="consultationRating" />
        <el-table-column label="咨询评价与建议" align="center" prop="consultationComment" />
        <el-table-column label="对咨询者可见状态" align="center" prop="delFlag" >
            <template slot-scope="scope">
                <dict-tag :options="dict.type.sys_show_hide" :value="scope.row.delFlag"/>
            </template>
        </el-table-column>
        <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
          <template slot-scope="scope">
            <el-button
              size="mini"
              type="text"
              icon="el-icon-edit"
              @click.native.stop="handleUpdate(scope.row)"
            >修改</el-button>
            <el-button
              size="mini"
              type="text"
              icon="el-icon-delete"
              @click.native.stop="handleEnd(scope.row)"
            >结束</el-button>
            <el-button
              size="mini"
              type="text"
              icon="el-icon-delete"
              @click.native.stop="handleDelete(scope.row)"
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
  
      <!-- 添加或修改咨询会话对话框 -->
      <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
        <el-form ref="form" :model="form" :rules="rules" label-width="80px">
          <el-form-item label="被咨询者账号" prop="consultationHostName">
            <el-input v-model="form.consultationHostName" placeholder="请输入被咨询者账号" />
          </el-form-item>
          <el-form-item label="咨询者账号" prop="consultationClientName">
            <el-input v-model="form.consultationClientName" placeholder="请输入咨询者账号" />
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
  import { listConsultation, getConsultation, delConsultation, addConsultation, updateConsultation, endConsultation } from "@/api/standard/consultation";
  
  export default {
    name: "Consultation",
    dicts: ['sys_show_hide'],
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
        // 咨询会话表格数据
        consultationList: [],
        // 弹出层标题
        title: "",
        // 是否显示弹出层
        open: false,
        //日期范围
        dateRange: [],
        // 查询参数
        queryParams: {
          pageNum: 1,
          pageSize: 10,
          consultationHostId: null,
          consultationHostName: null,
          consultationClientId: null,
          consultationClientName: null,
          startTime: null,
          lastActiveTime: null,
          endTime: null,
          consultationRating: null,
          consultationComment: null,
          delFlag: null,
        },
        // 表单参数
        form: {},
        // 表单校验
        rules: {
            consultationHostName: [
            { required: true, message: "被咨询者账号不能为空", trigger: "blur" }
          ],
          consultationClientName: [
            { required: true, message: "咨询者账号不能为空", trigger: "blur" }
          ],
        }
      };
    },
    created() {
      this.getList();
    },
    methods: {
      /** 查询咨询会话列表 */
      getList() {
        this.loading = true;
        listConsultation(this.addDateRange(this.queryParams, this.dateRange)).then(response => {
          this.consultationList = response.rows;
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
          consultationId: null,
          consultationHostId: null,
          consultationHostName: null,
          consultationClientId: null,
          consultationClientName: null,
          startTime: null,
          lastActiveTime: null,
          endTime: null,
          consultationRating: null,
          consultationComment: null,
          delFlag: null
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
        this.dateRange = [];
        this.resetForm("queryForm");
        this.handleQuery();
      },
      // 多选框选中数据
      handleSelectionChange(selection) {
        this.ids = selection.map(item => item.consultationId)
        this.single = selection.length!==1
        this.multiple = !selection.length
      },
      /** 修改按钮操作 */
      handleUpdate(row) {
        this.reset();
        const consultationId = row.consultationId || this.ids
        getConsultation(consultationId).then(response => {
          this.form = response.data;
          this.open = true;
          this.title = "修改咨询会话";
        });
      },
      /** 提交按钮 */
      submitForm() {
        this.$refs["form"].validate(valid => {
          if (valid) {
            if (this.form.consultationId != null) {
              updateConsultation(this.form).then(response => {
                this.$modal.msgSuccess("修改成功");
                this.open = false;
                this.getList();
              });
            } 
          }
        });
      },
      handleEnd(row) {
        const consultationId = row.consultationId || this.ids;
        this.$modal.confirm('是否确认结束咨询会话编号为"' + consultationId + '"的会话？').then(function() {
            return endConsultation(consultationId);
        }).then(() => {
          this.getList();
          this.$modal.msgSuccess("结束成功");
        }).catch(() => {});
      },
      /** 删除按钮操作 */
      handleDelete(row) {
        const consultationIds = row.consultationId || this.ids;
        this.$modal.confirm('是否确认删除咨询会话编号为"' + consultationIds + '"的数据项？').then(function() {
          return delConsultation(consultationIds);
        }).then(() => {
          this.getList();
          this.$modal.msgSuccess("删除成功");
        }).catch(() => {});
      },
      /** 导出按钮操作 */
      handleExport() {
        this.download('system/consultation/export', {
          ...this.queryParams
        }, `consultation_${new Date().getTime()}.xlsx`)
      },
      getRecord(row) {
        const consultationId = row.consultationId;
        const params = { pageNum: this.queryParams.pageNum };
        this.$tab.openPage("修改ID为[" + consultationId + "]的会话", '/consultation/get-detail/index/' + consultationId, params);
      }
    }
  };
  </script>
  