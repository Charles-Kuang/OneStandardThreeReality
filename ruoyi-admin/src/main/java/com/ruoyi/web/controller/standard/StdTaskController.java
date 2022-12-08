package com.ruoyi.web.controller.system;

import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.entity.StdTask;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.system.domain.StdUserTask;
import com.ruoyi.system.service.IStdTaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/task")
public class StdTaskController extends BaseController {
    @Autowired
    private IStdTaskService taskService;

    @GetMapping("/list")
    public TableDataInfo list(StdUserTask userTask) {
        startPage();
        List<StdTask> list = taskService.selectTaskListByUserIdAndTaskStatus(userTask);
        return getDataTable(list);
    }

}
