package com.ruoyi.system.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.StdUserMapper;
import com.ruoyi.common.core.domain.entity.StdUser;
import com.ruoyi.system.service.IStdUserService;

/**
 * 用户信息Service业务层处理
 *
 * @author ruoyi
 * @date 2022-12-08
 */
@Service
public class StdUserServiceImpl implements IStdUserService
{
    @Autowired
    private StdUserMapper stdUserMapper;

    /**
     * 查询用户信息
     *
     * @param userId 用户信息主键
     * @return 用户信息
     */
    @Override
    public StdUser selectStdUserByUserId(Long userId)
    {
        return stdUserMapper.selectStdUserByUserId(userId);
    }

    /**
     * 查询用户信息列表
     *
     * @param stdUser 用户信息
     * @return 用户信息
     */
    @Override
    public List<StdUser> selectStdUserList(StdUser stdUser)
    {
        return stdUserMapper.selectStdUserList(stdUser);
    }

    /**
     * 新增用户信息
     *
     * @param stdUser 用户信息
     * @return 结果
     */
    @Override
    public int insertStdUser(StdUser stdUser)
    {
        stdUser.setCreateTime(DateUtils.getNowDate());
        return stdUserMapper.insertStdUser(stdUser);
    }

    /**
     * 修改用户信息
     *
     * @param stdUser 用户信息
     * @return 结果
     */
    @Override
    public int updateStdUser(StdUser stdUser)
    {
        stdUser.setUpdateTime(DateUtils.getNowDate());
        return stdUserMapper.updateStdUser(stdUser);
    }

    /**
     * 批量删除用户信息
     *
     * @param userIds 需要删除的用户信息主键
     * @return 结果
     */
    @Override
    public int deleteStdUserByUserIds(Long[] userIds)
    {
        return stdUserMapper.deleteStdUserByUserIds(userIds);
    }

    /**
     * 删除用户信息信息
     *
     * @param userId 用户信息主键
     * @return 结果
     */
    @Override
    public int deleteStdUserByUserId(Long userId)
    {
        return stdUserMapper.deleteStdUserByUserId(userId);
    }
}
