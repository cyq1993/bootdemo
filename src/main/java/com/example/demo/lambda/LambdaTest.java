package com.example.demo.lambda;

import java.util.Optional;
import java.util.Random;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

/**
 * @description:
 * @author: cyq
 * @create: 2023/5/4 19:51
 */
public class LambdaTest {
    public static void main(String[] args) {
        Append a = (s1, s2) -> {
            String s = s1 + s2;
            return s;
        };
        Append a1 = (s1, s2) -> s1 + s2;

        Supplier();

        Predicate();

        Function();

    }

    /*
     *java.util.function.Supplier 是 Java 8 中的一个函数式接口，
     * 它定义了一个无参的方法 get()，用于获取一个泛型类型的返回值
     * */
    public static void Supplier() {
        // 使用 Supplier 生成随机数
        Supplier<Integer> supplier = () -> new Random().nextInt(100);
        int randomNum = supplier.get();
        System.out.println("生成随机数 " + randomNum);

        // 使用 Supplier 处理空对象
        String str = null;
        Optional.ofNullable(str).orElseGet(() -> {
         String ss = "ccc";
         return ss;
        });
        System.out.println("result的值 " + str);

    }

    public static void Predicate(){
        Predicate<Integer> predicate = (num) -> num > 0;
        boolean res = predicate.test(100);
        System.out.println(res);
    }

    public static void Function(){
        Function<Integer, String> function = (num) -> num > 0 ? "是正数":"是负数";
        //function和func1等价 方法体中的代码一行时，可以省略大括号
        Function<Integer, String> function1 = (num) -> {
            if(num > 0){
                return "是正数";
            }   else {
                return "是负数";
            }
        };

        String res = function.apply(100);
        System.out.println(res);
    }


}
