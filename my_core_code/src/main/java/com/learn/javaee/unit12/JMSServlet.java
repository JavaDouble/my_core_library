package com.learn.javaee.unit12;

import java.io.IOException;
import java.io.PrintWriter;

import javax.jms.DeliveryMode;
import javax.jms.Queue;
import javax.jms.QueueConnection;
import javax.jms.QueueConnectionFactory;
import javax.jms.QueueReceiver;
import javax.jms.QueueSender;
import javax.jms.QueueSession;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.jms.Topic;
import javax.jms.TopicConnection;
import javax.jms.TopicConnectionFactory;
import javax.jms.TopicPublisher;
import javax.jms.TopicSession;
import javax.jms.TopicSubscriber;
import javax.naming.InitialContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
/**
 * JMS的演示
 *
 * @WebServlet注解用于标注在一个继承了HttpServlet类之上，属于类级别的注解。
 *
 * 用法形如：@WebServlet("/DisplayHeader1")
 * 其中 /DisplayHeader1 表示访问该servlet的 url 映射（地址）（此处为相对路径，即 “项目名称/DisplayHeader1” ）。
 *
 * 该注解的作用等价于 在web.xml中配置的该servlet的<servlet-mapping>元素中<url-pattern>的配置，比如：
 * <url-pattern>/DisplayHeader2</url-pattern>
 * 此时，访问如下两个路径的效果是一样的，不互斥：http://localhost:8080/test/DisplayHeader1
 * http://localhost:8080/test/DisplayHeader2
 *
 * @author Double
 *
 */
public class JMSServlet extends HttpServlet {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String path=request.getServletPath();
		if("/send.mq".equals(path)){
			activeMQMessageProducerServlet(request, response);
		}else if("/receive.mq".equals(path)){
			activeMQMessageConsumerServlet(request, response);
		}else if("/publisher.mq".equals(path)){
			activeMQPublisherServlet(request, response);
		}else if("/subscriber.mq".equals(path)){
			activeMQSubscriberServlet(request, response);
		}else{
			throw new RuntimeException("查无此页！");
		}
	}


	/**
	 * 基于Tomcat + JNDI + ActiveMQ实现JMS的点对点消息传送
	 * 1.消息生产者Servlet
	 *
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	private void activeMQMessageProducerServlet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		try {
			// get the initial context
			InitialContext context = new InitialContext();

			// lookup the queue object
			Queue queue = (Queue) context.lookup("java:comp/env/queue/queue0");

			// lookup the queue connection factory
			QueueConnectionFactory conFactory = (QueueConnectionFactory) context
					.lookup("java:comp/env/queue/connectionFactory");

			// create a queue connection
			QueueConnection queConn = conFactory.createQueueConnection();

			// create a queue session
			QueueSession queSession = queConn.createQueueSession(false, Session.DUPS_OK_ACKNOWLEDGE);

			// create a queue sender
			QueueSender queSender = queSession.createSender(queue);
			queSender.setDeliveryMode(DeliveryMode.NON_PERSISTENT);

			// create a simple message to say "Hello World"
			TextMessage message = queSession.createTextMessage("Hello World");

			// send the message
			queSender.send(message);

			// print what we did
			out.write("Message Sent: " + message.getText());

			// close the queue connection
			queConn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 * 基于Tomcat + JNDI + ActiveMQ实现JMS的点对点消息传送
	 * 2.消息消费者Servlet
	 *
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	private void activeMQMessageConsumerServlet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    PrintWriter out = response.getWriter();

        try {
            // get the initial context
            InitialContext context = new InitialContext();

            // lookup the queue object
            Queue queue = (Queue) context.lookup("java:comp/env/queue/queue0");

            // lookup the queue connection factory
            QueueConnectionFactory conFactory = (QueueConnectionFactory) context
                    .lookup("java:comp/env/queue/connectionFactory");

            // create a queue connection
            QueueConnection queConn = conFactory.createQueueConnection();

            // create a queue session
            QueueSession queSession = queConn.createQueueSession(false,
                    Session.AUTO_ACKNOWLEDGE);

            // create a queue receiver
            QueueReceiver queReceiver = queSession.createReceiver(queue);

            // start the connection
            queConn.start();

            // receive a message
            TextMessage message = (TextMessage) queReceiver.receive();

            // print the message
            out.write("Message Received: " + message.getText());

            // close the queue connection
            queConn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
	}

	/**
	 * 基于Tomcat + JNDI + ActiveMQ实现JMS发布/订阅消息传送例子
	 * 1.新建一个发布者Servlet
	 *
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	private void activeMQPublisherServlet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    PrintWriter out = response.getWriter();

        try {
            // get the initial context
            InitialContext ctx = new InitialContext();

            // lookup the topic object
            Topic topic = (Topic) ctx.lookup("java:comp/env/topic/topic0");

            // lookup the topic connection factory
            TopicConnectionFactory connFactory = (TopicConnectionFactory) ctx
                    .lookup("java:comp/env/topic/connectionFactory");

            // create a topic connection
            TopicConnection topicConn = connFactory.createTopicConnection();

            // create a topic session
            TopicSession topicSession = topicConn.createTopicSession(false,
                    Session.AUTO_ACKNOWLEDGE);

            // create a topic publisher
            TopicPublisher topicPublisher = topicSession.createPublisher(topic);
            topicPublisher.setDeliveryMode(DeliveryMode.NON_PERSISTENT);

            // create the "Hello World" message
            TextMessage message = topicSession.createTextMessage();
            message.setText("Hello World");

            // publish the messages
            topicPublisher.publish(message);

            // print what we did
            out.write("Message published: " + message.getText());

            // close the topic connection
            topicConn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
	}

	/**
	 * 基于Tomcat + JNDI + ActiveMQ实现JMS发布/订阅消息传送例子
	 * 2.新建一个订阅者Servlet
	 *
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	private void activeMQSubscriberServlet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    PrintWriter out = response.getWriter();

        try {
            // get the initial context
            InitialContext ctx = new InitialContext();

            // lookup the topic object
            Topic topic = (Topic) ctx.lookup("java:comp/env/topic/topic0");

            // lookup the topic connection factory
            TopicConnectionFactory connFactory = (TopicConnectionFactory) ctx
                    .lookup("java:comp/env/topic/connectionFactory");

            // create a topic connection
            TopicConnection topicConn = connFactory.createTopicConnection();

            // create a topic session
            TopicSession topicSession = topicConn.createTopicSession(false,
                    Session.AUTO_ACKNOWLEDGE);

            // create a topic subscriber
            TopicSubscriber topicSubscriber = topicSession
                    .createSubscriber(topic);

            // start the connection
            topicConn.start();

            // receive the message
            TextMessage message = (TextMessage) topicSubscriber.receive();

            // print the message
            out.write("Message received: " + message.getText());

            // close the topic connection
            topicConn.close();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
	}

}
