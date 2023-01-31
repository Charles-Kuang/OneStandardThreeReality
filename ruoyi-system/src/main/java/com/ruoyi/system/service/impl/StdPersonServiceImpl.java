package com.ruoyi.system.service.impl;

import java.util.Calendar;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ruoyi.system.mapper.StdDeptMapper;
import com.ruoyi.system.mapper.StdPersonMapper;
import com.ruoyi.system.mapper.StdPersonTrackMapper;
import com.ruoyi.common.core.domain.entity.StdPerson;
import com.ruoyi.common.core.domain.entity.StdPersonTrack;
import com.ruoyi.system.service.IStdPersonService;

/**
 * 个体Service业务层处理
 * 
 * @author ruoyi
 * @date 2022-12-29
 */
@Service
public class StdPersonServiceImpl implements IStdPersonService 
{
    @Autowired
    private StdPersonMapper stdPersonMapper;

    @Autowired
    private StdDeptMapper stdDeptMapper;

    @Autowired
    private StdPersonTrackMapper stdPersonTrackMapper;

    /**
     * 查询个体
     * 
     * @param id 个体主键
     * @return 个体
     */
    @Override
    public StdPerson selectStdPersonById(Long id)
    {
        return stdPersonMapper.selectStdPersonById(id);
    }

    /**
     * 查询个体列表
     * 
     * @param stdPerson 个体
     * @return 个体
     */
    @Override
    public List<StdPerson> selectStdPersonList(StdPerson stdPerson)
    {
        return stdPersonMapper.selectStdPersonList(stdPerson);
    }

    /**
     * 新增个体
     * 
     * @param stdPerson 个体
     * @return 结果
     */
    @Override
    public int insertStdPerson(StdPerson stdPerson)
    {
        if(stdPerson.getDeptId() != null) {
            stdDeptMapper.addPeopleNumByDeptId(stdPerson.getDeptId());
            stdPerson.setOldDeptId(stdPerson.getDeptId());
        }
        return stdPersonMapper.insertStdPerson(stdPerson);
    }

    /**
     * 修改个体
     * 
     * @param stdPerson 个体
     * @return 结果
     */
    @Override
    public int updateStdPerson(StdPerson stdPerson, Long oldStdHouseId)
    {
        if(stdPerson.getOldDeptId() != null)
            stdDeptMapper.reducePeopleNumByDeptId(stdPerson.getOldDeptId());
        if(stdPerson.getDeptId() != null) {
            stdDeptMapper.addPeopleNumByDeptId(stdPerson.getDeptId());
            stdPerson.setOldDeptId(stdPerson.getDeptId());
        }

        //居住轨迹
        if(oldStdHouseId != stdPerson.getCurrentStdHouseId()) {
            StdPersonTrack personTrack = new StdPersonTrack();
            personTrack.setPersonIdType(stdPerson.getPersonIdType());
            personTrack.setPersonId(stdPerson.getPersonId());
            personTrack.setMoveDate(Calendar.getInstance().getTime());
            personTrack.setOldHouseId(oldStdHouseId);
            personTrack.setNewHouseId(stdPerson.getCurrentStdHouseId());
            stdPersonTrackMapper.insertStdPersonTrack(personTrack);
        }

        return stdPersonMapper.updateStdPerson(stdPerson);
    }

    /**
     * 批量删除个体
     * 
     * @param ids 需要删除的个体主键
     * @return 结果
     */
    @Override
    public int deleteStdPersonByIds(Long[] ids)
    {
        for(Long id : ids) {
            Long deptId = stdPersonMapper.selectStdPersonById(id).getOldDeptId();
            stdDeptMapper.reducePeopleNumByDeptId(deptId);

            String personIdType = stdPersonMapper.selectStdPersonById(id).getPersonIdType();
            String personId = stdPersonMapper.selectStdPersonById(id).getPersonId();
            stdPersonTrackMapper.deleteStdPersonTrackByPerson(personIdType, personId);
        }
        return stdPersonMapper.deleteStdPersonByIds(ids);
    }

    /**
     * 删除个体信息
     * 
     * @param id 个体主键
     * @return 结果
     */
    @Override
    public int deleteStdPersonById(Long id)
    {
        Long deptId = stdPersonMapper.selectStdPersonById(id).getOldDeptId();
        stdDeptMapper.reducePeopleNumByDeptId(deptId);

        String personIdType = stdPersonMapper.selectStdPersonById(id).getPersonIdType();
        String personId = stdPersonMapper.selectStdPersonById(id).getPersonId();
        stdPersonTrackMapper.deleteStdPersonTrackByPerson(personIdType, personId);

        return stdPersonMapper.deleteStdPersonById(id);
    }

    @Override
    public boolean existsByPersonId(String personIdType, String personId) {
        int result = stdPersonMapper.existsByPersonId(personIdType, personId);
        return result > 0;
    }

    @Override
    public boolean existsByCurrentStdHouseId(Long currentStdHouseId) {
        int result = stdPersonMapper.existsByCurrentStdHouseId(currentStdHouseId);
        return result > 0;
    }
}
