package com.ruoyi.system.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.GsRegionModificationHistoryMapper;
import com.ruoyi.common.core.domain.entity.GsRegionModificationHistory;
import com.ruoyi.system.service.IGsRegionModificationHistoryService;

/**
 * 行政区修改轨迹Service业务层处理
 * 
 * @author ruoyi
 * @date 2023-01-20
 */
@Service
public class GsRegionModificationHistoryServiceImpl implements IGsRegionModificationHistoryService 
{
    @Autowired
    private GsRegionModificationHistoryMapper gsRegionModificationHistoryMapper;


    /**
     * 查询行政区修改轨迹列表
     * 
     * @param gsRegionModificationHistory 行政区修改轨迹
     * @return 行政区修改轨迹
     */
    @Override
    public List<GsRegionModificationHistory> selectGsRegionModificationHistoryList(GsRegionModificationHistory gsRegionModificationHistory)
    {
        return gsRegionModificationHistoryMapper.selectGsRegionModificationHistoryList(gsRegionModificationHistory);
    }

    /**
     * 新增行政区修改轨迹
     * 
     * @param gsRegionModificationHistory 行政区修改轨迹
     * @return 结果
     */
    @Override
    public int insertGsRegionModificationHistory(GsRegionModificationHistory gsRegionModificationHistory)
    {
        return gsRegionModificationHistoryMapper.insertGsRegionModificationHistory(gsRegionModificationHistory);
    }

    /**
     * 批量删除行政区修改轨迹
     * 
     * @param modificationIds 需要删除的行政区修改轨迹主键
     * @return 结果
     */
    @Override
    public int deleteGsRegionModificationHistoryByModificationIds(Long[] modificationIds)
    {
        return gsRegionModificationHistoryMapper.deleteGsRegionModificationHistoryByModificationIds(modificationIds);
    }

    /**
     * 删除行政区修改轨迹信息
     * 
     * @param modificationId 行政区修改轨迹主键
     * @return 结果
     */
    @Override
    public int deleteGsRegionModificationHistoryByModificationId(Long modificationId)
    {
        return gsRegionModificationHistoryMapper.deleteGsRegionModificationHistoryByModificationId(modificationId);
    }

    @Override
    public int deleteGsRegionModificationHistoryByRegionId(Long regionId) {
        return gsRegionModificationHistoryMapper.deleteGsRegionModificationHistoryByRegionId(regionId);
    }
}
