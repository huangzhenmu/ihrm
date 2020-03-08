package com.ihrm.common.exception;

import com.ihrm.common.entity.Result;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class BaseExceptionHandler {
    @ResponseBody
    @ExceptionHandler(value = Exception.class)
    public Result error(HttpServletRequest request, HttpServletResponse response,Exception e) throws IOException{
        e.printStackTrace();
        if (e.getClass() == CommonException.class){
            CommonException ce = (CommonException)e;
            return new Result(ce.getCode());
        }else {
            return Result.SERVER_ERROR();
        }
    }
}
