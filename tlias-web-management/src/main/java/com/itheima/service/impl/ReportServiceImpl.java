package com.itheima.service.impl;

import com.itheima.mapper.EmpMapper;
import com.itheima.mapper.StudentMapper;
import com.itheima.pojo.ClazzOption;
import com.itheima.pojo.JobOption;
import com.itheima.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class ReportServiceImpl implements ReportService {
    @Autowired
    private EmpMapper empMapper;
    @Autowired
    private StudentMapper studentMapper;
    @Override
    public JobOption getEmpJobData() {
        List<Map<String, Object>> list = empMapper.countEmpJobData();
        List<Object> joblist = list.stream().map(dataMap -> dataMap.get("pos")).toList();
        List<Object> datalist = list.stream().map(dataMap -> dataMap.get("num")).toList();
        return new JobOption(joblist, datalist);
    }

    @Override
    public List<Map<String, Object>> getEmpGenderData() {

        return empMapper.countEmpGenderData();
    }

    @Override
    public List<Map<String, Object>> getStudentDegreeData() {

        return studentMapper.countStudentDegreeData();
    }

    @Override
    public ClazzOption getStudentClazzData() {
        List<Map<String, Object>> list = studentMapper.countStudentClazzData();
        List<Object> clazzlist = list.stream().map(dataMap -> dataMap.get("clazz")).toList();
        List<Object> datalist = list.stream().map(dataMap -> dataMap.get("num")).toList();
        return new ClazzOption(clazzlist, datalist);
    }
}
