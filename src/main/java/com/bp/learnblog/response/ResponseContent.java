package com.bp.learnblog.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 响应数据格式
 * @author DH
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ResponseContent<T> {
    /**
     * 响应码(默认为0(成功))
     */
    private Integer code = 0;

    /**
     * 响应信息
     */
    private String msg;

    /**
     * 响应数据
     */
    private T result;
}
