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

/**
 * 自动生成action
 * 
 * @author chenjiahe 2019年09月08日16:57:47
 * 
 */
public class GeneratorActionUtil {

    private static String templateDir = "com/hx/auto/file";//获取模板路径
    private static String templateName = "Action.tpl";//action模板名称
    
    /**生成action
     * @param cl 实体类
     * @param urlData 生成配置信息
     * @param sqlParam 是否生成动态sql的文件（SqlParam.java）,已经生成就不用生成
     * @throws Exception
     */
    public static void generatorAction(Class<?> cl, UrlData urlData, boolean sqlParam) throws Exception {
        try {

            // 反射start
            // 类名
            String entityName = cl.getSimpleName();
            // 类名首字母小写
            String initial = entityName.substring(0, 1).toLowerCase();
            String entityNameSmall = initial + entityName.substring(1, entityName.length());

            //判断是否存在
			//生成文件路径
			String targetFile = urlData.getActionUrl()[1].replace(".", "/")+"/";
			//生成文件名称
			targetFile += entityName + "Controller.java";
			targetFile = "./"+urlData.getActionUrl()[0].replace(".", "/")+"/"+targetFile;
			File file = new File(targetFile);
			if(file.exists()){
				//存在就结束
				return;
			}

			//获取实体类包名
            String[] strs = cl.getName().split("\\.");
            String packageName = "";
            //去掉类名
            for(int i=0;i<strs.length-1;i++) {
            	packageName += "."+strs[i];
            }
            packageName = packageName.replaceFirst(".", "");
            
            //映射文件的文件夹
            //Resource resource = new ClassPathResource(templateDir);
    		//File file2 = resource.getFile();
            File file2 = new File(Thread.currentThread().getContextClassLoader().getResource(templateDir).getFile());
        	// 反射end
        	Configuration cfg = new Configuration();
        	// 指定模板文件从何处加载的数据源，这里设置成一个文件目录
        	cfg.setDirectoryForTemplateLoading(file2);
        	//cfg.setDirectoryForTemplateLoading(new File(templateDir));
        	cfg.setObjectWrapper(new DefaultObjectWrapper());
        	// 获取或创建模板
        	Template template = cfg.getTemplate(templateName);
        	
        	// 创建数据模型
        	HashMap<String, Object> root = new HashMap<String, Object>();
        	
        	//action包名
        	if(!CommonTool.checkNotNull(urlData.getActionUrl())) {
        		System.err.println("没有生成action路径");
        		return;
        	}
        	root.put("packageName",urlData.getActionUrl()[1]);
        	//实体类的类名
        	root.put("entityName", entityName);
        	//实体类的类名（首字母小写）
        	root.put("entityNameSmall", entityNameSmall);

			if(!CommonTool.checkNotNull(urlData.getTotalUrl())) {
				System.err.println("没有设置总包路径");
				return;
			}
        	//dao的包名
        	if(!CommonTool.checkNotNull(urlData.getDaoUrl())) {
        		System.err.println("没有dao路径");
        		return;
        	}
			root.put("TotalPackageName",urlData.getTotalUrl());
        	root.put("DAOPackageName",urlData.getDaoUrl()[1]);
        	//实体类的包名
        	root.put("entityPackageName",packageName);
        	//service的包名
        	if(!CommonTool.checkNotNull(urlData.getServiceUrl())) {
        		System.err.println("没有service路径");
        		return;
        	}
        	root.put("servicePackageName",urlData.getServiceUrl()[1]);

        	System.out.println("actionUrl:"+targetFile);
        	// 将模板和数据模型合并 输出到Console
        	Writer out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(targetFile), "UTF-8"));
        	template.process(root, out);
        	out.flush();
        	out.close();
        } catch (IOException e) {
            e.printStackTrace();
        } 
    }
}