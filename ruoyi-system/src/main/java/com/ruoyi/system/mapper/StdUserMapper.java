package com.ruoyi.system.mapper;

import java.util.List;
import com.ruoyi.common.core.domain.entity.StdUser;

/**
 * 用户信息Mapper接口
 *
 * @author ruoyi
 * @date 2022-12-08
 */
public interface StdUserMapper
{
    /**
     * 查询用户信息
     *
     * @param userId 用户信息主键
     * @return 用户信息
     */
    public StdUser selectStdUserByUserId(Long userId);

    /**
     * 查询用户信息列表
     *
     * @param stdUser 用户信息
     * @return 用户信息集合
     */
    public List<StdUser> selectStdUserList(StdUser stdUser);

    /**
     * 新增用户信息
     *
     * @param stdUser 用户信息
     * @return 结果
     */
    public int insertStdUser(StdUser stdUser);

    /**
     * 修改用户信息
     *
     * @param stdUser 用户信息
     * @return 结果
     */
    public int updateStdUser(StdUser stdUser);

    /**
     * 删除用户信息
     *
     * @param userId 用户信息主键
     * @return 结果
     */
    public int deleteStdUserByUserId(Long userId);

    /**
     * 批量删除用户信息
     *
     * @param userIds 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteStdUserByUserIds(Long[] userIds);
}
