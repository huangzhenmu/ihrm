package com.ihrm.controller;

import com.ihrm.common.controller.BaseController;
import com.ihrm.common.entity.Result;
import com.ihrm.common.entity.ResultCode;
import com.ihrm.company.Department;
import com.ihrm.company.ro.DeptListRo;
import com.ihrm.service.CompanyService;
import com.ihrm.service.DepartmentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController()
@RequestMapping("/company")
public class DepartmentController extends BaseController {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private DepartmentService departmentService;
    @Autowired
    private CompanyService companyService;

    /**
     * 新增部门
     * @param department
     * @return
     */
    @RequestMapping(value = "/department",method = RequestMethod.POST)
    public Result save(@RequestBody Department department){
        // TODO 所属企业id目前先用1来代替
        department.setCompanyId(super.companyId);
        departmentService.save(department);
        return new Result(ResultCode.SUCCESS);
    }

    /**
     * 部门列表
     * @return
     */
    @RequestMapping(value = "/department",method = RequestMethod.GET)
    public Result findAll(){
        List<Department> departmentList = departmentService.findAll(super.companyId);
        logger.info("部门个数：{}",departmentList.size());
        //构造返回结果
        DeptListRo deptListRo = new DeptListRo(companyService.findById(companyId),departmentList);
        return new Result(ResultCode.SUCCESS,deptListRo);
    }

    @RequestMapping(value = "/department/{id}",method= RequestMethod.GET)
    public Result findById(@PathVariable("id") String id){
        Department department = departmentService.findbyId(id);
        return new Result(ResultCode.SUCCESS,department);
    }

    @RequestMapping(value = "/department/{id}",method= RequestMethod.PUT)
    public Result update(@PathVariable("id") String id,@RequestBody Department department){
        department.setId(id);
        departmentService.update(department);
        return new Result(ResultCode.SUCCESS);
    }

    @RequestMapping(value = "/department/{id}",method= RequestMethod.DELETE)
    public Result deleteById(@PathVariable("id") String id){
        departmentService.deleteById(id);
        return new Result(ResultCode.SUCCESS);
    }
}
