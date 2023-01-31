package com.ruoyi.web.controller.standard;

import com.ruoyi.common.utils.StringUtils;
import java.util.Calendar;
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
import com.ruoyi.common.core.domain.entity.GsRegion;
import com.ruoyi.common.core.domain.entity.GsRegionModificationHistory;
import com.ruoyi.system.service.IGsRegionModificationHistoryService;
import com.ruoyi.system.service.IGsRegionService;
import com.ruoyi.system.service.IStdHouseService;
import com.ruoyi.common.utils.poi.ExcelUtil;

/**
 * 行政区Controller
 *
 * @author ruoyi
 * @date 2022-12-13
 */
@RestController
@RequestMapping("/standard/region")
public class GsRegionController extends BaseController
{
    @Autowired
    private IGsRegionService gsRegionService;

    @Autowired
    private IStdHouseService stdHouseService;

    @Autowired
    private IGsRegionModificationHistoryService gsRegionModificationHistoryService;

    /**
     * 查询行政区列表
     */
    @PreAuthorize("@ss.hasPermi('system:region:list')")
    @GetMapping("/list")
    public AjaxResult list(GsRegion gsRegion)
    {
        List<GsRegion> list = gsRegionService.selectGsRegionList(gsRegion);
        return success(list);
    }

    /**
     * 导出行政区列表
     */
    @PreAuthorize("@ss.hasPermi('system:region:export')")
    @Log(title = "行政区", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, GsRegion gsRegion)
    {
        List<GsRegion> list = gsRegionService.selectGsRegionList(gsRegion);
        ExcelUtil<GsRegion> util = new ExcelUtil<GsRegion>(GsRegion.class);
        util.exportExcel(response, list, "行政区数据");
    }

    /**
     * 获取行政区详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:region:query')")
    @GetMapping(value = "/{code}")
    public AjaxResult getInfo(@PathVariable("code") Long code)
    {
        return success(gsRegionService.selectGsRegionByCode(code));
    }

    /**
     * 新增行政区
     */
    @PreAuthorize("@ss.hasPermi('system:region:add')")
    @Log(title = "行政区", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody GsRegion gsRegion)
    {
        if(gsRegionService.existsByCode(gsRegion.getCode()))
            return warn("行政区编码已存在");
        gsRegion.setCommitStatus("1");
        return toAjax(gsRegionService.insertGsRegion(gsRegion));
    }

    /**
     * 修改行政区
     */
    @PreAuthorize("@ss.hasPermi('system:region:edit')")
    @Log(title = "行政区", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody GsRegion gsRegion)
    {
        GsRegion oldRegion = gsRegionService.selectGsRegionByCode(gsRegion.getCode());
        GsRegionModificationHistory history = new GsRegionModificationHistory();
        history.setOldValue("");
        history.setNewValue("");
        if(!StringUtils.isEmpty(oldRegion.getPolice()) && !oldRegion.getPolice().equals(gsRegion.getPolice())) {
            history.setOldValue(history.getOldValue() + "所属公安机关: " + oldRegion.getPolice() + "\n");
            history.setNewValue(history.getNewValue() + "所属公安机关: " + gsRegion.getPolice() + "\n");
            history.setModificationRegionId(oldRegion.getRegionId());
            history.setModificationRegionName(oldRegion.getName());
            history.setModificationDate(Calendar.getInstance().getTime());
        }
        if(!oldRegion.getName().equals(gsRegion.getName())) {
            history.setOldValue(history.getOldValue() + "行政区简称: " + oldRegion.getName() + "\n");
            history.setNewValue(history.getNewValue() + "行政区简称: " + gsRegion.getName() + "\n");
            history.setModificationRegionId(oldRegion.getRegionId());
            history.setModificationRegionName(oldRegion.getName());
            history.setModificationDate(Calendar.getInstance().getTime());
        }
        if(!oldRegion.getFullName().equals(gsRegion.getFullName())) {
            history.setOldValue(history.getOldValue() + "行政区全称: " + oldRegion.getFullName() + "\n");
            history.setNewValue(history.getNewValue() + "行政区全称: " + gsRegion.getFullName() + "\n");
            history.setModificationRegionId(oldRegion.getRegionId());
            history.setModificationRegionName(oldRegion.getName());
            history.setModificationDate(Calendar.getInstance().getTime());
        }
        if(!oldRegion.getFirstLetter().equals(gsRegion.getFirstLetter())) {
            history.setOldValue(history.getOldValue() + "行政区拼音首字母: " + oldRegion.getFirstLetter() + "\n");
            history.setNewValue(history.getNewValue() + "行政区拼音首字母: " + gsRegion.getFirstLetter() + "\n");
            history.setModificationRegionId(oldRegion.getRegionId());
            history.setModificationRegionName(oldRegion.getName());
            history.setModificationDate(Calendar.getInstance().getTime());
        }
        if(!StringUtils.isEmpty(oldRegion.getType()) && !oldRegion.getType().equals(gsRegion.getType())) {
            String oldType = "";
            String newType = "";
            if(oldRegion.getType().equals("111")) {
                oldType = "城市";
            } else if(oldRegion.getType().equals("112")) {
                oldType = "乡";
            } else if(oldRegion.getType().equals("121")) {
                oldType = "村";
            }

            if(gsRegion.getType().equals("111")) {
                newType = "城市";
            } else if(gsRegion.getType().equals("112")) {
                newType = "乡";
            } else if(gsRegion.getType().equals("121")) {
                newType = "村";
            }

            history.setOldValue(history.getOldValue() + "行政区类型: " + oldType + "\n");
            history.setNewValue(history.getNewValue() + "行政区类型: " + newType + "\n");
            history.setModificationRegionId(oldRegion.getRegionId());
            history.setModificationRegionName(oldRegion.getName());
            history.setModificationDate(Calendar.getInstance().getTime());
        }

        if(!oldRegion.getLng().equals(gsRegion.getLng())) {
            history.setOldValue(history.getOldValue() + "行政区经度: " + oldRegion.getLng() + "\n");
            history.setNewValue(history.getNewValue() + "行政区经度: " + gsRegion.getLng() + "\n");
            history.setModificationRegionId(oldRegion.getRegionId());
            history.setModificationRegionName(oldRegion.getName());
            history.setModificationDate(Calendar.getInstance().getTime());
        }

        if(!oldRegion.getLat().equals(gsRegion.getLat())) {
            history.setOldValue(history.getOldValue() + "行政区纬度: " + oldRegion.getLat() + "\n");
            history.setNewValue(history.getNewValue() + "行政区纬度: " + gsRegion.getLat() + "\n");
            history.setModificationRegionId(oldRegion.getRegionId());
            history.setModificationRegionName(oldRegion.getName());
            history.setModificationDate(Calendar.getInstance().getTime());
        }

        String newForefathersStr = "0";
        if(gsRegion.getProvinceCode() != null)
            newForefathersStr += "," + gsRegion.getProvinceCode();
        if(gsRegion.getCityCode() != null)
            newForefathersStr += "," + gsRegion.getCityCode();
        if(gsRegion.getDistrictCode() != null)
            newForefathersStr += "," + gsRegion.getDistrictCode();
        if(gsRegion.getStreetCode() != null)
            newForefathersStr += "," + gsRegion.getStreetCode();
        
        System.out.println(newForefathersStr + "---" + oldRegion.getForefathers());
        if(!oldRegion.getForefathers().equals(newForefathersStr)) {
            String oldForefathers[] = oldRegion.getForefathers().split(",");
            String newForefathers[] = newForefathersStr.split(",");

            String oldForefathersName = "";
            String newForefathersName = "";

            for(int i=1; i<oldForefathers.length; i++) {

                oldForefathersName += gsRegionService.selectNameByRegionCode(Long.valueOf(oldForefathers[i]));
            }

            for(int i=1; i<newForefathers.length; i++) {
                newForefathersName += gsRegionService.selectNameByRegionCode(Long.valueOf(newForefathers[i]));
            }
            
            history.setOldValue(history.getOldValue() + "父级行政区: " + oldForefathersName + "\n");
            history.setNewValue(history.getNewValue() + "父级行政区: " + newForefathersName + "\n");
            history.setModificationRegionId(oldRegion.getRegionId());
            history.setModificationRegionName(oldRegion.getName());
            history.setModificationDate(Calendar.getInstance().getTime());
        }

        if(history.getOldValue() != "")
            gsRegionModificationHistoryService.insertGsRegionModificationHistory(history);

        return toAjax(gsRegionService.updateGsRegion(gsRegion));
    }

    /**
     * 删除行政区
     */
    @PreAuthorize("@ss.hasPermi('system:region:remove')")
    @Log(title = "行政区", businessType = BusinessType.DELETE)
    @DeleteMapping("/{code}")
    public AjaxResult remove(@PathVariable Long code)
    {
        if(stdHouseService.existsByPositionCode(code))
            return warn("该行政区下存在房屋，不允许停用");
        if (gsRegionService.hasChildByRegionCode(code))
            return warn("存在下级行政区，不允许删除");
        gsRegionModificationHistoryService.deleteGsRegionModificationHistoryByRegionId(
            gsRegionService.selectGsRegionByCode(code).getRegionId()
        );
        return toAjax(gsRegionService.deleteGsRegionByCode(code));
    }

    @PreAuthorize("@ss.hasPermi('system:region:repeal')")
    @Log(title = "行政区", businessType = BusinessType.UPDATE)
    @PutMapping("/changeDeprecated")
    public AjaxResult changeDeprecated(@RequestBody GsRegion gsRegion) {
        if (gsRegionService.hasChildByRegionCode(gsRegion.getCode()))
            return warn("存在下级行政区，不允许停用");
        if(stdHouseService.existsByPositionCode(gsRegion.getCode()))
            return warn("该行政区下存在房屋，不允许停用");
        return toAjax(gsRegionService.repealGsRegion(gsRegion));
    }

    @PreAuthorize("@ss.hasPermi('system:region:audit')")
    @GetMapping(value = "/audit/{code}")
    public AjaxResult changeAuditState(@PathVariable("code") Long code) {
        GsRegion gsRegion = gsRegionService.selectGsRegionByCode(code);
        if (gsRegion.getLevel() != 0 && gsRegionService.uncheckedAuditState(gsRegion.getParentCode()))
            return warn("上级行政区未入库，不允许入库");
        gsRegion.setAuditState(1L);
        gsRegion.setAuditUser(getUsername());
        return success(gsRegionService.auditGsRegion(gsRegion));
    }
}
