package com.lrs.common.auth;

import java.io.Console;
import java.util.Arrays;

/**
 * 在java 1.6以后可以使用Console对象对控制台进行输出，获取控制台的输入
 *
 * @author Swedish-li
 */
public class ConsoleTest {
    public static void main(String[] args) {
        Console console = null;
        char[] passwords = null;
        if ((console = System.console()) != null) {
            console.printf("Testing password%n");
            // -------------------------
            // 在控制台以 `Password:` 的格式等待密码输入，且密码不回显
            passwords = console.readPassword("%s", "Password:");
            // TODO 业务逻辑处理

            // 处理完成后进行密码清理，减少密码在内存中的保存时间
            if (passwords != null) {
                Arrays.fill(passwords, ' ');
            }
        } else {
            // 在Eclipse中运行此方法，eclipse占用了jvm
            // 的控制台，并且该对象是单例，因此无法再Eclipse中获取Console对象
            System.out.println("无法获取Console！");
        }

        System.out.println(Arrays.toString(passwords));
    }
}
