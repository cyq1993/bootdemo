package com.example.demo.javaee;

/**
 * @description: 枚举测试
 * @author: cyq
 * @create: 2023/4/25 17:21
 */
public enum TestEnum {
    TEST1;
    public static void main(String[] args) {
        System.out.println(TEST1.getClass());
        TEST1.log();
    }

    public static void log(){
        System.out.println("枚举类的静态方法执行");
    }
}
