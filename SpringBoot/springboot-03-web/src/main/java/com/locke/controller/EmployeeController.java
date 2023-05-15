package com.locke.controller;

import com.locke.dao.DepartmentDao;
import com.locke.dao.EmployeeDao;
import com.locke.pojo.Department;
import com.locke.pojo.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Collection;

@Controller
public class EmployeeController {

    final
    EmployeeDao employeeDao;
    final
    DepartmentDao departmentDao;

    // 使用构造器注入 @Autowired 都可以省略
    @Autowired
    public EmployeeController(EmployeeDao employeeDao, DepartmentDao departmentDao) {
        this.employeeDao = employeeDao;
        this.departmentDao = departmentDao;
    }

    @RequestMapping("/emps")
    public String list(Model model) {
        Collection<Employee> employees = employeeDao.getAll();
        model.addAttribute("emps",employees);
        return "emp/list";
    }

    @GetMapping("emp")
    public String toAddPage(Model model) {
        //查出所有部门的信息
        Collection<Department> department = departmentDao.getDepartment();
        model.addAttribute("departments",department);
        return "emp/add";
    }

    @PostMapping("/emp")
    public String addEmp(Employee employee) {
        employeeDao.save(employee); // 调用底层业务方法保存员工信息
        return "redirect:/emps";

    }

    // 去到员工的修改页面
    @GetMapping("/emp/{id}")
    public String toUpdateEmp(@PathVariable("id") Integer id, Model model) {
        // 查出原来的数据
        Employee employee = employeeDao.getEmployeeById(id);
        model.addAttribute("emp",employee);
        // 查出所有部门的信息
        Collection<Department> department = departmentDao.getDepartment();
        model.addAttribute("departments",department);
        return "emp/update";
    }

    @PostMapping("/updateEmp")
    public String updateEmp(Employee employee) {
        employeeDao.save(employee);
        return "redirect:/emps";
    }


    // 删除员工
    @GetMapping("/delemp/{id}")
    public String deleteEmp(@PathVariable("id") Integer id) {
        employeeDao.delete(id);
        return "redirect:/emps";
    }


}

