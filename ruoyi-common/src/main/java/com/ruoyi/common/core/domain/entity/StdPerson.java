package com.ruoyi.common.core.domain.entity;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 个体对象 std_person
 * 
 * @author ruoyi
 * @date 2022-12-29
 */
public class StdPerson extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 标识UID */
    private Long id;

    /** 证件类型 */
    @Excel(name = "证件类型")
    private String personIdType;

    /** 证件号码(id) */
    @Excel(name = "证件号码(id)")
    private String personId;

    /** 个体姓名 */
    @Excel(name = "个体姓名")
    private String personName;

    /** 个体性别 */
    @Excel(name = "个体性别")
    private String personGender;

    /** 个体英文名 */
    @Excel(name = "个体英文名")
    private String personEnglishName;

    /** 所属派出所 */
    @Excel(name = "所属派出所")
    private String police;

    /** 人员类别 */
    @Excel(name = "人员类别")
    private String personType;

    /** 是否已关联标准地址 */
    @Excel(name = "是否已关联标准地址")
    private String relatedToStdPosition;

    /** 户籍行政区划 */
    private Long residencePositionCode;

    private String residenceStdAddress;
    
    /** 户籍地址 */
    private Long residenceStdHouseId;
    
    /** 目前住址行政区划 */
    private Long currentPositionCode;
    
    private String currentStdAddress;
    
    /** 目前住址 */
    private Long currentStdHouseId;


    private Long currentProvinceCode;
    private Long currentCityCode;
    private Long currentDistrictCode;
    private Long currentStreetCode;


    private Long residenceProvinceCode;
    private Long residenceCityCode;
    private Long residenceDistrictCode;
    private Long residenceStreetCode;

    /** 民族 */
    @Excel(name = "民族")
    private String nation;

    private Long deptId;

    private Long oldDeptId;

    private String deptName;

    /** 出生日期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "出生日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date birthday;

    /** 职业 */
    @Excel(name = "职业")
    private String occupation;

    /** 婚姻状况 */
    @Excel(name = "婚姻状况")
    private String married;

    /** 学历 */
    @Excel(name = "学历")
    private String degree;

    /** 联系方式 */
    @Excel(name = "联系方式")
    private String contacts;

    /** 暂住理由 */
    @Excel(name = "暂住理由")
    private String tempResidenceReason;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setPersonIdType(String personIdType) 
    {
        this.personIdType = personIdType;
    }

    public String getPersonIdType() 
    {
        return personIdType;
    }
    public void setPersonId(String personId) 
    {
        this.personId = personId;
    }

    public String getPersonId() 
    {
        return personId;
    }
    public void setPersonName(String personName) 
    {
        this.personName = personName;
    }

    public String getPersonName() 
    {
        return personName;
    }
    public void setPersonGender(String personGender) 
    {
        this.personGender = personGender;
    }

    public String getPersonGender() 
    {
        return personGender;
    }
    public void setPersonEnglishName(String personEnglishName) 
    {
        this.personEnglishName = personEnglishName;
    }

    public String getPersonEnglishName() 
    {
        return personEnglishName;
    }
    public void setPolice(String police) 
    {
        this.police = police;
    }

    public String getPolice() 
    {
        return police;
    }
    public void setPersonType(String personType) 
    {
        this.personType = personType;
    }

    public String getPersonType() 
    {
        return personType;
    }
    public void setRelatedToStdPosition(String relatedToStdPosition) 
    {
        this.relatedToStdPosition = relatedToStdPosition;
    }

    public String getRelatedToStdPosition() 
    {
        return relatedToStdPosition;
    }
    public void setResidenceStdHouseId(Long residenceStdHouseId) 
    {
        this.residenceStdHouseId = residenceStdHouseId;
    }

    public Long getResidenceStdHouseId() 
    {
        return residenceStdHouseId;
    }
    public void setCurrentStdHouseId(Long currentStdHouseId) 
    {
        this.currentStdHouseId = currentStdHouseId;
    }

    public Long getCurrentStdHouseId() 
    {
        return currentStdHouseId;
    }
    public void setNation(String nation) 
    {
        this.nation = nation;
    }

    public String getNation() 
    {
        return nation;
    }
    public void setBirthday(Date birthday) 
    {
        this.birthday = birthday;
    }

    public Date getBirthday() 
    {
        return birthday;
    }
    public void setOccupation(String occupation) 
    {
        this.occupation = occupation;
    }

    public String getOccupation() 
    {
        return occupation;
    }
    public void setMarried(String married) 
    {
        this.married = married;
    }

    public String getMarried() 
    {
        return married;
    }
    public void setDegree(String degree) 
    {
        this.degree = degree;
    }

    public String getDegree() 
    {
        return degree;
    }
    public void setContacts(String contacts) 
    {
        this.contacts = contacts;
    }

    public String getContacts() 
    {
        return contacts;
    }
    public void setTempResidenceReason(String tempResidenceReason) 
    {
        this.tempResidenceReason = tempResidenceReason;
    }

    public String getTempResidenceReason() 
    {
        return tempResidenceReason;
    }

    public Long getResidencePositionCode() {
        return residencePositionCode;
    }

    public void setResidencePositionCode(Long residencePositionCode) {
        this.residencePositionCode = residencePositionCode;
    }

    public Long getCurrentPositionCode() {
        return currentPositionCode;
    }

    public void setCurrentPositionCode(Long currentPositionCode) {
        this.currentPositionCode = currentPositionCode;
    }

    public Long getCurrentProvinceCode() {
        return currentProvinceCode;
    }

    public void setCurrentProvinceCode(Long currentProvinceCode) {
        this.currentProvinceCode = currentProvinceCode;
    }

    public Long getCurrentCityCode() {
        return currentCityCode;
    }

    public void setCurrentCityCode(Long currentCityCode) {
        this.currentCityCode = currentCityCode;
    }

    public Long getCurrentDistrictCode() {
        return currentDistrictCode;
    }

    public void setCurrentDistrictCode(Long currentDistrictCode) {
        this.currentDistrictCode = currentDistrictCode;
    }

    public Long getCurrentStreetCode() {
        return currentStreetCode;
    }

    public void setCurrentStreetCode(Long currentStreetCode) {
        this.currentStreetCode = currentStreetCode;
    }

    public Long getResidenceProvinceCode() {
        return residenceProvinceCode;
    }

    public void setResidenceProvinceCode(Long residenceProvinceCode) {
        this.residenceProvinceCode = residenceProvinceCode;
    }

    public Long getResidenceCityCode() {
        return residenceCityCode;
    }

    public void setResidenceCityCode(Long residenceCityCode) {
        this.residenceCityCode = residenceCityCode;
    }

    public Long getResidenceDistrictCode() {
        return residenceDistrictCode;
    }

    public void setResidenceDistrictCode(Long residenceDistrictCode) {
        this.residenceDistrictCode = residenceDistrictCode;
    }

    public Long getResidenceStreetCode() {
        return residenceStreetCode;
    }

    public void setResidenceStreetCode(Long residenceStreetCode) {
        this.residenceStreetCode = residenceStreetCode;
    }

    public String getResidenceStdAddress() {
        return residenceStdAddress;
    }

    public void setResidenceStdAddress(String residenceStdAddress) {
        this.residenceStdAddress = residenceStdAddress;
    }

    public String getCurrentStdAddress() {
        return currentStdAddress;
    }

    public void setCurrentStdAddress(String currentStdAddress) {
        this.currentStdAddress = currentStdAddress;
    }

    public Long getDeptId() {
        return deptId;
    }

    public void setDeptId(Long deptId) {
        this.deptId = deptId;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public Long getOldDeptId() {
        return oldDeptId;
    }

    public void setOldDeptId(Long oldDeptId) {
        this.oldDeptId = oldDeptId;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("personIdType", getPersonIdType())
            .append("personId", getPersonId())
            .append("personName", getPersonName())
            .append("personGender", getPersonGender())
            .append("personEnglishName", getPersonEnglishName())
            .append("police", getPolice())
            .append("personType", getPersonType())
            .append("relatedToStdPosition", getRelatedToStdPosition())
            .append("residencePositionCode", getResidencePositionCode())
            .append("residenceStdHouseId", getResidenceStdHouseId())
            .append("currentPositionCode", getCurrentPositionCode())
            .append("currentStdHouseId", getCurrentStdHouseId())
            .append("nation", getNation())
            .append("birthday", getBirthday())
            .append("occupation", getOccupation())
            .append("married", getMarried())
            .append("degree", getDegree())
            .append("contacts", getContacts())
            .append("tempResidenceReason", getTempResidenceReason())
            .toString();
    }
}
