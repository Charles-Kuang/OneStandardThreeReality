package com.ruoyi.web.controller.standard;

import java.util.List;
import javax.servlet.http.HttpServletResponse;

import com.ruoyi.common.core.domain.entity.GsRegion;
import com.ruoyi.system.service.IGsRegionService;
import com.ruoyi.system.service.IStdDeptService;
import com.ruoyi.system.service.IStdHouseDailyCheckService;

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
import com.ruoyi.common.core.domain.entity.StdHouse;
import com.ruoyi.system.service.IStdHouseService;
import com.ruoyi.system.service.IStdPersonService;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 门牌Controller
 * 
 * @author ruoyi
 * @date 2022-12-28
 */
@RestController
@RequestMapping("/standard/house")
public class StdHouseController extends BaseController
{
    @Autowired
    private IStdHouseService stdHouseService;

    @Autowired
    private IGsRegionService gsRegionService;

    @Autowired
    private IStdPersonService stdPersonService;

    @Autowired
    private IStdDeptService stdDeptService;

    @Autowired
    private IStdHouseDailyCheckService stdHouseDailyCheckService;

    /**
     * 查询门牌列表
     */
    @PreAuthorize("@ss.hasPermi('system:house:list')")
    @GetMapping("/list")
    public TableDataInfo list(StdHouse stdHouse)
    {
        startPage();
        List<StdHouse> list = stdHouseService.selectStdHouseList(stdHouse);
        return getDataTable(list);
    }

    /**
     * 导出门牌列表
     */
    @PreAuthorize("@ss.hasPermi('system:house:export')")
    @Log(title = "门牌", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, StdHouse stdHouse)
    {
        List<StdHouse> list = stdHouseService.selectStdHouseList(stdHouse);
        ExcelUtil<StdHouse> util = new ExcelUtil<StdHouse>(StdHouse.class);
        util.exportExcel(response, list, "门牌数据");
    }

    /**
     * 获取门牌详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:house:query')")
    @GetMapping(value = "/{houseId}")
    public AjaxResult getInfo(@PathVariable("houseId") Long houseId)
    {
        return success(stdHouseService.selectStdHouseByHouseId(houseId));
    }

    /**
     * 新增门牌
     */
    @PreAuthorize("@ss.hasPermi('system:house:add')")
    @Log(title = "门牌", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody StdHouse stdHouse)
    {
        if(stdHouseService.selectStdHouseByHouseId(stdHouse.getHouseId()) != null)
            return warn("该房屋产权号已存在");
        if(stdHouse.getHostIdType().equals("2"))
            return warn("户主不可为无证件人员");
        if(!stdHouse.getHostIdType().equals("2") && !stdPersonService.existsByPersonId(stdHouse.getHostIdType(), stdHouse.getHostId())) {
            return warn("该户主不存在");
        }
        if(StringUtils.isNotNull(stdHouse.getControlNumber()) && stdHouse.getControlNumber() != 0 && stdHouseService.existsByControlNumber(stdHouse.getControlNumber())) {
            return warn("流管编号重复");
        }
        if(StringUtils.isNotNull(stdHouse.getControlDegree()) && stdHouse.getControlDegree().equals("3")) {
            //未签安全承诺书禁租
            if(StringUtils.isNotNull(stdHouse.getResponsibilitySignature()) && stdHouse.getResponsibilitySignature().equals("0")) {
                return warn("未签治安责任书的房屋安全分类应为“禁租”");
            }
            //禁租房屋不可租用
            if(StringUtils.isNotNull(stdHouse.getHouseUsage()) && stdHouse.getHouseUsage().equals("1")) {
                return warn("安全分类为“禁租”的房屋不可租用");
            }
        }

        if(StringUtils.isNotNull(stdHouse.getResponsibilitySignature()) && stdHouse.getResponsibilitySignature().equals("0")) {
            if(StringUtils.isNotNull(stdHouse.getHouseUsage()) && stdHouse.getHouseUsage().equals("1")) {
                return warn("未签治安责任书的房屋不可租用");
            }
        }

        GsRegion gsRegion = gsRegionService.selectGsRegionByCode(stdHouse.getPositionCode());
        stdHouse.setStandardAddress(gsRegion.getFullName() + " " + stdHouse.getResidentialVillageName() + " " + stdHouse.getDoorNumber() + "号");
        return toAjax(stdHouseService.insertStdHouse(stdHouse));
    }

    /**
     * 修改门牌
     */
    @PreAuthorize("@ss.hasPermi('system:house:edit')")
    @Log(title = "门牌", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody StdHouse stdHouse)
    {
        if(!stdHouse.getHostIdType().equals("2") && !stdPersonService.existsByPersonId(stdHouse.getHostIdType(), stdHouse.getHostId())) {
            return warn("该户主不存在");
        }
        if(StringUtils.isNotNull(stdHouse.getControlNumber()) && stdHouse.getControlNumber().longValue() != 0
            && (stdHouseService.existsByControlNumber(stdHouse.getControlNumber()) && !stdHouseService.selectStdHouseByHouseId(stdHouse.getHouseId()).getControlNumber().equals(stdHouse.getControlNumber()))) {
            return warn("流管编号重复");
        }
        if(!StringUtils.isNotNull(stdHouse.getControlNumber()))
            stdHouse.setControlNumber(0L);
        if(!StringUtils.isNotNull(stdHouse.getRoomQuantity()))
            stdHouse.setRoomQuantity(0L);
        //安全分类为"禁租""
        if(StringUtils.isNotNull(stdHouse.getControlDegree()) && stdHouse.getControlDegree().equals("3")) {
            //禁租房屋不可租用
            if(StringUtils.isNotNull(stdHouse.getHouseUsage()) && stdHouse.getHouseUsage().equals("1")) {
                return warn("安全分类为“禁租”的房屋不可租用");
            }
        }

        if(StringUtils.isNotNull(stdHouse.getResponsibilitySignature()) && stdHouse.getResponsibilitySignature().equals("0")) {
            if(StringUtils.isNotNull(stdHouse.getControlDegree()) && !stdHouse.getControlDegree().equals("3")) {
                return warn("未签治安责任书的房屋安全分类应为“禁租”");
            }
        }
        GsRegion gsRegion = gsRegionService.selectGsRegionByCode(stdHouse.getPositionCode());
        stdHouse.setStandardAddress(gsRegion.getFullName() + " " + stdHouse.getResidentialVillageName() + " " + stdHouse.getDoorNumber() + "号");
        return toAjax(stdHouseService.updateStdHouse(stdHouse));
    }

    /**
     * 删除门牌
     */
    @PreAuthorize("@ss.hasPermi('system:house:remove')")
    @Log(title = "门牌", businessType = BusinessType.DELETE)
	@DeleteMapping("/{houseIds}")
    public AjaxResult remove(@PathVariable Long[] houseIds)
    {
        for(Long houseId : houseIds) {
            if(stdPersonService.existsByCurrentStdHouseId(houseId))
                return warn("房屋" + houseId + "下存在现住人口，不可删除");
            if(stdDeptService.existsByStdHouseId(houseId))
                return warn("房屋" + houseId + "下存在现有单位，不可删除");       
        }

        for(Long houseId : houseIds) {
            stdHouseDailyCheckService.deleteStdHouseDailyCheckByHouseId(houseId);
        }
        
        return toAjax(stdHouseService.deleteStdHouseByHouseIds(houseIds));
    }
}
