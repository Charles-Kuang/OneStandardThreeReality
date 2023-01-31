package com.ruoyi.system.mapper;

import java.util.List;
import com.ruoyi.common.core.domain.entity.StdInvolved;

/**
 * 主体涉案Mapper接口
 * 
 * @author ruoyi
 * @date 2023-01-18
 */
public interface StdInvolvedMapper 
{
    /**
     * 查询主体涉案
     * 
     * @param involvedId 主体涉案主键
     * @return 主体涉案
     */
    public StdInvolved selectStdHouseInvolvedByInvolvedId(Long involvedId);
    public StdInvolved selectStdDeptInvolvedByInvolvedId(Long involvedId);

    /**
     * 查询主体涉案列表
     * 
     * @param stdInvolved 主体涉案
     * @return 主体涉案集合
     */
    public List<StdInvolved> selectStdHouseInvolvedList(StdInvolved stdInvolved);
    public List<StdInvolved> selectStdDeptInvolvedList(StdInvolved stdInvolved);

    /**
     * 新增主体涉案
     * 
     * @param stdInvolved 主体涉案
     * @return 结果
     */
    public int insertStdInvolved(StdInvolved stdInvolved);

    /**
     * 修改主体涉案
     * 
     * @param stdInvolved 主体涉案
     * @return 结果
     */
    public int updateStdInvolved(StdInvolved stdInvolved);

    /**
     * 删除主体涉案
     * 
     * @param involvedId 主体涉案主键
     * @return 结果
     */
    public int deleteStdInvolvedByInvolvedId(Long involvedId);

    /**
     * 批量删除主体涉案
     * 
     * @param involvedIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteStdInvolvedByInvolvedIds(Long[] involvedIds);

    public int deleteStdInvolvedByInvolvedEntity(String involvedEntityType, Long involvedEntityId);
}
