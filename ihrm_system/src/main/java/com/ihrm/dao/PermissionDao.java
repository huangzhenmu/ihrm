package com.ihrm.dao;

import com.ihrm.system.Permission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface PermissionDao extends JpaRepository<Permission,String>, JpaSpecificationExecutor<Permission> {
    public List<Permission> findByTypeAndPid(int type, String Pid);
}
