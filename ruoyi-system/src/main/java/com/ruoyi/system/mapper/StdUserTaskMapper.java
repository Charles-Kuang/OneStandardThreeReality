package com.ruoyi.system.mapper;

import org.apache.ibatis.annotations.Param;


public interface StdUserTaskMapper {
    public int countUserTaskByUserId(Long userId);

    public int countUserTaskByUserIdAndTaskStatus(@Param("userId") Long userId, @Param("taskStatus") int taskStatus);

}
