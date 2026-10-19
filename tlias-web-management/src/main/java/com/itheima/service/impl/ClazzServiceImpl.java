package com.itheima.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.itheima.mapper.ClazzMapper;
import com.itheima.mapper.EmpMapper;
import com.itheima.pojo.*;
import com.itheima.service.ClazzService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class ClazzServiceImpl implements ClazzService {
    @Autowired
    private ClazzMapper clazzMapper;
    @Autowired
    private EmpMapper empMapper;
        @Override
    public PageResult<Clazz> page(ClazzQueryParam clazzQueryParam) {
            int page = clazzQueryParam.getPage() != null ? clazzQueryParam.getPage() : 1;
            int pageSize = clazzQueryParam.getPageSize() != null ? clazzQueryParam.getPageSize() : 10;
            PageHelper.startPage(page,pageSize);
            List<Clazz> clazzList = clazzMapper.list(clazzQueryParam);
            clazzList.forEach(clazz -> {
                clazz.setMasterName(empMapper.getById(clazz.getMasterId()).getName());
                LocalDate  now = LocalDate.now();
                clazz.setStatus(clazz.getBeginDate().isAfter(now) ? "未开班" : clazz.getEndDate().isBefore(now) ?  "已结课": "在读");
            });
            Page<Clazz> p = (Page<Clazz>) clazzList;
        return new PageResult<Clazz>(p.getTotal(),p.getResult());
    }

    @Override
    public void deleteById(Integer id) {
        clazzMapper.deleteById(id);
    }

    @Override
    public void add(Clazz clazz) {
        clazz.setCreateTime(LocalDateTime.now());
        clazz.setUpdateTime(LocalDateTime.now());
        clazzMapper.add(clazz);
    }

    @Override
    public Clazz getById(Integer id) {
        return clazzMapper.getById(id);
    }

    @Override
    public void update(Clazz clazz) {
        clazzMapper.updateById(clazz);
    }

    @Override
    public List<Clazz> findAll() {
        return clazzMapper.findAll();
    }
}
