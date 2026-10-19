package com.itheima.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.itheima.mapper.EmpMapper;
import com.itheima.mapper.OperateLogMapper;
import com.itheima.pojo.OperateLogQueryParam;
import com.itheima.pojo.OperateLog;
import com.itheima.pojo.PageResult;
import com.itheima.service.OperateLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OperateLogServiceImpl implements OperateLogService {
    @Autowired
    private OperateLogMapper operateLogMapper;
    @Autowired
    private EmpMapper empMapper;
    @Override
    public PageResult<OperateLog> page(OperateLogQueryParam operateLogQueryParam) {
        int page = operateLogQueryParam.getPage() != null ? operateLogQueryParam.getPage() : 1;
        int pageSize = operateLogQueryParam.getPageSize() != null ? operateLogQueryParam.getPageSize() : 10;
        PageHelper.startPage(page,pageSize);
        List<OperateLog> operateLogList = operateLogMapper.list(operateLogQueryParam);
        operateLogList.forEach(operateLog -> {
            operateLog.setOperateEmpName(empMapper.getById(operateLog.getOperateEmpId()).getName());
        });
        Page<OperateLog> p = (Page<OperateLog>) operateLogList;
        return new PageResult<OperateLog>(p.getTotal(),p.getResult());
    }
}
