package com.lmc.millionServer;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

public class Client {
    private final static String SERVER = "172.16.2.200";

    public void run(int beginPort, int endPort){
        System.out.println("客户端启动中");

        NioEventLoopGroup group = new NioEventLoopGroup();
        Bootstrap bootstrap = new Bootstrap();

        bootstrap.group(group)
                .channel(NioSocketChannel.class)
                .option(ChannelOption.SO_REUSEADDR, true)
                .handler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel ch) throws Exception {
                    }
                });

        int index= 0;
        int finalPort;
        while(true){
            finalPort = beginPort + index;
            try {
                bootstrap.connect(SERVER,finalPort).addListener((ChannelFutureListener) future -> {
                    if(!future.isSuccess()){
                        System.err.println("客户端创建连接失败");
                    }
                }).get();
            } catch (Exception e) {
                e.printStackTrace();
            }

            ++index;
            if(index == (endPort - beginPort)){
                index = 0;
            }
        }


    }

    public static void main(String[] args) {
        new Client().run(Config.BEGIN_PORT, Config.END_PORT);
    }
}
