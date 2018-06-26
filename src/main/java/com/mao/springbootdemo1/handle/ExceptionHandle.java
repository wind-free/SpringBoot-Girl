package com.mao.springbootdemo1.handle;

import com.mao.springbootdemo1.domain.Result;
import com.mao.springbootdemo1.exception.GirlException;
import com.mao.springbootdemo1.utils.RsultUtil;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * create by maoxiangyu
 */
@ControllerAdvice
public class ExceptionHandle {
    private final static Logger logger=LoggerFactory.getLogger(ExceptionHandle.class);
    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public Result handle(Exception e){
        if (e instanceof GirlException ){
            GirlException girlException=(GirlException)e;
            return RsultUtil.erro(girlException.getCode(),girlException.getMessage());
        }else{
            logger.error("系统异常{}",e);
            return RsultUtil.erro(-1,"未知错误");
        }
    }
}
