package com.ruoyi.system.mapper;

import java.util.List;
import com.ruoyi.common.core.domain.entity.StdDept;

/**
 * 单位Mapper接口
 * 
 * @author ruoyi
 * @date 2023-01-03
 */
public interface StdDeptMapper 
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

    public int existsByDeptId(Long deptId);

    /**
     * 删除单位
     * 
     * @param deptId 单位主键
     * @return 结果
     */
    public int deleteStdDeptByDeptId(Long deptId);

    /**
     * 批量删除单位
     * 
     * @param deptIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteStdDeptByDeptIds(Long[] deptIds);

    public int checkLastInsertId();

    public int addPeopleNumByDeptId(Long deptId);

    public int reducePeopleNumByDeptId(Long deptId);

    public int existsByStdHouseId(Long stdHouseId);
}
