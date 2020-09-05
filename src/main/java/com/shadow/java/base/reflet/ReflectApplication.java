package com.shadow.java.base.reflet;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;

/**
 * @author shadow
 * @create 2020-08-23
 * @description
 */
public class ReflectApplication {

	public static void main(String[] args) throws ClassNotFoundException, NoSuchMethodException, NoSuchFieldException, IllegalAccessException, InvocationTargetException, InstantiationException {

		Class<?> clazz = Class.forName(TestClass.class.getName());

		// 构造器
		Constructor<?>[] constructors = clazz.getConstructors();

		Constructor<?>[] declaredConstructors = clazz.getDeclaredConstructors();

		Arrays.asList(constructors).forEach(System.out::println);
		System.out.println("---------------------");
		Arrays.asList(declaredConstructors).forEach(System.out::println);

		// 成员属性
		Field[] fields = clazz.getFields();

		Field[] declaredFields = clazz.getDeclaredFields();

		Arrays.asList(fields).forEach(System.out::println);
		System.out.println("---------------------");
		Arrays.asList(declaredFields).forEach(System.out::println);

		// 方法
		Method[] methods = clazz.getMethods();

		Method[] declaredMethods = clazz.getDeclaredMethods();
		Arrays.asList(methods).forEach(System.out::println);
		System.out.println("---------------------");
		Arrays.asList(declaredMethods).forEach(System.out::println);

		// 打破访问修饰符的限制
		Arrays.asList(declaredConstructors).forEach(item -> item.setAccessible(true));
		Arrays.asList(declaredFields).forEach(item -> item.setAccessible(true));
		Arrays.asList(declaredMethods).forEach(item -> item.setAccessible(true));

		// 实例化对象
		Constructor<?> constructor = clazz.getConstructor(String.class);
		TestClass hello = (TestClass)constructor.newInstance("hello");
		hello.get();

		// 属性注入
		String name = "李四";
		TestClass instance = new TestClass("王五");
		Field field = clazz.getDeclaredField("name");
		field.setAccessible(true);
		field.set(instance,name);
		instance.get();


	}
}
