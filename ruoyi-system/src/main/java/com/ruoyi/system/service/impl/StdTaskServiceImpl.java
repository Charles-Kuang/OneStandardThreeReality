package com.ruoyi.system.service.impl;

import com.ruoyi.common.core.domain.entity.StdTask;
import com.ruoyi.system.domain.StdUserTask;
import com.ruoyi.system.mapper.StdTaskMapper;
import com.ruoyi.system.service.IStdTaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StdTaskServiceImpl implements IStdTaskService {
    @Autowired
    private StdTaskMapper taskMapper;

    @Override
    public List<StdTask> selectTaskList(StdTask task) {
        return taskMapper.selectTaskList(task);
    }

    @Override
    public List<StdTask> selectTaskListByUserIdAndTaskStatus(StdUserTask userTask) {
        return taskMapper.selectTaskListByUserIdAndTaskStatus(userTask);
    }

}
