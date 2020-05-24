package com.ihrm.common.shiro.realm;

import com.ihrm.system.response.ProfileResult;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

import java.util.Set;

public class IhrmRealm extends AuthorizingRealm {

    //授权方法
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        //获取通过认证的用户信息
        ProfileResult profileResult = (ProfileResult)principalCollection.getPrimaryPrincipal();
        //获取用户的权限
        Set<String> apis = (Set<String>)profileResult.getRoles().get("apis");
        //构造权限对象
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        simpleAuthorizationInfo.setStringPermissions(apis);
        return simpleAuthorizationInfo;
    }

    //认证方法(这里先不做实现，放在用户realm里做实现)
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        return null;
    }
}
