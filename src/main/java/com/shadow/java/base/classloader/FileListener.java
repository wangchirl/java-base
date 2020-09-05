package com.shadow.java.base.classloader;

import org.apache.commons.io.monitor.FileAlterationListenerAdaptor;

import java.io.File;

/**
 * @author shadow
 * @create 2020-09-05
 * @description
 */
public class FileListener extends FileAlterationListenerAdaptor {

	@Override
	public void onFileChange(File file) {
		if(file.getName().indexOf(".class")!=-1){
			try {
				Application.close();
				MyClassLoader myClassLoader = new MyClassLoader(Application.rootPath, Application.rootPath + "/com/shadow/java/base/classloader/demo");
				Application.start0(myClassLoader);
			} catch (Exception e){
				e.printStackTrace();
			}
		}

	}
}
