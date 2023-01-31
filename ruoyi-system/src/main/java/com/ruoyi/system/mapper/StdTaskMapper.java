package com.ruoyi.system.mapper;

import com.ruoyi.common.core.domain.entity.StdTask;
import com.ruoyi.system.domain.StdUserTask;

import java.util.List;

public interface StdTaskMapper {
    public List<StdTask> selectTaskList(StdTask task);

    public List<StdTask> selectTaskListByUserIdAndTaskStatus(StdUserTask userTask);
}
