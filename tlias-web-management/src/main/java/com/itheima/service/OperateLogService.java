package com.itheima.service;


import com.itheima.pojo.OperateLogQueryParam;
import com.itheima.pojo.OperateLog;
import com.itheima.pojo.PageResult;

public interface OperateLogService {
    PageResult<OperateLog> page(OperateLogQueryParam operateLogQueryParam);
}
