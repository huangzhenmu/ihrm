package com.ihrm.dao;

import com.ihrm.company.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * (CoCompany)表数据库访问层
 *
 * @author makejava
 * @since 2020-03-06 10:48:11
 */
public interface CompanyDao extends JpaRepository<Company,String >, JpaSpecificationExecutor<Company> {

}