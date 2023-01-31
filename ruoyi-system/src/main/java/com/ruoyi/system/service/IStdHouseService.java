package com.ruoyi.system.service;

import java.util.List;
import com.ruoyi.common.core.domain.entity.StdHouse;

/**
 * 门牌Service接口
 * 
 * @author ruoyi
 * @date 2022-12-28
 */
public interface IStdHouseService 
{
    /**
     * 查询门牌
     * 
     * @param houseId 门牌主键
     * @return 门牌
     */
    public StdHouse selectStdHouseByHouseId(Long houseId);

    /**
     * 查询门牌列表
     * 
     * @param stdHouse 门牌
     * @return 门牌集合
     */
    public List<StdHouse> selectStdHouseList(StdHouse stdHouse);

    /**
     * 新增门牌
     * 
     * @param stdHouse 门牌
     * @return 结果
     */
    public int insertStdHouse(StdHouse stdHouse);

    /**
     * 修改门牌
     * 
     * @param stdHouse 门牌
     * @return 结果
     */
    public int updateStdHouse(StdHouse stdHouse);

    /**
     * 批量删除门牌
     * 
     * @param houseIds 需要删除的门牌主键集合
     * @return 结果
     */
    public int deleteStdHouseByHouseIds(Long[] houseIds);

    /**
     * 删除门牌信息
     * 
     * @param houseId 门牌主键
     * @return 结果
     */
    public int deleteStdHouseByHouseId(Long houseId);

    public boolean existsByHouseId(Long houseId);

    public boolean existsByControlNumber(Long controlNumber);

    public boolean existsByHostId(String hostIdType, String hostId);

    public boolean existsByPositionCode(Long positionCode);
}
