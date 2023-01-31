package com.ruoyi.system.service;

import java.util.List;
import com.ruoyi.common.core.domain.entity.StdPerson;

/**
 * 个体Service接口
 * 
 * @author ruoyi
 * @date 2022-12-29
 */
public interface IStdPersonService 
{
    /**
     * 查询个体
     * 
     * @param id 个体主键
     * @return 个体
     */
    public StdPerson selectStdPersonById(Long id);

    /**
     * 查询个体列表
     * 
     * @param stdPerson 个体
     * @return 个体集合
     */
    public List<StdPerson> selectStdPersonList(StdPerson stdPerson);

    /**
     * 新增个体
     * 
     * @param stdPerson 个体
     * @return 结果
     */
    public int insertStdPerson(StdPerson stdPerson);

    /**
     * 修改个体
     * 
     * @param stdPerson 个体
     * @return 结果
     */
    public int updateStdPerson(StdPerson stdPerson, Long oldStdHouseId);

    /**
     * 批量删除个体
     * 
     * @param ids 需要删除的个体主键集合
     * @return 结果
     */
    public int deleteStdPersonByIds(Long[] ids);

    /**
     * 删除个体信息
     * 
     * @param id 个体主键
     * @return 结果
     */
    public int deleteStdPersonById(Long id);

    public boolean existsByPersonId(String personIdType, String personId);

    public boolean existsByCurrentStdHouseId(Long currentStdHouseId);
}
