package com.shadow.java.base.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @author shadow
 * @create 2020-08-23
 * @description
 */
public class JDKProxy {
	public static void main(String[] args) {

		RealUser realUser = new RealUser();

		UserInterface userInterface = (UserInterface) Proxy.newProxyInstance(JDKProxy.class.getClassLoader(), new Class[]{UserInterface.class}, new InvocationHandler() {
			@Override
			public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
				System.out.println("before invoked");
				Object result = method.invoke(realUser, args);
				System.out.println("after invoked");
				return result;
			}
		});
		userInterface.run();


	}
}
