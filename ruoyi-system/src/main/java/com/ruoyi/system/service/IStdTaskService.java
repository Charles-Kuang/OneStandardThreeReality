package com.ruoyi.system.service;

import com.ruoyi.common.core.domain.entity.StdTask;

import java.util.List;

public interface IStdUserTaskService {
    public List<StdTask> selectTaskList(StdTask task);

    public List<StdTask> selectTasksByUserId(Long userId);

    public List<StdTask> selectTasksByUserIdAndTaskStatus(Long userId, int );
}
