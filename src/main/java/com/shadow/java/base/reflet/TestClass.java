package com.shadow.java.base.reflet;

/**
 * @author shadow
 * @create 2020-09-05
 * @description
 */
public class TestClass {

	private String name;

	public int age;

	private TestClass(){

	}

	public TestClass(String name) {
		this.name = name;
	}

	public void get(){
		System.out.println(get(name));
	}

	private String get(String name) {
		return name;
	}
}
