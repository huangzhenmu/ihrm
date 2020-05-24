package com.ihrm.common.shiro.session;

import org.apache.shiro.web.servlet.ShiroHttpServletRequest;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.apache.shiro.web.util.WebUtils;
import org.springframework.util.StringUtils;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.Serializable;

/**
 * 自定义sessionId获取方式
 * 获取请求头中的session信息
 */
public class IhrmSessionManager extends DefaultWebSessionManager {

    private static final String AUTHORIZATION = "Authorization";

    private static final String REFERENCED_SESSION_ID_SOURCE = "header";

    public Serializable getSessionId(ServletRequest request, ServletResponse response){
        String header = WebUtils.toHttp(request).getHeader(AUTHORIZATION);
        //如果请求头没有session
        if (StringUtils.isEmpty(header)){
            //新增一个session
            return super.getSessionId(request,response);
        }else {
            //获取请求头中的session信息，更新到shiro的session中去
            String sessionId = header.replaceAll("Bearer ","");//去掉请求头中的前缀
            //将sessionId设置到请求中
            request.setAttribute(ShiroHttpServletRequest.REFERENCED_SESSION_ID_SOURCE,REFERENCED_SESSION_ID_SOURCE);
            request.setAttribute(ShiroHttpServletRequest.REFERENCED_SESSION_ID,sessionId);
            request.setAttribute(ShiroHttpServletRequest.REFERENCED_SESSION_ID_IS_VALID,Boolean.TRUE);
            return sessionId;
        }
    }
}
