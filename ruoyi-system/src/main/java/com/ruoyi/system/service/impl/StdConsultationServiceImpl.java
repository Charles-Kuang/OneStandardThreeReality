package com.ruoyi.system.service.impl;

import java.util.List;

import com.ruoyi.common.core.domain.entity.StdConsultation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.StdConsultationMapper;
import com.ruoyi.system.service.IStdConsultationService;

/**
 * 咨询会话Service业务层处理
 *
 * @author ruoyi
 * @date 2022-12-09
 */
@Service
public class StdConsultationServiceImpl implements IStdConsultationService
{
    @Autowired
    private StdConsultationMapper stdConsultationMapper;

    /**
     * 查询咨询会话
     *
     * @param consultationId 咨询会话主键
     * @return 咨询会话
     */
    @Override
    public StdConsultation selectStdConsultationByConsultationId(Long consultationId)
    {
        return stdConsultationMapper.selectStdConsultationByConsultationId(consultationId);
    }

    /**
     * 查询咨询会话列表
     *
     * @param stdConsultation 咨询会话
     * @return 咨询会话
     */
    @Override
    public List<StdConsultation> selectStdConsultationList(StdConsultation stdConsultation)
    {
        return stdConsultationMapper.selectStdConsultationList(stdConsultation);
    }

    /**
     * 新增咨询会话
     *
     * @param stdConsultation 咨询会话
     * @return 结果
     */
    @Override
    public int insertStdConsultation(StdConsultation stdConsultation)
    {
        return stdConsultationMapper.insertStdConsultation(stdConsultation);
    }

    /**
     * 修改咨询会话
     *
     * @param stdConsultation 咨询会话
     * @return 结果
     */
    @Override
    public int updateStdConsultation(StdConsultation stdConsultation)
    {
        return stdConsultationMapper.updateStdConsultation(stdConsultation);
    }

    /**
     * 批量删除咨询会话
     *
     * @param consultationIds 需要删除的咨询会话主键
     * @return 结果
     */
    @Override
    public int deleteStdConsultationByConsultationIds(Long[] consultationIds)
    {
        return stdConsultationMapper.deleteStdConsultationByConsultationIds(consultationIds);
    }

    /**
     * 删除咨询会话信息
     *
     * @param consultationId 咨询会话主键
     * @return 结果
     */
    @Override
    public int deleteStdConsultationByConsultationId(Long consultationId)
    {
        return stdConsultationMapper.deleteStdConsultationByConsultationId(consultationId);
    }

    @Override
    public int endConsultationByConsultationId(Long consultationId) {
        return stdConsultationMapper.endConsultationByConsultationId(consultationId);
    }
}
