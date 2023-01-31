package com.ruoyi.common.core.domain.entity;

import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.annotation.Excel.ColumnType;
import com.ruoyi.common.annotation.Excel.Type;
import com.ruoyi.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.Date;

public class StdTask extends BaseEntity {
    @Excel(name = "任务ID", cellType = ColumnType.NUMERIC, prompt = "任务编号")
    private Long taskId;

    @Excel(name = "任务名称")
    private String taskName;

    @Excel(name = "任务内容")
    private String taskContent;

    @Excel(name = "用户ID", cellType = ColumnType.NUMERIC, prompt = "用户编号")
    private Long userId;

    @Excel(name = "领取时间", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss", type = Type.EXPORT)
    private Date startTime;

    @Excel(name = "结束时间", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss", type = Type.EXPORT)
    private Date endTime;

    private int taskStatus;

    public StdTask() {}

    public StdTask(Long taskId) {
        this.taskId = taskId;
    }

    public Long getTaskId() {
        return taskId;
    }

    public void setTaskId(Long taskId) {
        this.taskId = taskId;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public String getTaskContent() {
        return taskContent;
    }

    public void setTaskContent(String taskContent) {
        this.taskContent = taskContent;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public int getTaskStatus() {
        return taskStatus;
    }

    public void setTaskStatus(int taskStatus) {
        this.taskStatus = taskStatus;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("taskId", taskId)
                .append("taskName", taskName)
                .append("taskContent", taskContent)
                .append("userId", userId)
                .append("start_time", startTime)
                .append("end_time", endTime)
                .append("task_status", taskStatus)
                .toString();
    }
}
