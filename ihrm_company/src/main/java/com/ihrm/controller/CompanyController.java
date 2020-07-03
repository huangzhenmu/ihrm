package com.ihrm.controller;

import com.ihrm.common.entity.Result;
import com.ihrm.common.entity.ResultCode;
import com.ihrm.company.Company;
import com.ihrm.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * (CoCompany)表控制层
 *
 * @author makejava
 * @since 2020-03-06 10:48:14
 */
@RestController
@RequestMapping("company")
@CrossOrigin
public class CompanyController {
    /**
     * 服务对象
     */
    @Autowired
    private CompanyService companyService;

    /**
     * 分页查询所有数据
     *
     * @param page 分页对象
     * @param company 查询实体
     * @return 所有数据
     */
    /*@GetMapping
    public Result selectAll(Page<Company> page, Company company) {
        return success(this.coCompanyService.page(page, new QueryWrapper<>(company)));
    }
*/

    /**
     * 新增数据
     *
     * @param company 实体对象
     * @return 新增结果
     */
    @PostMapping("/add")
    public Result add(@RequestBody Company company) throws Exception {
        companyService.add(company);
        return Result.SUCCESS();
    }

    /**
     * 修改数据
     *
     * @param company 实体对象
     * @return 修改结果
     */
    @PutMapping("/{id}")
    public Result update(@RequestBody Company company,@PathVariable(name = "id") String id) throws  Exception{
        Company one = companyService.findById(id);
        one.setName(company.getName());
        one.setRemarks(company.getRemarks());
        one.setState(company.getState());
        one.setAuditState(company.getAuditState());
        companyService.update(one);
        return Result.SUCCESS();
    }

    /**
     * 删除数据
     *
     * @return 删除结果
     */
    @DeleteMapping
    public Result delete(@PathVariable(name = "id") String id) {
        companyService.deleteById(id);
        return Result.SUCCESS();
    }

    /**
     * 根据ID获取公司信息
     * @param id
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/get/{id}", method = RequestMethod.GET)
    public Result findById(@PathVariable(name = "id") String id) throws Exception {
        Company company = companyService.findById(id);
        return new Result(ResultCode.SUCCESS,company);
    }

    /**
     * 获取企业列表
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "", method = RequestMethod.GET)
    public Result findAll() throws Exception {
         List<Company> companyList = companyService.findAll();
         return new Result(ResultCode.SUCCESS,companyList);
    }
}