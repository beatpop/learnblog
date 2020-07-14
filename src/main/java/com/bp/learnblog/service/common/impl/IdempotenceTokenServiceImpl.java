package com.bp.learnblog.service.common.impl;

import com.bp.learnblog.exception.IdempotenceException;
import com.bp.learnblog.service.common.IdempotenceTokenService;
import com.bp.learnblog.util.RedisUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.UUID;

/**
 * 接口幂等性 token（requestId）
 *
 * @author DH
 */
@Slf4j
@Service
public class IdempotenceTokenServiceImpl implements IdempotenceTokenService {
    /**
     * 请求头或请求参数中 token 的名称为 requestId
     */
    private static final String TOKEN_NAME = "requestId";

    /**
     * token 前缀
     */
    private static final String TOKEN_PREFIX = "token:";

    @Resource
    private RedisUtil redisUtil;

    /**
     * 创建 Token（requestId）
     *
     * @return String
     */
    @Override
    public String createToken() {
        String strUuid = UUID.randomUUID().toString();
        StringBuilder token = new StringBuilder();
        token.append(TOKEN_PREFIX).append(strUuid);

        // 保存到 redis 中 (并设置过期时间为 5 min)
        redisUtil.setEx(token.toString(), token.toString(), 300);

        return token.toString();
    }

    /**
     * 验证Token（requestId）合法性
     *
     * @param request 请求
     */
    @Override
    public void checkToken(HttpServletRequest request) {
        String token = request.getHeader(TOKEN_NAME);
        if (StringUtils.isEmpty(token)) {
            token = request.getParameter(TOKEN_NAME);
            if (StringUtils.isEmpty(token)) {
                log.warn("requestId(token)验证为空！ #request: {}", request);
                throw new IdempotenceException("requestId验证为空！");
            }
        }

        // token 已失效
        if (!redisUtil.exists(token)) {
            log.warn("requestId(token)已失效，请刷新再重新操作！ #request: {}", request.getParameterMap());
            throw new IdempotenceException("requestId(token)已失效，请刷新再重新操作！");
        }

        // token 验证有误
        if (!token.equals(redisUtil.get(token))) {
            log.warn("requestId(token)验证有误，请重新操作！ #request: {}", request.getParameterMap());
            throw new IdempotenceException("requestId(token)验证有误，请重新操作！");
        }

        // 删除 token 并判断是否成功
        Long delNum = redisUtil.del(token);
        if (delNum <= 0L) {
            log.warn("系统有误，请重新操作！删除幂等性requestId(token)=={}有误, #request: {}",
                    token, request.getParameterMap());
            throw new IdempotenceException("系统有误，请重新操作！");
        }
    }
}
