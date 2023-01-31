package com.ruoyi.common.core.domain.entity;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 咨询会话对象 std_consultation
 *
 * @author ruoyi
 * @date 2022-12-09
 */
public class StdConsultation extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 咨询会话id */
    private Long consultationId;

    /** 被咨询者id */
    @Excel(name = "被咨询者id")
    private Long consultationHostId;

    @Excel(name = "被咨询者账号")
    private String consultationHostName;

    /** 咨询者id */
    @Excel(name = "咨询者id")
    private Long consultationClientId;

    @Excel(name = "咨询者账号")
    private String consultationClientName;

    /** 会话开始时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date startTime;

    /** 会话上次活跃时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date lastActiveTime;

    /** 会话结束时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date endTime;

    /** 咨询评分 */
    @Excel(name = "咨询评分")
    private Long consultationRating;

    /** 咨询评价与建议 */
    @Excel(name = "咨询评价与建议")
    private String consultationComment;

    /** 对咨询者可见状态 */
    @Excel(name = "对咨询者可见状态")
    private Long delFlag;

    public void setConsultationId(Long consultationId)
    {
        this.consultationId = consultationId;
    }

    public Long getConsultationId()
    {
        return consultationId;
    }
    public void setConsultationHostId(Long consultationHostId)
    {
        this.consultationHostId = consultationHostId;
    }

    public Long getConsultationHostId()
    {
        return consultationHostId;
    }
    public void setConsultationClientId(Long consultationClientId)
    {
        this.consultationClientId = consultationClientId;
    }

    public Long getConsultationClientId()
    {
        return consultationClientId;
    }
    public void setStartTime(Date startTime)
    {
        this.startTime = startTime;
    }

    public Date getStartTime()
    {
        return startTime;
    }
    public void setLastActiveTime(Date lastActiveTime)
    {
        this.lastActiveTime = lastActiveTime;
    }

    public Date getLastActiveTime()
    {
        return lastActiveTime;
    }
    public void setEndTime(Date endTime)
    {
        this.endTime = endTime;
    }

    public Date getEndTime()
    {
        return endTime;
    }
    public void setConsultationRating(Long consultationRating)
    {
        this.consultationRating = consultationRating;
    }

    public Long getConsultationRating()
    {
        return consultationRating;
    }
    public void setConsultationComment(String consultationComment)
    {
        this.consultationComment = consultationComment;
    }

    public String getConsultationComment()
    {
        return consultationComment;
    }
    public void setDelFlag(Long delFlag)
    {
        this.delFlag = delFlag;
    }

    public Long getDelFlag()
    {
        return delFlag;
    }

    public String getConsultationHostName() {
        return consultationHostName;
    }

    public void setConsultationHostName(String consultationHostName) {
        this.consultationHostName = consultationHostName;
    }

    public String getConsultationClientName() {
        return consultationClientName;
    }

    public void setConsultationClientName(String consultationClientName) {
        this.consultationClientName = consultationClientName;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
                .append("consultationId", getConsultationId())
                .append("consultationHostId", getConsultationHostId())
                .append("consultationClientId", getConsultationClientId())
                .append("startTime", getStartTime())
                .append("lastActiveTime", getLastActiveTime())
                .append("endTime", getEndTime())
                .append("consultationRating", getConsultationRating())
                .append("consultationComment", getConsultationComment())
                .append("delFlag", getDelFlag())
                .toString();
    }
}
