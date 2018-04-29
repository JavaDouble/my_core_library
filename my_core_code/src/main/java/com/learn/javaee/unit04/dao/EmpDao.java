package com.learn.javaee.unit04.dao;

import java.util.List;

import com.learn.javaee.unit04.entity.Emp;



public interface EmpDao {

	List<Emp> findAll();
	void save(Emp e);
}
