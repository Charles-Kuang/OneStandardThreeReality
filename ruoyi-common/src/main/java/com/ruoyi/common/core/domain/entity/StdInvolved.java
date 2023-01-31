package com.ruoyi.common.core.domain.entity;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 主体涉案对象 std_involved
 * 
 * @author ruoyi
 * @date 2023-01-18
 */
public class StdInvolved extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 涉案ID */
    @Excel(name = "涉案ID")
    private Long involvedId;

    /** 涉案主体类别 */
    private String involvedEntityType;
    private String involvedEntityName;

    /** 涉案主体ID */
    @Excel(name = "涉案主体ID")
    private Long involvedEntityId;

    /** 涉案日期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "涉案日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date caseDate;

    /** 涉案类别 */
    @Excel(name = "涉案类别")
    private String caseType;

    /** 案件名称 */
    @Excel(name = "案件名称")
    private String caseName;

    /** 危害程度 */
    @Excel(name = "危害程度")
    private String dangerDegree;

    private String stdAddress;

    public void setInvolvedId(Long involvedId) 
    {
        this.involvedId = involvedId;
    }

    public Long getInvolvedId() 
    {
        return involvedId;
    }
    public void setInvolvedEntityType(String involvedEntityType) 
    {
        this.involvedEntityType = involvedEntityType;
    }

    public String getInvolvedEntityType() 
    {
        return involvedEntityType;
    }
    public void setInvolvedEntityId(Long involvedEntityId) 
    {
        this.involvedEntityId = involvedEntityId;
    }

    public Long getInvolvedEntityId() 
    {
        return involvedEntityId;
    }
    public void setInvolvedEntityName(String involvedEntityName) 
    {
        this.involvedEntityName = involvedEntityName;
    }

    public String getInvolvedEntityName() 
    {
        return involvedEntityName;
    }
    public void setCaseDate(Date caseDate) 
    {
        this.caseDate = caseDate;
    }

    public Date getCaseDate() 
    {
        return caseDate;
    }
    public void setCaseType(String caseType) 
    {
        this.caseType = caseType;
    }

    public String getCaseType() 
    {
        return caseType;
    }
    public void setCaseName(String caseName) 
    {
        this.caseName = caseName;
    }

    public String getCaseName() 
    {
        return caseName;
    }
    public void setDangerDegree(String dangerDegree) 
    {
        this.dangerDegree = dangerDegree;
    }

    public String getDangerDegree() 
    {
        return dangerDegree;
    }

    public void setStdAddress(String stdAddress) 
    {
        this.stdAddress = stdAddress;
    }

    public String getStdAddress() 
    {
        return stdAddress;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("involvedId", getInvolvedId())
            .append("involvedEntityType", getInvolvedEntityType())
            .append("involvedEntityId", getInvolvedEntityId())
            .append("caseDate", getCaseDate())
            .append("caseType", getCaseType())
            .append("caseName", getCaseName())
            .append("dangerDegree", getDangerDegree())
            .append("stdAddress", getStdAddress())
            .toString();
    }
}
