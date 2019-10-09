package com.lmc.bio;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class BioServer {

    private static final int PORT = 8080;

    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket(PORT);
            System.out.println("服务开启");
            Socket socket = null;
            while (true){
                socket = serverSocket.accept();
                if(socket != null){
                    System.out.println("服务连接");
                    new Thread(new Handler(socket)).start();
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if(serverSocket != null){
                System.out.println("服务关闭");
                serverSocket.close();
            }
        }
    }
}
