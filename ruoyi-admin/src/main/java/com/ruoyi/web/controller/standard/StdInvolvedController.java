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
import com.ruoyi.common.core.domain.entity.StdInvolved;
import com.ruoyi.system.service.IStdInvolvedService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 主体涉案Controller
 * 
 * @author ruoyi
 * @date 2023-01-18
 */
@RestController
@RequestMapping("/standard/involved")
public class StdInvolvedController extends BaseController
{
    @Autowired
    private IStdInvolvedService stdInvolvedService;

    /**
     * 查询主体涉案列表
     */
    @PreAuthorize("@ss.hasPermi('system:involved:list')")
    @GetMapping("/list")
    public TableDataInfo list(StdInvolved stdInvolved)
    {
        startPage();
        List<StdInvolved> list = stdInvolvedService.selectStdInvolvedList(stdInvolved);
        return getDataTable(list);
    }

    /**
     * 导出主体涉案列表
     */
    @PreAuthorize("@ss.hasPermi('system:involved:export')")
    @Log(title = "主体涉案", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, StdInvolved stdInvolved)
    {
        List<StdInvolved> list = stdInvolvedService.selectStdInvolvedList(stdInvolved);
        ExcelUtil<StdInvolved> util = new ExcelUtil<StdInvolved>(StdInvolved.class);
        util.exportExcel(response, list, "主体涉案数据");
    }

    /**
     * 获取主体涉案详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:involved:query')")
    @GetMapping(value = "/{involvedEntityType}/{involvedId}")
    public AjaxResult getInfo(@PathVariable("involvedId") Long involvedId, @PathVariable("involvedEntityType") String involvedEntityType)
    {
        return success(stdInvolvedService.selectStdInvolvedByInvolvedId(involvedEntityType, involvedId));
    }

    /**
     * 新增主体涉案
     */
    @PreAuthorize("@ss.hasPermi('system:involved:add')")
    @Log(title = "主体涉案", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody StdInvolved stdInvolved)
    {
        return toAjax(stdInvolvedService.insertStdInvolved(stdInvolved));
    }

    /**
     * 修改主体涉案
     */
    @PreAuthorize("@ss.hasPermi('system:involved:edit')")
    @Log(title = "主体涉案", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody StdInvolved stdInvolved)
    {
        return toAjax(stdInvolvedService.updateStdInvolved(stdInvolved));
    }

    /**
     * 删除主体涉案
     */
    @PreAuthorize("@ss.hasPermi('system:involved:remove')")
    @Log(title = "主体涉案", businessType = BusinessType.DELETE)
	@DeleteMapping("/{involvedIds}")
    public AjaxResult remove(@PathVariable Long[] involvedIds)
    {
        return toAjax(stdInvolvedService.deleteStdInvolvedByInvolvedIds(involvedIds));
    }
}
