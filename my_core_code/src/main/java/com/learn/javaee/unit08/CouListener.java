package com.learn.javaee.unit08;

import javax.servlet.ServletRequestAttributeEvent;
import javax.servlet.ServletRequestAttributeListener;
import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;

/**
 * 监听器演示request的创建销毁、增加删除修改在tomcat的时机
 * @author Double
 *
 */
public class CouListener implements ServletRequestListener,ServletRequestAttributeListener {

	/**
	 * tomcat销毁request前自动调用此方法
	 */
	public void requestDestroyed(ServletRequestEvent sre) {
		System.out.println("销毁request");
	}

	/**
	 * tomcat创建request后自动调用此方法
	 */
	public void requestInitialized(ServletRequestEvent sre) {
		System.out.println("创建request");
		System.out.println(sre.getServletRequest());
	}

	public void attributeAdded(ServletRequestAttributeEvent srae) {
		System.out.println("向request内存数据");
	}

	public void attributeRemoved(ServletRequestAttributeEvent srae) {
		System.out.println("移除request内的数据");
	}

	public void attributeReplaced(ServletRequestAttributeEvent srae) {
		System.out.println("替换request内的数据");
	}
}
