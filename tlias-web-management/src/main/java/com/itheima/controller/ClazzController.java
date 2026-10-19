package com.itheima.controller;

import com.itheima.pojo.Clazz;
import com.itheima.pojo.ClazzQueryParam;
import com.itheima.pojo.PageResult;
import com.itheima.pojo.Result;
import com.itheima.service.ClazzService;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Delete;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/clazzs")
public class ClazzController {
    @Autowired
    private ClazzService clazzService;
    //班级列表查询
    @GetMapping
    public Result page(ClazzQueryParam clazzQueryParam){

        log.info("分页查询，参数：{}",clazzQueryParam);
        PageResult<Clazz> pageResult = clazzService.page(clazzQueryParam);
        return Result.success(pageResult);
    }
    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id){
        log.info("删除班级数据，id：{}",id);
        clazzService.deleteById(id);
        return Result.success();
    }
    @PostMapping
    public Result add(@RequestBody Clazz clazz){
        log.info("添加班级数据：{}",clazz);
        clazzService.add(clazz);
        return Result.success();
    }
    @GetMapping("/{id}")
    public Result getInfo(@PathVariable Integer id){
        log.info("查询班级数据，id：{}",id);
        Clazz clazz = clazzService.getById(id);
        return Result.success(clazz);
    }
    @PutMapping
    public Result update(@RequestBody Clazz clazz){
        log.info("修改班级数据：{}",clazz);
        clazzService.update(clazz);
        return Result.success();
    }
    @GetMapping("/list")
    public Result list(){
        log.info("查询所有班级数据");
        List<Clazz> clazzList = clazzService.findAll();
        return Result.success(clazzList);
    }

}
