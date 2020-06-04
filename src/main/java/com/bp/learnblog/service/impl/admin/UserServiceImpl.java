package com.bp.learnblog.service.impl.admin;

import com.bp.learnblog.dao.admin.UserRepository;
import com.bp.learnblog.entity.admin.User;
import com.bp.learnblog.service.admin.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

/**
 * @author DH
 */
@Slf4j
@Service
public class UserServiceImpl implements UserService {

    /**
     * 定义 salt 的位数
     */
    private static final int SALT_NUM = 16;

    @Resource
    private UserRepository userRepository;

    /**
     * 根据用户名查找用户
     *
     * @param username 用户名
     * @return User
     */
    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username.trim());
    }

    /**
     * 根据id查找用户
     *
     * @param id
     * @return
     */
    @Override
    public User findById(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    /**
     * 查找所有用户
     *
     * @return
     */
    @Override
    public Iterable<User> findAll() {
        return userRepository.findAll();
    }

    /**
     * 校验用户名密码
     *
     * @param username 用户名
     * @param password 密码
     * @return Boolean
     */
    @Override
    public boolean checkUserPassword(String username, String password) {
        if (StringUtils.isEmpty(username) || StringUtils.isEmpty(password)) {
            return false;
        }

        // 查找当前用户并进行密码校验
        User user = this.findByUsername(username);
        return user != null && user.getPassword().equals(generateSha256SecurePassword(password, user.getSalt()));
    }

    /**
     * 创建用户（用户注册）
     *
     * @param user 用户信息
     * @return
     */
    @Override
    public User createUser(User user) {
        if (user != null) {
            User newUser = new User();
            String salt = null;
            try {
                salt = generateSalt();
            } catch (NoSuchAlgorithmException e) {
                log.error("Create new user error - #user:#{} - {}", user.toString(), e.getMessage());
                return null;
            }

            byte[] saltByte = convertSaltToBytes(salt);
            if (user.getId() != null) {
                newUser.setId(user.getId());
            }
            newUser.setUsername(user.getUsername().trim());
            newUser.setSalt(saltByte);
            newUser.setPassword(generateSha256SecurePassword(user.getPassword().trim(), saltByte));
            newUser.setRoles(user.getRoles());
            userRepository.save(newUser);

            return newUser;
        }

        log.warn("user cannot be null when creating a new user...");
        return null;
    }

    /**
     * 更新用户
     *
     * @param user
     * @return
     */
    @Override
    public User updateUser(User user) {
        if (user != null) {
            User updateUser = userRepository.findByUsername(user.getUsername());
            if (updateUser != null) {
                updateUser.setRealName(user.getRealName());
                updateUser.setRoles(user.getRoles());
                userRepository.save(updateUser);

                return updateUser;
            }
        }
        log.warn("updating user is null!");
        return null;
    }

    /**
     * 根据id删除用户
     *
     * @param id
     * @return bool
     */
    @Override
    public boolean deleteById(Long id) {
        try {
            userRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            log.warn("Delete user which id is - " + id + "faithfully. {}" + e.getMessage());
            return false;
        }
    }

    /**
     * 整合明文密码+盐生成SHA256密文密码
     * @param rawPassword 明文密码
     * @param salt 盐
     * @return String
     */
    private String generateSha256SecurePassword(String rawPassword, byte[] salt) {
        String securePassword = null;
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
            messageDigest.update(salt);
            byte[] bytes = messageDigest.digest(rawPassword.getBytes());

            StringBuilder builder = new StringBuilder();
            for (int i = 0, bytesLength = bytes.length; i < bytesLength; i++) {
                // 转16进制进行处理
                builder.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
            }
            securePassword = builder.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        return securePassword;
    }

    /**
     * 生成获取 salt (16位)
     * @return String
     * @throws NoSuchAlgorithmException
     */
    private String generateSalt() throws NoSuchAlgorithmException {
        // 生成强随机数
        SecureRandom secureRandom = SecureRandom.getInstance("SHA1PRNG");

        // 从62个字母数字中随机抽取指定位数（默认16位）组成salt
        String str = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < SALT_NUM; i++) {
            builder.append(str.charAt(secureRandom.nextInt(62)));
        }

        return builder.toString();
    }

    /**
     * 将字符串类型的 salt 转换为 byte[]类型
     * @param salt 盐
     * @return
     */
    private byte[] convertSaltToBytes(String salt) {
        byte[] saltBytes = new byte[SALT_NUM];
        System.arraycopy(salt.getBytes(), 0, saltBytes, 0, SALT_NUM);
        return saltBytes;
    }
}
