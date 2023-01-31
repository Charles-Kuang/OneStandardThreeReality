package com.ruoyi.system.mapper;

import java.util.List;
import com.ruoyi.common.core.domain.entity.StdHouse;

/**
 * 门牌Mapper接口
 * 
 * @author ruoyi
 * @date 2022-12-28
 */
public interface StdHouseMapper 
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
     * 删除门牌
     * 
     * @param houseId 门牌主键
     * @return 结果
     */
    public int deleteStdHouseByHouseId(Long houseId);

    /**
     * 批量删除门牌
     * 
     * @param houseIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteStdHouseByHouseIds(Long[] houseIds);

    public int existsByHouseId(Long houseId);

    public int existsByControlNumber(Long controlNumber);

    public int existsByHostId(String hostIdType, String hostId);

    public int existsByPositionCode(Long positionCode);
}
