package com.ihrm.shiro;

import com.ihrm.common.entity.Result;
import com.ihrm.common.entity.ResultCode;
import com.ihrm.common.shiro.realm.IhrmRealm;
import com.ihrm.service.PermissionService;
import com.ihrm.service.UserService;
import com.ihrm.system.Permission;
import com.ihrm.system.User;
import com.ihrm.system.response.ProfileResult;
import org.apache.shiro.authc.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 登录realm
 */
public class UserLoginRealm extends IhrmRealm {
    @Autowired
    private UserService userService;

    @Autowired
    private PermissionService permissionService;

    public void  setName(String name){
        super.setName("UserLoginRealm");
    }
    //登录认证方法
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        //获取登录的用户名和密码
        UsernamePasswordToken usernamePasswordToken = (UsernamePasswordToken) authenticationToken;
        String userName = usernamePasswordToken.getUsername();
        String password = new String(usernamePasswordToken.getPassword());
        //查找数据库中的用户信息
        User user = userService.findByMobile(userName);
        //登录成功
        if (user != null && user.getPassword().equals(password)) {
            //构造安全数据并返回（安全数据：用户基本数据，权限信息 profileResult）
            ProfileResult result = null;
            //普通用户
            if ("user".equals(user.getLevel())) {
                result = new ProfileResult(user);
            } else {
                Map map = new HashMap();
                //企业管理员
                if ("coAdmin".equals(user.getLevel())) {
                    map.put("enVisible", "1");
                }
                List<Permission> list = permissionService.findAll(map);
                result = new ProfileResult(user, list);
            }
            //构造认证通过info：安全数据，密码，realm域名
            SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(result,user.getPassword(),this.getName());
            return info;
        }
        //返回null，会抛出异常，标识用户名和密码不匹配
        return null;
    }
}
