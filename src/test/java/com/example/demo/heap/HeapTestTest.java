package com.example.demo.heap;

import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @description:
 * @author: cyq
 * @create: 2023/4/24 16:09
 */
class HeapTestTest {

    @Test
    void test1() {
        Set<Person> set = new HashSet<>();
        Person p1 = new Person("唐僧", 25);
        Person p2 = new Person("孙悟空", 26);
        Person p3 = new Person("猪八戒", 27);
        set.add(p1);
        set.add(p2);
        set.add(p3);
        System.out.println("总共有:" + set.size() + " 个元素!"); //结果：总共有:3 个元素!
        p3.setAge(2); //修改p3的年龄,此时p3元素对应的hashcode值发生改变
        set.remove(p3); //此时remove不掉，造成内存泄漏
        set.add(p3); //重新添加，居然添加成功
        System.out.println("总共有:" + set.size() + " 个元素!"); //结果：总共有:4 个元素!
        for (Person person : set) {
            System.out.println(person);
        }
    }
    @Test
     void test2() {
        Vector v = new Vector(10);
        for (int i = 1; i < 100; i++) {
            Object o = new Object();
            v.add(o);
            o = null;
        }
        v.clear();
    }
}