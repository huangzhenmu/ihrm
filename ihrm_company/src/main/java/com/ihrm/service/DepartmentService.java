package com.ihrm.service;

import com.ihrm.common.controller.BaseController;
import com.ihrm.common.service.BaseService;
import com.ihrm.common.utils.IdWorker;
import com.ihrm.company.Department;
import com.ihrm.dao.DepartmentDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;

@Service
public class DepartmentService extends BaseService<Department> {
    @Autowired
    private DepartmentDao departmentDao;

    @Autowired
    private IdWorker idWorker;

    public void save(Department department){
        department.setId(idWorker.nextId()+"");
        departmentDao.save(department);
    }

    public void update(Department department){
        Department update = departmentDao.findById(department.getId()).get();
        update.setCode(department.getCode());
        update.setIntroduce(department.getIntroduce());
        update.setName(department.getName());
        departmentDao.save(update);
    }

    public Department findbyId(String id){
        return departmentDao.findById(id).get();
    }

    public List<Department> findAll(String companyId){
        /**
         * 构造查询条件
         */
        /*Specification<Department> spec = new Specification<Department>() {
            *//**
             *
             * @param root 包含了所有的对象数据
             * @param cq 一般不用
             * @param cb 构造查询条件，如equal（相等 ）、notin、le(小于等于）、ge（大于等于）等等
             * @return
             *//*
            @Override
            public Predicate toPredicate(Root<Department> root, CriteriaQuery<?> cq, CriteriaBuilder cb) {
                return cb.equal(root.get("companyId").as(String.class),companyId);//where companyId = "1"
            }
        };*/
        return departmentDao.findAll(getSpec(companyId));
    }

    public void deleteById(String id){
        departmentDao.deleteById(id);
    }
}
