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
import com.ruoyi.common.core.domain.entity.StdConsultationRecord;
import com.ruoyi.system.service.IStdConsultationRecordService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 消息记录Controller
 *
 * @author ruoyi
 * @date 2022-12-09
 */
@RestController
@RequestMapping("/standard/record")
public class StdConsultationRecordController extends BaseController
{
    @Autowired
    private IStdConsultationRecordService stdConsultationRecordService;

    /**
     * 查询消息记录列表
     */
    @PreAuthorize("@ss.hasPermi('system:record:list')")
    @GetMapping("/list")
    public TableDataInfo list(StdConsultationRecord stdConsultationRecord)
    {
        startPage();
        List<StdConsultationRecord> list = stdConsultationRecordService.selectStdConsultationRecordList(stdConsultationRecord);
        return getDataTable(list);
    }

    /**
     * 导出消息记录列表
     */
    @PreAuthorize("@ss.hasPermi('system:record:export')")
    @Log(title = "消息记录", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, StdConsultationRecord stdConsultationRecord)
    {
        List<StdConsultationRecord> list = stdConsultationRecordService.selectStdConsultationRecordList(stdConsultationRecord);
        ExcelUtil<StdConsultationRecord> util = new ExcelUtil<StdConsultationRecord>(StdConsultationRecord.class);
        util.exportExcel(response, list, "消息记录数据");
    }

    /**
     * 获取消息记录详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:record:query')")
    @GetMapping(value = "/{messageId}")
    public AjaxResult getInfo(@PathVariable("messageId") Long messageId)
    {
        return success(stdConsultationRecordService.selectStdConsultationRecordByMessageId(messageId));
    }

    /**
     * 新增消息记录
     */
    @PreAuthorize("@ss.hasPermi('system:record:add')")
    @Log(title = "消息记录", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody StdConsultationRecord stdConsultationRecord)
    {
        return toAjax(stdConsultationRecordService.insertStdConsultationRecord(stdConsultationRecord));
    }

    /**
     * 修改消息记录
     */
    @PreAuthorize("@ss.hasPermi('system:record:edit')")
    @Log(title = "消息记录", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody StdConsultationRecord stdConsultationRecord)
    {
        return toAjax(stdConsultationRecordService.updateStdConsultationRecord(stdConsultationRecord));
    }

    /**
     * 删除消息记录
     */
    @PreAuthorize("@ss.hasPermi('system:record:remove')")
    @Log(title = "消息记录", businessType = BusinessType.DELETE)
    @DeleteMapping("/{messageIds}")
    public AjaxResult remove(@PathVariable Long[] messageIds)
    {
        return toAjax(stdConsultationRecordService.deleteStdConsultationRecordByMessageIds(messageIds));
    }
}
