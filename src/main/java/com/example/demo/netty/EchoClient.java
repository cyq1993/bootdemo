package com.example.demo.netty;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;
import io.netty.buffer.PooledByteBufAllocator;
import io.netty.buffer.UnpooledByteBufAllocator;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;

import java.net.InetSocketAddress;
import java.util.Scanner;

/**
 * @description:
 * @author: cyq
 * @create: 2023/4/14 15:57
 */
public class EchoClient {
    public static void main(String[] args) throws Exception {
        EventLoopGroup group = new NioEventLoopGroup();
        try {
            Bootstrap bootstrap = new Bootstrap()
                    .group(group)
                    .channel(NioSocketChannel.class)
                    .option(ChannelOption.SO_BACKLOG, 10240)
                    .option(ChannelOption.ALLOCATOR, PooledByteBufAllocator.DEFAULT) // 使用内存池
                    .remoteAddress(new InetSocketAddress("localhost", 8080))
                    .handler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        public void initChannel(SocketChannel ch) throws Exception {
                            ch.pipeline().addLast(new ClientHandler());
                        }
                    });
            ChannelFuture future = bootstrap.connect().sync();
            Scanner scanner = new Scanner(System.in);
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                future.channel().writeAndFlush(PooledByteBufAllocator.DEFAULT.buffer(line.getBytes().length).writeBytes(line.getBytes()));
            }
        } finally {
            group.shutdownGracefully().sync();
        }

    }

}
class ClientHandler extends SimpleChannelInboundHandler<ByteBuf> {
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, ByteBuf msg) throws Exception {
        byte[] data = new byte[msg.readableBytes()];
        msg.readBytes(data);
        System.out.println("Received message from server: " + new String(data));
        System.out.println(" ByteBufAllocator.DEFAULT " + ByteBufAllocator.DEFAULT.buffer());
        System.out.println(" PooledByteBufAllocator.DEFAULT " + PooledByteBufAllocator.DEFAULT.buffer());
        System.out.println(" UnpooledByteBufAllocator.DEFAULT " + UnpooledByteBufAllocator.DEFAULT.buffer());
        System.out.println(" " + ctx.channel().alloc().heapBuffer());

    }
}

