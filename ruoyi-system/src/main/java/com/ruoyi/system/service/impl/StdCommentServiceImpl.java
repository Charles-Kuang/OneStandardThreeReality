package com.ruoyi.system.service.impl;

import java.util.List;

import com.ruoyi.common.core.domain.entity.StdComment;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.StdCommentMapper;
import com.ruoyi.system.service.IStdCommentService;

/**
 * 新闻评论Service业务层处理
 *
 * @author ruoyi
 * @date 2022-12-04
 */
@Service
public class StdCommentServiceImpl implements IStdCommentService
{
    @Autowired
    private StdCommentMapper stdCommentMapper;

    /**
     * 查询新闻评论
     *
     * @param commentId 新闻评论主键
     * @return 新闻评论
     */
    @Override
    public StdComment selectStdCommentByCommentId(Long commentId)
    {
        return stdCommentMapper.selectStdCommentByCommentId(commentId);
    }

    /**
     * 查询新闻评论列表
     *
     * @param stdComment 新闻评论
     * @return 新闻评论
     */
    @Override
    public List<StdComment> selectStdCommentList(StdComment stdComment)
    {
        return stdCommentMapper.selectStdCommentList(stdComment);
    }

    /**
     * 新增新闻评论
     *
     * @param stdComment 新闻评论
     * @return 结果
     */
    @Override
    public int insertStdComment(StdComment stdComment)
    {
        stdComment.setCreateTime(DateUtils.getNowDate());
        return stdCommentMapper.insertStdComment(stdComment);
    }

    /**
     * 修改新闻评论
     *
     * @param stdComment 新闻评论
     * @return 结果
     */
    @Override
    public int updateStdComment(StdComment stdComment)
    {
        return stdCommentMapper.updateStdComment(stdComment);
    }

    /**
     * 批量删除新闻评论
     *
     * @param commentIds 需要删除的新闻评论主键
     * @return 结果
     */
    @Override
    public int deleteStdCommentByCommentIds(Long[] commentIds)
    {
        return stdCommentMapper.deleteStdCommentByCommentIds(commentIds);
    }

    /**
     * 删除新闻评论信息
     *
     * @param commentId 新闻评论主键
     * @return 结果
     */
    @Override
    public int deleteStdCommentByCommentId(Long commentId)
    {
        return stdCommentMapper.deleteStdCommentByCommentId(commentId);
    }
}
