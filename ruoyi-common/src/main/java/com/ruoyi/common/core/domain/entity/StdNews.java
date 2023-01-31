package com.ruoyi.common.core.domain.entity;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 新闻对象 std_news
 *
 * @author ruoyi
 * @date 2022-12-04
 */
public class StdNews extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 新闻id */
    private Long newsId;

    /** 新闻标题 */
    @Excel(name = "新闻标题")
    private String newsName;

    /** 新闻简介 */
    @Excel(name = "新闻简介")
    private String newsIntroduction;

    /** 新闻内容 */
    @Excel(name = "新闻内容")
    private String newsContent;

    /** 已阅人数 */
    @Excel(name = "已阅人数")
    private Long readNum;

    /** 评论人数 */
    @Excel(name = "评论人数")
    private Long commentNum;

    /** 显示顺序 */
    private Long orderNum;

    /** 删除标志（0代表存在 2代表删除） */
    private String delFlag;

    private String commentable;

    private String remark;

    private String thumbnail;

    private String publish;

    public void setNewsId(Long newsId)
    {
        this.newsId = newsId;
    }

    public Long getNewsId()
    {
        return newsId;
    }
    public void setNewsName(String newsName)
    {
        this.newsName = newsName;
    }

    public String getNewsName()
    {
        return newsName;
    }
    public void setNewsIntroduction(String newsIntroduction)
    {
        this.newsIntroduction = newsIntroduction;
    }

    public String getNewsIntroduction()
    {
        return newsIntroduction;
    }
    public void setNewsContent(String newsContent)
    {
        this.newsContent = newsContent;
    }

    public String getNewsContent()
    {
        return newsContent;
    }
    public void setReadNum(Long readNum)
    {
        this.readNum = readNum;
    }

    public Long getReadNum()
    {
        return readNum;
    }
    public void setCommentNum(Long commentNum)
    {
        this.commentNum = commentNum;
    }

    public Long getCommentNum()
    {
        return commentNum;
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

    public String getCommentable() {
        return commentable;
    }

    public void setCommentable(String commentable) {
        this.commentable = commentable;
    }

    @Override
    public String getRemark() {
        return remark;
    }

    @Override
    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public String getPublish() {
        return publish;
    }

    public void setPublish(String publish) {
        this.publish = publish;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
                .append("newsId", getNewsId())
                .append("newsName", getNewsName())
                .append("newsIntroduction", getNewsIntroduction())
                .append("newsContent", getNewsContent())
                .append("createBy", getCreateBy())
                .append("createTime", getCreateTime())
                .append("readNum", getReadNum())
                .append("commentNum", getCommentNum())
                .append("orderNum", getOrderNum())
                .append("delFlag", getDelFlag())
                .toString();
    }
}
