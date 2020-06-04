package com.bp.learnblog.controller;

import com.bp.learnblog.response.ResponseContent;
import com.bp.learnblog.util.ResponseUtil;
import org.apache.shiro.ShiroException;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authz.UnauthorizedException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.sql.SQLException;

/**
 * 异常控制器
 *
 * @author DH
 */
@RestControllerAdvice
public class ExceptionController {
    /**
     * Shiro异常
     *
     * @param e
     * @return
     */
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler(ShiroException.class)
    public ResponseContent handle401(ShiroException e) {
        return ResponseUtil.error(401, "您无权限访问！");
    }

    /**
     * 验证不通过异常
     * @param e
     * @return
     */
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler(UnauthorizedException.class)
    public ResponseContent handle401(UnauthorizedException e) {
        return ResponseUtil.error(401, e.getMessage());
    }

    /**
     * 验证异常
     * @param e
     * @return
     */
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler(AuthenticationException.class)
    public ResponseContent handleUnAuthentication(AuthenticationException e) {
        return ResponseUtil.error(401, e.getMessage());
    }

    /**
     * SQL异常
     * @param e
     * @return
     */
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(SQLException.class)
    public ResponseContent handleSqlError(SQLException e) {
        return ResponseUtil.error(500, e.getMessage());
    }
}
