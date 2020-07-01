package com.hx.auto;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.lang.reflect.Field;
import java.util.HashMap;

import com.gitee.sunchenbin.mybatis.actable.annotation.Column;
import com.gitee.sunchenbin.mybatis.actable.annotation.Table;
import com.gitee.sunchenbin.mybatis.actable.constants.MySqlTypeConstant;
import com.hx.auto.common.JdbcType;
import com.hx.auto.common.ReadEntityData;
import com.hx.auto.common.UrlData;
import com.hx.auto.util.CommonTool;

import freemarker.template.Configuration;
import freemarker.template.DefaultObjectWrapper;
import freemarker.template.Template;

/**
 * 自动生成mapper.xml
 * 
 * @author chenjiahe 2019年09月08日23:57:47
 * 
 */
public class GeneratorMapperUtil {

    private static String templateDir = "com/hx/auto/file";//获取模板路径
    private static String templateName = "Mapper.tpl";//action模板名称
    
    /**生成mapper.xml
     * @param cl 实体类
     * @param urlData 生成配置信息
     * @throws Exception
     */
    public static void generatorMapper(Class<?> cl,UrlData urlData) throws Exception {
        try {
            // 反射start
            // 类名
            String entityName = cl.getSimpleName();
            // 类名首字母小写
            String initial = entityName.substring(0, 1).toLowerCase();
            String entityNameSmall = initial + entityName.substring(1, entityName.length());
            
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
        	
        	//实体类所有的字段
        	//用来存储数据
			ReadEntityData readEntityData = new ReadEntityData();
			//表名称
			Table table = cl.getAnnotation(Table.class);
			readEntityData.setTableName(table.name());
        	// 取得本类的全部属性
			Field[] fields = cl.getDeclaredFields();
			for (Field field:fields) {
				// 判断方法中是否有指定注解类型的注解
				boolean hasAnnotation = field.isAnnotationPresent(Column.class);
				if (hasAnnotation) {
					// 根据注解类型返回方法的指定类型注解
					Column column = field.getAnnotation(Column.class);

					//判断是不是
					boolean isBol = false;
					Integer isBlob = 0;
					if(column.type().equals(MySqlTypeConstant.TEXT)||column.type().equals(MySqlTypeConstant.LONGTEXT)
					||column.type().equals(MySqlTypeConstant.LONGBLOB)){
						isBol = true;
						isBlob = 1;
					}

					//类型
					//String type = column.type().toUpperCase();
					String type = JdbcType.jdbcTypeData(field.getType().getTypeName().toUpperCase(),isBol);
					//主键
					if (column.isKey()) {
						readEntityData.setEntityIdName(field.getName());
						readEntityData.setEntityIdData("#{"+field.getName()+"}");
						if(!CommonTool.checkNotNull(column.name())) {
							readEntityData.setTableIdName(field.getName());
						}else {
							readEntityData.setTableIdName(column.name());
						}
						readEntityData.setKeyType(type);
						continue;
					}
					//存储数据
					if(!CommonTool.checkNotNull(column.name())) {
						readEntityData.fielData(field.getName(),field.getName(),type,isBlob,"#{"+field.getName()+"}");
					}else {
						readEntityData.fielData(field.getName(),column.name(),type,isBlob,"#{"+field.getName()+"}");
					}
				}
			}
        	// 创建数据模型
        	HashMap<String, Object> root = new HashMap<String, Object>();
        	
        	//action包名
        	if(!CommonTool.checkNotNull(urlData.getActionUrl())) {
        		System.err.println("没有生成action路径");
        		return;
        	}
			if(!CommonTool.checkNotNull(urlData.getTotalUrl())) {
				System.err.println("没有设置总包路径");
				return;
			}
			root.put("TotalPackageName",urlData.getTotalUrl());
        	root.put("packageActionName",urlData.getActionUrl()[1]);
        	root.put("packageEntityName",packageName);
        	//实体类的类名
        	root.put("entityName", entityName);
        	//实体类的类名（首字母小写）
        	root.put("entityNameSmall", entityNameSmall);
        	//表字段 数据
        	root.put("fieldData",readEntityData);
        	
        	root.put("whereSentence","${whereSentence}");
        	root.put("updateSentence","${updateSentence}");
        	
        	//dao的包名
        	if(!CommonTool.checkNotNull(urlData.getDaoUrl())) {
        		System.err.println("没有dao路径");
        		return;
        	}
        	root.put("DAOPackageName",urlData.getDaoUrl()[1]);
        	//实体类的包名
        	root.put("entityPackageName",packageName);
        	
        	//生成文件路径
			String targetFile = urlData.getMapperUrl()[1].replace(".", "/")+"/";
			//生成文件名称
			targetFile += entityName + "Mapper.xml";
			//补全路径
			targetFile = "./"+urlData.getMapperUrl()[0].replace(".", "/")+"/"+targetFile;
			//获取是否已经生成过文件，拿取自定义的内容
			root.put("customData",GeneratorReadXmlUtil.readMapperXml(targetFile));


        	System.out.println("mapperUrl:"+targetFile);
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