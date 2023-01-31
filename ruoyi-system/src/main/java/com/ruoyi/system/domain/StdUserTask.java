package com.ruoyi.system.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;

public class StdUserTask {
    private Long userId;

    private Long taskId;

    private int taskStatus;

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("userId", userId)
                .append("taskId", taskId)
                .append("taskStatus", taskStatus)
                .toString();
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getTaskId() {
        return taskId;
    }

    public void setTaskId(Long taskId) {
        this.taskId = taskId;
    }

    public int getTaskStatus() {
        return taskStatus;
    }

    public void setTaskStatus(int taskStatus) {
        this.taskStatus = taskStatus;
    }
}
