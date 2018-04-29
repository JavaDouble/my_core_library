package com.learn.jdbc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.learn.jdbc.DBUtil;
import com.learn.jdbc.entity.Userinfo;





/**
 * 使用DAO重构
 * 数据访问与业务逻辑分离 封装对数据的访问
 *
 * DAO
 * DAO是数据持久层中所有类的统称，作用是将业务逻辑数数据哭操作分离开。使得业务逻辑更专注，
 * 并且对数据库的操作可以完全面向对象化。
 * DAO负责将实体类与数据库中表中数据进行映射。
 * @author soft01
 *
 */
public class UserinfoDaoClass {
	/**
	 * 将给定的Userinfo实例表示的用户信息保存
	 * @param userinfo
	 * @return
	 */
	public boolean save(Userinfo userinfo) {
		try {
			Connection conn=DBUtil.getConnection();
			String sql="INSERT INTO userinfo_lqs "+"(id,username,password,email,nickname,account) "+"VALUES "+"(seq_userinfo_id_lqs.NEXTVAL,?,?,?,?,?)";
			System.out.println(sql);
			PreparedStatement ps=conn.prepareStatement(sql,new String[]{"id"});
			ps.setString(1,userinfo.getUsername());
			ps.setString(2,userinfo.getPassword());
			ps.setString(3,userinfo.getEmail());
			ps.setString(4,userinfo.getNickname());
			ps.setDouble(5,userinfo.getAccount());
			int num=ps.executeUpdate();
			if(num>0){
				ResultSet rs=ps.getGeneratedKeys();
				rs.next();
				int id=rs.getInt(1);
				userinfo.setId(id);
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
			DBUtil.TransRollBack();
		}finally{
			DBUtil.closeConnection();
		}
		return false;
	}
	/**
	 * 根据给定的用户名查询用户信息
	 * @param username
	 * @return
	 */
	 public Userinfo findByUsername(String username) {
		 try {
			 Connection conn=DBUtil.getConnection();
			 String sql="SELECT id,username,password,email,nickname,account FROM userinfo_lqs "+"WHERE username=?";
			 PreparedStatement ps=conn.prepareStatement(sql);
			 ps.setString(1,username);
			 ResultSet rs=ps.executeQuery();
			 if(rs.next()){
				 int id=rs.getInt("id");
				 String user=rs.getString("username");
				 String password=rs.getString("password");
				 String email=rs.getString("email");
				 String nickname=rs.getString("nickname");
				 double account=rs.getDouble("account");
				 Userinfo userinfo=new Userinfo(id, user, password, email, nickname, account);
				 return userinfo;
			 }
		} catch (Exception e) {
			e.printStackTrace();
			//DBUtil.TransRollBack();
		}finally{
			DBUtil.closeConnection();
		}
		return null;
	}

	/**
	 * 查看所有用户信息
	 */
	 public List<Userinfo> findAll() {
		 List<Userinfo> list=new ArrayList<Userinfo>();
		 try {
			 Connection conn=DBUtil.getConnection();
			 String sql="SELECT id,username,password,email,nickname,account FROM userinfo_lqs";
			 PreparedStatement ps=conn.prepareStatement(sql);
			 ResultSet rs=ps.executeQuery();
			 while(rs.next()){
				 int id=rs.getInt("id");
				 String username=rs.getString("username");
				 String password=rs.getString("password");
				 String email=rs.getString("email");
				 String nickname=rs.getString("nickname");
				 double account=rs.getDouble("account");
				 Userinfo userinfo=new Userinfo(id, username, password, email, nickname, account);
				 list.add(userinfo);
			 }
		} catch (Exception e) {
			e.printStackTrace();
			DBUtil.TransRollBack();
		}finally{
			DBUtil.closeConnection();
		}
		 return list;
	}
	 /**
	  * 删除给定的用户
	  * @param userinfo
	  * @return
	  */
	 public boolean delete(String username) {
		 try {
			Connection conn=DBUtil.getConnection();
			String sql="DELETE FROM userinfo_lqs "+"WHERE username=?";
			PreparedStatement ps=conn.prepareStatement(sql);
			ps.setString(1,username);
			int num=ps.executeUpdate();
			if(num>0){
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
			DBUtil.TransRollBack();
		}finally{
			DBUtil.closeConnection();
		}
		 return false;
	}

	 /**
	  * 修改给定用户信息
	  * @param userinfo
	  * @return
	  */
	 public boolean update(Userinfo userinfo) {

		 return false;
	}
}
