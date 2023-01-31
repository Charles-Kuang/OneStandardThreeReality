package com.ruoyi.system.mapper;

import java.util.List;
import com.ruoyi.common.core.domain.entity.StdHouseDailyCheck;

/**
 * 房屋日常检查Mapper接口
 * 
 * @author ruoyi
 * @date 2023-01-17
 */
public interface StdHouseDailyCheckMapper 
{
    /**
     * 查询房屋日常检查
     * 
     * @param checkId 房屋日常检查主键
     * @return 房屋日常检查
     */
    public StdHouseDailyCheck selectStdHouseDailyCheckByCheckId(Long checkId);

    /**
     * 查询房屋日常检查列表
     * 
     * @param stdHouseDailyCheck 房屋日常检查
     * @return 房屋日常检查集合
     */
    public List<StdHouseDailyCheck> selectStdHouseDailyCheckList(StdHouseDailyCheck stdHouseDailyCheck);

    /**
     * 新增房屋日常检查
     * 
     * @param stdHouseDailyCheck 房屋日常检查
     * @return 结果
     */
    public int insertStdHouseDailyCheck(StdHouseDailyCheck stdHouseDailyCheck);

    /**
     * 修改房屋日常检查
     * 
     * @param stdHouseDailyCheck 房屋日常检查
     * @return 结果
     */
    public int updateStdHouseDailyCheck(StdHouseDailyCheck stdHouseDailyCheck);

    /**
     * 删除房屋日常检查
     * 
     * @param checkId 房屋日常检查主键
     * @return 结果
     */
    public int deleteStdHouseDailyCheckByCheckId(Long checkId);

    public int deleteStdHouseDailyCheckByHouseId(Long houseId);

    /**
     * 批量删除房屋日常检查
     * 
     * @param checkIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteStdHouseDailyCheckByCheckIds(Long[] checkIds);
}
