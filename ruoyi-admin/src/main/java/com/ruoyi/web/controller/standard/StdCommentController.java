package com.ruoyi.web.controller.standard;

import java.util.List;
import javax.servlet.http.HttpServletResponse;

import com.ruoyi.common.core.domain.entity.StdComment;
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
import com.ruoyi.system.service.IStdCommentService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 新闻评论Controller
 *
 * @author ruoyi
 * @date 2022-12-04
 */
@RestController
@RequestMapping("/standard/comment")
public class StdCommentController extends BaseController
{
    @Autowired
    private IStdCommentService stdCommentService;

    /**
     * 查询新闻评论列表
     */
    @PreAuthorize("@ss.hasPermi('system:comment:list')")
    @GetMapping("/list")
    public TableDataInfo list(StdComment stdComment)
    {
        startPage();
        List<StdComment> list = stdCommentService.selectStdCommentList(stdComment);
        return getDataTable(list);
    }

    /**
     * 导出新闻评论列表
     */
    @PreAuthorize("@ss.hasPermi('system:comment:export')")
    @Log(title = "新闻评论", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, StdComment stdComment)
    {
        List<StdComment> list = stdCommentService.selectStdCommentList(stdComment);
        ExcelUtil<StdComment> util = new ExcelUtil<StdComment>(StdComment.class);
        util.exportExcel(response, list, "新闻评论数据");
    }

    /**
     * 获取新闻评论详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:comment:query')")
    @GetMapping(value = "/{commentId}")
    public AjaxResult getInfo(@PathVariable("commentId") Long commentId)
    {
        return success(stdCommentService.selectStdCommentByCommentId(commentId));
    }

    /**
     * 新增新闻评论
     */
    @PreAuthorize("@ss.hasPermi('system:comment:add')")
    @Log(title = "新闻评论", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody StdComment stdComment)
    {
        return toAjax(stdCommentService.insertStdComment(stdComment));
    }

    /**
     * 修改新闻评论
     */
    @PreAuthorize("@ss.hasPermi('system:comment:edit')")
    @Log(title = "新闻评论", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody StdComment stdComment)
    {
        return toAjax(stdCommentService.updateStdComment(stdComment));
    }

    /**
     * 删除新闻评论
     */
    @PreAuthorize("@ss.hasPermi('system:comment:remove')")
    @Log(title = "新闻评论", businessType = BusinessType.DELETE)
    @DeleteMapping("/{commentIds}")
    public AjaxResult remove(@PathVariable Long[] commentIds)
    {
        return toAjax(stdCommentService.deleteStdCommentByCommentIds(commentIds));
    }
}
