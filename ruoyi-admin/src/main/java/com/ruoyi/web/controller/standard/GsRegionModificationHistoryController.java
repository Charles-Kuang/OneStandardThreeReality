package com.ruoyi.web.controller.standard;

import java.util.List;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.core.domain.entity.GsRegionModificationHistory;
import com.ruoyi.system.service.IGsRegionModificationHistoryService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 行政区修改轨迹Controller
 * 
 * @author ruoyi
 * @date 2023-01-20
 */
@RestController
@RequestMapping("/standard/history")
public class GsRegionModificationHistoryController extends BaseController
{
    @Autowired
    private IGsRegionModificationHistoryService gsRegionModificationHistoryService;

    /**
     * 查询行政区修改轨迹列表
     */
    @PreAuthorize("@ss.hasPermi('system:history:list')")
    @GetMapping("/list")
    public TableDataInfo list(GsRegionModificationHistory gsRegionModificationHistory)
    {
        startPage();
        List<GsRegionModificationHistory> list = gsRegionModificationHistoryService.selectGsRegionModificationHistoryList(gsRegionModificationHistory);
        return getDataTable(list);
    }

    /**
     * 导出行政区修改轨迹列表
     */
    @PreAuthorize("@ss.hasPermi('system:history:export')")
    @Log(title = "行政区修改轨迹", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, GsRegionModificationHistory gsRegionModificationHistory)
    {
        List<GsRegionModificationHistory> list = gsRegionModificationHistoryService.selectGsRegionModificationHistoryList(gsRegionModificationHistory);
        ExcelUtil<GsRegionModificationHistory> util = new ExcelUtil<GsRegionModificationHistory>(GsRegionModificationHistory.class);
        util.exportExcel(response, list, "行政区修改轨迹数据");
    }

    /**
     * 新增行政区修改轨迹
     */
    @PreAuthorize("@ss.hasPermi('system:history:add')")
    @Log(title = "行政区修改轨迹", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody GsRegionModificationHistory gsRegionModificationHistory)
    {
        return toAjax(gsRegionModificationHistoryService.insertGsRegionModificationHistory(gsRegionModificationHistory));
    }

    /**
     * 删除行政区修改轨迹
     */
    @PreAuthorize("@ss.hasPermi('system:history:remove')")
    @Log(title = "行政区修改轨迹", businessType = BusinessType.DELETE)
	@DeleteMapping("/{modificationIds}")
    public AjaxResult remove(@PathVariable Long[] modificationIds)
    {
        return toAjax(gsRegionModificationHistoryService.deleteGsRegionModificationHistoryByModificationIds(modificationIds));
    }
}
