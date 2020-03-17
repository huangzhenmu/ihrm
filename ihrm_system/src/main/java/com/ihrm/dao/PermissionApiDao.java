package com.ihrm.dao;

import com.ihrm.system.PermissionApi;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface PermissionApiDao extends JpaRepository<PermissionApi,String>, JpaSpecificationExecutor<PermissionApi> {
}
