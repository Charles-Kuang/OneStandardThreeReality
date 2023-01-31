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
import com.ruoyi.common.core.domain.entity.StdHouseDailyCheck;
import com.ruoyi.system.service.IStdHouseDailyCheckService;
import com.ruoyi.system.service.IStdPersonService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 房屋日常检查Controller
 * 
 * @author ruoyi
 * @date 2023-01-17
 */
@RestController
@RequestMapping("/standard/check")
public class StdHouseDailyCheckController extends BaseController
{
    @Autowired
    private IStdHouseDailyCheckService stdHouseDailyCheckService;

    @Autowired
    private IStdPersonService stdPersonService;

    /**
     * 查询房屋日常检查列表
     */
    @PreAuthorize("@ss.hasPermi('system:check:list')")
    @GetMapping("/list")
    public TableDataInfo list(StdHouseDailyCheck stdHouseDailyCheck)
    {
        startPage();
        List<StdHouseDailyCheck> list = stdHouseDailyCheckService.selectStdHouseDailyCheckList(stdHouseDailyCheck);
        return getDataTable(list);
    }

    /**
     * 导出房屋日常检查列表
     */
    @PreAuthorize("@ss.hasPermi('system:check:export')")
    @Log(title = "房屋日常检查", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, StdHouseDailyCheck stdHouseDailyCheck)
    {
        List<StdHouseDailyCheck> list = stdHouseDailyCheckService.selectStdHouseDailyCheckList(stdHouseDailyCheck);
        ExcelUtil<StdHouseDailyCheck> util = new ExcelUtil<StdHouseDailyCheck>(StdHouseDailyCheck.class);
        util.exportExcel(response, list, "房屋日常检查数据");
    }

    /**
     * 获取房屋日常检查详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:check:query')")
    @GetMapping(value = "/{checkId}")
    public AjaxResult getInfo(@PathVariable("checkId") Long checkId)
    {
        return success(stdHouseDailyCheckService.selectStdHouseDailyCheckByCheckId(checkId));
    }

    /**
     * 新增房屋日常检查
     */
    @PreAuthorize("@ss.hasPermi('system:check:add')")
    @Log(title = "房屋日常检查", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody StdHouseDailyCheck stdHouseDailyCheck)
    {
        if(stdHouseDailyCheck.getInspectorIdType().equals("2"))
            return warn("检查人不可为无证件人员");
        if(!stdHouseDailyCheck.getInspectorIdType().equals("2") && !stdPersonService.existsByPersonId(stdHouseDailyCheck.getInspectorIdType(), stdHouseDailyCheck.getInspectorId())) {
            return warn("该检查人不存在");
        }
        if(stdHouseDailyCheck.getDangerType().equals("0") || stdHouseDailyCheck.getDangerType().equals("1")) {
            if(!stdHouseDailyCheck.getCheckResult().equals("2")) {
                return warn("危险等级为‘极危’或‘危险’的房屋，检查结果应为‘不通过’");
            }
        }
        if(stdHouseDailyCheck.getDangerType().equals("4")) {
            if(!stdHouseDailyCheck.getCheckResult().equals("0")) {
                return warn("危险等级为‘无’的房屋，检查结果应为‘通过’");
            }
        }
        return toAjax(stdHouseDailyCheckService.insertStdHouseDailyCheck(stdHouseDailyCheck));
    }

    /**
     * 修改房屋日常检查
     */
    @PreAuthorize("@ss.hasPermi('system:check:edit')")
    @Log(title = "房屋日常检查", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody StdHouseDailyCheck stdHouseDailyCheck)
    {
        if(stdHouseDailyCheck.getInspectorIdType().equals("2"))
            return warn("检查人不可为无证件人员");
        if(!stdHouseDailyCheck.getInspectorIdType().equals("2") && !stdPersonService.existsByPersonId(stdHouseDailyCheck.getInspectorIdType(), stdHouseDailyCheck.getInspectorId())) {
            return warn("该检查人不存在");
        }
        if(stdHouseDailyCheck.getManageDate() != null) {
            if(stdHouseDailyCheck.getManageDate().before(stdHouseDailyCheck.getCheckDate())) {
                return warn("处理日期不应在检查日期之前");
            }
        }
        return toAjax(stdHouseDailyCheckService.updateStdHouseDailyCheck(stdHouseDailyCheck));
    }

    /**
     * 删除房屋日常检查
     */
    @PreAuthorize("@ss.hasPermi('system:check:remove')")
    @Log(title = "房屋日常检查", businessType = BusinessType.DELETE)
	@DeleteMapping("/{checkIds}")
    public AjaxResult remove(@PathVariable Long[] checkIds)
    {
        return toAjax(stdHouseDailyCheckService.deleteStdHouseDailyCheckByCheckIds(checkIds));
    }
}
