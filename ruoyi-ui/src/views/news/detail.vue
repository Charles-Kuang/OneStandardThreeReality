<template>
    <div class="app-container">
    <el-row :gutter="20">
        <el-card class="box-card">
        <div slot="header" class="clearfix">
            <span>{{news.newsName}}</span>
        </div>
        <div>
            <el-col :span="24" :xs="24">
                <ul class="list-group list-group-striped">
                <li class="list-group-item">
                    发布时间:
                    <div class="pull-right" >{{ news.createTime }}</div>
                </li>
                <li class="list-group-item">
                    阅读量:
                    <div class="pull-right">{{ news.readNum }}</div>
                </li>
                </ul>
            </el-col>
        </div>
        </el-card>
    </el-row>
   
    <el-row :gutter="20" :xs="24">
        <el-card class="box-card">
            <span v-html="news.newsContent">
            
            </span>
            <div><span>作者： {{news.createBy}}</span></div>
        </el-card>
        <el-button
            size="mini"
            type="primary"
            icon="el-icon-edit"
            @click="handleNewsUpdate"
            v-hasPermi="['system:news:edit']"
            >修改新闻内容</el-button>
    </el-row>

    <el-row :gutter="20" :xs="24">
        <template>
            <div class="app-container">
                <el-form :model="queryParams" ref="queryForm" size="small" :inline="true" v-show="showSearch" label-width="68px">
                <el-form-item label="评论内容" prop="commentContent">
                    <el-input
                    v-model="queryParams.commentContent"
                    placeholder="请输入评论内容"
                    clearable
                    @keyup.enter.native="handleQuery"
                    />
                </el-form-item>
                <el-form-item label="评论者" prop="reviewerName">
                    <el-input
                    v-model="queryParams.reviewerName"
                    placeholder="请输入评论者名称"
                    clearable
                    @keyup.enter.native="handleQuery"
                    />
                </el-form-item>
                <el-form-item label="父评论id" prop="parentId">
                    <el-input
                    v-model="queryParams.parentId"
                    placeholder="请输入父评论id"
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
                    v-hasPermi="['system:comment:add']"
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
                    v-hasPermi="['system:comment:edit']"
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
                    v-hasPermi="['system:comment:remove']"
                    >删除</el-button>
                </el-col>
                <el-col :span="1.5">
                    <el-button
                    type="warning"
                    plain
                    icon="el-icon-download"
                    size="mini"
                    @click="handleExport"
                    v-hasPermi="['system:comment:export']"
                    >导出</el-button>
                </el-col>
                <right-toolbar :showSearch.sync="showSearch" @queryTable="getList"></right-toolbar>
                </el-row>

                <el-table v-loading="loading" :data="commentList" @selection-change="handleSelectionChange">
                <el-table-column type="selection" width="55" align="center" />
                <el-table-column label="新闻评论id" align="center" prop="commentId" />
                <el-table-column label="新闻评论内容" align="center" prop="commentContent" >
                  <template slot-scope="scope">
                    <p v-html='scope.row.commentContent'></p>
                  </template>
                </el-table-column>
                <el-table-column label="评论者" align="center" prop="reviewerName" />
                <el-table-column label="父评论id" align="center" prop="parentId" />
                <el-table-column label="点赞数" align="center" prop="likesNum" />
                <el-table-column label="点踩数" align="center" prop="dislikesNum" />
                <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
                    <template slot-scope="scope">
                    <el-button
                        size="mini"
                        type="text"
                        icon="el-icon-edit"
                        @click="handleUpdate(scope.row)"
                        v-hasPermi="['system:comment:edit']"
                    >修改</el-button>
                    <el-button
                        size="mini"
                        type="text"
                        icon="el-icon-delete"
                        @click="handleDelete(scope.row)"
                        v-hasPermi="['system:comment:remove']"
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
                <el-dialog :title="news_title" :visible.sync="newsOpen" width="500px" append-to-body>
                  <el-form ref="newsForm" :model="newsForm" :rules="newsRules" label-width="80px">
                    <el-form-item label="新闻标题" prop="newsName">
                      <el-input v-model="newsForm.newsName" placeholder="请输入新闻标题" />
                    </el-form-item>
                    <el-form-item label="新闻简介" prop="newsIntroduction">
                      <el-input v-model="newsForm.newsIntroduction" placeholder="请输入新闻简介" />
                    </el-form-item>
                    <el-form-item label="新闻内容">
                      <editor v-model="newsForm.newsContent" :min-height="192"/>
                    </el-form-item>
                    <el-form-item label="新闻作者" prop="createBy">
                      <el-input v-model="newsForm.createBy" placeholder="请输入新闻作者笔名" />
                    </el-form-item>
                    <el-form-item label="已阅人数" prop="readNum">
                      <el-input v-model="newsForm.readNum" placeholder="请输入已阅人数" />
                    </el-form-item>
                    <el-form-item label="评论人数" prop="commentNum">
                      <el-input v-model="newsForm.commentNum" placeholder="请输入评论人数" />
                    </el-form-item>
                  </el-form>
                  <div slot="footer" class="dialog-footer">
                    <el-button type="primary" @click="submitNewsForm">确 定</el-button>
                    <el-button @click="cancel">取 消</el-button>
                  </div>
                </el-dialog>

                <!-- 添加或修改新闻评论对话框 -->
                <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
                <el-form ref="form" :model="form" :rules="rules" label-width="80px">
                    <el-form-item label="新闻评论内容">
                    <editor v-model="form.commentContent" :min-height="192"/>
                    </el-form-item>
                    <el-form-item label="父评论id" prop="parentId">
                    <el-input v-model="form.parentId" placeholder="请输入父评论id"/>
                    </el-form-item>
                    <el-form-item label="点赞数" prop="likesNum">
                    <el-input v-model="form.likesNum" placeholder="请输入点赞数" />
                    </el-form-item>
                    <el-form-item label="点踩数" prop="dislikesNum">
                    <el-input v-model="form.dislikesNum" placeholder="请输入点踩数" />
                    </el-form-item>
                </el-form>
                <div slot="footer" class="dialog-footer">
                    <el-button type="primary" @click="submitForm">确 定</el-button>
                    <el-button @click="cancel">取 消</el-button>
                </div>
                </el-dialog>
            </div>
            </template>
    </el-row>

    

    </div>
  </template>

  
  <script>
  import { getNews, updateNews } from "@/api/standard/news";
  import { listComment, getComment, delComment, addComment, updateComment } from "@/api/standard/comment";
  
  export default {
    name: "NewsDetail",
    data() {
      return {
        newsId: undefined,
        news: {},
        commentList: [],
        newComment: undefined,
        ids: [],
        // 非单个禁用
        single: true,
        // 非多个禁用
        multiple: true,
        // 显示搜索条件
        showSearch: true,
        // 总条数
        total: 0,
        // 新闻评论表格数据
        commentList: [],
        // 弹出层标题
        title: "",
        // 是否显示弹出层
        open: false,
        newsOpen: false,

        queryParams: {
            pageNum: 1,
            pageSize: 10,
            commentContent: null,
            reviewerId: null,
            reviewerName: null,
            parentId: null,
            orderNum: null,
            newsId: null,
        },
        // 表单参数
        form: {},
        newsForm: {},
        // 表单校验
        rules: {
        },
        newsRules: {
        }
      };
    },
    created() {
        this.getList();
    },
    methods: {
    getList() {
        this.loading = true;
        this.newsId = this.$route.params && this.$route.params.newsId;
        this.queryParams.newsId = this.newsId;
        
        getNews(this.newsId).then(response => {
          this.news = response.data;
          listComment(this.queryParams).then(response => {
                this.commentList = response.rows;
                this.total = response.total;
                this.loading = false;
            });
        });
      },

      // 取消按钮
    cancel() {
      this.open = false;
      this.newsOpen = false;
      this.reset();
    },
    // 表单重置
    reset() {
      this.form = {
        commentId: null,
        commentContent: null,
        newsId: null,
        reviewerId: null,
        reviewerName: null,
        parentId: null,
        likesNum: null,
        dislikesNum: null,
        createTime: null,
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
      this.ids = selection.map(item => item.commentId)
      this.single = selection.length!==1
      this.multiple = !selection.length
    },
    /** 新增按钮操作 */
    handleAdd() {
      this.reset();
      this.open = true;
      this.title = "添加新闻评论";
    },
    /** 修改按钮操作 */
    handleUpdate(row) {
      this.reset();
      const commentId = row.commentId || this.ids
      getComment(commentId).then(response => {
        this.form = response.data;
        this.open = true;
        this.title = "修改新闻评论";
      });
    },
    handleNewsUpdate() {
      this.reset();
      getNews(this.newsId).then(response => {
        this.newsForm = response.data;
        this.newsOpen = true;
        this.title = "修改新闻";
      });
    },
    /** 提交按钮 */
    submitForm() {
      this.$refs["form"].validate(valid => {
        if (valid) {
          this.form.newsId = this.newsId;
          //添加评论者为管理员
          this.form.reviewerId = 1;
          this.form.reviewerName = 'admin';
          if (this.form.commentId != null) {
            updateComment(this.form).then(response => {
              this.$modal.msgSuccess("修改成功");
              this.open = false;
              this.getList();
            });
          } else {
            addComment(this.form).then(response => {
              this.$modal.msgSuccess("新增成功");
              this.open = false;
              this.getList();
            });
          }
        }
      });
    },
    /** 提交新闻按钮 */
    submitNewsForm() {
      this.$refs["newsForm"].validate(valid => {
        if (valid) {
          updateNews(this.newsForm).then(response => {
            this.news = this.newsForm;
            this.$modal.msgSuccess("修改新闻成功");
            this.newsOpen = false;
          });
        }
      });
    },
    /** 删除按钮操作 */
    handleDelete(row) {
      const commentIds = row.commentId || this.ids;
      this.$modal.confirm('是否确认删除新闻评论编号为"' + commentIds + '"的数据项？').then(function() {
        return delComment(commentIds);
      }).then(() => {
        this.getList();
        this.$modal.msgSuccess("删除成功");
      }).catch(() => {});
    },
    /** 导出按钮操作 */
    handleExport() {
      this.download('system/comment/export', {
        ...this.queryParams
      }, `comment_${new Date().getTime()}.xlsx`)
    }
    }
  };
  </script>