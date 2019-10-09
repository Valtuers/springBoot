package com.lmc.bio;


import java.io.*;
import java.net.Socket;

public class Client {

    private static final int PORT = 8080;
    public static final String HOST = "localhost";

    public static void main(String[] args) {
        Socket socket = null;
        BufferedReader in = null;
        PrintWriter out = null;

        try{
            socket = new Socket(HOST,PORT);
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()),true);
//            String body = null;
//            while((body=in.readLine())!=null){
//                out.println(body);
//                System.out.println(in.readLine());
//            }
            out.println("hahaha");
            System.out.println(in.readLine());



        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try{
                if(in != null){
                    in.close();
                }
                if(out != null){
                    out.close();
                }
                if(socket != null){
                    socket.close();
                }
            } catch (Exception e){
                e.printStackTrace();
            }
        }

    }
}
