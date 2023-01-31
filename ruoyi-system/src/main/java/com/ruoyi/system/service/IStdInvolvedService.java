package com.ruoyi.system.service;

import java.util.List;
import com.ruoyi.common.core.domain.entity.StdInvolved;

/**
 * 主体涉案Service接口
 * 
 * @author ruoyi
 * @date 2023-01-18
 */
public interface IStdInvolvedService 
{
    /**
     * 查询主体涉案
     * 
     * @param involvedId 主体涉案主键
     * @return 主体涉案
     */
    public StdInvolved selectStdInvolvedByInvolvedId(String involvedEntityType, Long involvedId);

    /**
     * 查询主体涉案列表
     * 
     * @param stdInvolved 主体涉案
     * @return 主体涉案集合
     */
    public List<StdInvolved> selectStdInvolvedList(StdInvolved stdInvolved);

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
     * 批量删除主体涉案
     * 
     * @param involvedIds 需要删除的主体涉案主键集合
     * @return 结果
     */
    public int deleteStdInvolvedByInvolvedIds(Long[] involvedIds);

    /**
     * 删除主体涉案信息
     * 
     * @param involvedId 主体涉案主键
     * @return 结果
     */
    public int deleteStdInvolvedByInvolvedId(Long involvedId);
}
