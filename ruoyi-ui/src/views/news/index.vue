<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="新闻标题" prop="newsName">
        <el-input
          v-model="queryParams.newsName"
          placeholder="请输入新闻标题"
          clearable
          @keyup.enter.native="handleQuery"
        />
      </el-form-item>
      <el-form-item label="新闻简介" prop="newsIntroduction">
        <el-input
          v-model="queryParams.newsIntroduction"
          placeholder="请输入新闻简介"
          clearable
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
          v-hasPermi="['system:news:add']"
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
          v-hasPermi="['system:news:edit']"
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
          v-hasPermi="['system:news:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="el-icon-download"
          size="mini"
          @click="handleExport"
          v-hasPermi="['system:news:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="newsList" @row-click="newsDetail" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="新闻id" align="center" prop="newsId" />
      <el-table-column label="新闻标题" align="center" prop="newsName" />
      <el-table-column label="新闻简介" align="center" prop="newsIntroduction" />
      <!--<el-table-column label="新闻内容" align="center" prop="newsContent" />-->
      <el-table-column label="新闻作者" align="center" prop="createBy" />
      <el-table-column label="创建时间" align="center" prop="createTime" width="160">
        <template slot-scope="scope">
          <span>{{ parseTime(scope.row.createTime) }}</span>
        </template>
      </el-table-column>
      <el-table-column label="已阅人数" align="center" prop="readNum" />
      <el-table-column label="评论人数" align="center" prop="commentNum" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template slot-scope="scope">
          <el-button
            size="mini"
            type="text"
            icon="el-icon-edit"
            @click.native.stop="handleUpdate(scope.row)"
            v-hasPermi="['system:news:edit']"
          >修改</el-button>
          <el-button
            size="mini"
            type="text"
            icon="el-icon-delete"
            @click.native.stop="handleDelete(scope.row)"
            v-hasPermi="['system:news:remove']"
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

    <!-- 添加或修改新闻对话框 -->
    <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
      <el-form ref="form" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="新闻标题" prop="newsName">
          <el-input v-model="form.newsName" placeholder="请输入新闻标题" />
        </el-form-item>
        <el-form-item label="新闻简介" prop="newsIntroduction">
          <el-input v-model="form.newsIntroduction" placeholder="请输入新闻简介" />
        </el-form-item>
        <el-form-item label="新闻内容">
          <editor v-model="form.newsContent" :min-height="192"/>
        </el-form-item>
        <el-form-item label="新闻作者" prop="createBy">
          <el-input v-model="form.createBy" placeholder="请输入新闻作者" />
        </el-form-item>
        <el-form-item label="已阅人数" prop="readNum" v-if="this.edit">
          <el-input v-model="form.readNum" placeholder="请输入已阅人数" />
        </el-form-item>
        <el-form-item label="评论人数" prop="commentNum" v-if="this.edit">
          <el-input v-model="form.commentNum" placeholder="请输入评论人数" />
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
import { listNews, getNews, delNews, addNews, updateNews } from "@/api/standard/news";

export default {
  name: "News",
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
      // 新闻表格数据
      newsList: [],
      // 弹出层标题
      title: "",
      // 是否显示弹出层
      open: false,
      //区分添加/修改
      edit: undefined,
      // 查询参数
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        newsName: null,
        newsIntroduction: null,
        newsContent: null,
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
    /** 查询新闻列表 */
    getList() {
      this.loading = true;
      listNews(this.queryParams).then(response => {
        this.newsList = response.rows;
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
        newsId: null,
        newsName: null,
        newsIntroduction: null,
        newsContent: null,
        createBy: null,
        createTime: null,
        readNum: null,
        commentNum: null,
        orderNum: null,
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
      this.resetForm("queryForm");
      this.handleQuery();
    },
    // 多选框选中数据
    handleSelectionChange(selection) {
      this.ids = selection.map(item => item.newsId)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加新闻";
      this.edit = false;
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const newsId = row.newsId || this.ids
      this.edit = true;
      getNews(newsId).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改新闻";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          if (this.form.newsId != null) {
            updateNews(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addNews(this.form).then(response => {
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
      const newsIds = row.newsId || this.ids;
      this.$modal.confirm('是否确认删除新闻编号为"' + newsIds + '"的数据项？').then(function() {
        return delNews(newsIds);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('system/news/export', {
        ...this.queryParams
      }, `news_${new Date().getTime()}.xlsx`)
    },
    newsDetail(row) {
      const newsId = row.newsId;
      const newsName = row.newsName;
      const params = { pageNum: this.queryParams.pageNum };
      this.$tab.openPage("修改新闻[" + newsName + "]内容及评论", '/news/get-detail/index/' + newsId, params);
    }
  }
};
</script>