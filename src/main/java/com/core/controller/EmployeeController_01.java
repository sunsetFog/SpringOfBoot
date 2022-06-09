package com.core.controller;

import com.core.dao.MapDepartmentDao_01;
import com.core.dao.MapEmployeeDao_01;
import com.core.pojo.DepartmentPojo_01;
import com.core.pojo.MapEmployeePojo_01;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

/*
Post获取form表单参数：
方法1.public String seasonAdd(MapEmployeePojo mapEmployeePojo) {
方法2.public String seasonAdd(Integer id, String lastName, String email, Integer gender, MapDepartmentPojo mapDepartmentPojo, Date birth) {

Post接收JSON数据
1.public String login(@RequestBody MapEmployeePojo mapEmployeePojo){
2.@RequestParam("username") String username

Get接收JSON数据
1.@RequestParam("username") String username
2.HttpServletRequest request
String username = request.getParameter("username");

Get接收path参数
@GetMapping("store/update/{id}")
@PathVariable("id") int id
*/
@Controller
public class EmployeeController_01 {
    @Autowired
    MapEmployeeDao_01 mapEmployeeDao01;
    @Autowired
    MapDepartmentDao_01 mapDepartmentDao01;

    @RequestMapping("/season/list")
    public String seasonList(Model model){
        // 获取所有员工
        Collection<MapEmployeePojo_01> all = mapEmployeeDao01.getAll();
        model.addAttribute("employeeList", all);
        return "employee/list.html";

    }
    // 跳转添加页
    @GetMapping("/season/queryAdd")
    public String seasonToAdd(Model model) {
        // 获取所有部门
        Collection<DepartmentPojo_01> departmentList = mapDepartmentDao01.getDepartments();
        model.addAttribute("departmentList", departmentList);
        return "employee/add";
    }
    // 用于添加   输入日期必须是yyyy-MM-dd格式，否则报400
    @PostMapping("/season/add")
    public String seasonAdd(MapEmployeePojo_01 mapEmployeePojo01, Model model) {
        System.out.println("--保存功能--：" + mapEmployeePojo01);
        mapEmployeeDao01.save(mapEmployeePojo01);
        seasonList(model);
        return "employee/list";
    }
    // 跳转修改页
    @GetMapping("/season/strip/{id}")
    public String seasonToUpdate(@PathVariable("id")Integer id, Model model) {
        // 获取id的那条数据
        MapEmployeePojo_01 mapEmployeePojo01ById = mapEmployeeDao01.getEmployeeById(id);
        model.addAttribute("tiao", mapEmployeePojo01ById);
        seasonToAdd(model);
        return "employee/update";
    }
    // 修改
    @RequestMapping("/season/update")
    public String seasonUpdate(MapEmployeePojo_01 mapEmployeePojo01, Model model) {
        System.out.println("修改功能：" + mapEmployeePojo01);
        mapEmployeeDao01.save(mapEmployeePojo01);
        seasonList(model);
        return "employee/list";
    }
    // 删除
    @GetMapping("/season/delete/{id}")
    public String seasonDelete(@PathVariable("id")Integer id, Model model) {
        mapEmployeeDao01.delete(id);
        seasonList(model);
        return "employee/list";
    }
}
