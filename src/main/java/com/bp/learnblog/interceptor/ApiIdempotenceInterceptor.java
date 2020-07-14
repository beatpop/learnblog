package com.bp.learnblog.interceptor;

import com.bp.learnblog.annotation.ApiIdempotence;
import com.bp.learnblog.service.common.IdempotenceTokenService;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

/**
 * 幂等性接口拦截器
 *
 * @author DH
 */
public class ApiIdempotenceInterceptor implements HandlerInterceptor {
    @Resource
    private IdempotenceTokenService tokenService;

    /**
     * 校验是否有接口幂等性注解 @ApiIdempotence 并进行处理
     * @param request
     * @param response
     * @param handler
     * @return
     * @throws Exception
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        if (!(handler instanceof HandlerMethod)) {
            return true;
        }

        HandlerMethod handlerMethod = (HandlerMethod) handler;
        Method method = handlerMethod.getMethod();
        // 获取方法中是否带有接口幂等性的注解 @ApiIdempotence 并处理
        ApiIdempotence methodAnnotation = method.getAnnotation(ApiIdempotence.class);
        if (methodAnnotation != null) {
            tokenService.checkToken(request);
        }

        return true;
    }
}
