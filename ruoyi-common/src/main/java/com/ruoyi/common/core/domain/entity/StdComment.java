package com.ruoyi.common.core.domain.entity;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 新闻评论对象 std_news_comment
 *
 * @author ruoyi
 * @date 2022-12-04
 */
public class StdComment extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 新闻评论id */
    private Long commentId;

    /** 新闻评论内容 */
    @Excel(name = "新闻评论内容")
    private String commentContent;

    /** 新闻id */
    private Long newsId;

    /** 评论者id */
    private Long reviewerId;

    private String reviewerName;

    /** 父评论id */
    @Excel(name = "父评论id")
    private Long parentId;

    /** 点赞数 */
    @Excel(name = "点赞数")
    private Long likesNum;

    /** 点踩数 */
    @Excel(name = "点踩数")
    private Long dislikesNum;

    /** 显示顺序 */
    private Long orderNum;

    /** 删除标志（0代表存在 2代表删除） */
    private String delFlag;

    public void setCommentId(Long commentId)
    {
        this.commentId = commentId;
    }

    public Long getCommentId()
    {
        return commentId;
    }
    public void setCommentContent(String commentContent)
    {
        this.commentContent = commentContent;
    }

    public String getCommentContent()
    {
        return commentContent;
    }
    public void setNewsId(Long newsId)
    {
        this.newsId = newsId;
    }

    public Long getNewsId()
    {
        return newsId;
    }
    public void setReviewerId(Long reviewerId)
    {
        this.reviewerId = reviewerId;
    }

    public Long getReviewerId()
    {
        return reviewerId;
    }
    public void setParentId(Long parentId)
    {
        this.parentId = parentId;
    }

    public Long getParentId()
    {
        return parentId;
    }
    public void setLikesNum(Long likesNum)
    {
        this.likesNum = likesNum;
    }

    public Long getLikesNum()
    {
        return likesNum;
    }
    public void setDislikesNum(Long dislikesNum)
    {
        this.dislikesNum = dislikesNum;
    }

    public Long getDislikesNum()
    {
        return dislikesNum;
    }
    public void setOrderNum(Long orderNum)
    {
        this.orderNum = orderNum;
    }

    public Long getOrderNum()
    {
        return orderNum;
    }
    public void setDelFlag(String delFlag)
    {
        this.delFlag = delFlag;
    }

    public String getDelFlag()
    {
        return delFlag;
    }

    public String getReviewerName() {
        return reviewerName;
    }

    public void setReviewerName(String reviewerName) {
        this.reviewerName = reviewerName;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
                .append("commentId", getCommentId())
                .append("commentContent", getCommentContent())
                .append("newsId", getNewsId())
                .append("reviewerId", getReviewerId())
                .append("parentId", getParentId())
                .append("likesNum", getLikesNum())
                .append("dislikesNum", getDislikesNum())
                .append("createTime", getCreateTime())
                .append("orderNum", getOrderNum())
                .append("delFlag", getDelFlag())
                .toString();
    }
}
