package com.ihrm.common.controller;

import com.ihrm.common.entity.Result;
import com.ihrm.common.entity.ResultCode;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 配置公共的错误认证控制器
 */
@RestController
@CrossOrigin
public class ErrorController {
    //公共错误跳转
    @RequestMapping(value="autherror")
    public Result autherror(int code) {
        //code = 1：未登录 否则是权限不足
        return code ==1?new Result(ResultCode.UNAUTHENTICATED):new Result(ResultCode.UNAUTHORISE);
    }
}
