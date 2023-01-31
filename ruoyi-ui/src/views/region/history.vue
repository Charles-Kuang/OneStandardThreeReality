<template>
    <div class="app-container">
  
      <el-row :gutter="10" class="mb8">
        <el-col :span="1.5">
          <el-button
            type="danger"
            plain
            icon="el-icon-delete"
            size="mini"
            :disabled="multiple"
            @click="handleDelete"
            v-hasPermi="['system:history:remove']"
          >删除</el-button>
        </el-col>
        <el-col :span="1.5">
          <el-button
            type="warning"
            plain
            icon="el-icon-download"
            size="mini"
            @click="handleExport"
            v-hasPermi="['system:history:export']"
          >导出</el-button>
        </el-col>
      </el-row>
  
      <el-table v-loading="loading" :data="historyList" @selection-change="handleSelectionChange">
        <el-table-column type="selection" width="55" align="center" />
        <el-table-column label="修改ID" align="center" prop="modificationId" />
        <el-table-column label="修改行政区" align="center" prop="modificationRegionName" />
        <el-table-column label="修改时间" align="center" prop="modificationDate" width="180">
          <template slot-scope="scope">
            <span>{{ parseTime(scope.row.modificationDate, '{y}-{m}-{d}') }}</span>
          </template>
        </el-table-column>
        <el-table-column label="旧属性值" align="center" prop="oldValue" :show-overflow-tooltip='true' />
        <el-table-column label="新属性值" align="center" prop="newValue" :show-overflow-tooltip='true' />
        <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
          <template slot-scope="scope">
            <el-button
              size="mini"
              type="text"
              icon="el-icon-delete"
              @click="handleDelete(scope.row)"
              v-hasPermi="['system:history:remove']"
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
  
    </div>
  </template>
  
  <script>
  import { listHistory, delHistory } from "@/api/standard/regionModificationHistory";
  
  export default {
    name: "History",
    dicts: ['host_id_type'],
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
        // 总条数
        total: 0,
        // 行政区修改轨迹表格数据
        historyList: [],
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
        }
      };
    },
    created() {
      this.getList();
    },
    methods: {
      /** 查询行政区修改轨迹列表 */
      getList() {
        this.loading = true;
        this.modificationRegionId = this.$route.params.modificationRegionId;
        this.queryParams.modificationRegionId = this.modificationRegionId;
        listHistory(this.queryParams).then(response => {
          this.historyList = response.rows;
          this.total = response.total;
          this.loading = false;
        });
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
        this.ids = selection.map(item => item.modificationId)
        this.single = selection.length!==1
        this.multiple = !selection.length
      },
      /** 删除按钮操作 */
      handleDelete(row) {
        const modificationIds = row.modificationId || this.ids;
        this.$modal.confirm('是否确认删除行政区修改轨迹编号为"' + modificationIds + '"的数据项？').then(function() {
          return delHistory(modificationIds);
        }).then(() => {
          this.getList();
          this.$modal.msgSuccess("删除成功");
        }).catch(() => {});
      },
      /** 导出按钮操作 */
      handleExport() {
        this.download('system/history/export', {
          ...this.queryParams
        }, `history_${new Date().getTime()}.xlsx`)
      }
    }
  };
  </script>
  