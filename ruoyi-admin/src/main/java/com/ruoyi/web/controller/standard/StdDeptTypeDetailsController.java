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
import com.ruoyi.common.core.domain.entity.StdDeptTypeDetails;
import com.ruoyi.system.service.IStdDeptTypeDetailsService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 单位类别关系Controller
 * 
 * @author ruoyi
 * @date 2023-01-05
 */
@RestController
@RequestMapping("/standard/details")
public class StdDeptTypeDetailsController extends BaseController
{
    @Autowired
    private IStdDeptTypeDetailsService stdDeptTypeDetailsService;

    /**
     * 查询单位类别关系列表
     */
    @PreAuthorize("@ss.hasPermi('system:details:list')")
    @GetMapping("/list")
    public TableDataInfo list(StdDeptTypeDetails stdDeptTypeDetails)
    {
        startPage();
        List<StdDeptTypeDetails> list = stdDeptTypeDetailsService.selectStdDeptTypeDetailsList(stdDeptTypeDetails);
        return getDataTable(list);
    }

    /**
     * 导出单位类别关系列表
     */
    @PreAuthorize("@ss.hasPermi('system:details:export')")
    @Log(title = "单位类别关系", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, StdDeptTypeDetails stdDeptTypeDetails)
    {
        List<StdDeptTypeDetails> list = stdDeptTypeDetailsService.selectStdDeptTypeDetailsList(stdDeptTypeDetails);
        ExcelUtil<StdDeptTypeDetails> util = new ExcelUtil<StdDeptTypeDetails>(StdDeptTypeDetails.class);
        util.exportExcel(response, list, "单位类别关系数据");
    }

    /**
     * 获取单位类别关系详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:details:query')")
    @GetMapping(value = "/{detailId}")
    public AjaxResult getInfo(@PathVariable("detailId") Long detailId)
    {
        return success(stdDeptTypeDetailsService.selectStdDeptTypeDetailsByDetailId(detailId));
    }

    /**
     * 新增单位类别关系
     */
    @PreAuthorize("@ss.hasPermi('system:details:add')")
    @Log(title = "单位类别关系", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody StdDeptTypeDetails stdDeptTypeDetails)
    {
        return toAjax(stdDeptTypeDetailsService.insertStdDeptTypeDetails(stdDeptTypeDetails));
    }

    /**
     * 修改单位类别关系
     */
    @PreAuthorize("@ss.hasPermi('system:details:edit')")
    @Log(title = "单位类别关系", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody StdDeptTypeDetails stdDeptTypeDetails)
    {
        return toAjax(stdDeptTypeDetailsService.updateStdDeptTypeDetails(stdDeptTypeDetails));
    }

    /**
     * 删除单位类别关系
     */
    @PreAuthorize("@ss.hasPermi('system:details:remove')")
    @Log(title = "单位类别关系", businessType = BusinessType.DELETE)
	@DeleteMapping("/{detailIds}")
    public AjaxResult remove(@PathVariable Long[] detailIds)
    {
        return toAjax(stdDeptTypeDetailsService.deleteStdDeptTypeDetailsByDetailIds(detailIds));
    }
}
