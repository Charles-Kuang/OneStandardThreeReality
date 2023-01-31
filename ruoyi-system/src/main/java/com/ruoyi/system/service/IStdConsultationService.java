package com.ruoyi.system.service;

import com.ruoyi.common.core.domain.entity.StdConsultation;

import java.util.List;

/**
 * 咨询会话Service接口
 *
 * @author ruoyi
 * @date 2022-12-09
 */
public interface IStdConsultationService
{
    /**
     * 查询咨询会话
     *
     * @param consultationId 咨询会话主键
     * @return 咨询会话
     */
    public StdConsultation selectStdConsultationByConsultationId(Long consultationId);

    /**
     * 查询咨询会话列表
     *
     * @param stdConsultation 咨询会话
     * @return 咨询会话集合
     */
    public List<StdConsultation> selectStdConsultationList(StdConsultation stdConsultation);

    /**
     * 新增咨询会话
     *
     * @param stdConsultation 咨询会话
     * @return 结果
     */
    public int insertStdConsultation(StdConsultation stdConsultation);

    /**
     * 修改咨询会话
     *
     * @param stdConsultation 咨询会话
     * @return 结果
     */
    public int updateStdConsultation(StdConsultation stdConsultation);

    /**
     * 批量删除咨询会话
     *
     * @param consultationIds 需要删除的咨询会话主键集合
     * @return 结果
     */
    public int deleteStdConsultationByConsultationIds(Long[] consultationIds);

    /**
     * 删除咨询会话信息
     *
     * @param consultationId 咨询会话主键
     * @return 结果
     */
    public int deleteStdConsultationByConsultationId(Long consultationId);

    public int endConsultationByConsultationId(Long consultationId);
}
