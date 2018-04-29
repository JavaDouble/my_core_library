package com.learn.javase.chat_tcp;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

/**
 * 聊天室客户端
 * @author Double
 *
 */
public class Client {
	/*
	 * 套接字 java.net.Socket
	 * 封装了TCP协议，使用其进行网络通讯，Socket是运行在客户端的
	 */
	private Socket socket;

	/**
	 * 构造方法，用来初始化客户端
	 * @throws Exception
	 */
	public Client() throws Exception{
		try {
			/*
			 * 实例化Socket时需要传入两个参数：参数1:服务端的ip地址	参数2:服务端的端口号
			 * 通过IP可以连接到服务端计算机，通过端口连接到运行在服务端计算机上的服务端应用程序。
			 * 创建Socket的过程就是连接的过程，所以若服务端没有响应，这里会抛出异常。
			 *
			 * 服务器ip和端口：ip是用来区别计算机 唯一的  端口是用来区别计算机上的那个应用程序
			 * host本地ip:localhost/127.0.0.1 port:0~65535 一般写四位数字 8000以后的数字
			 * 4000以前的一般都绑定了知名厂商流行软件，容易起冲突。
			 */
			System.out.println("正在连接服务端...");
			socket = new Socket("localhost",8088);
			System.out.println("已连接服务端!");
		} catch (Exception e) {
			System.out.println("客户端初始化失败!");
			throw e;
		}
	}

	/**
	 * 启动客户端的方法
	 */
	public void start(){
		try {
			Scanner scanner = new Scanner(System.in);
			//先要求用户输入一个昵称
			String nickName = null;
			while(true){
				System.out.println("请输入昵称:");
				nickName = scanner.nextLine();
				if(nickName.length()>0){
					break;
				}
				System.out.println("至少输入一个字符.");
			}

			/*
			 * 若想向服务端发送信息，需要通过socket获取输出流，通过该流写出的数据就会通过网络发送至服务端了。
			 */
			OutputStream out=socket.getOutputStream();
			System.out.println(out.getClass().getName());//java.net.SocketOutputStream
			OutputStreamWriter osw= new OutputStreamWriter(out,"UTF-8");
			PrintWriter pw= new PrintWriter(osw,true);
			//首先将昵称发送给服务端
			pw.println(nickName);
			System.out.println("欢迎你:"+nickName+"!开始聊天吧!");

			//首先启动接受服务端消息的线程
			ServerHandler handler = new ServerHandler();
			Thread t = new Thread(handler);
			t.start();

			while(true){
				String message = scanner.nextLine();
				pw.println(message);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		try {
			Client client = new Client();
			client.start();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 该线程用来接收并处理服务端发送过来的消息
	 * @author Double
	 *
	 */
	private class ServerHandler implements Runnable{
		public void run(){
			try {
				BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream(),"UTF-8"));

				String message = null;
				while((message = br.readLine())!=null){
					System.out.println(message);
				}
			} catch (Exception e) {

			}
		}

	}
}
