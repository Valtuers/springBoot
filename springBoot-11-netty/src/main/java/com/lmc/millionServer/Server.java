package com.lmc.millionServer;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

public class Server {

    public void run(int beginPort, int endPort){
        System.out.println("服务端启动中");

        EventLoopGroup bossGroup = new NioEventLoopGroup();
        EventLoopGroup workGroup = new NioEventLoopGroup();
        ServerBootstrap serverBootstrap = new ServerBootstrap();

        serverBootstrap.group(bossGroup, workGroup)
                .channel(NioServerSocketChannel.class)
                .childOption(ChannelOption.SO_REUSEADDR, true)

                .childHandler(new TcpCountHandler());

        for(;beginPort<endPort;beginPort++){
            int port = beginPort;
            serverBootstrap.bind(port).addListener((ChannelFutureListener) future->{
                System.out.println("服务端成功绑定接口 port="+port);
            });
        }

    }

    public static void main(String[] args) {
        new Server().run(Config.BEGIN_PORT, Config.END_PORT);
    }
}
