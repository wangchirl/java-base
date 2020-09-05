package com.shadow.java.base.proxy;

/**
 * @author shadow
 * @create 2020-08-23
 * @description
 */
public class RealUser  implements UserInterface{
	@Override
	public void run() {
		System.out.println("i am running");
	}
}
