package com.ruoyi.system.service;

import java.util.List;
import com.ruoyi.common.core.domain.entity.StdPersonTrack;

/**
 * 人口居住轨迹Service接口
 * 
 * @author ruoyi
 * @date 2023-01-18
 */
public interface IStdPersonTrackService 
{
    /**
     * 查询人口居住轨迹
     * 
     * @param trackId 人口居住轨迹主键
     * @return 人口居住轨迹
     */
    public StdPersonTrack selectStdPersonTrackByTrackId(Long trackId);

    /**
     * 查询人口居住轨迹列表
     * 
     * @param stdPersonTrack 人口居住轨迹
     * @return 人口居住轨迹集合
     */
    public List<StdPersonTrack> selectStdPersonTrackList(StdPersonTrack stdPersonTrack);

    /**
     * 新增人口居住轨迹
     * 
     * @param stdPersonTrack 人口居住轨迹
     * @return 结果
     */
    public int insertStdPersonTrack(StdPersonTrack stdPersonTrack);

    /**
     * 修改人口居住轨迹
     * 
     * @param stdPersonTrack 人口居住轨迹
     * @return 结果
     */
    public int updateStdPersonTrack(StdPersonTrack stdPersonTrack);

    /**
     * 删除人口居住轨迹信息
     * 
     * @param trackId 人口居住轨迹主键
     * @return 结果
     */
    public int deleteStdPersonTrackByTrackId(Long trackId);
}
