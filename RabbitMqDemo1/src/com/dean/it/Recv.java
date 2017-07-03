package com.dean.it;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.ConsumerCancelledException;
import com.rabbitmq.client.QueueingConsumer;
import com.rabbitmq.client.ShutdownSignalException;

public class Recv {
	
	//队列名称
	private final static String QUEUE_NAME = "hello";
	public static void main(String[] args) throws IOException, TimeoutException, ShutdownSignalException, ConsumerCancelledException, InterruptedException{
		//创建连接工厂
		ConnectionFactory factory = new ConnectionFactory();
		//设置接收者主机或主机ip
		factory.setHost("localhost");
		//创建连接
		Connection conn = factory.newConnection();
		//创建频道
		Channel channel = conn.createChannel();
		//声明队列
		channel.queueDeclare(QUEUE_NAME, false, false, false, null);
		System.out.println("[*] waitting for msgs. To exit proecess CTR+C");
		//创建消费者队列
		QueueingConsumer consumer = new QueueingConsumer(channel);
		//指定消费队列
		channel.basicConsume(QUEUE_NAME, true, consumer);
		while(true){
			QueueingConsumer.Delivery delivery = consumer.nextDelivery();
			String msg = new String(delivery.getBody());
			System.out.println("[x] Receivered '"+msg+"'");
		}
	}
}
