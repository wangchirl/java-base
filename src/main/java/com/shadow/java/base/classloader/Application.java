package com.shadow.java.base.classloader;

import org.apache.commons.io.monitor.FileAlterationMonitor;
import org.apache.commons.io.monitor.FileAlterationObserver;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @author shadow
 * @create 2020-09-05
 * @description
 */
public class Application {

	public static String rootPath;

	public void start(){
		// springboot 项目启动
		init();
		new TestClass().say();
	}

	public void init() {
		System.out.println("Application 初始化");
	}

	public static void run(Class<?> clazz) throws Exception {
		String rootPath = clazz.getResource("/").getPath().replaceAll("%20"," ");
		rootPath = new File(rootPath).getPath();
		Application.rootPath = rootPath;
		startFileMonitor(rootPath);
		MyClassLoader myClassLoader = new MyClassLoader(rootPath, rootPath + "/com/shadow/java/base/classloader");
		start0(myClassLoader);
	}

	public static void startFileMonitor(String rootPath) throws Exception {
		FileAlterationObserver fileAlterationObserver = new FileAlterationObserver(rootPath);
		fileAlterationObserver.addListener(new FileListener());
		FileAlterationMonitor fileAlterationMonitor = new FileAlterationMonitor(500);
		fileAlterationMonitor.addObserver(fileAlterationObserver);
		fileAlterationMonitor.start();
	}

	public static void start0(MyClassLoader myClassLoader) throws IllegalAccessException, InstantiationException, ClassNotFoundException, NoSuchMethodException, InvocationTargetException {
		Class<?> aClass = myClassLoader.loadClass("com.shadow.java.base.classloader.Application");
		Object o = aClass.newInstance();
		Method start = aClass.getMethod("start");
		start.invoke(o);
	}

	public static void close(){
		System.out.println("关闭项目");
		// 容器清理....
		System.runFinalization();
		System.gc();
	}

}
