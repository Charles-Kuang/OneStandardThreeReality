package com.ruoyi.common.core.domain.entity;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 单位类别关系对象 std_dept_type_details
 * 
 * @author ruoyi
 * @date 2023-01-04
 */
public class StdDeptTypeDetails extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    private Long detailId;

    /** 单位营业执照号码 */
    private Long deptId;

    private String deptName;

    /** 单位类别ID */
    private String deptType;

    /** 经营状况 */
    @Excel(name = "经营状况")
    private String businessStatus;

    /** 经营项目 */
    @Excel(name = "经营项目")
    private String businessProject;

    /** 副营项目 */
    @Excel(name = "副营项目")
    private String viceProject;

    /** 等级评定 */
    @Excel(name = "等级评定")
    private String rating;

    /** 面积 */
    @Excel(name = "面积")
    private Double area;

    /** 民警负责人警号 */
    @Excel(name = "民警负责人警号")
    private String policeRepresentativeId;

    /** 民警负责人姓名 */
    @Excel(name = "民警负责人姓名")
    private String policeRepresentativeName;


    public void setDetailId(Long detailId) 
    {
        this.detailId = detailId;
    }

    public Long getDetailId() 
    {
        return detailId;
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
    public void setBusinessStatus(String businessStatus) 
    {
        this.businessStatus = businessStatus;
    }

    public String getBusinessStatus() 
    {
        return businessStatus;
    }
    public void setBusinessProject(String businessProject) 
    {
        this.businessProject = businessProject;
    }

    public String getBusinessProject() 
    {
        return businessProject;
    }
    public void setViceProject(String viceProject) 
    {
        this.viceProject = viceProject;
    }

    public String getViceProject() 
    {
        return viceProject;
    }

    public String getDeptName() 
    {
        return deptName;
    }
    public void setDeptName(String deptName) 
    {
        this.deptName = deptName;
    }

    public void setRating(String rating) 
    {
        this.rating = rating;
    }

    public String getRating() 
    {
        return rating;
    }
    public void setArea(Double area)
    {
        this.area = area;
    }

    public Double getArea()
    {
        return area;
    }
    public void setPoliceRepresentativeId(String policeRepresentativeId) 
    {
        this.policeRepresentativeId = policeRepresentativeId;
    }

    public String getPoliceRepresentativeId() 
    {
        return policeRepresentativeId;
    }
    public void setPoliceRepresentativeName(String policeRepresentativeName) 
    {
        this.policeRepresentativeName = policeRepresentativeName;
    }

    public String getPoliceRepresentativeName() 
    {
        return policeRepresentativeName;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("detailId", getDetailId())
            .append("deptId", getDeptId())
            .append("deptType", getDeptType())
            .append("businessStatus", getBusinessStatus())
            .append("businessProject", getBusinessProject())
            .append("viceProject", getViceProject())
            .append("rating", getRating())
            .append("area", getArea())
            .append("policeRepresentativeId", getPoliceRepresentativeId())
            .append("policeRepresentativeName", getPoliceRepresentativeName())
            .toString();
    }
}
