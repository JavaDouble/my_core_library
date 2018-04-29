package com.learn.javaee.unit08;


import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

/**
 * 监听器——统计在线人数	使用session
 * @author Double
 *
 */
public class CountListener implements HttpSessionListener,HttpSessionAttributeListener {

	private int count=0;

	@Override
	public void sessionCreated(HttpSessionEvent se) {
		count++;
		HttpSession session=se.getSession();
		session.getServletContext().setAttribute("count", count);
	}

	@Override
	public void attributeAdded(HttpSessionBindingEvent se) {

	}

	@Override
	public void attributeRemoved(HttpSessionBindingEvent se) {

	}

	@Override
	public void attributeReplaced(HttpSessionBindingEvent se) {

	}



	@Override
	public void sessionDestroyed(HttpSessionEvent se) {
		count--;
		HttpSession session=se.getSession();
		session.getServletContext().setAttribute("count", count);
	}


}
