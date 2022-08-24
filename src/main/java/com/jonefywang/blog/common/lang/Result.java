package com.jonefywang.blog.common.lang;

import lombok.Data;

import java.io.Serializable;
import java.sql.SQLType;

/**
 * @ClassName Result
 * @Description 统一异常结果
 * @Author 19285
 * @Date 2022/8/24 22:20
 * @Version 1.0
 */
@Data
public class Result implements Serializable {
    private int code;
    private String msg;
    private Object data;

    /**
     * 成功信息结果集一次 封装
     * @param data
     * @return
     */
    public static Result success( Object data) {
        return success(200,"操作成功",data);

    }

    /**
     * 成功信息结果集
     * @param code
     * @param msg
     * @param data
     * @return
     */
    public static Result success(int code, String msg, Object data) {
        Result result = new Result();
        result.setCode(code);
        result.setMsg(msg);
        result.setData(data);
        return result;
    }

    /**
     * 失败消息结果集二次封装
     * @param msg
     * @return
     */
    public static Result fail(String msg) {
        return fail(400, msg, null);
    }

    /**
     * 失败消息结果集一次封装
     * @param msg
     * @param data
     * @return
     */
    public static Result fail(String msg, Object data) {
        return fail(400, msg, data);
    }

    /**
     * 失败消息结果集
     * @param code
     * @param msg
     * @param data
     * @return
     */
    public static Result fail(int code, String msg, Object data) {
        Result result = new Result();
        result.setCode(code);
        result.setMsg(msg);
        result.setData(data);
        return result;
    }
}
