package com.ruoyi.system.service;

import java.util.List;
import com.ruoyi.common.core.domain.entity.GsRegionModificationHistory;

/**
 * 行政区修改轨迹Service接口
 * 
 * @author ruoyi
 * @date 2023-01-20
 */
public interface IGsRegionModificationHistoryService 
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
     * 批量删除行政区修改轨迹
     * 
     * @param modificationIds 需要删除的行政区修改轨迹主键集合
     * @return 结果
     */
    public int deleteGsRegionModificationHistoryByModificationIds(Long[] modificationIds);

    /**
     * 删除行政区修改轨迹信息
     * 
     * @param modificationId 行政区修改轨迹主键
     * @return 结果
     */
    public int deleteGsRegionModificationHistoryByModificationId(Long modificationId);

    public int deleteGsRegionModificationHistoryByRegionId(Long regionId);
}
