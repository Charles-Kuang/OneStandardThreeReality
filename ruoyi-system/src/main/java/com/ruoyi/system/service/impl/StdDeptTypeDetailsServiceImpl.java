package com.ruoyi.system.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.StdDeptTypeDetailsMapper;
import com.ruoyi.common.core.domain.entity.StdDeptTypeDetails;
import com.ruoyi.system.service.IStdDeptTypeDetailsService;

/**
 * 单位类别关系Service业务层处理
 * 
 * @author ruoyi
 * @date 2023-01-05
 */
@Service
public class StdDeptTypeDetailsServiceImpl implements IStdDeptTypeDetailsService 
{
    @Autowired
    private StdDeptTypeDetailsMapper stdDeptTypeDetailsMapper;

    /**
     * 查询单位类别关系
     * 
     * @param detailId 单位类别关系主键
     * @return 单位类别关系
     */
    @Override
    public StdDeptTypeDetails selectStdDeptTypeDetailsByDetailId(Long detailId)
    {
        return stdDeptTypeDetailsMapper.selectStdDeptTypeDetailsByDetailId(detailId);
    }

    /**
     * 查询单位类别关系列表
     * 
     * @param stdDeptTypeDetails 单位类别关系
     * @return 单位类别关系
     */
    @Override
    public List<StdDeptTypeDetails> selectStdDeptTypeDetailsList(StdDeptTypeDetails stdDeptTypeDetails)
    {
        return stdDeptTypeDetailsMapper.selectStdDeptTypeDetailsList(stdDeptTypeDetails);
    }

    /**
     * 新增单位类别关系
     * 
     * @param stdDeptTypeDetails 单位类别关系
     * @return 结果
     */
    @Override
    public int insertStdDeptTypeDetails(StdDeptTypeDetails stdDeptTypeDetails)
    {
        return stdDeptTypeDetailsMapper.insertStdDeptTypeDetails(stdDeptTypeDetails);
    }

    /**
     * 修改单位类别关系
     * 
     * @param stdDeptTypeDetails 单位类别关系
     * @return 结果
     */
    @Override
    public int updateStdDeptTypeDetails(StdDeptTypeDetails stdDeptTypeDetails)
    {
        return stdDeptTypeDetailsMapper.updateStdDeptTypeDetails(stdDeptTypeDetails);
    }

    /**
     * 批量删除单位类别关系
     * 
     * @param detailIds 需要删除的单位类别关系主键
     * @return 结果
     */
    @Override
    public int deleteStdDeptTypeDetailsByDetailIds(Long[] detailIds)
    {
        return stdDeptTypeDetailsMapper.deleteStdDeptTypeDetailsByDetailIds(detailIds);
    }

    /**
     * 删除单位类别关系信息
     * 
     * @param detailId 单位类别关系主键
     * @return 结果
     */
    @Override
    public int deleteStdDeptTypeDetailsByDetailId(Long detailId)
    {
        return stdDeptTypeDetailsMapper.deleteStdDeptTypeDetailsByDetailId(detailId);
    }
}
