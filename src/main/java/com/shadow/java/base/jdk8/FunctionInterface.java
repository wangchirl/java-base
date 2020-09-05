package com.shadow.java.base.jdk8;

/**
 * @author shadow
 * @create 2020-08-23
 * @description
 */
@FunctionalInterface
public interface FunctionInterface {

	void say();

	default void hello(){
		System.out.println("hello");
	}

	public static void hi() {
		System.out.println("hi");
	}

}
