package com.ruoyi.system.service;

import com.ruoyi.common.core.domain.entity.StdNews;

import java.util.List;

/**
 * 新闻Service接口
 *
 * @author ruoyi
 * @date 2022-12-04
 */
public interface IStdNewsService
{
    /**
     * 查询新闻
     *
     * @param newsId 新闻主键
     * @return 新闻
     */
    public StdNews selectStdNewsByNewsId(Long newsId);

    /**
     * 查询新闻列表
     *
     * @param stdNews 新闻
     * @return 新闻集合
     */
    public List<StdNews> selectStdNewsList(StdNews stdNews);

    /**
     * 新增新闻
     *
     * @param stdNews 新闻
     * @return 结果
     */
    public int insertStdNews(StdNews stdNews);

    /**
     * 修改新闻
     *
     * @param stdNews 新闻
     * @return 结果
     */
    public int updateStdNews(StdNews stdNews);

    /**
     * 批量删除新闻
     *
     * @param newsIds 需要删除的新闻主键集合
     * @return 结果
     */
    public int deleteStdNewsByNewsIds(Long[] newsIds);

    /**
     * 删除新闻信息
     *
     * @param newsId 新闻主键
     * @return 结果
     */
    public int deleteStdNewsByNewsId(Long newsId);
}
