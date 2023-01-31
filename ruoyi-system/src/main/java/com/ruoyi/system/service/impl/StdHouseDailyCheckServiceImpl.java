package com.ruoyi.system.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.StdHouseDailyCheckMapper;
import com.ruoyi.common.core.domain.entity.StdHouseDailyCheck;
import com.ruoyi.system.service.IStdHouseDailyCheckService;

/**
 * 房屋日常检查Service业务层处理
 * 
 * @author ruoyi
 * @date 2023-01-17
 */
@Service
public class StdHouseDailyCheckServiceImpl implements IStdHouseDailyCheckService 
{
    @Autowired
    private StdHouseDailyCheckMapper stdHouseDailyCheckMapper;

    /**
     * 查询房屋日常检查
     * 
     * @param checkId 房屋日常检查主键
     * @return 房屋日常检查
     */
    @Override
    public StdHouseDailyCheck selectStdHouseDailyCheckByCheckId(Long checkId)
    {
        return stdHouseDailyCheckMapper.selectStdHouseDailyCheckByCheckId(checkId);
    }

    /**
     * 查询房屋日常检查列表
     * 
     * @param stdHouseDailyCheck 房屋日常检查
     * @return 房屋日常检查
     */
    @Override
    public List<StdHouseDailyCheck> selectStdHouseDailyCheckList(StdHouseDailyCheck stdHouseDailyCheck)
    {
        return stdHouseDailyCheckMapper.selectStdHouseDailyCheckList(stdHouseDailyCheck);
    }

    /**
     * 新增房屋日常检查
     * 
     * @param stdHouseDailyCheck 房屋日常检查
     * @return 结果
     */
    @Override
    public int insertStdHouseDailyCheck(StdHouseDailyCheck stdHouseDailyCheck)
    {
        return stdHouseDailyCheckMapper.insertStdHouseDailyCheck(stdHouseDailyCheck);
    }

    /**
     * 修改房屋日常检查
     * 
     * @param stdHouseDailyCheck 房屋日常检查
     * @return 结果
     */
    @Override
    public int updateStdHouseDailyCheck(StdHouseDailyCheck stdHouseDailyCheck)
    {
        return stdHouseDailyCheckMapper.updateStdHouseDailyCheck(stdHouseDailyCheck);
    }

    /**
     * 批量删除房屋日常检查
     * 
     * @param checkIds 需要删除的房屋日常检查主键
     * @return 结果
     */
    @Override
    public int deleteStdHouseDailyCheckByCheckIds(Long[] checkIds)
    {
        return stdHouseDailyCheckMapper.deleteStdHouseDailyCheckByCheckIds(checkIds);
    }

    /**
     * 删除房屋日常检查信息
     * 
     * @param checkId 房屋日常检查主键
     * @return 结果
     */
    @Override
    public int deleteStdHouseDailyCheckByCheckId(Long checkId)
    {
        return stdHouseDailyCheckMapper.deleteStdHouseDailyCheckByCheckId(checkId);
    }

    @Override
    public int deleteStdHouseDailyCheckByHouseId(Long houseId) {
        return stdHouseDailyCheckMapper.deleteStdHouseDailyCheckByHouseId(houseId);
    }
}
