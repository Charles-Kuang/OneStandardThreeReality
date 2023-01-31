package com.ruoyi.web.controller.standard;

import java.util.List;
import javax.servlet.http.HttpServletResponse;

import com.ruoyi.common.core.domain.entity.StdHouse;
import com.ruoyi.common.core.domain.entity.StdDept;
import com.ruoyi.system.service.IStdDeptService;
import com.ruoyi.system.service.IStdHouseService;
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
import com.ruoyi.common.core.domain.entity.StdPerson;
import com.ruoyi.system.service.IStdPersonService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 个体Controller
 * 
 * @author ruoyi
 * @date 2022-12-29
 */
@RestController
@RequestMapping("/standard/person")
public class StdPersonController extends BaseController
{
    @Autowired
    private IStdPersonService stdPersonService;

    @Autowired
    private IStdHouseService stdHouseService;

    @Autowired
    private IStdDeptService stdDeptService;

    /**
     * 查询个体列表
     */
    @PreAuthorize("@ss.hasPermi('system:person:list')")
    @GetMapping("/list")
    public TableDataInfo list(StdPerson stdPerson)
    {
        startPage();
        List<StdPerson> list = stdPersonService.selectStdPersonList(stdPerson);
        return getDataTable(list);
    }

    /**
     * 导出个体列表
     */
    @PreAuthorize("@ss.hasPermi('system:person:export')")
    @Log(title = "个体", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, StdPerson stdPerson)
    {
        List<StdPerson> list = stdPersonService.selectStdPersonList(stdPerson);
        ExcelUtil<StdPerson> util = new ExcelUtil<StdPerson>(StdPerson.class);
        util.exportExcel(response, list, "个体数据");
    }

    /**
     * 获取个体详细信息
     */
    @PreAuthorize("@ss.hasPermi('system:person:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(stdPersonService.selectStdPersonById(id));
    }

    /**
     * 新增个体
     */
    @PreAuthorize("@ss.hasPermi('system:person:add')")
    @Log(title = "个体", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody StdPerson stdPerson)
    {
        if(stdPerson.getPersonIdType().equals("2")) {
            stdPerson.setPersonId("0");
        }

        if(!stdPerson.getPersonIdType().equals("2") && stdPersonService.existsByPersonId(stdPerson.getPersonIdType(), stdPerson.getPersonId())) {
            return warn("该实口已存在");
        }
        if(stdPerson.getResidenceStdHouseId() != null) {
            if(!stdHouseService.existsByHouseId(stdPerson.getResidenceStdHouseId()))
                return warn("该户籍房屋不存在");
            StdHouse residenceStdHouse = stdHouseService.selectStdHouseByHouseId(stdPerson.getResidenceStdHouseId());
            stdPerson.setResidenceStdAddress(residenceStdHouse.getStandardAddress());
            stdPerson.setResidencePositionCode(residenceStdHouse.getPositionCode());
            stdPerson.setRelatedToStdPosition("Y");
        }
        if(!stdHouseService.existsByHouseId(stdPerson.getCurrentStdHouseId()))
            return warn("该现住房屋不存在");
        if(stdPerson.getDeptId() != null && !stdDeptService.existsByDeptId(stdPerson.getDeptId()))
            return warn("该公司营业执照编号不存在");
        StdHouse currentStdHouse = stdHouseService.selectStdHouseByHouseId(stdPerson.getCurrentStdHouseId());
        stdPerson.setCurrentStdAddress(currentStdHouse.getStandardAddress());
        stdPerson.setCurrentPositionCode(currentStdHouse.getPositionCode());
        return toAjax(stdPersonService.insertStdPerson(stdPerson));
    }

    /**
     * 修改个体
     */
    @PreAuthorize("@ss.hasPermi('system:person:edit')")
    @Log(title = "个体", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody StdPerson stdPerson)
    {
        if(stdPerson.getResidenceStdHouseId() != null) {
            if(!stdHouseService.existsByHouseId(stdPerson.getResidenceStdHouseId()))
                return warn("该户籍房屋不存在");
            StdHouse residenceStdHouse = stdHouseService.selectStdHouseByHouseId(stdPerson.getResidenceStdHouseId());
            stdPerson.setResidenceStdAddress(residenceStdHouse.getStandardAddress());
            stdPerson.setResidencePositionCode(residenceStdHouse.getPositionCode());
            stdPerson.setRelatedToStdPosition("Y");
        }
        if(!stdHouseService.existsByHouseId(stdPerson.getCurrentStdHouseId()))
            return warn("该现住房屋不存在");
        if(stdPerson.getDeptId() != null && !stdDeptService.existsByDeptId(stdPerson.getDeptId()))
            return warn("该公司营业执照编号不存在");
        StdHouse currentStdHouse = stdHouseService.selectStdHouseByHouseId(stdPerson.getCurrentStdHouseId());
        stdPerson.setCurrentStdAddress(currentStdHouse.getStandardAddress());
        stdPerson.setCurrentPositionCode(currentStdHouse.getPositionCode());
        if(stdPerson.getPersonIdType() == "2") 
            stdPerson.setPersonId("0");
        // 联系方式发生改变 则连着dept的一起改
        if(!stdPersonService.selectStdPersonById(stdPerson.getId()).getContacts().equals(stdPerson.getContacts())) {
            StdDept stdDept = new StdDept();
            String oldContacts = stdPersonService.selectStdPersonById(stdPerson.getId()).getContacts();
            String newContacts = stdPerson.getContacts();

            stdDept.setLegalRepresentativeContacts(oldContacts);
            stdDept.setLegalRepresentativeIdType(stdPerson.getPersonIdType());
            stdDept.setLegalRepresentativeId(stdPerson.getPersonId());
            List<StdDept> depts = stdDeptService.selectStdDeptList(stdDept);

            if(!depts.isEmpty()) {
                for (StdDept singleDept : depts) {
                    singleDept.setLegalRepresentativeContacts(newContacts);
                    stdDeptService.updateStdDept(singleDept);
                }
            }

            stdDept.setLegalRepresentativeContacts("");
            stdDept.setLegalRepresentativeIdType("");
            stdDept.setLegalRepresentativeId("");
            stdDept.setLeaderContacts(oldContacts);
            stdDept.setLeaderIdType(stdPerson.getPersonIdType());
            stdDept.setLeaderId(stdPerson.getPersonId());
            depts = stdDeptService.selectStdDeptList(stdDept);
            
            if(!depts.isEmpty()) {
                for (StdDept singleDept : depts) {
                    singleDept.setLeaderContacts(newContacts);
                    stdDeptService.updateStdDept(singleDept);
                }
            }

            stdDept.setLeaderContacts("");
            stdDept.setLeaderIdType("");
            stdDept.setLeaderId("");
            stdDept.setSecurityRepresentativeContacts(oldContacts);
            stdDept.setSecurityRepresentativeIdType(stdPerson.getPersonIdType());
            stdDept.setSecurityRepresentativeId(stdPerson.getPersonId());
            depts = stdDeptService.selectStdDeptList(stdDept);
            
            if(!depts.isEmpty()) {
                for (StdDept singleDept : depts) {
                    singleDept.setSecurityRepresentativeContacts(newContacts);
                    stdDeptService.updateStdDept(singleDept);
                }
            }

            stdDept.setSecurityRepresentativeContacts("");
            stdDept.setSecurityRepresentativeIdType("");
            stdDept.setSecurityRepresentativeId("");
            stdDept.setFireControllerContacts(oldContacts);
            stdDept.setFireControllerName(stdPerson.getPersonName());
            depts = stdDeptService.selectStdDeptList(stdDept);
            
            if(!depts.isEmpty()) {
                for (StdDept singleDept : depts) {
                    singleDept.setFireControllerContacts(newContacts);
                    stdDeptService.updateStdDept(singleDept);
                }
            }
        }
        if(stdPerson.getResidenceStdHouseId() == null)
            stdPerson.setRelatedToStdPosition("N");

        return toAjax(stdPersonService.updateStdPerson(stdPerson, stdPersonService.selectStdPersonById(stdPerson.getId()).getCurrentStdHouseId()));
    }

    /**
     * 删除个体
     */
    @PreAuthorize("@ss.hasPermi('system:person:remove')")
    @Log(title = "个体", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        for(Long id : ids) {
            String person_id = stdPersonService.selectStdPersonById(id).getPersonId();
            String person_id_type = stdPersonService.selectStdPersonById(id).getPersonIdType();
            if(stdHouseService.existsByHostId(person_id_type, person_id)) {
                return warn("证件号为" + person_id + "的人口下存在房屋，不可删除");
            }
        }
        return toAjax(stdPersonService.deleteStdPersonByIds(ids));
    }
}
