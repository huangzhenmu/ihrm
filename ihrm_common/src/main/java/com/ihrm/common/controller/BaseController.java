package com.ihrm.common.controller;

import com.ihrm.system.response.ProfileResult;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.springframework.web.bind.annotation.ModelAttribute;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class BaseController {
    public HttpServletRequest request;
    public HttpServletResponse response;
    protected String companyId;
    protected String companyName;

    //在过滤路由时执行的方法
    @ModelAttribute
    public void setReqAndResp(HttpServletRequest request,HttpServletResponse response){
        this.request = request;
        this.response = response;
        this.companyId = "1";
    }

    public String parseCompanyId(){
        return companyId;
    }

    public String parseCompanyName(){
        return "hzm";
    }

    //使用shiro获取
    @ModelAttribute
    public void setResAnReq(HttpServletRequest request,HttpServletResponse response) {
        this.request = request;
        this.response = response;
        //获取session中的安全数据
        Subject subject = SecurityUtils.getSubject();
        //1.subject获取所有的安全数据集合
        PrincipalCollection principals = subject.getPrincipals();
        if(principals != null && !principals.isEmpty()){
            //2.获取安全数据
            ProfileResult result = (ProfileResult)principals.getPrimaryPrincipal();
            this.companyId = result.getCompanyId();
            this.companyName = result.getCompany();
        }
    }
}
