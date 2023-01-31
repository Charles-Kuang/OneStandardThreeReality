package com.ruoyi.system.service.impl;

import java.util.ArrayList;
import java.util.List;
import com.ruoyi.common.utils.StringUtils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ruoyi.system.mapper.StdDeptMapper;
import com.ruoyi.system.mapper.StdDeptTypeDetailsMapper;
import com.ruoyi.system.mapper.StdInvolvedMapper;
import com.ruoyi.common.core.domain.entity.StdDept;
import com.ruoyi.common.core.domain.entity.StdDeptTypeDetails;
import com.ruoyi.system.service.IStdDeptService;

/**
 * 单位Service业务层处理
 * 
 * @author ruoyi
 * @date 2023-01-03
 */
@Service
public class StdDeptServiceImpl implements IStdDeptService 
{
    @Autowired
    private StdDeptMapper stdDeptMapper;

    @Autowired
    private StdDeptTypeDetailsMapper stdDeptTypeDetailsMapper;

    @Autowired
    private StdInvolvedMapper stdInvolvedMapper;

    /**
     * 查询单位
     * 
     * @param deptId 单位主键
     * @return 单位
     */
    @Override
    public StdDept selectStdDeptByDeptId(Long deptId)
    {
        return stdDeptMapper.selectStdDeptByDeptId(deptId);
    }

    /**
     * 查询单位列表
     * 
     * @param stdDept 单位
     * @return 单位
     */
    @Override
    public List<StdDept> selectStdDeptList(StdDept stdDept)
    {
        return stdDeptMapper.selectStdDeptList(stdDept);
    }

    /**
     * 新增单位
     * 
     * @param stdDept 单位
     * @return 结果
     */
    @Override
    @Transactional
    public int insertStdDept(StdDept stdDept)
    {
        int rows = stdDeptMapper.insertStdDept(stdDept);
        insertDeptDetails(stdDept);
        return rows;
    }

    @Override
    @Transactional
    public void insertDeptDetails(StdDept dept) {
        this.insertDeptDetails(dept.getDeptId(), dept.getDeptTypes());
    }

    public void insertDeptDetails(Long deptId, String[] deptTypes)
    {
        if (StringUtils.isNotEmpty(deptTypes))
        {
            // 新增用户与角色管理
            List<StdDeptTypeDetails> list = new ArrayList<StdDeptTypeDetails>(deptTypes.length);
            for (String deptType : deptTypes)
            {
                StdDeptTypeDetails dtd = new StdDeptTypeDetails();
                dtd.setDeptId(deptId);
                dtd.setDeptType(deptType);
                list.add(dtd);
            }
            stdDeptTypeDetailsMapper.batchDeptDetails(list);
        }
    }

    /**
     * 修改单位
     * 
     * @param stdDept 单位
     * @return 结果
     */
    @Override
    public int updateStdDept(StdDept stdDept)
    {
        Long deptId = stdDept.getDeptId();
        stdDeptTypeDetailsMapper.deleteDeptDetailsByDeptId(deptId);
        insertDeptDetails(stdDept);
        return stdDeptMapper.updateStdDept(stdDept);
    }

    /**
     * 批量删除单位
     * 
     * @param deptIds 需要删除的单位主键
     * @return 结果
     */
    @Override
    public int deleteStdDeptByDeptIds(Long[] deptIds)
    {
        for(Long deptId : deptIds) {
            stdInvolvedMapper.deleteStdInvolvedByInvolvedEntity("单位", deptId);
        }
        stdDeptTypeDetailsMapper.deleteDeptDetails(deptIds);
        return stdDeptMapper.deleteStdDeptByDeptIds(deptIds);
    }

    /**
     * 删除单位信息
     * 
     * @param deptId 单位主键
     * @return 结果
     */
    @Override
    public int deleteStdDeptByDeptId(Long deptId)
    {
        stdInvolvedMapper.deleteStdInvolvedByInvolvedEntity("单位", deptId);
        stdDeptTypeDetailsMapper.deleteDeptDetailsByDeptId(deptId);
        return stdDeptMapper.deleteStdDeptByDeptId(deptId);
    }

    @Override
    public boolean existsByDeptId(Long deptId) {
        int result = stdDeptMapper.existsByDeptId(deptId);
        return result > 0;
    }

    @Override
    public boolean existsByStdHouseId(Long stdHouseId) {
        int result = stdDeptMapper.existsByStdHouseId(stdHouseId);
        return result > 0;
    }
}
