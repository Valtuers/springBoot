package com.lmc.bio;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Date;

public class Handler implements Runnable {

    private Socket socket;

    public Handler(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        BufferedReader in = null;
        PrintWriter out = null;

        try {
            in = new BufferedReader(new InputStreamReader(this.socket.getInputStream(), "UTF-8"));
            out = new PrintWriter(new OutputStreamWriter(this.socket.getOutputStream(), "UTF-8"),
                    true);

            String body = null;

            while((body = in.readLine()) != null && body.length() != 0){
                System.out.println("服务器接收到:"+body);
                out.println(new Date());
            }
        } catch (Exception e){
            e.printStackTrace();
        } finally {
            try{
                if(in != null){
                    in.close();
                }
                if(out != null){
                    out.close();
                }
                if(this.socket != null){
                    this.socket.close();
                }
            } catch (Exception e){
                e.printStackTrace();
            }
        }
    }
}
