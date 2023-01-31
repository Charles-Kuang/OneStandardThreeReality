package com.ruoyi.system.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.StdHouseMapper;
import com.ruoyi.system.mapper.StdInvolvedMapper;
import com.ruoyi.common.core.domain.entity.StdHouse;
import com.ruoyi.system.service.IStdHouseService;

/**
 * 门牌Service业务层处理
 * 
 * @author ruoyi
 * @date 2022-12-28
 */
@Service
public class StdHouseServiceImpl implements IStdHouseService 
{
    @Autowired
    private StdHouseMapper stdHouseMapper;

    @Autowired
    private StdInvolvedMapper stdInvolvedMapper;

    /**
     * 查询门牌
     * 
     * @param houseId 门牌主键
     * @return 门牌
     */
    @Override
    public StdHouse selectStdHouseByHouseId(Long houseId)
    {
        return stdHouseMapper.selectStdHouseByHouseId(houseId);
    }

    /**
     * 查询门牌列表
     * 
     * @param stdHouse 门牌
     * @return 门牌
     */
    @Override
    public List<StdHouse> selectStdHouseList(StdHouse stdHouse)
    {
        return stdHouseMapper.selectStdHouseList(stdHouse);
    }

    /**
     * 新增门牌
     * 
     * @param stdHouse 门牌
     * @return 结果
     */
    @Override
    public int insertStdHouse(StdHouse stdHouse)
    {
        return stdHouseMapper.insertStdHouse(stdHouse);
    }

    /**
     * 修改门牌
     * 
     * @param stdHouse 门牌
     * @return 结果
     */
    @Override
    public int updateStdHouse(StdHouse stdHouse)
    {
        return stdHouseMapper.updateStdHouse(stdHouse);
    }

    /**
     * 批量删除门牌
     * 
     * @param houseIds 需要删除的门牌主键
     * @return 结果
     */
    @Override
    public int deleteStdHouseByHouseIds(Long[] houseIds)
    {
        for(Long houseId : houseIds) {
            stdInvolvedMapper.deleteStdInvolvedByInvolvedEntity("房屋", houseId);
        }
        return stdHouseMapper.deleteStdHouseByHouseIds(houseIds);
    }

    /**
     * 删除门牌信息
     * 
     * @param houseId 门牌主键
     * @return 结果
     */
    @Override
    public int deleteStdHouseByHouseId(Long houseId)
    {
        stdInvolvedMapper.deleteStdInvolvedByInvolvedEntity("房屋", houseId);
        return stdHouseMapper.deleteStdHouseByHouseId(houseId);
    }

    @Override
    public boolean existsByHouseId(Long houseId) {
        return stdHouseMapper.existsByHouseId(houseId) > 0;
    }

    @Override
    public boolean existsByControlNumber(Long controlNumber) {
        return stdHouseMapper.existsByControlNumber(controlNumber) > 0;
    }

    @Override
    public boolean existsByHostId(String hostIdType, String hostId) {
        return stdHouseMapper.existsByHostId(hostIdType, hostId) > 0;
    }

    @Override
    public boolean existsByPositionCode(Long positionCode) {
        int result = stdHouseMapper.existsByPositionCode(positionCode);
        return result > 0;
    }
}
