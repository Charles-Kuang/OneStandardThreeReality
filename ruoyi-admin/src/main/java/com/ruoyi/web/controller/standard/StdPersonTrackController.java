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
import com.ruoyi.common.core.domain.entity.StdPersonTrack;
import com.ruoyi.system.service.IStdPersonTrackService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 人口居住轨迹Controller
 * 
 * @author ruoyi
 * @date 2023-01-18
 */
@RestController
@RequestMapping("/standard/track")
public class StdPersonTrackController extends BaseController
{
    @Autowired
    private IStdPersonTrackService stdPersonTrackService;

    /**
     * 查询人口居住轨迹列表
     */
    @PreAuthorize("@ss.hasPermi('system:track:list')")
    @GetMapping("/list")
    public TableDataInfo list(StdPersonTrack stdPersonTrack)
    {
        startPage();
        List<StdPersonTrack> list = stdPersonTrackService.selectStdPersonTrackList(stdPersonTrack);
        return getDataTable(list);
    }

    /**
     * 导出人口居住轨迹列表
     */
    @PreAuthorize("@ss.hasPermi('system:track:export')")
    @Log(title = "人口居住轨迹", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, StdPersonTrack stdPersonTrack)
    {
        List<StdPersonTrack> list = stdPersonTrackService.selectStdPersonTrackList(stdPersonTrack);
        ExcelUtil<StdPersonTrack> util = new ExcelUtil<StdPersonTrack>(StdPersonTrack.class);
        util.exportExcel(response, list, "人口居住轨迹数据");
    }

    /**
     * 获取人口居住轨迹详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:track:query')")
    @GetMapping(value = "/{trackId}")
    public AjaxResult getInfo(@PathVariable("trackId") Long trackId)
    {
        return success(stdPersonTrackService.selectStdPersonTrackByTrackId(trackId));
    }

    /**
     * 新增人口居住轨迹
     */
    @PreAuthorize("@ss.hasPermi('system:track:add')")
    @Log(title = "人口居住轨迹", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody StdPersonTrack stdPersonTrack)
    {
        return toAjax(stdPersonTrackService.insertStdPersonTrack(stdPersonTrack));
    }

    /**
     * 修改人口居住轨迹
     */
    @PreAuthorize("@ss.hasPermi('system:track:edit')")
    @Log(title = "人口居住轨迹", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody StdPersonTrack stdPersonTrack)
    {
        return toAjax(stdPersonTrackService.updateStdPersonTrack(stdPersonTrack));
    }

    /**
     * 删除人口居住轨迹
     */
    @PreAuthorize("@ss.hasPermi('system:track:remove')")
    @Log(title = "人口居住轨迹", businessType = BusinessType.DELETE)
	@DeleteMapping("/{trackIds}")
    public AjaxResult remove(@PathVariable Long trackId)
    {
        return toAjax(stdPersonTrackService.deleteStdPersonTrackByTrackId(trackId));
    }
}
