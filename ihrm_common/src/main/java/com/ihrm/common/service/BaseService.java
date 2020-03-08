package com.ihrm.common.service;

import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

public class BaseService<T>{
    public Specification<T> getSpec(String companyId){
        Specification<T> spec = new Specification<T>() {
            /**
             *
             * @param root 包含了所有的对象数据
             * @param cq 一般不用
             * @param cb 构造查询条件，如equal（相等 ）、notin、le(小于等于）、ge（大于等于）等等
             * @return
             */
            @Override
            public Predicate toPredicate(Root<T> root, CriteriaQuery<?> cq, CriteriaBuilder cb) {
                return cb.equal(root.get("companyId").as(String.class),companyId);//where companyId = "1"
            }
        };
        return spec;
    }
}
