package com.ithWind.toolkit;

import org.mindrot.jbcrypt.BCrypt;

public final class PasswordUtil {
    public static String encryptPassword(String plainPassword) {
        // workFactor是一个可选参数，用来控制加密强度（计算时间和复杂度），数值越大越安全
        return BCrypt.hashpw(plainPassword, BCrypt.gensalt());
    }

    // 验证密码是否正确
    public static boolean validatePassword(String plainPassword, String encryptedPasswordFromDatabase) {
        return BCrypt.checkpw(plainPassword, encryptedPasswordFromDatabase);
    }

    public static void main(String[] args) {
        String plainPassword = "123456";

        // 加密密码
        String encryptedPassword = encryptPassword(plainPassword);
        System.out.println("Encrypted password: " + encryptedPassword);

        // 验证密码
        boolean isValid = validatePassword(plainPassword, encryptedPassword);
        System.out.println("Is password valid?: " + isValid); // 应输出true
    }
}
