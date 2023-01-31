package com.ruoyi.system.mapper;

import java.util.List;
import com.ruoyi.common.core.domain.entity.GsRegionModificationHistory;

/**
 * 行政区修改轨迹Mapper接口
 * 
 * @author ruoyi
 * @date 2023-01-20
 */
public interface GsRegionModificationHistoryMapper 
{
    /**
     * 查询行政区修改轨迹列表
     * 
     * @param gsRegionModificationHistory 行政区修改轨迹
     * @return 行政区修改轨迹集合
     */
    public List<GsRegionModificationHistory> selectGsRegionModificationHistoryList(GsRegionModificationHistory gsRegionModificationHistory);

    /**
     * 新增行政区修改轨迹
     * 
     * @param gsRegionModificationHistory 行政区修改轨迹
     * @return 结果
     */
    public int insertGsRegionModificationHistory(GsRegionModificationHistory gsRegionModificationHistory);

    /**
     * 删除行政区修改轨迹
     * 
     * @param modificationId 行政区修改轨迹主键
     * @return 结果
     */
    public int deleteGsRegionModificationHistoryByModificationId(Long modificationId);

    /**
     * 批量删除行政区修改轨迹
     * 
     * @param modificationIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteGsRegionModificationHistoryByModificationIds(Long[] modificationIds);

    public int deleteGsRegionModificationHistoryByRegionId(Long regionId);
}
