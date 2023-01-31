package com.ruoyi.system.mapper;

import com.ruoyi.common.core.domain.entity.StdDeptTypeDetails;

import java.util.List;



public interface StdDeptTypeDetailsMapper {
    public int batchDeptDetails(List<StdDeptTypeDetails> deptDetailsList);

    public int deleteDeptDetailsByDeptId(Long deptId);

    public int deleteDeptDetails(Long[] ids);

    /**
     * 查询单位类别关系
     * 
     * @param detailId 单位类别关系主键
     * @return 单位类别关系
     */
    public StdDeptTypeDetails selectStdDeptTypeDetailsByDetailId(Long detailId);

    /**
     * 查询单位类别关系列表
     * 
     * @param stdDeptTypeDetails 单位类别关系
     * @return 单位类别关系集合
     */
    public List<StdDeptTypeDetails> selectStdDeptTypeDetailsList(StdDeptTypeDetails stdDeptTypeDetails);

    /**
     * 新增单位类别关系
     * 
     * @param stdDeptTypeDetails 单位类别关系
     * @return 结果
     */
    public int insertStdDeptTypeDetails(StdDeptTypeDetails stdDeptTypeDetails);

    /**
     * 修改单位类别关系
     * 
     * @param stdDeptTypeDetails 单位类别关系
     * @return 结果
     */
    public int updateStdDeptTypeDetails(StdDeptTypeDetails stdDeptTypeDetails);

    /**
     * 删除单位类别关系
     * 
     * @param detailId 单位类别关系主键
     * @return 结果
     */
    public int deleteStdDeptTypeDetailsByDetailId(Long detailId);

    /**
     * 批量删除单位类别关系
     * 
     * @param detailIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteStdDeptTypeDetailsByDetailIds(Long[] detailIds);
}
