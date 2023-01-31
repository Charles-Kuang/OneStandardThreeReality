package com.ruoyi.system.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.StdNewsMapper;
import com.ruoyi.common.core.domain.entity.StdNews;
import com.ruoyi.system.service.IStdNewsService;

/**
 * 新闻Service业务层处理
 *
 * @author ruoyi
 * @date 2022-12-04
 */
@Service
public class StdNewsServiceImpl implements IStdNewsService
{
    @Autowired
    private StdNewsMapper stdNewsMapper;

    /**
     * 查询新闻
     *
     * @param newsId 新闻主键
     * @return 新闻
     */
    @Override
    public StdNews selectStdNewsByNewsId(Long newsId)
    {
        return stdNewsMapper.selectStdNewsByNewsId(newsId);
    }

    /**
     * 查询新闻列表
     *
     * @param stdNews 新闻
     * @return 新闻
     */
    @Override
    public List<StdNews> selectStdNewsList(StdNews stdNews)
    {
        return stdNewsMapper.selectStdNewsList(stdNews);
    }

    /**
     * 新增新闻
     *
     * @param stdNews 新闻
     * @return 结果
     */
    @Override
    public int insertStdNews(StdNews stdNews)
    {
        stdNews.setCreateTime(DateUtils.getNowDate());
        return stdNewsMapper.insertStdNews(stdNews);
    }

    /**
     * 修改新闻
     *
     * @param stdNews 新闻
     * @return 结果
     */
    @Override
    public int updateStdNews(StdNews stdNews)
    {
        return stdNewsMapper.updateStdNews(stdNews);
    }

    /**
     * 批量删除新闻
     *
     * @param newsIds 需要删除的新闻主键
     * @return 结果
     */
    @Override
    public int deleteStdNewsByNewsIds(Long[] newsIds)
    {
        return stdNewsMapper.deleteStdNewsByNewsIds(newsIds);
    }

    /**
     * 删除新闻信息
     *
     * @param newsId 新闻主键
     * @return 结果
     */
    @Override
    public int deleteStdNewsByNewsId(Long newsId)
    {
        return stdNewsMapper.deleteStdNewsByNewsId(newsId);
    }
}
