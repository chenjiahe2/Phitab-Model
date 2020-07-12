package com.hx.auto;

import com.hx.auto.common.UrlData;

import java.util.Map;

/**
 * 自动生成工具 
 * 2019-08-29
 * @author cjh
 *
 */
public class AutoDomeUtil {
	
	public static void main(String[] args) throws Exception {
		System.out.println("开始");
		UrlData urlData = new UrlData();
		urlData.setTotalUrl("com.hx");
		urlData.actionUrlData("hx-web.src.main.java","com.hx.controller.admin");
		urlData.daoUrlData("hx-web.src.main.java","com.hx.dao.mapper");
		urlData.serviceUrlData("hx-web.src.main.java","com.hx.service");
		urlData.serviceImplUrlData("hx-web.src.main.java","com.hx.service.impl");
		urlData.mapperUrlData("hx-web.src.main.java", "com.hx.dao.mapperXml");

	/*
		//dao
		GeneratorUtil.generatorDao(SysAdmin.class,urlData);
		//mapper
		GeneratorUtil.generatorMapper(SysAdmin.class, urlData);
		//action
		GeneratorUtil.generatorAction(SysAdmin.class,urlData);
		// 生成service
		GeneratorUtil.generatorService(SysAdmin.class,urlData);

		//通过实体类的包获取所有的表，直接全部生成
		//GeneratorUtil.generatorTableByPackUrl("com.hx.model", urlData);
		*/
		//generatorTableByPackUrl(packPath, urlData);
		
		/*Class cl = TestM2.class;
		Field[] fields = cl.getDeclaredFields();
		for(Field field:fields) {
			System.out.println("field.getName():"+field.getName());
			System.out.println("field.getType():"+field.getType());
			System.out.println("field.getGenericType():"+field.getGenericType());
			System.out.println("field.getModifiers():"+field.getModifiers());
		}*/
		
		//StringBuffer stringBuffer = SimpleToolUtil.getFileContent("com/cjh/auto/file/test.txt");
		//System.out.println("stringBuffer:"+stringBuffer);
	}

}