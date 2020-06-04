package com.bp.learnblog.config.shiro;

import com.bp.learnblog.entity.admin.Permission;
import com.bp.learnblog.entity.admin.Role;
import com.bp.learnblog.entity.admin.User;
import com.bp.learnblog.service.admin.UserService;
import com.bp.learnblog.util.JwtUtil;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

import javax.annotation.Resource;

/**
 * 用户信息验证及处理 Realm
 *
 * @author DH
 */
public class JwtRealm extends AuthorizingRealm {
    @Resource
    private UserService userService;

    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof JwtToken;
    }

    /**
     * 获取用户的角色，权限信息保存到 SimpleAuthorizationInfo
     *
     * @param principalCollection 认证信息
     * @return SimpleAuthorizationInfo
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        // 获取当前用户信息
        String username = JwtUtil.getUsername(principalCollection.toString());
        User user = null;
        if (!"".equals(username) && username != null) {
            user = userService.findByUsername(username);
        }
        // 把当前的用户角色及权限信息保存到 SimpleAuthorizationInfo 中
        SimpleAuthorizationInfo authenticationInfo = new SimpleAuthorizationInfo();
        if (user != null && user.getRoles().size() > 0) {
            for (Role role : user.getRoles()) {
                authenticationInfo.addRole(role.getName());
                if (role.getPermissions().size() > 0) {
                    for (Permission permission : role.getPermissions()) {
                        authenticationInfo.addStringPermission(permission.getName());
                    }
                }
            }
        }

        return authenticationInfo;
    }

    /**
     * 进行身份认证（验证用户输入的账号和密码是否正确）
     *
     * @param authenticationToken token令牌
     * @return SimpleAuthenticationInfo
     * @throws AuthenticationException exception
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken)
            throws AuthenticationException {
        // 根据token解密获取用户名
        String token = (String) authenticationToken.getCredentials();
        String username = JwtUtil.getUsername(token);

        // token无效（非法）
        if (username == null) {
            throw new AuthenticationException("Token invalid!");
        }

        // 查找用户并判断
        User user = userService.findByUsername(username);
        if (user == null) {
            throw new AuthenticationException("User didn't existed!");
        }

        // token失效
        if (!JwtUtil.verify(token, username, user.getPassword())) {
            throw new AuthenticationException("Token invalid or expired!");
        }

        return new SimpleAuthenticationInfo(token, token, username);
    }
}
