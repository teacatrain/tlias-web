package com.itheima.mapper;

import com.itheima.pojo.Student;
import com.itheima.pojo.StudentQueryParam;
import org.apache.ibatis.annotations.MapKey;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface StudentMapper {
    List<Student> list(StudentQueryParam queryParam);

    void deleteByIds(List<Integer> ids);

    void add(Student student);

    Student getById(Integer id);

    void updateById(Student student);

    void updateViolation(Integer id, Integer score);
    @MapKey("name")
    List<Map<String, Object>> countStudentDegreeData();
    @MapKey("clazz")
    List<Map<String, Object>> countStudentClazzData();
}
