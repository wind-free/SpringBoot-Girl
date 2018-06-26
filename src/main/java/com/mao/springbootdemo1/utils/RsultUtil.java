package com.mao.springbootdemo1.utils;

import com.mao.springbootdemo1.domain.Result;

/**
 * create by maoxiangyu
 */
public class RsultUtil {

    public static Result success(Object object){
        Result result=new Result();
        result.setCode(0);
        result.setMsg("成功");
        result.setData(object);
        return result;
    }

    public static  Result success(){
        return success(null);
    }

    public static Result erro(Integer code,String msg){
        Result result=new Result();
        result.setCode(code);
        result.setMsg(msg);
        return result;
    }
}
