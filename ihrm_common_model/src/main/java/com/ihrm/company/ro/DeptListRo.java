package com.ihrm.company.ro;

import com.ihrm.company.Company;
import com.ihrm.company.Department;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor

public class DeptListRo {
    private String companyId;
    private String companyName;
    private String companyManage;
    private List<Department> depts;

    public DeptListRo(Company company,List depts){
        this.companyId = company.getId();
        this.companyName = company.getName();
        this.companyManage = company.getLegalRepresentative();//公司的联系人

        this.depts = depts;

    }
}
