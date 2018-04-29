package com.learn.jdbc.dao;

import java.util.List;
import com.learn.jdbc.entity.Userinfo;

public interface UserinfoDAO {

	boolean save(Userinfo userinfo);
	Userinfo findByUsername(String username);
	Userinfo findById(int id);
	List<Userinfo> findAll();
	boolean deleteByUsername(String username);
	boolean deleteById(int id);
	int updateByUsername(String username);
}
