package com.ruoyi.system.service;

import java.util.List;
import com.ruoyi.common.core.domain.entity.StdConsultationRecord;

/**
 * 消息记录Service接口
 *
 * @author ruoyi
 * @date 2022-12-09
 */
public interface IStdConsultationRecordService
{
    /**
     * 查询消息记录
     *
     * @param messageId 消息记录主键
     * @return 消息记录
     */
    public StdConsultationRecord selectStdConsultationRecordByMessageId(Long messageId);

    /**
     * 查询消息记录列表
     *
     * @param stdConsultationRecord 消息记录
     * @return 消息记录集合
     */
    public List<StdConsultationRecord> selectStdConsultationRecordList(StdConsultationRecord stdConsultationRecord);

    /**
     * 新增消息记录
     *
     * @param stdConsultationRecord 消息记录
     * @return 结果
     */
    public int insertStdConsultationRecord(StdConsultationRecord stdConsultationRecord);

    /**
     * 修改消息记录
     *
     * @param stdConsultationRecord 消息记录
     * @return 结果
     */
    public int updateStdConsultationRecord(StdConsultationRecord stdConsultationRecord);

    /**
     * 批量删除消息记录
     *
     * @param messageIds 需要删除的消息记录主键集合
     * @return 结果
     */
    public int deleteStdConsultationRecordByMessageIds(Long[] messageIds);

    /**
     * 删除消息记录信息
     *
     * @param messageId 消息记录主键
     * @return 结果
     */
    public int deleteStdConsultationRecordByMessageId(Long messageId);
}
