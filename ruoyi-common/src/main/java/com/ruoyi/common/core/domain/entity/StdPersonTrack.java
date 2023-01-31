package com.ruoyi.common.core.domain.entity;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 人口居住轨迹对象 std_person_track
 * 
 * @author ruoyi
 * @date 2023-01-18
 */
public class StdPersonTrack extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 轨迹ID */
    private Long trackId;

    /** 证件类型 */
    @Excel(name = "证件类型")
    private String personIdType;

    /** 证件号码 */
    @Excel(name = "证件号码")
    private String personId;

    /** 曾住地ID */
    @Excel(name = "曾住地ID")
    private Long oldHouseId;

    /** 现住地ID */
    @Excel(name = "现住地ID")
    private Long newHouseId;

    private String oldAddress;
    private String newAddress;

    /** 流动时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "流动时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date moveDate;

    public void setTrackId(Long trackId) 
    {
        this.trackId = trackId;
    }

    public Long getTrackId() 
    {
        return trackId;
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
    public void setOldHouseId(Long oldHouseId) 
    {
        this.oldHouseId = oldHouseId;
    }

    public Long getOldHouseId() 
    {
        return oldHouseId;
    }
    public void setNewHouseId(Long newHouseId) 
    {
        this.newHouseId = newHouseId;
    }

    public Long getNewHouseId() 
    {
        return newHouseId;
    }
    public void setMoveDate(Date moveDate) 
    {
        this.moveDate = moveDate;
    }

    public Date getMoveDate() 
    {
        return moveDate;
    }

    public void setOldAddress(String oldAddress) {
        this.oldAddress = oldAddress;
    }
    public String getOldAddress() {
        return oldAddress;
    }

    public void setNewAddress(String newAddress) {
        this.newAddress = newAddress;
    }
    public String getNewAddress() {
        return newAddress;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("trackId", getTrackId())
            .append("personIdType", getPersonIdType())
            .append("personId", getPersonId())
            .append("oldHouseId", getOldHouseId())
            .append("newHouseId", getNewHouseId())
            .append("moveDate", getMoveDate())
            .toString();
    }
}
