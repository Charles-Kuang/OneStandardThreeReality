package com.ruoyi.system.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.StdPersonTrackMapper;
import com.ruoyi.common.core.domain.entity.StdPersonTrack;
import com.ruoyi.system.service.IStdPersonTrackService;

/**
 * 人口居住轨迹Service业务层处理
 * 
 * @author ruoyi
 * @date 2023-01-18
 */
@Service
public class StdPersonTrackServiceImpl implements IStdPersonTrackService 
{
    @Autowired
    private StdPersonTrackMapper stdPersonTrackMapper;

    /**
     * 查询人口居住轨迹
     * 
     * @param trackId 人口居住轨迹主键
     * @return 人口居住轨迹
     */
    @Override
    public StdPersonTrack selectStdPersonTrackByTrackId(Long trackId)
    {
        return stdPersonTrackMapper.selectStdPersonTrackByTrackId(trackId);
    }

    /**
     * 查询人口居住轨迹列表
     * 
     * @param stdPersonTrack 人口居住轨迹
     * @return 人口居住轨迹
     */
    @Override
    public List<StdPersonTrack> selectStdPersonTrackList(StdPersonTrack stdPersonTrack)
    {
        return stdPersonTrackMapper.selectStdPersonTrackList(stdPersonTrack);
    }

    /**
     * 新增人口居住轨迹
     * 
     * @param stdPersonTrack 人口居住轨迹
     * @return 结果
     */
    @Override
    public int insertStdPersonTrack(StdPersonTrack stdPersonTrack)
    {
        return stdPersonTrackMapper.insertStdPersonTrack(stdPersonTrack);
    }

    /**
     * 修改人口居住轨迹
     * 
     * @param stdPersonTrack 人口居住轨迹
     * @return 结果
     */
    @Override
    public int updateStdPersonTrack(StdPersonTrack stdPersonTrack)
    {
        return stdPersonTrackMapper.updateStdPersonTrack(stdPersonTrack);
    }

    /**
     * 删除人口居住轨迹信息
     * 
     * @param trackId 人口居住轨迹主键
     * @return 结果
     */
    @Override
    public int deleteStdPersonTrackByTrackId(Long trackId)
    {
        return stdPersonTrackMapper.deleteStdPersonTrackByTrackId(trackId);
    }
}
