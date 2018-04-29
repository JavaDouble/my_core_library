package com.learn.javaee.unit05.entity;

import java.io.Serializable;
/**
 * 满足javaBean规范
 *
 * @author Double
 *
 */
public class Course implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	//对象的属性courseId
	private Integer courseId;
	private String cname;
	private Integer days;
	public Course() {
		super();
	}
	public Course(Integer courseId, String cname, Integer days) {
		super();
		this.courseId = courseId;
		this.cname = cname;
		this.days = days;
	}

	//实体类 自动生成的是getCourseId和setCourseId 这里修改一下getId和setId 演示EL表达式的语法

	//Bean的属性id
	//1.通过get方法所看到的属性 2.除去get首字母小写所获得的词
	public Integer getId() {
		return courseId;
	}

	public void setId(Integer courseId) {
		this.courseId = courseId;
	}

	public String getCname() {
		return cname;
	}
	public void setCname(String cname) {
		this.cname = cname;
	}
	public Integer getDays() {
		return days;
	}
	public void setDays(Integer days) {
		this.days = days;
	}

}
