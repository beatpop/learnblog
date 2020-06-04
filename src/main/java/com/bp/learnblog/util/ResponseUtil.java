package com.bp.learnblog.util;

import com.bp.learnblog.response.ResponseContent;

/**
 * 响请求应格式
 * @author DH
 */
public class ResponseUtil {
    /**
     * 响应成功(带有数据)
     * @param object 响应数据
     * @return ResponseContent
     */
    public static ResponseContent success(Object object) {
        return new ResponseContent(0, "success", object);
    }

    /**
     * 响应成功(无数据)
     * @return ResponseContent
     */
    public static ResponseContent success() {
        return success(null);
    }

    /**
     * 响应失败(并返回错误码及错误内容)
     * @param code 错误码
     * @param msg 错误信息
     * @return ResponseContent
     */
    public static ResponseContent error(Integer code, String msg) {
        return new ResponseContent(code, msg, null);
    }
}
