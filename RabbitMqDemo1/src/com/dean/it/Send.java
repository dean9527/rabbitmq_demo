package com.dean.it;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

public class Send {
	//队列名称
	private final static String QUEUE_NAME = "hello";
	
	public static void main(String[] args) throws IOException, TimeoutException{
		//创建连接到rabbitmq
		ConnectionFactory factory = new ConnectionFactory();
		//设置rabbitmq所在主机ip或主机名
		factory.setHost("localhost");
		//创建一个连接
		Connection conn = factory.newConnection();
		//创建一个频道
		Channel channel = conn.createChannel();
		//指定一个队列
		channel.queueDeclare(QUEUE_NAME, false, false, false, null);
		//发送的消息
		String msg = "hello world!";
		//往队列中发出一条消息
		channel.basicPublish("", QUEUE_NAME, null, msg.getBytes());
		System.out.println("[x] sent '" + msg + "'");
		//关闭频道
		channel.close();
		//关闭连接
		conn.close();
	}
}
