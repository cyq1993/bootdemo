package com.example.demo.netty.bytebuf;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.PooledByteBufAllocator;
import org.junit.jupiter.api.Test;

import java.nio.charset.CharsetDecoder;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @description:
 * @author: cyq
 * @create: 2023/4/25 10:52
 */
class ByteBufOperationTest {

    @Test
    void test1() {
    }

    @Test
    void test2() {
        //ByteBuf 的基本操作
        ByteBuf buf = PooledByteBufAllocator.DEFAULT.buffer(1024);
        byte[] bytes = new String("你好").getBytes();
        System.out.println("" + buf.isReadable());
        buf.writeBytes(bytes);
        System.out.println("" + buf.isReadable());
        byte[] bytes1 = new byte[buf.readableBytes()];
        buf.readBytes(bytes1);
        System.out.println(new String(bytes1));
        System.out.println("" + buf.isReadable());
        byte[] bytes2 = new String("，世界").getBytes();
        buf.writeBytes(bytes2);


    }
}