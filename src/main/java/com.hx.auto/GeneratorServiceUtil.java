package com.hx.auto;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.HashMap;

import com.hx.auto.common.UrlData;
import com.hx.auto.util.CommonTool;
import freemarker.template.Configuration;
import freemarker.template.DefaultObjectWrapper;
import freemarker.template.Template;
import freemarker.template.TemplateException;

/**
 * 自动生成Service and ServiceImpl
 * 
 * @author chenjiahe 2019年09月08日16:57:47
 * 
 */
public class GeneratorServiceUtil {

    private static String templateDir = "com/hx/auto/file";//获取模板路径

    private static String templateServiceName = "Service.tpl";//service名称
    private static String templateServiceImplName = "ServiceImpl.tpl";//serviceImpl名称
    /**
     * @param cl 实体类
     * @param urlData 配置信息
     * @throws Exception
     */
    @SuppressWarnings("deprecation")
    public static void generatorService(Class<?> cl, UrlData urlData) throws Exception {
        try {
        	//获取类的包名
        	String className = cl.getSimpleName();
        	String[] strs = cl.getName().split("\\.");
        	String packageName = "";
        	for(int i=0;i<strs.length-1;i++) {
        		packageName += "."+strs[i];
        	}
        	packageName = packageName.replaceFirst(".","");
        	
        	// 类名首字母小写
        	String initial = className.substring(0, 1).toLowerCase();
        	String classNamex = initial + className.substring(1, className.length());
        	// 取得本类的全部属性

			String targetFile = urlData.getServiceUrl()[1].replace(".", "/")+"/";
			targetFile += className + "Service.java";
			//补全路径
			targetFile = "./"+urlData.getServiceUrl()[0].replace(".", "/")+"/"+targetFile;
			File fileService = new File(targetFile);
			if(fileService.exists()){
				//存在就结束
				return;
			}


			String targetFile2 = urlData.getServiceImplUrl()[1].replace(".", "/")+"/";
			targetFile2 += className + "ServiceImpl.java";
			//补全路径
			targetFile2 = "./"+urlData.getServiceImplUrl()[0].replace(".", "/")+"/"+targetFile2;
			File fileServiceImple = new File(targetFile2);
			if(fileServiceImple.exists()){
				System.out.println("fileServiceImple:"+targetFile2);
				//存在就结束
				return;
			}

        	//映射文件的文件夹
        	File file2 = new File(Thread.currentThread().getContextClassLoader().getResource(templateDir).getFile());
        	Configuration cfg = new Configuration();
        	// 指定模板文件从何处加载的数据源，这里设置成一个文件目录
        	cfg.setDirectoryForTemplateLoading(file2);
        	cfg.setObjectWrapper(new DefaultObjectWrapper());
        	
        	// 获取或创建模板
        	Template template = cfg.getTemplate(templateServiceName);
        	
        	// 创建数据模型
        	HashMap<String, Object> root = new HashMap<String, Object>();
        	root.put("className", classNamex);
        	root.put("classNameUP", className);
        	root.put("packageName", packageName);
        	root.put("servicePack", urlData.getServiceUrl()[1]);
        	root.put("actionPack", urlData.getActionUrl()[1]);
        	

        	System.out.println("serviceUrl:"+targetFile);
        	// 将模板和数据模型合并 输出到Console
        	Writer out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(targetFile), "UTF-8"));
        	template.process(root, out);
        	out.flush();
        	out.close();

			if(!CommonTool.checkNotNull(urlData.getTotalUrl())) {
				System.err.println("没有设置总包路径");
				return;
			}
			root.put("TotalPackageName",urlData.getTotalUrl());
        	root.put("serviceImpPack", urlData.getServiceImplUrl()[1]);
        	root.put("daoPack", urlData.getDaoUrl()[1]);
        	//serviceImp文件
        	// 获取或创建模板
        	Template template2 = cfg.getTemplate(templateServiceImplName);

        	System.out.println("serviceImpUrl:"+targetFile2);
        	// 将模板和数据模型合并 输出到Console
        	Writer out2 = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(targetFile2), "UTF-8"));
        	template2.process(root, out2);
        	out2.flush();
        	out2.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (TemplateException e) {
            e.printStackTrace();
        }
    }
}