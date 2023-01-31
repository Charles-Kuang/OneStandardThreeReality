package com.ruoyi.common.core.domain.entity;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 房屋日常检查对象 std_house_daily_check
 * 
 * @author ruoyi
 * @date 2023-01-17
 */
public class StdHouseDailyCheck extends BaseEntity
{
    private static final long serialVersionUID = 1L;


    /** 检查ID */
    private Long checkId;

    /** 房屋ID */
    @Excel(name = "房屋ID")
    private Long houseId;

    private String StdAddress;

    /** 检查日期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "检查日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date checkDate;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date manageDate;

    private String inspectorIdType;

    /** 检查人 */
    @Excel(name = "检查人证件号")
    private String inspectorId;

    private String inspectorName;

    /** 危险类别 */
    @Excel(name = "危险类别")
    private String dangerType;

    /** 检查类别 */
    @Excel(name = "检查类别")
    private String checkType;

    /** 检查结果 */
    @Excel(name = "检查结果")
    private String checkResult;

    /** 处理结果 */
    @Excel(name = "处理结果")
    private String manageResult;

    public void setCheckId(Long checkId) 
    {
        this.checkId = checkId;
    }

    public Long getCheckId() 
    {
        return checkId;
    }
    public void setHouseId(Long houseId) 
    {
        this.houseId = houseId;
    }

    public Long getHouseId() 
    {
        return houseId;
    }
    public void setCheckDate(Date checkDate) 
    {
        this.checkDate = checkDate;
    }

    public Date getCheckDate() 
    {
        return checkDate;
    }
    public void setManageDate(Date manageDate) 
    {
        this.manageDate = manageDate;
    }

    public Date getManageDate() 
    {
        return manageDate;
    }
    public void setInspectorId(String inspectorId)
    {
        this.inspectorId = inspectorId;
    }

    public String getInspectorId() 
    {
        return inspectorId;
    }
    public void setDangerType(String dangerType) 
    {
        this.dangerType = dangerType;
    }

    public String getDangerType() 
    {
        return dangerType;
    }
    public void setCheckType(String checkType) 
    {
        this.checkType = checkType;
    }

    public String getCheckType() 
    {
        return checkType;
    }
    public void setCheckResult(String checkResult) 
    {
        this.checkResult = checkResult;
    }

    public String getCheckResult() 
    {
        return checkResult;
    }
    public void setManageResult(String manageResult) 
    {
        this.manageResult = manageResult;
    }

    public String getManageResult() 
    {
        return manageResult;
    }


    public String getStdAddress() {
        return StdAddress;
    }

    public void setStdAddress(String stdAddress) {
        StdAddress = stdAddress;
    }

    public String getInspectorName() {
        return inspectorName;
    }

    public void setInspectorName(String inspectorName) {
        this.inspectorName = inspectorName;
    }

    public void setInspectorIdType(String inspectorIdType) 
    {
        this.inspectorIdType = inspectorIdType;
    }

    public String getInspectorIdType() 
    {
        return inspectorIdType;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("checkId", getCheckId())
            .append("houseId", getHouseId())
            .append("checkDate", getCheckDate())
            .append("manageDate", getManageDate())
            .append("inspectorId", getInspectorId())
            .append("dangerType", getDangerType())
            .append("checkType", getCheckType())
            .append("checkResult", getCheckResult())
            .append("manageResult", getManageResult())
            .append("remark", getRemark())
            .toString();
    }
}
