package com.ruoyi.web.controller.standard;

import java.util.List;
import java.util.stream.Collectors;

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
import com.ruoyi.common.core.domain.entity.StdDept;
import com.ruoyi.common.core.domain.entity.StdDeptTypeDetails;
import com.ruoyi.common.core.domain.entity.StdPerson;
import com.ruoyi.system.service.IStdDeptService;
import com.ruoyi.system.service.IStdHouseService;
import com.ruoyi.system.service.IStdPersonService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.utils.StringUtils;

/**
 * 单位Controller
 * 
 * @author ruoyi
 * @date 2023-01-03
 */
@RestController
@RequestMapping("/standard/dept")
public class StdDeptController extends BaseController
{
    @Autowired
    private IStdDeptService stdDeptService;

    
    @Autowired
    private IStdHouseService stdHouseService;

    @Autowired
    private IStdPersonService stdPersonService;

    /**
     * 查询单位列表
     */
    @PreAuthorize("@ss.hasPermi('system:dept:list')")
    @GetMapping("/list")
    public TableDataInfo list(StdDept stdDept)
    {
        startPage();
        List<StdDept> list = stdDeptService.selectStdDeptList(stdDept);
        return getDataTable(list);
    }

    /**
     * 导出单位列表
     */
    @PreAuthorize("@ss.hasPermi('system:dept:export')")
    @Log(title = "单位", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, StdDept stdDept)
    {
        List<StdDept> list = stdDeptService.selectStdDeptList(stdDept);
        ExcelUtil<StdDept> util = new ExcelUtil<StdDept>(StdDept.class);
        util.exportExcel(response, list, "单位数据");
    }

    /**
     * 获取单位详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:dept:query')")
    @GetMapping(value = "/{deptId}")
    public AjaxResult getInfo(@PathVariable("deptId") Long deptId)
    {
        AjaxResult ajax = AjaxResult.success();
        
        StdDept stdDept = stdDeptService.selectStdDeptByDeptId(deptId);
        ajax.put(AjaxResult.DATA_TAG, stdDept);
        ajax.put("deptTypes", stdDept.getDeptTypeDetails().stream().map(StdDeptTypeDetails::getDeptType).collect(Collectors.toList()));
        
        return ajax;
    }

    /**
     * 新增单位
     */
    @PreAuthorize("@ss.hasPermi('system:dept:add')")
    @Log(title = "单位", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody StdDept stdDept)
    {
        if(StringUtils.isNotNull(stdDeptService.selectStdDeptByDeptId(stdDept.getDeptId()))) {
            return warn("单位营业执照号码重复");
        }
        if(!stdHouseService.existsByHouseId(stdDept.getStdHouseId()))
            return warn("该实体房屋不存在");
        if(stdDept.getLegalRepresentativeIdType().equals("2") || !stdPersonService.existsByPersonId(stdDept.getLegalRepresentativeIdType(), stdDept.getLegalRepresentativeId())) {
            return warn("该法定代表人不存在或无证件");
        } else {
            StdPerson legalRepresentative = new StdPerson();
            legalRepresentative.setPersonIdType(stdDept.getLegalRepresentativeIdType());
            legalRepresentative.setPersonId(stdDept.getLegalRepresentativeId());
            legalRepresentative.setPersonName(stdDept.getLegalRepresentativeName());
            if(stdDept.getLegalRepresentativeContacts() != null && !stdDept.getLegalRepresentativeContacts().equals("stdDept")) 
                legalRepresentative.setContacts(stdDept.getLegalRepresentativeContacts());
            if(stdPersonService.selectStdPersonList(legalRepresentative).isEmpty()) {
                return warn("该法定代表人联系方式或姓名与证件号不符");
            }
        }


        if(stdDept.getLeaderIdType().equals("2") || !stdPersonService.existsByPersonId(stdDept.getLeaderIdType(), stdDept.getLeaderId())) {
            return warn("该单位负责人不存在或无证件");
        } else {
            StdPerson leader = new StdPerson();
            leader.setPersonIdType(stdDept.getLeaderIdType());
            leader.setPersonId(stdDept.getLeaderId());
            leader.setPersonName(stdDept.getLeaderName());
            if(stdDept.getLeaderContacts() != null && !stdDept.getLeaderContacts().equals("stdDept")) 
                leader.setContacts(stdDept.getLeaderContacts());
            if(stdPersonService.selectStdPersonList(leader).isEmpty()) {
                return warn("该单位负责人联系方式或姓名与证件号不符");
            }
        }

        if(stdDept.getSecurityRepresentativeIdType().equals("2") || !stdPersonService.existsByPersonId(stdDept.getSecurityRepresentativeIdType(), stdDept.getSecurityRepresentativeId())) {
            return warn("该治安负责人不存在或无证件");
        } else {
            StdPerson securityRepresentative = new StdPerson();
            securityRepresentative.setPersonIdType(stdDept.getSecurityRepresentativeIdType());
            securityRepresentative.setPersonId(stdDept.getSecurityRepresentativeId());
            securityRepresentative.setPersonName(stdDept.getSecurityRepresentativeName());
            if(stdDept.getSecurityRepresentativeContacts() != null && !stdDept.getSecurityRepresentativeContacts().equals("")) 
            securityRepresentative.setContacts(stdDept.getSecurityRepresentativeContacts());
            if(stdPersonService.selectStdPersonList(securityRepresentative).isEmpty()) {
                return warn("该治安负责人联系方式或姓名与证件号不符");
            }
        }

        StdPerson securityRepresentative = new StdPerson();
        boolean hasName = false, hasContact = false;
        if(stdDept.getFireControllerName() != null && !stdDept.getFireControllerName().equals("")) {
            securityRepresentative.setPersonName(stdDept.getFireControllerName());
            if(stdPersonService.selectStdPersonList(securityRepresentative).isEmpty())
                return warn("不存在姓名为“" + stdDept.getFireControllerName() + "”的实体人口");
            hasName = true;
        } 
        if(stdDept.getFireControllerContacts() != null && !stdDept.getFireControllerContacts().equals(""))  {
            securityRepresentative.setContacts(stdDept.getFireControllerContacts());
            if(!hasName && stdPersonService.selectStdPersonList(securityRepresentative).isEmpty())
                return warn("不存在联系方式为“" + stdDept.getFireControllerContacts() + "”的实体人口");
            hasContact = true;
        }
        if(hasName && hasContact && stdPersonService.selectStdPersonList(securityRepresentative).isEmpty()) {
            return warn("该消防负责人联系方式与姓名不符");
        }

        return toAjax(stdDeptService.insertStdDept(stdDept));
    }

    /**
     * 修改单位
     */
    @PreAuthorize("@ss.hasPermi('system:dept:edit')")
    @Log(title = "单位", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody StdDept stdDept)
    {
        if(!stdHouseService.existsByHouseId(stdDept.getStdHouseId()))
            return warn("该实体房屋不存在");
        if(!stdPersonService.existsByPersonId(stdDept.getLegalRepresentativeIdType(), stdDept.getLegalRepresentativeId())) {
            return warn("该法定代表人不存在或无证件");
        } else {
            StdPerson legalRepresentative = new StdPerson();
            legalRepresentative.setPersonIdType(stdDept.getLegalRepresentativeIdType());
            legalRepresentative.setPersonId(stdDept.getLegalRepresentativeId());
            legalRepresentative.setPersonName(stdDept.getLegalRepresentativeName());
            if(stdDept.getLegalRepresentativeContacts() != null && stdDept.getLegalRepresentativeContacts() != "") 
                legalRepresentative.setContacts(stdDept.getLegalRepresentativeContacts());
            if(stdPersonService.selectStdPersonList(legalRepresentative).isEmpty()) 
                return warn("该法定代表人联系方式或姓名与证件号不符");
        }

        if(!stdPersonService.existsByPersonId(stdDept.getLeaderIdType(), stdDept.getLeaderId())) {
            return warn("该单位负责人不存在或无证件");
        } else {
            StdPerson leader = new StdPerson();
            leader.setPersonIdType(stdDept.getLeaderIdType());
            leader.setPersonId(stdDept.getLeaderId());
            leader.setPersonName(stdDept.getLeaderName());
            if(stdDept.getLeaderContacts() != null && stdDept.getLeaderContacts() != "") 
                leader.setContacts(stdDept.getLeaderContacts());
            if(stdPersonService.selectStdPersonList(leader).isEmpty()) 
                return warn("该单位负责人联系方式或姓名与证件号不符");
        }

        if(!stdPersonService.existsByPersonId(stdDept.getSecurityRepresentativeIdType(), stdDept.getSecurityRepresentativeId())) {
            return warn("该治安负责人不存在或无证件");
        } else {
            StdPerson securityRepresentative = new StdPerson();
            securityRepresentative.setPersonIdType(stdDept.getSecurityRepresentativeIdType());
            securityRepresentative.setPersonId(stdDept.getSecurityRepresentativeId());
            securityRepresentative.setPersonName(stdDept.getSecurityRepresentativeName());
            securityRepresentative.setContacts(stdDept.getSecurityRepresentativeContacts());
            if(stdPersonService.selectStdPersonList(securityRepresentative).isEmpty()) 
                return warn("该治安负责人联系方式或姓名与证件号不符");
        }

        
        StdPerson securityRepresentative = new StdPerson();
        boolean hasName = false, hasContact = false;
        if(stdDept.getFireControllerName() != null && !stdDept.getFireControllerName().equals("")) {
            securityRepresentative.setPersonName(stdDept.getFireControllerName());
            if(stdPersonService.selectStdPersonList(securityRepresentative).isEmpty())
                return warn("不存在姓名为“" + stdDept.getFireControllerName() + "”的实体人口");
            hasName = true;
        } 
        if(stdDept.getFireControllerContacts() != null && !stdDept.getFireControllerContacts().equals(""))  {
            securityRepresentative.setContacts(stdDept.getFireControllerContacts());
            if(!hasName && stdPersonService.selectStdPersonList(securityRepresentative).isEmpty())
                return warn("不存在联系方式为“" + stdDept.getFireControllerContacts() + "”的实体人口");
            hasContact = true;
        }
        if(hasName && hasContact && stdPersonService.selectStdPersonList(securityRepresentative).isEmpty()) {
            return warn("该消防负责人联系方式与姓名不符");
        }
        

        return toAjax(stdDeptService.updateStdDept(stdDept));
    }

    /**
     * 删除单位
     */
    @PreAuthorize("@ss.hasPermi('system:dept:remove')")
    @Log(title = "单位", businessType = BusinessType.DELETE)
	@DeleteMapping("/{deptIds}")
    public AjaxResult remove(@PathVariable Long[] deptIds)
    {
        for(Long deptId : deptIds) {
            StdDept dept = stdDeptService.selectStdDeptByDeptId(deptId);
            if(dept.getPeopleNum() > 0)
                return warn("营业执照编号为" + deptId.toString() + "的单位存在从业人员，不可删除，请重新选择");
        }
        return toAjax(stdDeptService.deleteStdDeptByDeptIds(deptIds));
    }
}
