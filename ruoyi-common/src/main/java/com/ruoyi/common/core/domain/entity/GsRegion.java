package com.ruoyi.common.core.domain.entity;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 行政区对象 gs_region
 *
 * @author ruoyi
 * @date 2022-12-13
 */
public class GsRegion extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 行政区id */
    private Long regionId;

    /** 父行政区id */
    private Long parentCode;

    /** 父行政区名称 */
    @Excel(name = "父行政区名称")
    private String parentName;

    /** 祖级列表 */
    private String forefathers;

    private String oldForefathers;

    /** 所属公安机关 */
    @Excel(name = "所属公安机关")
    private String police;

    /** 行政区编码 */
    private Long code;

    private Long oldCode;

    /** 行政区简称 */
    @Excel(name = "行政区简称")
    private String name;

    /** 行政区全称 */
    @Excel(name = "行政区全称")
    private String fullName;

    /** 拼音首字母 */
    @Excel(name = "拼音首字母")
    private String firstLetter;

    /** 中心经度 */
    @Excel(name = "中心经度")
    private String lng;

    /** 中心纬度 */
    @Excel(name = "中心纬度")
    private String lat;

    /** 地理网格 */
    @Excel(name = "地理网格")
    private String geographicGrid;

    /** 地理实体 */
    @Excel(name = "地理实体")
    private String geographicEnity;

    /** 城乡分类 */
    @Excel(name = "城乡分类")
    private String type;

    /** 是否包含子级 */
    private Integer hasChild;

    /** 层级 */
    private Long level;

    private Long provinceCode;

    private Long cityCode;

    private Long districtCode;

    private Long streetCode;

    private Long neighborhoodCode;

    /** 创建用户id */
    private Long createUserId;

    /** 创建用户名称 */
    @Excel(name = "创建用户名称")
    private String createUser;

    /** 创建用户联系电话 */
    @Excel(name = "创建用户联系电话")
    private String createUserPhone;

    /** 最后更新时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Excel(name = "最后更新时间", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss")
    private Date lastUpdateTime;

    /** 最后更新人 */
    @Excel(name = "最后更新人")
    private String lastUpdateUser;

    /** 最后更新人id */
    private Long lastUpdateUserId;

    /** 最后更新人联系电话 */
    @Excel(name = "最后更新人联系电话")
    private String lastUpdateUserPhone;

    /** 审核人 */
    @Excel(name = "审核人")
    private String auditUser;

    /** 审核人id */
    private Long auditUserId;

    /** 审核状态 */
    @Excel(name = "审核状态")
    private Long auditState;

    /** 审核人电话 */
    @Excel(name = "审核人电话")
    private String auditUserPhone;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Excel(name = "最后更新时间", width = 30, dateFormat = "yyyy-MM-dd HH:mm:ss")
    private Date auditTime;

    /** 提交状态 */
    @Excel(name = "提交状态")
    private String commitStatus;

    private String deprecated;

    public void setRegionId(Long regionId)
    {
        this.regionId = regionId;
    }

    public Long getRegionId()
    {
        return regionId;
    }
    public void setParentCode(Long parentCode)
    {
        this.parentCode = parentCode;
    }

    public Long getParentCode()
    {
        return parentCode;
    }
    public void setParentName(String parentName)
    {
        this.parentName = parentName;
    }

    public String getParentName()
    {
        return parentName;
    }
    public void setForefathers(String forefathers)
    {
        this.forefathers = forefathers;
    }

    public String getForefathers()
    {
        return forefathers;
    }
    public void setPolice(String police)
    {
        this.police = police;
    }

    public String getOldForefathers() {
        return oldForefathers;
    }

    public void setOldForefathers(String oldForefathers) {
        this.oldForefathers = oldForefathers;
    }

    public String getPolice()
    {
        return police;
    }
    public void setCode(Long code)
    {
        this.code = code;
    }

    public Long getCode()
    {
        return code;
    }
    public void setName(String name)
    {
        this.name = name;
    }

    public String getName()
    {
        return name;
    }
    public void setFullName(String fullName)
    {
        this.fullName = fullName;
    }

    public Long getOldCode() {
        return oldCode;
    }

    public void setOldCode(Long oldCode) {
        this.oldCode = oldCode;
    }

    public String getFullName()
    {
        return fullName;
    }
    public void setFirstLetter(String firstLetter)
    {
        this.firstLetter = firstLetter;
    }

    public String getFirstLetter()
    {
        return firstLetter;
    }
    public void setLng(String lng)
    {
        this.lng = lng;
    }

    public String getLng()
    {
        return lng;
    }
    public void setLat(String lat)
    {
        this.lat = lat;
    }

    public String getLat()
    {
        return lat;
    }
    public void setGeographicGrid(String geographicGrid)
    {
        this.geographicGrid = geographicGrid;
    }

    public String getGeographicGrid()
    {
        return geographicGrid;
    }
    public void setGeographicEnity(String geographicEnity)
    {
        this.geographicEnity = geographicEnity;
    }

    public String getGeographicEnity()
    {
        return geographicEnity;
    }
    public void setType(String type)
    {
        this.type = type;
    }

    public String getType()
    {
        return type;
    }
    public void setHasChild(Integer hasChild)
    {
        this.hasChild = hasChild;
    }

    public Integer getHasChild()
    {
        return hasChild;
    }
    public void setLevel(Long level)
    {
        this.level = level;
    }

    public Long getLevel()
    {
        return level;
    }
    public void setCreateUserId(Long createUserId)
    {
        this.createUserId = createUserId;
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

    public Long getNeighborhoodCode() {
        return neighborhoodCode;
    }

    public void setNeighborhoodCode(Long neighborhoodCode) {
        this.neighborhoodCode = neighborhoodCode;
    }

    public Long getCreateUserId()
    {
        return createUserId;
    }
    public void setCreateUser(String createUser)
    {
        this.createUser = createUser;
    }

    public String getCreateUser()
    {
        return createUser;
    }
    public void setCreateUserPhone(String createUserPhone)
    {
        this.createUserPhone = createUserPhone;
    }

    public String getCreateUserPhone()
    {
        return createUserPhone;
    }
    public void setLastUpdateTime(Date lastUpdateTime)
    {
        this.lastUpdateTime = lastUpdateTime;
    }

    public Date getLastUpdateTime()
    {
        return lastUpdateTime;
    }
    public void setLastUpdateUser(String lastUpdateUser)
    {
        this.lastUpdateUser = lastUpdateUser;
    }

    public String getLastUpdateUser()
    {
        return lastUpdateUser;
    }
    public void setLastUpdateUserId(Long lastUpdateUserId)
    {
        this.lastUpdateUserId = lastUpdateUserId;
    }

    public Long getLastUpdateUserId()
    {
        return lastUpdateUserId;
    }
    public void setLastUpdateUserPhone(String lastUpdateUserPhone)
    {
        this.lastUpdateUserPhone = lastUpdateUserPhone;
    }

    public String getLastUpdateUserPhone()
    {
        return lastUpdateUserPhone;
    }
    public void setAuditUser(String auditUser)
    {
        this.auditUser = auditUser;
    }

    public String getAuditUser()
    {
        return auditUser;
    }
    public void setAuditUserId(Long auditUserId)
    {
        this.auditUserId = auditUserId;
    }

    public Date getAuditTime() {
        return auditTime;
    }

    public void setAuditTime(Date auditTime) {
        this.auditTime = auditTime;
    }
    public Long getAuditUserId()
    {
        return auditUserId;
    }
    public void setAuditState(Long auditState)
    {
        this.auditState = auditState;
    }

    public Long getAuditState()
    {
        return auditState;
    }
    public void setAuditUserPhone(String auditUserPhone)
    {
        this.auditUserPhone = auditUserPhone;
    }

    public String getAuditUserPhone()
    {
        return auditUserPhone;
    }
    public void setCommitStatus(String commitStatus)
    {
        this.commitStatus = commitStatus;
    }

    public String getCommitStatus()
    {
        return commitStatus;
    }

    public String getDeprecated() {
        return deprecated;
    }

    public void setDeprecated(String deprecated) {
        this.deprecated = deprecated;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
                .append("regionId", getRegionId())
                .append("parentCode", getParentCode())
                .append("parentName", getParentName())
                .append("forefathers", getForefathers())
                .append("police", getPolice())
                .append("code", getCode())
                .append("name", getName())
                .append("fullName", getFullName())
                .append("firstLetter", getFirstLetter())
                .append("lng", getLng())
                .append("lat", getLat())
                .append("geographicGrid", getGeographicGrid())
                .append("geographicEnity", getGeographicEnity())
                .append("type", getType())
                .append("hasChild", getHasChild())
                .append("level", getLevel())
                .append("createTime", getCreateTime())
                .append("createUserId", getCreateUserId())
                .append("createUser", getCreateUser())
                .append("createUserPhone", getCreateUserPhone())
                .append("lastUpdateTime", getLastUpdateTime())
                .append("lastUpdateUser", getLastUpdateUser())
                .append("lastUpdateUserId", getLastUpdateUserId())
                .append("lastUpdateUserPhone", getLastUpdateUserPhone())
                .append("auditUser", getAuditUser())
                .append("auditUserId", getAuditUserId())
                .append("auditState", getAuditState())
                .append("auditUserPhone", getAuditUserPhone())
                .append("commitStatus", getCommitStatus())
                .append("deprecated", getDeprecated())
                .toString();
    }
}
