package com.example.demo.netty.bytebuf;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.PooledByteBufAllocator;
import io.netty.buffer.UnpooledByteBufAllocator;
import io.netty.util.ReferenceCountUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * @description: ByteBuf 的操作
 * @author: cyq
 * @create: 2023/4/24 11:36
 */
public class ByteBufOperation {
    public static void main(String[] args) throws InterruptedException {
        test1();

    }

    /**
     * 池化的ByteBufAllocator(PooledByteBufAllocator)
     * 不论是调用heapBuffer()还是directBuffer() 都会触发内存泄露
     * 因为池化分配始终保持对开辟的ByteBuf的引用。
     *
     * */
    public static void test1() throws InterruptedException {
        ByteBuf buf = PooledByteBufAllocator.DEFAULT.heapBuffer(1024);
        ReferenceCountUtil.release(buf);
        buf = null;
        System.gc();
        Thread.sleep(2000);
        PooledByteBufAllocator.DEFAULT.heapBuffer(1024);
    }

    public static void test2(){
        //ByteBuf 的基本操作
        ByteBuf buf = PooledByteBufAllocator.DEFAULT.buffer(1024);
        byte[] bytes = new String("你好").getBytes();
        System.out.println("" + buf.isReadable());
        buf.writeBytes(bytes);
        System.out.println("" + buf.isReadable());
        buf.readBytes(bytes);
        System.out.println(new String(bytes));
    }
}
