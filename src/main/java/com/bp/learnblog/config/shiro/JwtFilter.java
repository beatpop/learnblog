package com.bp.learnblog.config.shiro;

import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.web.filter.authc.BasicHttpAuthenticationFilter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;

/**
 * 自定义继承 BasicHttpAuthenticationFilter 的过滤器
 *
 * @author DH
 */
@Slf4j
public class JwtFilter extends BasicHttpAuthenticationFilter {
    /**
     * 定义Token请求头前缀
     */
    private static final String AUTHORIZATION_HEADER = "Authorization";

    /**
     * 定义请求内容类型
     */
    private static final String CONTENT_TYPE = "Content-Type";

    /**
     * 判断请求头是否有token（只需要判断是否带有 Authorization 请求头参数）
     *
     * @param request 请求
     * @param response 响应
     * @return Boolean
     */
    @Override
    protected boolean isLoginAttempt(ServletRequest request, ServletResponse response) {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        String authorizationHeader = httpServletRequest.getHeader(AUTHORIZATION_HEADER);
        return authorizationHeader != null;
    }

    /**
     * 判断是否有token请求头并处理登录检查token
     *
     * @param request 请求
     * @param response 响应
     * @param mappedValue
     * @return Boolean
     */
    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) {
        // 首先判断是否有Token
        if (isLoginAttempt(request, response)) {
            try {
                // 有token则进行executeLogin()登录检查token
                executeLogin(request, response);
                return true;
            } catch (Exception e) {
                unAuthorizeResponseError(response, e.getMessage());
                return false;
            }
        }

        // 请求头不存在token，则可能是游客登录或无需验证的URL请求
        return true;
    }

    /**
     * 进行登录检查token
     *
     * @param request 请求
     * @param response 响应
     * @return Boolean
     * @throws Exception 异常抛出处理
     */
    @Override
    protected boolean executeLogin(ServletRequest request, ServletResponse response) throws Exception {
        String token = ((HttpServletRequest) request).getHeader(AUTHORIZATION_HEADER);
        JwtToken jwtToken = new JwtToken(token);

        // 提交 Realm 进行登录处理，若报错则会抛出异常
        getSubject(request, response).login(jwtToken);

        // 无报错则登录成功
        return true;
    }

    /**
     * 进行跨域支持处理
     *
     * @param request 请求
     * @param response 响应
     * @return Boolean
     * @throws Exception 异常
     */
    @Override
    protected boolean preHandle(ServletRequest request, ServletResponse response) throws Exception {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;
        // 设置允许请求的来源域名（*表示全部）
        httpServletResponse.setHeader("Access-Control-Allow-Origin", "*");
        // 设置允许的请求方法
        httpServletResponse.setHeader("Access-Control-Allow-Methods", "GET,POST,PUT,PATCH,OPTIONS,DELETE");
        // 设置允许的请求头
        httpServletResponse.setHeader("Access-Control-Allow-Headers", AUTHORIZATION_HEADER + "," + CONTENT_TYPE);
        // 跨域时会首先发送一个OPTION请求，这里OPTION请求返回正常状态
        if (httpServletRequest.getMethod().equals(RequestMethod.OPTIONS.name())) {
            httpServletResponse.setStatus(HttpStatus.OK.value());
            return false;
        }
        return super.preHandle(request, response);
    }

    /**
     * 将非法请求跳转到 /unauthorized/**
     *
     * @param response 响应
     * @param message 错误信息
     */
    private void unAuthorizeResponseError(ServletResponse response, String message) {
        try {
            HttpServletResponse httpServletResponse = (HttpServletResponse) response;
            //设置编码，否则中文字符在重定向时会变为空字符串
            message = URLEncoder.encode(message, "UTF-8");
            httpServletResponse.sendRedirect("/unauthorized/" + message);
        } catch (IOException e) {
            log.error(e.getMessage());
        }
    }
}
