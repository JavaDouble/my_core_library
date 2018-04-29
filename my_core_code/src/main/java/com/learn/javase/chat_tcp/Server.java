package com.learn.javase.chat_tcp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

/**
 * 聊天室服务端
 * @author Double
 *
 */
public class Server {
	/*
	 * java.net.ServerSocket
	 * 用于向系统申请服务端口(客户端就是通过该端口与服务端应用程序建立连接的)		监听服务端口，以接受客户端的连接。
	 */
	private ServerSocket server;
	/*
	 * 该集合存放所有客户端的输出流，以便广播消息
	 */
	private Map<String,PrintWriter> allOut;

	public Server() throws Exception{
		try {
			/*
			 * 创建时申请服务端口，不能与系统中其他应用程序已使用端口冲突
			 * 否则抛出:AddressAlreadyInUser的错误
			 */
			server = new ServerSocket(8088);

			allOut = new HashMap<String,PrintWriter>();
		} catch (Exception e) {
			System.out.println("服务端初始化失败!");
			throw e;
		}
	}
	/**
	 * 将给定的某个客户端的输出流存入到共享集合
	 * @param out
	 */
	public synchronized void addOut(String nickName,PrintWriter out){
		allOut.put(nickName,out);
	}
	/**
	 * 将给定的客户端的输出流从共享集合中删除
	 * @param out
	 */
	public synchronized void removeOut(String nickName){
		allOut.remove(nickName);
	}
	/**
	 * 将给定的消息发送给所有客户端
	 * @param message
	 */
	public synchronized void sendMessage(String message){
		for(PrintWriter out : allOut.values()){
			out.println(message);
		}
	}
	/**
	 * 将指定消息发送给指定昵称的客户端
	 * @param nickName
	 * @param message
	 */
	public synchronized void sendMessage(String nickName,String message){
		PrintWriter out = allOut.get(nickName);
		if(out != null){
			out.println(message);
		}
	}


	public void start(){
		try {
			/*
			 * ServerSocket的accept方法是一个阻塞方法，作用是监听申请的服务端口直到一个客户端通过该端口与服务端
			 * 建立连接，accept方法才会执行完毕，并返回一个Socket实例，
			 * 通过这个Socket可以与刚刚建立连接的客户端进行交互。
			 */
			while(true){
				System.out.println("等待客户端连接...");
				Socket socket = server.accept();
				System.out.println("一个客户端连接了!");
				//启动线程处理该客户端交互
				ClientHandler handler= new ClientHandler(socket);
				Thread t = new Thread(handler);
				t.start();

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		try {
			Server server = new Server();
			server.start();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * 该线程用来处理与服务端连接的一个客户端的
	 * 交互工作。
	 * @author Double
	 *
	 */
	private class ClientHandler implements Runnable{
		//当前线程通过该Socket与该客户端交互
		private Socket socket;
		//客户端的IP地址
		private String host;
		//用户昵称
		private String nickName;

		public ClientHandler(Socket socket){
			this.socket = socket;
		}

		public void run(){
			PrintWriter pw = null;
			try {
				//获取客户端IP地址
				InetAddress address= socket.getInetAddress();
				//获取客户端IP地址的字符串形式
				host = address.getHostAddress();

				/*
				 * 想读取客户端发送过来的数据，需要通过Socket获取输入流。
				 */
				InputStream in = socket.getInputStream();
				InputStreamReader isr = new InputStreamReader(in,"UTF-8");
				BufferedReader br = new BufferedReader(isr);

				//首先读取客户端发送过来的昵称
				nickName = br.readLine();
				sendMessage(nickName+"上线了...");

				/*
				 * 通过Socket获取输出流，以便将消息发送给给客户端
				 */
				pw = new PrintWriter(new OutputStreamWriter(socket.getOutputStream(),"UTF-8"),true);
				//将该客户端的输出流存入共享集合
				addOut(nickName,pw);


				/*
				 * br.readLine方法在读取远端计算机发送过来的一行字符串时，远端计算机断开连接后，其不同
				 * 的操作系统会影响这里readLine方法的结果。
				 * 当windows的客户端断开连接时:br.readLine方法会直接抛出异常
				 *
				 * 当linux的客户端断开连接时:br.readLine方法会返回null
				 *
				 */
				String message = null;
				while((message = br.readLine())!=null){
//					System.out.println(host+"说:"+message);
//					pw.println(host+"说:"+message);
					//首先判断是否为私聊
					if(message.startsWith("@")){
						//先找到“:”的位置
						int index = message.indexOf(":");
						if(index<0){
							//告知当前客户端格式不对
							pw.println("私聊格式不对，私聊格式:@昵称:聊天内容");
						}else{
							//获取对方昵称
							String toNick = message.substring(1,index);
							//检查该用户是否存在:
							if(allOut.containsKey(toNick)){
								//获取聊天内容
								message = message.substring(index+1);
								sendMessage(toNick,nickName+"对你说:"+message);
							}else{
								pw.println("没有找到此用户:"+toNick);
							}
						}
					}else{
						//广播给所有客户端
						sendMessage(nickName+"说:"+message);
					}
				}
			} catch (Exception e) {

			} finally{
				//客户端断开后的处理
				//将该客户端的输出流从共享集合中删除
				removeOut(nickName);
				sendMessage(nickName+"下线了...");
				try {
					socket.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
