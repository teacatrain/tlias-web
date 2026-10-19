package com.itheima.controller;

import com.itheima.pojo.PageResult;
import com.itheima.pojo.Result;
import com.itheima.pojo.Student;
import com.itheima.pojo.StudentQueryParam;
import com.itheima.service.StudentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequestMapping("/students")
public class StudentController {
    @Autowired
    private StudentService studentService;
    @GetMapping
    public Result page(StudentQueryParam queryParam){
        log.info("分页查询，参数：{}",queryParam);
        PageResult<Student> pageResult = studentService.page(queryParam);
        return Result.success(pageResult);
    }
    @DeleteMapping("/{ids}")
    public Result Delete(@PathVariable String ids){
        log.info("删除学生，ids：{}",ids);
        List<Integer> idList = Arrays.stream(ids.split(",")).map(Integer::valueOf).collect(Collectors.toList());
        studentService.delete(idList);
        return Result.success();
    }

    @PostMapping
    public Result save(@RequestBody Student student){
        log.info("新增学生：{}",student);
        studentService.save(student);
        return Result.success();
    }
    @GetMapping("/{id}")
    public Result getInfo(@PathVariable Integer id){
        log.info("查询学生，id：{}",id);
        Student student = studentService.getInfo(id);
        return Result.success(student);
    }
    @PutMapping
    public Result update(@RequestBody Student student){
        log.info("修改学生数据：{}",student);
        studentService.update(student);
        return Result.success();
    }
    @PutMapping("/violation/{id}/{score}")
    public Result updateViolation(@PathVariable("id") Integer id,@PathVariable("score") Integer score){
        log.info("修改学生违纪次数：{}，学生违纪扣分：{}",id, score);
        studentService.updateViolation(id,score);
        return Result.success();
    }
}
