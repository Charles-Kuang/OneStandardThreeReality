package com.ruoyi.common.core.domain.entity;

import java.util.List;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 单位对象 std_dept
 * 
 * @author ruoyi
 * @date 2023-01-03
 */
public class StdDept extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 单位营业执照号码 */
    private Long deptId;

    /** 行政区划 */
    private Long positionCode;

    /** 详细地址 */
    @Excel(name = "详细地址ID")
    private Long stdHouseId;

    private String stdAddress;

    /** 单位性质 */
    @Excel(name = "单位性质")
    private String deptNature;

    /** 单位名称 */
    @Excel(name = "单位名称")
    private String deptName;

    /** 所属派出所 */
    @Excel(name = "所属派出所")
    private String police;

    /** 注销情况 */
    private String delFlag;

    /** 法定代表人证件类别 */
    @Excel(name = "法定代表人证件类别")
    private String legalRepresentativeIdType;

    /** 法定代表人证件号 */
    @Excel(name = "法定代表人证件号")
    private String legalRepresentativeId;

    /** 法定代表人姓名 */
    @Excel(name = "法定代表人姓名")
    private String legalRepresentativeName;

    /** 法定代表人联系方式 */
    @Excel(name = "法定代表人联系方式")
    private String legalRepresentativeContacts;

    /** 单位负责人证件类型 */
    @Excel(name = "单位负责人证件类型")
    private String leaderIdType;

    /** 单位负责人证件号 */
    @Excel(name = "单位负责人证件号")
    private String leaderId;

    /** 单位负责人姓名 */
    @Excel(name = "单位负责人姓名")
    private String leaderName;

    /** 单位负责人联系方式 */
    @Excel(name = "单位负责人联系方式")
    private String leaderContacts;

    /** 治安负责人证件类别 */
    @Excel(name = "治安负责人证件类别")
    private String securityRepresentativeIdType;

    /** 治安负责人证件号 */
    @Excel(name = "治安负责人证件号")
    private String securityRepresentativeId;

    /** 治安负责人姓名 */
    @Excel(name = "治安负责人姓名")
    private String securityRepresentativeName;

    /** 治安负责人联系方式 */
    @Excel(name = "治安负责人联系方式")
    private String securityRepresentativeContacts;

    /** 消防负责人姓名 */
    @Excel(name = "消防负责人姓名")
    private String fireControllerName;

    /** 消防负责人联系方式 */
    @Excel(name = "消防负责人联系方式")
    private String fireControllerContacts;

    private List<StdDeptTypeDetails> deptTypeDetails;

    private String[] deptTypes;

    private String deptType;

    private Long provinceCode;
    private Long cityCode;
    private Long districtCode;
    private Long streetCode;

    private Long peopleNum;

    public Long getPeopleNum() {
        return peopleNum;
    }

    public void setPeopleNum(Long peopleNum) {
        this.peopleNum = peopleNum;
    }

    public void setDeptId(Long deptId)
    {
        this.deptId = deptId;
    }

    public Long getDeptId() 
    {
        return deptId;
    }

    public void setDeptType(String deptType) 
    {
        this.deptType = deptType;
    }

    public String getDeptType() 
    {
        return deptType;
    }

    public void setPositionCode(Long positionCode) 
    {
        this.positionCode = positionCode;
    }

    public Long getPositionCode() 
    {
        return positionCode;
    }

    public String getStdAddress() {
        return stdAddress;
    }

    public void setStdAddress(String stdAddress) {
        this.stdAddress = stdAddress;
    }

    public Long getProvinceCode() {
        return provinceCode;
    }

    public void setProvinceCode(Long provinceCode) {
        this.provinceCode = provinceCode;
    }

    public Long getCityCode() {
        return cityCode;
    }

    public void setCityCode(Long cityCode) {
        this.cityCode = cityCode;
    }

    public Long getDistrictCode() {
        return districtCode;
    }

    public void setDistrictCode(Long districtCode) {
        this.districtCode = districtCode;
    }

    public Long getStreetCode() {
        return streetCode;
    }

    public void setStreetCode(Long streetCode) {
        this.streetCode = streetCode;
    }

    public void setStdHouseId(Long stdHouseId)
    {
        this.stdHouseId = stdHouseId;
    }

    public Long getStdHouseId() 
    {
        return stdHouseId;
    }
    public void setDeptNature(String deptNature) 
    {
        this.deptNature = deptNature;
    }

    public String getDeptNature() 
    {
        return deptNature;
    }
    public void setDeptName(String deptName) 
    {
        this.deptName = deptName;
    }

    public String getDeptName() 
    {
        return deptName;
    }
    public void setPolice(String police) 
    {
        this.police = police;
    }

    public String getPolice() 
    {
        return police;
    }
    public void setDelFlag(String delFlag) 
    {
        this.delFlag = delFlag;
    }

    public String getDelFlag() 
    {
        return delFlag;
    }
    public void setLegalRepresentativeIdType(String legalRepresentativeIdType) 
    {
        this.legalRepresentativeIdType = legalRepresentativeIdType;
    }

    public String getLegalRepresentativeIdType() 
    {
        return legalRepresentativeIdType;
    }
    public void setLegalRepresentativeId(String legalRepresentativeId) 
    {
        this.legalRepresentativeId = legalRepresentativeId;
    }

    public String getLegalRepresentativeId() 
    {
        return legalRepresentativeId;
    }
    public void setLegalRepresentativeName(String legalRepresentativeName) 
    {
        this.legalRepresentativeName = legalRepresentativeName;
    }

    public String getLegalRepresentativeName() 
    {
        return legalRepresentativeName;
    }
    public void setLegalRepresentativeContacts(String legalRepresentativeContacts) 
    {
        this.legalRepresentativeContacts = legalRepresentativeContacts;
    }

    public String getLegalRepresentativeContacts() 
    {
        return legalRepresentativeContacts;
    }
    public void setLeaderIdType(String leaderIdType) 
    {
        this.leaderIdType = leaderIdType;
    }

    public String getLeaderIdType() 
    {
        return leaderIdType;
    }
    public void setLeaderId(String leaderId) 
    {
        this.leaderId = leaderId;
    }

    public String getLeaderId() 
    {
        return leaderId;
    }
    public void setLeaderName(String leaderName) 
    {
        this.leaderName = leaderName;
    }

    public String getLeaderName() 
    {
        return leaderName;
    }
    public void setLeaderContacts(String leaderContacts) 
    {
        this.leaderContacts = leaderContacts;
    }

    public String getLeaderContacts() 
    {
        return leaderContacts;
    }
    public void setSecurityRepresentativeIdType(String securityRepresentativeIdType) 
    {
        this.securityRepresentativeIdType = securityRepresentativeIdType;
    }

    public String getSecurityRepresentativeIdType() 
    {
        return securityRepresentativeIdType;
    }
    public void setSecurityRepresentativeId(String securityRepresentativeId) 
    {
        this.securityRepresentativeId = securityRepresentativeId;
    }

    public String getSecurityRepresentativeId() 
    {
        return securityRepresentativeId;
    }
    public void setSecurityRepresentativeName(String securityRepresentativeName) 
    {
        this.securityRepresentativeName = securityRepresentativeName;
    }

    public String getSecurityRepresentativeName() 
    {
        return securityRepresentativeName;
    }

    public void setSecurityRepresentativeContacts(String securityRepresentativeContacts) 
    {
        this.securityRepresentativeContacts = securityRepresentativeContacts;
    }

    public String getSecurityRepresentativeContacts() 
    {
        return securityRepresentativeContacts;
    }

    public void setFireControllerName(String fireControllerName) 
    {
        this.fireControllerName = fireControllerName;
    }

    public String getFireControllerName() 
    {
        return fireControllerName;
    }
    public void setFireControllerContacts(String fireControllerContacts) 
    {
        this.fireControllerContacts = fireControllerContacts;
    }

    public String getFireControllerContacts() 
    {
        return fireControllerContacts;
    }

    public List<StdDeptTypeDetails> getDeptTypeDetails() {
        return deptTypeDetails;
    }

    public void setDeptTypeDetails(List<StdDeptTypeDetails> deptTypeDetails) {
        this.deptTypeDetails = deptTypeDetails;
    }

    public String[] getDeptTypes() {
        return deptTypes;
    }

    public void setDeptTypes(String[] deptTypes) {
        this.deptTypes = deptTypes;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("deptId", getDeptId())
            .append("positionCode", getPositionCode())
            .append("stdHouseId", getStdHouseId())
            .append("deptNature", getDeptNature())
            .append("deptName", getDeptName())
            .append("police", getPolice())
            .append("delFlag", getDelFlag())
            .append("legalRepresentativeIdType", getLegalRepresentativeIdType())
            .append("legalRepresentativeId", getLegalRepresentativeId())
            .append("legalRepresentativeName", getLegalRepresentativeName())
            .append("legalRepresentativeContacts", getLegalRepresentativeContacts())
            .append("leaderIdType", getLeaderIdType())
            .append("leaderId", getLeaderId())
            .append("leaderName", getLeaderName())
            .append("leaderContacts", getLeaderContacts())
            .append("securityRepresentativeIdType", getSecurityRepresentativeIdType())
            .append("securityRepresentativeId", getSecurityRepresentativeId())
            .append("securityRepresentativeName", getSecurityRepresentativeName())
            .append("securityRepresentativeContacts", getSecurityRepresentativeContacts())
            .append("fireControllerName", getFireControllerName())
            .append("fireControllerContacts", getFireControllerContacts())
            .toString();
    }
}
