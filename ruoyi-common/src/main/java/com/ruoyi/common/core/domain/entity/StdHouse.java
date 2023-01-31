package com.ruoyi.common.core.domain.entity;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 门牌对象 std_house
 *
 * @author ruoyi
 * @date 2022-12-28
 */
public class StdHouse extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 房屋id */
    private Long houseId;

    /** 所属居委会code */
    private Long positionCode;

    private Long ProvinceCode;
    private Long cityCode;
    private Long districtCode;
    private Long streetCode;

    /** 所属小区名 */
    @Excel(name = "所属小区名")
    private String residentialVillageName;

    /** 门牌号 */
    @Excel(name = "门牌号")
    private Long doorNumber;

    /** 房间号 */
    @Excel(name = "房间号")
    private Long houseNumber;

    /** 流管编号 */
    @Excel(name = "流管编号")
    private Long controlNumber;

    /** 房屋用途 */
    @Excel(name = "房屋用途")
    private String houseUsage;

    @Excel(name = "户主证件类型")
    private String hostIdType;

    /** 户主身份证号 */
    @Excel(name = "户主证件号码")
    private String hostId;

    /** 标准地址 */
    @Excel(name = "标准地址")
    private String standardAddress;

    /** 房屋名称 */
    @Excel(name = "房屋名称")
    private String houseName;

    /** 房屋类型 */
    @Excel(name = "房屋类型")
    private String houseType;

    /** 房屋分类 */
    @Excel(name = "房屋分类")
    private String controlDegree;

    /** 产权性质 */
    @Excel(name = "产权性质")
    private String propertyRight;

    /** 重点关注原因 */
    @Excel(name = "重点关注原因")
    private String controlReason;

    /** 房屋面积 */
    @Excel(name = "房屋面积")
    private Float houseArea;

    /** 治安责任书签署情况 */
    @Excel(name = "治安责任书签署情况")
    private String responsibilitySignature;

    /** 房屋数量 */
    @Excel(name = "房屋数量")
    private Long roomQuantity;

    /** 托管情况 */
    @Excel(name = "托管情况")
    private String houseDeposit;

    public void setHouseId(Long houseId)
    {
        this.houseId = houseId;
    }

    public Long getHouseId()
    {
        return houseId;
    }
    public void setPositionCode(Long positionCode)
    {
        this.positionCode = positionCode;
    }

    public Long getPositionCode()
    {
        return positionCode;
    }
    public void setResidentialVillageName(String residentialVillageName)
    {
        this.residentialVillageName = residentialVillageName;
    }

    public String getResidentialVillageName()
    {
        return residentialVillageName;
    }
    public void setDoorNumber(Long doorNumber)
    {
        this.doorNumber = doorNumber;
    }

    public Long getDoorNumber()
    {
        return doorNumber;
    }
    public void setHouseNumber(Long houseNumber)
    {
        this.houseNumber = houseNumber;
    }

    public Long getHouseNumber()
    {
        return houseNumber;
    }
    public void setControlNumber(Long controlNumber)
    {
        this.controlNumber = controlNumber;
    }

    public Long getControlNumber()
    {
        return controlNumber;
    }
    public void setHouseUsage(String houseUsage)
    {
        this.houseUsage = houseUsage;
    }

    public String getHouseUsage()
    {
        return houseUsage;
    }
    public void setHostId(String hostId)
    {
        this.hostId = hostId;
    }

    public String getHostId()
    {
        return hostId;
    }
    public void setStandardAddress(String standardAddress)
    {
        this.standardAddress = standardAddress;
    }

    public String getStandardAddress()
    {
        return standardAddress;
    }
    public void setHouseName(String houseName)
    {
        this.houseName = houseName;
    }

    public String getHouseName()
    {
        return houseName;
    }
    public void setHouseType(String houseType)
    {
        this.houseType = houseType;
    }

    public String getHouseType()
    {
        return houseType;
    }
    public void setControlDegree(String controlDegree)
    {
        this.controlDegree = controlDegree;
    }

    public String getControlDegree()
    {
        return controlDegree;
    }
    public void setPropertyRight(String propertyRight)
    {
        this.propertyRight = propertyRight;
    }

    public String getPropertyRight()
    {
        return propertyRight;
    }
    public void setControlReason(String controlReason)
    {
        this.controlReason = controlReason;
    }

    public String getControlReason()
    {
        return controlReason;
    }
    public void setHouseArea(Float houseArea)
    {
        this.houseArea = houseArea;
    }

    public Float getHouseArea()
    {
        return houseArea;
    }
    public void setResponsibilitySignature(String responsibilitySignature)
    {
        this.responsibilitySignature = responsibilitySignature;
    }

    public String getResponsibilitySignature()
    {
        return responsibilitySignature;
    }
    public void setRoomQuantity(Long roomQuantity)
    {
        this.roomQuantity = roomQuantity;
    }

    public Long getRoomQuantity()
    {
        return roomQuantity;
    }
    public void setHouseDeposit(String houseDeposit)
    {
        this.houseDeposit = houseDeposit;
    }

    public String getHouseDeposit()
    {
        return houseDeposit;
    }

    public Long getProvinceCode() {
        return ProvinceCode;
    }

    public void setProvinceCode(Long provinceCode) {
        ProvinceCode = provinceCode;
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

    public String getHostIdType() {
        return hostIdType;
    }

    public void setHostIdType(String hostIdType) {
        this.hostIdType = hostIdType;
    }

    public Long getStreetCode() {
        return streetCode;
    }

    public void setStreetCode(Long streetCode) {
        this.streetCode = streetCode;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
                .append("houseId", getHouseId())
                .append("positionCode", getPositionCode())
                .append("residentialVillageName", getResidentialVillageName())
                .append("doorNumber", getDoorNumber())
                .append("houseNumber", getHouseNumber())
                .append("controlNumber", getControlNumber())
                .append("houseUsage", getHouseUsage())
                .append("hostId", getHostId())
                .append("standardAddress", getStandardAddress())
                .append("houseName", getHouseName())
                .append("houseType", getHouseType())
                .append("controlDegree", getControlDegree())
                .append("propertyRight", getPropertyRight())
                .append("controlReason", getControlReason())
                .append("houseArea", getHouseArea())
                .append("responsibilitySignature", getResponsibilitySignature())
                .append("roomQuantity", getRoomQuantity())
                .append("houseDeposit", getHouseDeposit())
                .append("remark", getRemark())
                .toString();
    }
}
