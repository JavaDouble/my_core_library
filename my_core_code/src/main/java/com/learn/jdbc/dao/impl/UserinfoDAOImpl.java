package com.learn.jdbc.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.learn.jdbc.DBCP_DBUtil;
import com.learn.jdbc.dao.UserinfoDAO;
import com.learn.jdbc.entity.Userinfo;



public class UserinfoDAOImpl implements UserinfoDAO {

	/**
	 * 将给定的Userinfo实例表示的用户信息保存
	 * @param userinfo
	 * @return
	 */
	@Override
	public boolean save(Userinfo userinfo) {
		try {
			Connection conn=DBCP_DBUtil.getConnection();
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
			DBCP_DBUtil.TransRollBack();
		}finally{
			DBCP_DBUtil.closeConnection();
		}
		return false;
	}

	/**
	 * 根据给定的用户名查询用户信息
	 * @param username
	 * @return
	 */
	@Override
	public Userinfo findByUsername(String username) {
		 try {
			 Connection conn=DBCP_DBUtil.getConnection();
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
		}finally{
			DBCP_DBUtil.closeConnection();
		}
		return null;
	}

	@Override
	public Userinfo findById(int id) {
		return null;
	}

	/**
	 * 查看所有用户信息
	 */
	@Override
	public List<Userinfo> findAll() {
		 List<Userinfo> list=new ArrayList<Userinfo>();
		 try {
			 Connection conn=DBCP_DBUtil.getConnection();
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
		}finally{
			DBCP_DBUtil.closeConnection();
		}
		 return list;
	}

	 /**
	  * 删除给定的用户
	  * @param userinfo
	  * @return
	  */
	@Override
	public boolean deleteByUsername(String username) {
		 try {
				Connection conn=DBCP_DBUtil.getConnection();
				String sql="DELETE FROM userinfo_lqs "+"WHERE username=?";
				PreparedStatement ps=conn.prepareStatement(sql);
				ps.setString(1,username);
				int num=ps.executeUpdate();
				if(num>0){
					return true;
				}
			} catch (Exception e) {
				e.printStackTrace();
				DBCP_DBUtil.TransRollBack();
			}finally{
				DBCP_DBUtil.closeConnection();
			}
			 return false;
	}

	@Override
	public boolean deleteById(int id) {
		return false;
	}

	@Override
	public int updateByUsername(String username) {
		return 0;
	}

}
