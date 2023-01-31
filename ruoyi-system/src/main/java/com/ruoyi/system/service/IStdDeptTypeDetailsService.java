package com.ruoyi.system.service;

import java.util.List;
import com.ruoyi.common.core.domain.entity.StdDeptTypeDetails;

/**
 * 单位类别关系Service接口
 * 
 * @author ruoyi
 * @date 2023-01-05
 */
public interface IStdDeptTypeDetailsService 
{
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
     * 批量删除单位类别关系
     * 
     * @param detailIds 需要删除的单位类别关系主键集合
     * @return 结果
     */
    public int deleteStdDeptTypeDetailsByDetailIds(Long[] detailIds);

    /**
     * 删除单位类别关系信息
     * 
     * @param detailId 单位类别关系主键
     * @return 结果
     */
    public int deleteStdDeptTypeDetailsByDetailId(Long detailId);
}
