package com.ruoyi.system.service;

import com.ruoyi.common.core.domain.entity.StdComment;

import java.util.List;

/**
 * 新闻评论Service接口
 *
 * @author ruoyi
 * @date 2022-12-04
 */
public interface IStdCommentService
{
    /**
     * 查询新闻评论
     *
     * @param commentId 新闻评论主键
     * @return 新闻评论
     */
    public StdComment selectStdCommentByCommentId(Long commentId);

    /**
     * 查询新闻评论列表
     *
     * @param stdComment 新闻评论
     * @return 新闻评论集合
     */
    public List<StdComment> selectStdCommentList(StdComment stdComment);

    /**
     * 新增新闻评论
     *
     * @param stdComment 新闻评论
     * @return 结果
     */
    public int insertStdComment(StdComment stdComment);

    /**
     * 修改新闻评论
     *
     * @param stdComment 新闻评论
     * @return 结果
     */
    public int updateStdComment(StdComment stdComment);

    /**
     * 批量删除新闻评论
     *
     * @param commentIds 需要删除的新闻评论主键集合
     * @return 结果
     */
    public int deleteStdCommentByCommentIds(Long[] commentIds);

    /**
     * 删除新闻评论信息
     *
     * @param commentId 新闻评论主键
     * @return 结果
     */
    public int deleteStdCommentByCommentId(Long commentId);
}
