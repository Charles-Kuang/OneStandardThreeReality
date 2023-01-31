package com.ruoyi.web.controller.standard;

import java.util.List;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.core.domain.entity.StdNews;
import com.ruoyi.system.service.IStdNewsService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 新闻Controller
 *
 * @author ruoyi
 * @date 2022-12-04
 */
@RestController
@RequestMapping("/standard/news")
public class StdNewsController extends BaseController
{
    @Autowired
    private IStdNewsService stdNewsService;

    /**
     * 查询新闻列表
     */
    @PreAuthorize("@ss.hasPermi('system:news:list')")
    @GetMapping("/list")
    public TableDataInfo list(StdNews stdNews)
    {
        startPage();
        List<StdNews> list = stdNewsService.selectStdNewsList(stdNews);
        return getDataTable(list);
    }

    /**
     * 导出新闻列表
     */
    @PreAuthorize("@ss.hasPermi('system:news:export')")
    @Log(title = "新闻", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, StdNews stdNews)
    {
        List<StdNews> list = stdNewsService.selectStdNewsList(stdNews);
        ExcelUtil<StdNews> util = new ExcelUtil<StdNews>(StdNews.class);
        util.exportExcel(response, list, "新闻数据");
    }

    /**
     * 获取新闻详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:news:query')")
    @GetMapping(value = "/{newsId}")
    public AjaxResult getInfo(@PathVariable("newsId") Long newsId)
    {
        return success(stdNewsService.selectStdNewsByNewsId(newsId));
    }

    /**
     * 新增新闻
     */
    @PreAuthorize("@ss.hasPermi('system:news:add')")
    @Log(title = "新闻", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody StdNews stdNews)
    {
        return toAjax(stdNewsService.insertStdNews(stdNews));
    }

    /**
     * 修改新闻
     */
    @PreAuthorize("@ss.hasPermi('system:news:edit')")
    @Log(title = "新闻", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody StdNews stdNews)
    {
        return toAjax(stdNewsService.updateStdNews(stdNews));
    }

    /**
     * 删除新闻
     */
    @PreAuthorize("@ss.hasPermi('system:news:remove')")
    @Log(title = "新闻", businessType = BusinessType.DELETE)
    @DeleteMapping("/{newsIds}")
    public AjaxResult remove(@PathVariable Long[] newsIds)
    {
        return toAjax(stdNewsService.deleteStdNewsByNewsIds(newsIds));
    }
}
