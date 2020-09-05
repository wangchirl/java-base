package com.shadow.java.base.classloader;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author shadow
 * @create 2020-09-05
 * @description
 */
public class MyClassLoader extends ClassLoader {

	private String rootPath; // 路径

	private List<String> classes; // 需要加载的类

	public MyClassLoader(String rootPath,String...clazzPaths) throws IOException {
		this.rootPath = rootPath;
		this.classes = new ArrayList<>();
		for (String clazzPath : clazzPaths) {
			loadClassPath(new File(clazzPath));
		}
	}

	private void loadClassPath(File file) throws IOException {
		if(file.isDirectory()) {
			for (File file1 : file.listFiles()) {
				loadClassPath(file1);
			}
		}else {
			String fileName = file.getName();
			String filePath = file.getPath();
			String suffix = fileName.substring(fileName.lastIndexOf(".") + 1);
			if("class".equals(suffix)) {
				FileInputStream inputStream = new FileInputStream(file);
				byte[] bytes = new byte[(int) file.length()];
				inputStream.read(bytes);
				String className = getClassName(filePath);
				classes.add(className);
				defineClass(className,bytes,0,bytes.length);
			}
		}
	}

	private String getClassName(String filePath) {
		String className = filePath.replace(rootPath, "").replaceAll("\\\\", ".");
		className = className.substring(0,className.lastIndexOf("."));
		className = className.substring(1,className.length());
		return className;
	}

	@Override
	protected Class<?> loadClass(String name, boolean resolve) throws ClassNotFoundException {
		Class<?> loadedClass = findLoadedClass(name);
		if(loadedClass == null) {
			if(!classes.contains(name)){
				loadedClass = getSystemClassLoader().loadClass(name);
			}
			else {
				throw new ClassNotFoundException();
			}
		}
		return loadedClass;
	}

	public static void main(String[] args) throws Exception {

//		String rootpath = MyClassLoader.class.getResource("/").getPath()
//				.replaceAll("%20"," ");
//		rootpath = new File(rootpath).getPath();
//		while (true) {
//			MyClassLoader myClassLoader = new MyClassLoader(rootpath, rootpath + "/com/shadow/java/base/classloader/demo");
//			Class<?> aClass = myClassLoader.loadClass("com.shadow.java.base.classloader.TestClass");
//			Object o = aClass.newInstance();
//			aClass.getMethod("say").invoke(o);
//			System.out.println("-------------");
//			TestClass testClass = new TestClass();
//			testClass.say();
//			Thread.sleep(2000);
//		}

		// 全盘委托机制
		Application.run(MyClassLoader.class);

	}
}
