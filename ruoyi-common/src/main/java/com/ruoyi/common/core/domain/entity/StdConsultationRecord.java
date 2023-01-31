package com.ruoyi.common.core.domain.entity;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 消息记录对象 std_consultation_record
 *
 * @author ruoyi
 * @date 2022-12-09
 */
public class StdConsultationRecord extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 消息id */
    @Excel(name = "消息id")
    private Long messageId;

    /** 咨询会话id */
    @Excel(name = "咨询会话id")
    private Long consultationId;

    /** 消息发送者id */
    @Excel(name = "消息发送者id")
    private String messageSenderId;

    private String messageSenderName;

    /** 消息发送内容 */
    @Excel(name = "消息发送内容")
    private String messageContent;

    /** 消息时间戳 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date timeStamp;

    /** 对咨询者可见状态 */
    @Excel(name = "对咨询者可见状态")
    private Long delFlag;

    public void setMessageId(Long messageId)
    {
        this.messageId = messageId;
    }

    public Long getMessageId()
    {
        return messageId;
    }
    public void setConsultationId(Long consultationId)
    {
        this.consultationId = consultationId;
    }

    public Long getConsultationId()
    {
        return consultationId;
    }
    public void setMessageSenderId(String messageSenderId)
    {
        this.messageSenderId = messageSenderId;
    }

    public String getMessageSenderId()
    {
        return messageSenderId;
    }
    public void setMessageContent(String messageContent)
    {
        this.messageContent = messageContent;
    }

    public String getMessageContent()
    {
        return messageContent;
    }
    public void setTimeStamp(Date timeStamp)
    {
        this.timeStamp = timeStamp;
    }

    public Date getTimeStamp()
    {
        return timeStamp;
    }
    public void setDelFlag(Long delFlag)
    {
        this.delFlag = delFlag;
    }

    public Long getDelFlag()
    {
        return delFlag;
    }

    public String getMessageSenderName() {
        return messageSenderName;
    }

    public void setMessageSenderName(String messageSenderName) {
        this.messageSenderName = messageSenderName;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
                .append("messageId", getMessageId())
                .append("consultationId", getConsultationId())
                .append("messageSenderId", getMessageSenderId())
                .append("messageContent", getMessageContent())
                .append("timeStamp", getTimeStamp())
                .append("delFlag", getDelFlag())
                .toString();
    }
}
