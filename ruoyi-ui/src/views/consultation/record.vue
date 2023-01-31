<template>
    <div class="app-container">
      <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="100px">
        <el-form-item label="消息发送者" prop="messageSenderName">
          <el-input
            v-model="queryParams.messageSenderName"
            placeholder="请输入消息发送者"
            clearable
            @keyup.enter.native="handleQuery"
          />
        </el-form-item>
        <el-form-item label="消息时间戳">
          <el-date-picker
            v-model="daterangeTimeStamp"
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
        </el-col>
        <el-col :span="1.5">
          <el-button
            type="success"
            plain
            icon="el-icon-edit"
            size="mini"
            :disabled="single"
            @click="handleUpdate"
            v-hasPermi="['system:record:edit']"
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
            v-hasPermi="['system:record:remove']"
          >删除</el-button>
        </el-col>
        <el-col :span="1.5">
          <el-button
            type="warning"
            plain
            icon="el-icon-download"
            size="mini"
            @click="handleExport"
            v-hasPermi="['system:record:export']"
          >导出</el-button>
        </el-col>
        <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
      </el-row>
  
      <el-table v-loading="loading" :data="recordList" @selection-change="handleSelectionChange">
        <el-table-column type="selection" width="55" align="center" />
        <el-table-column label="消息发送者" align="center" prop="messageSenderName" />
        <el-table-column label="消息发送内容" align="center" prop="messageContent" >
            <template slot-scope="scope">
                <p v-html='scope.row.messageContent'></p>
            </template>
        </el-table-column>
        <el-table-column label="消息时间戳" align="center" prop="timeStamp" width="180">
          <template slot-scope="scope">
            <span>{{ parseTime(scope.row.timeStamp) }}</span>
          </template>
        </el-table-column>
        <el-table-column label="对咨询者可见状态" align="center" prop="delFlag">
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
              @click="handleUpdate(scope.row)"
              v-hasPermi="['system:record:edit']"
            >修改</el-button>
            <el-button
              size="mini"
              type="text"
              icon="el-icon-delete"
              @click="handleDelete(scope.row)"
              v-hasPermi="['system:record:remove']"
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
  
      <!-- 修改消息记录对话框 -->
      <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
        <el-form ref="form" :model="form" :rules="rules" label-width="80px">
          <el-form-item label="消息发送内容">
            <editor v-model="form.messageContent" :min-height="192"/>
          </el-form-item>
          <el-form-item label="对咨询者可见状态">
            <el-select v-model="form.delFlag" placeholder="请选择状态">
                <el-option
                  v-for="dict in dict.type.sys_show_hide"
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
  import { listRecord, getRecord, delRecord, addRecord, updateRecord } from "@/api/standard/record";
  
  export default {
    name: "Record",
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
        // 消息记录表格数据
        recordList: [],
        // 弹出层标题
        title: "",
        // 是否显示弹出层
        open: false,
        // 消息发送内容时间范围
        daterangeTimeStamp: [],
        // 查询参数
        queryParams: {
          pageNum: 1,
          pageSize: 10,
          consultationId: null,
          messageSenderId: null,
          messageSenderName: null,
          messageContent: null,
          timeStamp: null
        },
        // 表单参数
        form: {},
        // 表单校验
        rules: {
        }
      };
    },
    created() {
      this.getList();
    },
    methods: {
      /** 查询消息记录列表 */
      getList() {
        this.loading = true;
        this.consultationId = this.$route.params && this.$route.params.consultationId;
        this.queryParams.params = {};
        this.queryParams.consultationId = this.consultationId;

        if (null != this.daterangeTimeStamp && '' != this.daterangeTimeStamp) {
          this.queryParams.params["beginTimeStamp"] = this.daterangeTimeStamp[0];
          this.queryParams.params["endTimeStamp"] = this.daterangeTimeStamp[1];
        }
        listRecord(this.queryParams).then(response => {
          this.recordList = response.rows;
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
          messageId: null,
          consultationId: null,
          messageSenderId: null,
          messageSenderName: null,
          messageContent: null,
          timeStamp: null,
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
        this.daterangeTimeStamp = [];
        this.resetForm("queryForm");
        this.handleQuery();
      },
      // 多选框选中数据
      handleSelectionChange(selection) {
        this.ids = selection.map(item => item.messageId)
        this.single = selection.length!==1
        this.multiple = !selection.length
      },
      /** 修改按钮操作 */
      handleUpdate(row) {
        this.reset();
        const messageId = row.messageId || this.ids
        getRecord(messageId).then(response => {
          this.form = response.data;
          this.open = true;
          this.title = "修改消息记录";
        });
      },
      /** 提交按钮 */
      submitForm() {
        this.$refs["form"].validate(valid => {
          if (valid) {
            if (this.form.messageId != null) {
              updateRecord(this.form).then(response => {
                this.$modal.msgSuccess("修改成功");
                this.open = false;
                this.getList();
              });
            }
          }
        });
      },
      /** 删除按钮操作 */
      handleDelete(row) {
        const messageIds = row.messageId || this.ids;
        this.$modal.confirm('是否确认删除消息记录编号为"' + messageIds + '"的数据项？').then(function() {
          return delRecord(messageIds);
        }).then(() => {
          this.getList();
          this.$modal.msgSuccess("删除成功");
        }).catch(() => {});
      },
      /** 导出按钮操作 */
      handleExport() {
        this.download('system/record/export', {
          ...this.queryParams
        }, `record_${new Date().getTime()}.xlsx`)
      }
    }
  };
  </script>
  