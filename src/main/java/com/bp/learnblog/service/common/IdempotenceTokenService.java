package com.bp.learnblog.service.common;

import javax.servlet.http.HttpServletRequest;

/**
 * 接口幂等性 token（requestId）
 *
 * @author DH
 */
public interface IdempotenceTokenService {
    /**
     * 创建 Token（requestId）
     *
     * @return String
     */
    public String createToken();

    /**
     * 验证Token（requestId）合法性
     *
     * @param request 请求
     */
    public void checkToken(HttpServletRequest request);
}
