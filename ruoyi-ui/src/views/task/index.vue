<template>
    <div class="app-container">
      <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch">
        <el-form-item label="任务名称" prop="taskName">
          <el-input
            v-model="queryParams.taskName"
            placeholder="请输入任务名称"
            clearable
            style="width: 240px"
            @keyup.enter.native="handleQuery"
          />
        </el-form-item>
        <el-form-item label="任务内容" prop="taskContent">
          <el-input
            v-model="queryParams.taskContent"
            placeholder="请输入任务内容"
            clearable
            style="width: 240px"
            @keyup.enter.native="handleQuery"
          />
        </el-form-item>
        <el-form-item label="任务状态" prop="taskStatus">
            <el-select
            v-model="queryParams.taskStatus"
            placeholder="任务状态"
            clearable
            style="width: 240px"
            >
            <el-option
                v-for="dict in dict.type.std_task_status"
                :key="dict.value"
                :label="dict.label"
                :value="dict.value"
            />
            </el-select>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
          <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
        </el-form-item>
        
      </el-form>
  
      <el-table v-loading="loading" :data="taskList">
        <el-table-column label="任务编号" prop="taskId" width="200" />
        <el-table-column label="任务名称" prop="taskName" :show-overflow-tooltip="true" width="200" />
        <el-table-column label="任务内容" prop="taskContent" :show-overflow-tooltip="true" width="350" />
        <el-table-column label="任务状态" prop="taskStatus" width="250" >
            <template slot-scope="task_status">
              <dict-tag :options="dict.type.std_task_status" :value="task_status.row.taskStatus"/> 
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

    </div>
  </template>
  
  <script>
  import { listTask } from "@/api/standard/task";
  import { getUserProfile } from "@/api/system/user";
  
  export default {
    name: "Task",
    dicts: ['std_task_status'],
    data() {
      return {
        // 遮罩层
        loading: true,
        // 选中数组
        ids: [],
        // 显示搜索条件
        showSearch: true,
        // 总条数
        total: 0,
        // 任务表格数据
        taskList: [],
        // 日期范围
        dateRange: [],
        // 查询参数
        queryParams: {
          pageNum: 1,
          pageSize: 10,
          userId: undefined,
          taskName: undefined,
          taskContent: undefined,
          taskStatus: undefined,
        }
      };
    },
    created() {
        this.getList();
    },
    methods: {
      getParams() {
        this.queryParams.taskStatus = this.$route.query.taskStatus;
      },
      
      /** 查询任务列表 */
      getList() {
        this.getParams();
        this.loading = true;
        getUserProfile().then(response => {
            this.queryParams.userId = response.data.userId;
            listTask(this.queryParams).then(response2 => {
                this.taskList = response2.rows;
                this.total = response2.total;
                this.loading = false;
            }
            );
        });
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
      }
    }
  };
  </script>