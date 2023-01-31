package com.ruoyi.common.core.domain.entity;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 行政区修改轨迹对象 gs_region_modification_history
 * 
 * @author ruoyi
 * @date 2023-01-20
 */
public class GsRegionModificationHistory extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 修改ID */
    private Long modificationId;

    /** 修改行政区ID */
    @Excel(name = "修改行政区ID")
    private Long modificationRegionId;
    private String modificationRegionName;

    /** 修改时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "修改时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date modificationDate;

    /** 旧属性值 */
    @Excel(name = "旧属性值")
    private String oldValue;

    /** 新属性值 */
    @Excel(name = "新属性值")
    private String newValue;

    public void setModificationId(Long modificationId) 
    {
        this.modificationId = modificationId;
    }

    public Long getModificationId() 
    {
        return modificationId;
    }
    public void setModificationRegionId(Long modificationRegionId) 
    {
        this.modificationRegionId = modificationRegionId;
    }

    public Long getModificationRegionId() 
    {
        return modificationRegionId;
    }
    public void setModificationRegionName(String modificationRegionName) 
    {
        this.modificationRegionName = modificationRegionName;
    }

    public String getModificationRegionName() 
    {
        return modificationRegionName;
    }
    public void setModificationDate(Date modificationDate) 
    {
        this.modificationDate = modificationDate;
    }

    public Date getModificationDate() 
    {
        return modificationDate;
    }
    public void setOldValue(String oldValue) 
    {
        this.oldValue = oldValue;
    }

    public String getOldValue() 
    {
        return oldValue;
    }
    public void setNewValue(String newValue) 
    {
        this.newValue = newValue;
    }

    public String getNewValue() 
    {
        return newValue;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("modificationId", getModificationId())
            .append("modificationRegionId", getModificationRegionId())
            .append("modificationDate", getModificationDate())
            .append("oldValue", getOldValue())
            .append("newValue", getNewValue())
            .toString();
    }
}
