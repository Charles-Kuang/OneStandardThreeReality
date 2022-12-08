package com.ruoyi.system.service.impl;

import com.ruoyi.common.core.domain.entity.StdTask;
import com.ruoyi.system.mapper.StdUserTaskMapper;
import com.ruoyi.system.service.IStdTaskService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class StdTaskService implements IStdTaskService {
    @Autowired
    private StdUserTaskMapper userTaskMapper;

    @Override
    public List<StdTask> selectTaskList(StdTask task) {
        return null;
    }

    @Override
    public List<StdTask> selectTasksByUserId(Long userId) {
        return null;
    }

    @Override
    public List<StdTask> selectTasksByUserIdAndTaskStatus(Long userId, int taskStatus) {
        return null;
    }

    @Override
    public int countTasksByUserId(Long userId) {
        return userTaskMapper.countUserTaskByUserId(userId);
    }

    @Override
    public int countTasksByUserIdAndTaskStatus(Long userId, int taskStatus) {
        return userTaskMapper.countUserTaskByUserIdAndTaskStatus(userId, taskStatus);
    }
}
