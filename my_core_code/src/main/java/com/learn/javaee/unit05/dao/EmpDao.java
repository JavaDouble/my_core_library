package com.learn.javaee.unit05.dao;

import java.util.List;

import com.learn.javaee.unit05.entity.Emp;



public interface EmpDao {

	List<Emp> findAll();
	void save(Emp e);
}
