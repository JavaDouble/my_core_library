package com.learn.javase.reflect;

public class Junit4 {

	@Test
	public void testReflect() {
		System.out.println("package.reflect");
	}

	@Test
	public void testInvoke() {
		System.out.println("Invoke()");
	}
	
	@Test
	public void testMethod() {
		System.out.println("Method");
	}
	
	@Test
	private void testField() {
		System.out.println("Field");
	}
}
