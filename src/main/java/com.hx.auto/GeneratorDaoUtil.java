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
 * 自动生成DAO
 * 
 * @author chenjiahe 2019年09月08日16:57:47
 * 
 */
public class GeneratorDaoUtil {

    private static String templateDir = "com/hx/auto/file";//获取模板路径
    private static String templateName = "Dao.tpl";//action模板名称
    
    /**生成Dao
     * @param cl 实体类
     * @param urlData 生成配置信息
     * @throws Exception
     */
    public static void generatorDao(Class<?> cl,UrlData urlData) throws Exception {
        try {
            // 反射start
            // 类名
            String entityName = cl.getSimpleName();
            // 类名首字母小写
            String initial = entityName.substring(0, 1).toLowerCase();
            String entityNameSmall = initial + entityName.substring(1, entityName.length());


			//生成文件路径
			String targetFile = urlData.getDaoUrl()[1].replace(".", "/")+"/";
			//生成文件名称
			targetFile += entityName + "Mapper.java";
			//映射文件的文件夹
			//补全路径
			targetFile = "./"+urlData.getDaoUrl()[0].replace(".", "/")+"/"+targetFile;
			File file = new File(targetFile);
			if(file.exists()){
				//System.out.println("存在333...:");
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
        	
        	//dao的包名
        	if(!CommonTool.checkNotNull(urlData.getDaoUrl())) {
        		System.err.println("没有dao路径");
        		return;
        	}

			if(!CommonTool.checkNotNull(urlData.getTotalUrl())) {
				System.err.println("没有设置总包路径");
				return;
			}
			root.put("TotalPackageName",urlData.getTotalUrl());
        	root.put("DAOPackageName",urlData.getDaoUrl()[1]);
        	//实体类的包名
        	root.put("entityPackageName",packageName);

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