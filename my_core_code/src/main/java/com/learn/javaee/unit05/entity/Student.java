package com.learn.javaee.unit05.entity;

import java.io.Serializable;
/**
 * 满足javaBean规范
 *
 * @author Double
 *
 */
public class Student implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	private String sname;
	private String gender;
	private Integer age;
	private String[] interests;
	private Course course;
	public Student() {
		super();
	}
	public Student(String sname, String gender, Integer age, String[] interests, Course course) {
		super();
		this.sname = sname;
		this.gender = gender;
		this.age = age;
		this.interests = interests;
		this.course = course;
	}
	public String getSname() {
		return sname;
	}
	public void setSname(String sname) {
		this.sname = sname;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public Integer getAge() {
		return age;
	}
	public void setAge(Integer age) {
		this.age = age;
	}
	public String[] getInterests() {
		return interests;
	}
	public void setInterests(String[] interests) {
		this.interests = interests;
	}
	public Course getCourse() {
		return course;
	}
	public void setCourse(Course course) {
		this.course = course;
	}
}
