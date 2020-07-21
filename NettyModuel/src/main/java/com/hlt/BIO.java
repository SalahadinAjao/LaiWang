package com.hlt;

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Arrays;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @Author: houlintao
 * @Date:2020/7/20 下午5:03
 * @email 437547058@qq.com
 * @Version 1.0
 */
public class BIO {

    public static void main(String[] args) throws IOException {
        /**
         * 创建一个线程池，如果有客户端连接就创建一个线程与之通信
         */
        ExecutorService threadPool = Executors.newCachedThreadPool();
        //创建ServerSocket
        ServerSocket serverSocket = new ServerSocket(6666);
        System.out.println("服务器已启动!");

        while (true){
            //监听，等待客户端连接
            final Socket clientSocket = serverSocket.accept();
            System.out.println("有客户端连接了");
            threadPool.execute(new Runnable() {
                @Override
                public void run() {
                    handler(clientSocket);
                }
            });
        }
    }

    public static void handler(Socket socket){

        byte[] bytes = new byte[1024];

        String name = Thread.currentThread().getName();
        System.out.println("当前所在线程="+name);

        try {
            InputStream inputStream = socket.getInputStream();

            while (true){
                int read = inputStream.read(bytes);
                if (read != -1){
                    System.out.println("输出客户端发送的数据："+new String(bytes,0,read));
                }else {
                    break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
