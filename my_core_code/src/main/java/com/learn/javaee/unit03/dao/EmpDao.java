package com.learn.javaee.unit03.dao;

import java.util.List;

import com.learn.javaee.unit03.entity.Emp;







public interface EmpDao {

	List<Emp> findAll();
	void save(Emp e);
}
