package com.learn.javase.reflect;

public class Foo {
	
	Integer a=1;
	private String name="Tom";
	
	public void test1() {
		System.out.println("公开的方法!");
	}
	
	private void test2() {
		System.out.println("私有的方法!");
	}
	
	private String test3() {
		System.out.println("test3()");
		return "abc";
	}
}
