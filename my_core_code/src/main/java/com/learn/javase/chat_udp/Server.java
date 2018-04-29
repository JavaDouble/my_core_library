package com.learn.javase.chat_udp;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

/**
 * 聊天室服务端-UDP模式
 * @author Double
 *
 */
public class Server {
	/**
	 * 服务端的启动方法
	 */
	public void start() {
		try {
			/*
			 * 接收包的步骤: 1:创建Socket(一次) 2:创建一个合适大小的包 3:通过socket接收数据到包中 4:拆包取数据
			 */
			DatagramSocket socket = new DatagramSocket(8088);

			byte[] data = new byte[100];
			DatagramPacket recvPacket = new DatagramPacket(data, data.length);
			// 接收数据到包中
			// 注意,该方法是个阻塞方法。
			socket.receive(recvPacket);

			// 拆包拿数据
			byte[] d = recvPacket.getData();
			// 有效数据长度
			int dlen = recvPacket.getLength();
			/**
			 * String( byte[] b, int offset, int len, String charsetName)
			 * 将给定的字节数组中，从offset处 开始连续len个字节，再根据给定的字符集 转换为字符串
			 */
			String info = new String(d, 0, dlen, "UTF-8");
			System.out.println("客户端说:" + info);

			// 回复客户端
			String str = "你好！客户端！";
			data = str.getBytes("UTF-8");

			// 打包:准备包裹，填写地址，装入数据
			InetAddress address = recvPacket.getAddress();
			int port = recvPacket.getPort();
			// 创建发送包
			DatagramPacket sendPacket = new DatagramPacket(data, data.length, address, port);
			// 将包发送出去
			socket.send(sendPacket);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		Server server = new Server();
		server.start();
	}
}
