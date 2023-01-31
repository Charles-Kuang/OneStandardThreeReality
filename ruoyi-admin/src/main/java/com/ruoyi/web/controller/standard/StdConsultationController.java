package com.ruoyi.web.controller.standard;

import java.util.List;
import javax.servlet.http.HttpServletResponse;

import com.ruoyi.common.core.domain.entity.StdConsultation;
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
import com.ruoyi.system.service.IStdConsultationService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 咨询会话Controller
 *
 * @author ruoyi
 * @date 2022-12-09
 */
@RestController
@RequestMapping("/standard/consultation")
public class StdConsultationController extends BaseController
{
    @Autowired
    private IStdConsultationService stdConsultationService;

    /**
     * 查询咨询会话列表
     */
    @PreAuthorize("@ss.hasPermi('system:consultation:list')")
    @GetMapping("/list")
    public TableDataInfo list(StdConsultation stdConsultation)
    {
        startPage();
        List<StdConsultation> list = stdConsultationService.selectStdConsultationList(stdConsultation);
        return getDataTable(list);
    }

    /**
     * 导出咨询会话列表
     */
    @PreAuthorize("@ss.hasPermi('system:consultation:export')")
    @Log(title = "咨询会话", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, StdConsultation stdConsultation)
    {
        List<StdConsultation> list = stdConsultationService.selectStdConsultationList(stdConsultation);
        ExcelUtil<StdConsultation> util = new ExcelUtil<StdConsultation>(StdConsultation.class);
        util.exportExcel(response, list, "咨询会话数据");
    }

    /**
     * 获取咨询会话详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:consultation:query')")
    @GetMapping(value = "/{consultationId}")
    public AjaxResult getInfo(@PathVariable("consultationId") Long consultationId)
    {
        return success(stdConsultationService.selectStdConsultationByConsultationId(consultationId));
    }

    /**
     * 新增咨询会话
     */
    @PreAuthorize("@ss.hasPermi('system:consultation:add')")
    @Log(title = "咨询会话", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody StdConsultation stdConsultation)
    {
        return toAjax(stdConsultationService.insertStdConsultation(stdConsultation));
    }

    /**
     * 修改咨询会话
     */
    @PreAuthorize("@ss.hasPermi('system:consultation:edit')")
    @Log(title = "咨询会话", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody StdConsultation stdConsultation)
    {
        return toAjax(stdConsultationService.updateStdConsultation(stdConsultation));
    }

    /**
     * 删除咨询会话
     */
    @PreAuthorize("@ss.hasPermi('system:consultation:remove')")
    @Log(title = "咨询会话", businessType = BusinessType.DELETE)
    @DeleteMapping("/{consultationIds}")
    public AjaxResult remove(@PathVariable Long[] consultationIds)
    {
        return toAjax(stdConsultationService.deleteStdConsultationByConsultationIds(consultationIds));
    }

    @GetMapping(value = "/end/{consultationId}")
    public AjaxResult end(@PathVariable("consultationId") Long consultationId)
    {
        return success(stdConsultationService.endConsultationByConsultationId(consultationId));
    }
}
