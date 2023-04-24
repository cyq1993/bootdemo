package com.example.demo;

/**
 * @description: 类初始化属性 先静态 再动态
 * @author: cyq
 * @create: 2023/3/29 19:14
 */
public class ClassInitOrder {

    private  TestB b = new TestB();
    {
        System.out.println("执行普通代码块");
    }
    static {
        System.out.println("执行静态代码块");
    }
    private static TestA a = new TestA();
    public ClassInitOrder() {
        System.out.println("执行构造函数");
    }

    public static void main(String[] args) {

    }
}

class TestA{
    public TestA() {
        System.out.println(this.getClass().getName() + "执行构造函数");
    }
}

class TestB{
    public TestB() {
        System.out.println(this.getClass().getName() + "执行构造函数");
    }
}
