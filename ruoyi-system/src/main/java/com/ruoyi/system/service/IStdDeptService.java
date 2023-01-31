package com.ruoyi.system.service;

import java.util.List;
import com.ruoyi.common.core.domain.entity.StdDept;

/**
 * 单位Service接口
 * 
 * @author ruoyi
 * @date 2023-01-03
 */
public interface IStdDeptService 
{
    /**
     * 查询单位
     * 
     * @param deptId 单位主键
     * @return 单位
     */
    public StdDept selectStdDeptByDeptId(Long deptId);

    /**
     * 查询单位列表
     * 
     * @param stdDept 单位
     * @return 单位集合
     */
    public List<StdDept> selectStdDeptList(StdDept stdDept);

    /**
     * 新增单位
     * 
     * @param stdDept 单位
     * @return 结果
     */
    public int insertStdDept(StdDept stdDept);

    /**
     * 修改单位
     * 
     * @param stdDept 单位
     * @return 结果
     */
    public int updateStdDept(StdDept stdDept);

    /**
     * 批量删除单位
     * 
     * @param deptIds 需要删除的单位主键集合
     * @return 结果
     */
    public int deleteStdDeptByDeptIds(Long[] deptIds);

    /**
     * 删除单位信息
     * 
     * @param deptId 单位主键
     * @return 结果
     */
    public int deleteStdDeptByDeptId(Long deptId);

    public void insertDeptDetails(StdDept dept);

    public boolean existsByDeptId(Long deptId);

    public boolean existsByStdHouseId(Long stdHouseId);
}
