package com.ithWind.common;

import com.ithWind.domain.dto.UserInfo;

import java.util.Optional;

/**
 * 获取用户上下文
 */
public final class UserContext {
    // 使用ThreadLocal来存储每个线程的用户上下文，确保每个请求独立
    private static final ThreadLocal<UserInfo> currentUser = new ThreadLocal<>();

    /**
     * 设置当前登录用户
     * @param user 登录用户对象
     */
    public static void setCurrentUser(UserInfo user) {
        currentUser.set(user);
    }
    /**
     * 获取当前登录用户
     * @return 如果已登录则返回Optional.of(user)，否则返回Optional.empty()
     */
    public static Optional<UserInfo> getCurrentUser() {
        return Optional.ofNullable(currentUser.get());
    }
    /**
     * 清除当前登录用户信息
     */
    public static void clearCurrentUser() {
        currentUser.remove();
    }

}
