package com.ruoyi.system.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.StdConsultationRecordMapper;
import com.ruoyi.common.core.domain.entity.StdConsultationRecord;
import com.ruoyi.system.service.IStdConsultationRecordService;

/**
 * 消息记录Service业务层处理
 *
 * @author ruoyi
 * @date 2022-12-09
 */
@Service
public class StdConsultationRecordServiceImpl implements IStdConsultationRecordService
{
    @Autowired
    private StdConsultationRecordMapper stdConsultationRecordMapper;

    /**
     * 查询消息记录
     *
     * @param messageId 消息记录主键
     * @return 消息记录
     */
    @Override
    public StdConsultationRecord selectStdConsultationRecordByMessageId(Long messageId)
    {
        return stdConsultationRecordMapper.selectStdConsultationRecordByMessageId(messageId);
    }

    /**
     * 查询消息记录列表
     *
     * @param stdConsultationRecord 消息记录
     * @return 消息记录
     */
    @Override
    public List<StdConsultationRecord> selectStdConsultationRecordList(StdConsultationRecord stdConsultationRecord)
    {
        return stdConsultationRecordMapper.selectStdConsultationRecordList(stdConsultationRecord);
    }

    /**
     * 新增消息记录
     *
     * @param stdConsultationRecord 消息记录
     * @return 结果
     */
    @Override
    public int insertStdConsultationRecord(StdConsultationRecord stdConsultationRecord)
    {
        return stdConsultationRecordMapper.insertStdConsultationRecord(stdConsultationRecord);
    }

    /**
     * 修改消息记录
     *
     * @param stdConsultationRecord 消息记录
     * @return 结果
     */
    @Override
    public int updateStdConsultationRecord(StdConsultationRecord stdConsultationRecord)
    {
        return stdConsultationRecordMapper.updateStdConsultationRecord(stdConsultationRecord);
    }

    /**
     * 批量删除消息记录
     *
     * @param messageIds 需要删除的消息记录主键
     * @return 结果
     */
    @Override
    public int deleteStdConsultationRecordByMessageIds(Long[] messageIds)
    {
        return stdConsultationRecordMapper.deleteStdConsultationRecordByMessageIds(messageIds);
    }

    /**
     * 删除消息记录信息
     *
     * @param messageId 消息记录主键
     * @return 结果
     */
    @Override
    public int deleteStdConsultationRecordByMessageId(Long messageId)
    {
        return stdConsultationRecordMapper.deleteStdConsultationRecordByMessageId(messageId);
    }
}
