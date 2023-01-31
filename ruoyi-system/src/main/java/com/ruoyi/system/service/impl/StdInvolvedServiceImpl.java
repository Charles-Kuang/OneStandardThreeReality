package com.ruoyi.system.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.StdInvolvedMapper;
import com.ruoyi.common.core.domain.entity.StdInvolved;
import com.ruoyi.system.service.IStdInvolvedService;

/**
 * 主体涉案Service业务层处理
 * 
 * @author ruoyi
 * @date 2023-01-18
 */
@Service
public class StdInvolvedServiceImpl implements IStdInvolvedService 
{
    @Autowired
    private StdInvolvedMapper stdInvolvedMapper;

    /**
     * 查询主体涉案
     * 
     * @param involvedId 主体涉案主键
     * @return 主体涉案
     */
    @Override
    public StdInvolved selectStdInvolvedByInvolvedId(String involvedEntityType, Long involvedId)
    {
        if(involvedEntityType.equals("房屋"))
            return stdInvolvedMapper.selectStdHouseInvolvedByInvolvedId(involvedId);
        if(involvedEntityType.equals("单位"))
            return stdInvolvedMapper.selectStdDeptInvolvedByInvolvedId(involvedId);
        return null;
    }

    /**
     * 查询主体涉案列表
     * 
     * @param stdInvolved 主体涉案
     * @return 主体涉案
     */
    @Override
    public List<StdInvolved> selectStdInvolvedList(StdInvolved stdInvolved)
    {
        if(stdInvolved.getInvolvedEntityType().equals("房屋"))
            return stdInvolvedMapper.selectStdHouseInvolvedList(stdInvolved);
        if(stdInvolved.getInvolvedEntityType().equals("单位"))
            return stdInvolvedMapper.selectStdDeptInvolvedList(stdInvolved);
        return null;
    }

    /**
     * 新增主体涉案
     * 
     * @param stdInvolved 主体涉案
     * @return 结果
     */
    @Override
    public int insertStdInvolved(StdInvolved stdInvolved)
    {
        return stdInvolvedMapper.insertStdInvolved(stdInvolved);
    }

    /**
     * 修改主体涉案
     * 
     * @param stdInvolved 主体涉案
     * @return 结果
     */
    @Override
    public int updateStdInvolved(StdInvolved stdInvolved)
    {
        return stdInvolvedMapper.updateStdInvolved(stdInvolved);
    }

    /**
     * 批量删除主体涉案
     * 
     * @param involvedIds 需要删除的主体涉案主键
     * @return 结果
     */
    @Override
    public int deleteStdInvolvedByInvolvedIds(Long[] involvedIds)
    {
        return stdInvolvedMapper.deleteStdInvolvedByInvolvedIds(involvedIds);
    }

    /**
     * 删除主体涉案信息
     * 
     * @param involvedId 主体涉案主键
     * @return 结果
     */
    @Override
    public int deleteStdInvolvedByInvolvedId(Long involvedId)
    {
        return stdInvolvedMapper.deleteStdInvolvedByInvolvedId(involvedId);
    }
}
