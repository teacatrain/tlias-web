package com.itheima.controller;

import com.itheima.pojo.OperateLogQueryParam;
import com.itheima.pojo.OperateLog;
import com.itheima.pojo.PageResult;
import com.itheima.pojo.Result;
import com.itheima.service.OperateLogService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/log")
public class OperateLogController {
    @Autowired
    private OperateLogService logService;
    @GetMapping("/page")
    public Result list(OperateLogQueryParam operateLogQueryParam){
        log.info("查询操作日志：{}",operateLogQueryParam);
        PageResult<OperateLog> pageResult = logService.page(operateLogQueryParam);
        return Result.success(pageResult);
    }
}
