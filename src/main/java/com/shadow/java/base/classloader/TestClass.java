package com.shadow.java.base.classloader;

/**
 * @author shadow
 * @create 2020-09-05
 * @description
 */
public class TestClass {

	public void say(){
		System.out.println("hello version  1.10 " + getClass().getClassLoader());
	}
}
