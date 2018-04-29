package com.learn.javaee.unit05.web;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.learn.javaee.unit05.entity.Course;
import com.learn.javaee.unit05.entity.Student;

/**
 * Unit09的内容EL表达式、JSTL整合到Unit05中	模拟学生和课程查询功能演示EL表达式和JSTL
 *
 * Unit09资源：entity:Course/Student	FindStudentServlet和SysdateTag(自定义标签的演示)
 *
 * JSTL自定义标签所在类 s.tld 位置存放在webapp/WEB-INF/s.tld 和web.xml平级
 *
 * @author Double
 *
 */
public class FindStudentServlet extends HttpServlet {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		//1.获取参数(当前案例没有参数，省略)		2.处理业务(模拟查询)		3.转发或重定向(转发)

		//模拟查询一个学生
		Student stu=new Student();
		stu.setSname("zhangsan");
		stu.setGender("M");
		stu.setAge(23);
		stu.setInterests(new String[]{"打球","音乐","游戏"});

		Course c=new Course();
		c.setId(1);
		c.setCname("Java");
		c.setDays(85);
		stu.setCourse(c);

		//转发(/webapp/jsp/student.jsp)
		request.setAttribute("student",stu);
		request.getRequestDispatcher("jsp/student.jsp").forward(request, response);
	}
}
