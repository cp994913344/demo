package com.ljs.demo.common.response;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 响应工具类
 *
 * @author  liu_js
 * @date 2018-07-05
 *
 */
public class ResponseUtils {

    /**
     * 成功码
     */
    public static final Integer C_SUCCESS_CODE =200;

    /**
     * 失败码
     */
    public static final Integer C_ERROR_CODE = 500;

    public static Map<String, Object> ok() {
        Map<String, Object> map = new LinkedHashMap<String, Object>();
        map.put("code", C_SUCCESS_CODE);
        return map;
    }

    public static Map<String, Object> ok(Object data) {
        Map<String, Object> map = new LinkedHashMap<String, Object>();
        map.put("code", C_SUCCESS_CODE);
        map.put("data", data);
        return map;
    }

    public static Map<String, Object> ok(int total, Object data) {
        Map<String, Object> map = new LinkedHashMap<String, Object>();
        map.put("code", C_SUCCESS_CODE);
        map.put("total", total);
        map.put("data", data);
        return map;
    }

    public static Map<String, Object> error() {
        Map<String, Object> map = new LinkedHashMap<String, Object>();
        map.put("code", C_ERROR_CODE);
        map.put("message", "执行失败");
        return map;
    }

    public static Map<String, Object> error(String msg) {
        Map<String, Object> map = new LinkedHashMap<String, Object>();
        map.put("code", C_ERROR_CODE);
        map.put("message", msg);
        return map;
    }

    public static Map<String, Object> get(String code, String msg, Object data) {
        Map<String, Object> map = new LinkedHashMap<String, Object>();
        map.put("code", code);
        map.put("data", data);
        map.put("message", msg);
        return map;
    }

    public static Map<String, Object> get(String code, String msg, int total, Object data) {
        Map<String, Object> map = new LinkedHashMap<String, Object>();
        map.put("code", code);
        map.put("total", total);
        map.put("data", data);
        map.put("message", msg);
        return map;
    }

    public static int getCurrent(Integer current) {
        if (current == null || current <= 0) {
            return 1;
        }
        return current;
    }

    public static int getSize(Integer size) {
        if (size == null || size <= 0) {
            return 10;
        }
        return size;
    }

    public static int getCurrent(Integer current, Integer size) {
        return (current - 1) * size;
    }

}

