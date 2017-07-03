package com.dean.it;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.Consumer;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.Envelope;

public class New_Recv {
	private final static String QUEUE_NAME = "hello";
	public static void main(String[] args)throws java.io.IOException, java.lang.InterruptedException, TimeoutException{
		//创建连接工厂
		ConnectionFactory factory = new ConnectionFactory();
		//设置接收消息的主机ip
		factory.setHost("localhost");
		//创建连接
		Connection conn = factory.newConnection();
		//创建频道
		Channel channel = conn.createChannel();
	    //设置操作的队列
		channel.queueDeclare(QUEUE_NAME, false, false, false, null);
		System.out.println("[*] waiting for messages, To exit process CTRL+C.");
	    //创建消费者使用新的api
		Consumer consumer = new DefaultConsumer(channel){
			 @Override
			  public void handleDelivery(String consumerTag, Envelope envelope,
			                             AMQP.BasicProperties properties, byte[] body)
			      throws IOException {
				String msg = new String(body, "UTF-8");
				System.out.println("[x] received '" + msg + "'");
			}
		};
		channel.basicConsume(QUEUE_NAME, true, consumer);
	}
}
