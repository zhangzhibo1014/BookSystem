package com.robin.util;


import java.util.HashMap;
import java.util.Map;

/**
 *  给前端返回信息的自定义类
 */
public class Result {
    // 状态码
    private Integer code;
    // 信息
    private String msg;
    // 数据
    private Map<String, Object> mapResult = new HashMap<String, Object>();

    public static Result success() {
        Result result = new Result();
        result.setCode(200);
        result.setMsg("成功");
        return result;
    }

    public static Result fail() {
        Result result = new Result();
        result.setCode(400);
        result.setMsg("失败");
        return result;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Map<String, Object> getMapResult() {
        return mapResult;
    }

    public void setMapResult(Map<String, Object> mapResult) {
        this.mapResult = mapResult;
    }

    public Result add(String key, Object value) {
        this.getMapResult().put(key, value);
        return this;
    }
}
