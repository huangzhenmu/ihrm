package com.ihrm.controller;

import com.ihrm.common.controller.BaseController;
import com.ihrm.common.entity.PageResult;
import com.ihrm.common.entity.Result;
import com.ihrm.common.entity.ResultCode;
import com.ihrm.common.exception.CommonException;
import com.ihrm.common.utils.JwtUtils;
import com.ihrm.common.utils.PermissionConstants;
import com.ihrm.service.UserService;
import com.ihrm.system.Permission;
import com.ihrm.system.Role;
import com.ihrm.system.User;
import com.ihrm.system.response.ProfileResult;
import io.jsonwebtoken.Claims;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("sys")
public class UserController extends BaseController {
    @Autowired
    private UserService userService;
    @Autowired
    private JwtUtils jwtUtils;

    /**
     * 用户登录
     *
     * @param loginMap
     * @return
     */
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public Result login(@RequestBody Map<String, String> loginMap) {
        String mobile = loginMap.get("mobile");
        String password = loginMap.get("password");
        try {
            password = new Md5Hash(password,mobile,3).toString();//密码，盐，加密次数
            //构造token
            UsernamePasswordToken token = new UsernamePasswordToken(mobile,password);
            //获取用户subject
            Subject subject = SecurityUtils.getSubject();
            //登录验证 使用自定义的realm验证
            subject.login(token);
            //获取sessionId
            Serializable id = subject.getSession().getId();
            //返回成功结果
            return new Result(ResultCode.SUCCESS,id);
        }catch (Exception e){
            return new Result(ResultCode.MOBILEORPASSWORDERROR);
        }
    }

    /**
     * 用户登录成功之后，获取用户信息
     * 1.获取用户id
     * 2.根据用户id查询用户
     *3.构建返回值对象
     * 4.响应
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping(value="/profile",method = RequestMethod.POST)
    public Result profile(HttpServletRequest request) throws Exception {
        //1.获取请求头信息：名称=Authorization
        String authorization = request.getHeader("Authorization");
        if(StringUtils.isEmpty(authorization)) {
            throw new CommonException(ResultCode.UNAUTHENTICATED);
        }
        //2.替换Bearer+空格
        String token = authorization.replace("Bearer ","");
        //3.解析token
        Claims claims = jwtUtils.parseJwt(token);
        String userid = claims.getId();
        User user = userService.findById(userid);
        ProfileResult result = new ProfileResult(user);
        return new Result(ResultCode.SUCCESS,result);
    }

    //保存用户    
    @RequestMapping(value = "/user", method = RequestMethod.POST)
    public Result add(@RequestBody User user) throws Exception {
        user.setCompanyId(parseCompanyId());
        user.setCompanyName(parseCompanyName());
        userService.save(user);
        return Result.SUCCESS();
    }

    //更新用户
    @RequestMapping(value = "/user/{id}", method = RequestMethod.PUT)
    public Result update(@PathVariable(name = "id") String id, @RequestBody User user) throws Exception {
        user.setId(id);
        userService.update(user);
        return Result.SUCCESS();
    }

    //删除用户
    @RequiresPermissions("API-USER-DELETE")//基于注解的权限控制  只有拥有该权限才可以执行该操作
    @RequestMapping(value = "/user/{id}", method = RequestMethod.DELETE)
    public Result delete(@PathVariable(name = "id") String id) throws Exception {
        userService.delete(id);
        return Result.SUCCESS();
    }

    /**     * 根据ID查询用户     */
    @RequestMapping(value = "/user/{id}", method = RequestMethod.GET)
    public Result findById(@PathVariable(name = "id") String id) throws Exception {
        User user = userService.findById(id);
        return new Result(ResultCode.SUCCESS,user);
    }

    /**     * 分页查询用户     */
    @RequestMapping(value = "/user", method = RequestMethod.GET)
    public Result findByPage(int page,int pagesize,@RequestParam Map<String,Object> map) throws Exception {
        map.put("companyId",parseCompanyId());
        Page<User> searchPage = userService.findSearch(map, page, pagesize);
        PageResult<User> pr = new PageResult(searchPage.getTotalElements(),searchPage.getContent());
        return new Result(ResultCode.SUCCESS,pr);
    }

    /**
     * 分配角色
     * @param map
     * @return
     */
    @RequestMapping(value = "/user/assignRoles", method = RequestMethod.PUT)
    public Result assignRoles(@RequestBody Map<String,Object> map) {
        //1.获取被分配的用户id        
        String userId = (String) map.get("id");
        //2.获取到角色的id列表
        List<String> roleIds = (List<String>) map.get("roleIds");
        //3.调用service完成角色分配        
        userService.assignRoles(userId,roleIds);
        return new Result(ResultCode.SUCCESS);
    }
}
