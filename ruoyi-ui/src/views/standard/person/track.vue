<template>
    <div class="app-container">
  
      <el-row :gutter="10" class="mb8">
        <el-col :span="1.5">
          <el-button
            type="warning"
            plain
            icon="el-icon-download"
            size="mini"
            @click="handleExport"
            v-hasPermi="['system:track:export']"
          >导出</el-button>
        </el-col>
      </el-row>
  
      <el-table v-loading="loading" :data="trackList" >
        <el-table-column label="轨迹ID" align="center" prop="trackId" />
        <el-table-column label="证件类型" align="center" prop="personIdType">
          <template slot-scope="scope">
            <dict-tag :options="dict.type.host_id_type" :value="scope.row.personIdType"/>
          </template>
        </el-table-column>
        <el-table-column label="证件号码" align="center" prop="personId" />
        <el-table-column label="曾住地" align="center" prop="oldAddress" />
        <el-table-column label="现住地" align="center" prop="newAddress" />
        <el-table-column label="流动时间" align="center" prop="moveDate" width="180">
          <template slot-scope="scope">
            <span>{{ parseTime(scope.row.moveDate, '{y}-{m}-{d}') }}</span>
          </template>
        </el-table-column>
        <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
          <template slot-scope="scope">
            <el-button
              size="mini"
              type="text"
              icon="el-icon-delete"
              @click="handleDelete(scope.row)"
              v-hasPermi="['system:track:remove']"
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
  import { listTrack, delTrack } from "@/api/standard/personTrack";
  
  export default {
    name: "Track",
    dicts: ['host_id_type'],
    data() {
      return {
        // 遮罩层
        loading: true,
        // 总条数
        total: 0,
        // 人口居住轨迹表格数据
        trackList: [],
        // 查询参数
        queryParams: {
          pageNum: 1,
          pageSize: 10,
        },
      };
    },
    created() {
      this.getList();
    },
    methods: {
      /** 查询人口居住轨迹列表 */
      getList() {
        this.loading = true;
        this.personIdType = this.$route.params.personIdType;
        this.personId = this.$route.params.personId;

        this.queryParams.personId = this.personId;
        this.queryParams.personIdType = this.personIdType;
        listTrack(this.queryParams).then(response => {
          this.trackList = response.rows;
          this.total = response.total;
          this.loading = false;
        });
      },
      /** 删除按钮操作 */
      handleDelete(row) {
        const trackIds = row.trackId
        this.$modal.confirm('是否确认删除人口居住轨迹编号为"' + trackIds + '"的数据项？').then(function() {
          return delTrack(trackIds);
        }).then(() => {
          this.getList();
          this.$modal.msgSuccess("删除成功");
        }).catch(() => {});
      },
      /** 导出按钮操作 */
      handleExport() {
        this.download('system/track/export', {
          ...this.queryParams
        }, `track_${new Date().getTime()}.xlsx`)
      }
    }
  };
  </script>
  