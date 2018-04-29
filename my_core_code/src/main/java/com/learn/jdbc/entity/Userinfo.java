package com.learn.jdbc.entity;

import java.io.Serializable;
/**
 * 该类用于描述数据库中userinfo表  该类的每一个实例可以表示userinfo表中的一条记录
 * 这种用来表示数据库某张表的类称为"实体类"(PO,VO)
 * @author Double
 *
 */
public class Userinfo implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = -1513760040418812338L;

	private int id;
	private String username;
	private String password;
	private String email;
	private String nickname;
	private double account;

	public Userinfo() {

	}

	public Userinfo(int id, String username, String password, String email, String nickname, double account) {
		this();
		this.id = id;
		this.username = username;
		this.password = password;
		this.email = email;
		this.nickname = nickname;
		this.account = account;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public double getAccount() {
		return account;
	}
	public void setAccount(double account) {
		this.account = account;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Userinfo other = (Userinfo) obj;
		if (id != other.id)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "编号：" + id + ",姓名：" + username + ",密码：" + password + ",邮箱：" + email
				+ ",昵称：" + nickname + ",工资：" + account;
	}
}
