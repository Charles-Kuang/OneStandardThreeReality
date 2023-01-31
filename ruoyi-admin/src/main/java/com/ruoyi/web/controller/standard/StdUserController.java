package com.ruoyi.web.controller.standard;

import java.util.List;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.core.domain.entity.StdUser;
import com.ruoyi.system.service.IStdUserService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 用户信息Controller
 *
 * @author ruoyi
 * @date 2022-12-08
 */
@RestController
@RequestMapping("/standard/myUser")
public class StdUserController extends BaseController
{
    @Autowired
    private IStdUserService stdUserService;

    /**
     * 查询用户信息列表
     */
    @GetMapping("/list")
    public TableDataInfo list(StdUser stdUser)
    {
        startPage();
        List<StdUser> list = stdUserService.selectStdUserList(stdUser);
        return getDataTable(list);
    }

    /**
     * 导出用户信息列表
     */
    @Log(title = "用户信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, StdUser stdUser)
    {
        List<StdUser> list = stdUserService.selectStdUserList(stdUser);
        ExcelUtil<StdUser> util = new ExcelUtil<StdUser>(StdUser.class);
        util.exportExcel(response, list, "用户信息数据");
    }

    /**
     * 获取用户信息详细信息
     */
    @GetMapping(value = "/{userId}")
    public AjaxResult getInfo(@PathVariable("userId") Long userId)
    {
        return success(stdUserService.selectStdUserByUserId(userId));
    }

    /**
     * 新增用户信息
     */
    @Log(title = "用户信息", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody StdUser stdUser)
    {
        return toAjax(stdUserService.insertStdUser(stdUser));
    }

    /**
     * 修改用户信息
     */
    @Log(title = "用户信息", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody StdUser stdUser)
    {
        return toAjax(stdUserService.updateStdUser(stdUser));
    }

    /**
     * 删除用户信息
     */
    @Log(title = "用户信息", businessType = BusinessType.DELETE)
    @DeleteMapping("/{userIds}")
    public AjaxResult remove(@PathVariable Long[] userIds)
    {
        return toAjax(stdUserService.deleteStdUserByUserIds(userIds));
    }
}
