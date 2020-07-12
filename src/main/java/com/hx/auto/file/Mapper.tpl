<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE mapper
PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN"
"http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<!-- namespace:该mapper.xml映射文件的 唯一标识 -->
<mapper namespace="${DAOPackageName}.${entityName}Mapper">

	<!-- 整个实体类修改，表字段=实体类字段-->
	<sql id="Update_Column_All">
		<trim prefixOverrides=",">
			<#list fieldData.fields as item>,${item.tableName} = ${item.mybatisName}</#list>
		</trim>
	</sql>
	 
	<!--  后续通过  namespace.id-->
	<!--parameterType:输入参数的类型
	resultType：查询返回结果值的类型  ，返回类型  -->
	<insert id="insert" parameterType="${packageEntityName}.${entityName}">
	    <selectKey keyProperty="id" resultType="String" order="BEFORE">
			select replace(uuid(),'-','') from dual
		</selectKey>
	    insert into ${fieldData.tableName} (${fieldData.tableIdName}<#list fieldData.fields as item>,${item.tableName}</#list>)
	    values (${fieldData.entityIdData}<#list fieldData.fields as item>,${item.mybatisName}</#list>)
	</insert>
	<select id="selectList" resultType="${packageEntityName}.${entityName}" parameterType="com.hx.mybatisTool.SqlParam" >
		${whereSentence}
	</select>
	<select id="selectListMap" resultType="java.util.Map" parameterType="com.hx.mybatisTool.SqlParam" >
    		${whereSentence}
    	</select>
	<select id="selectOne" resultType="${packageEntityName}.${entityName}" parameterType="com.hx.mybatisTool.SqlParam" >
    	${whereSentence} order by ${fieldData.tableIdName} desc LIMIT 1
    </select>
    <select id="selectOneMap" resultType="java.util.Map" parameterType="com.hx.mybatisTool.SqlParam" >
        	${whereSentence} order by ${fieldData.tableIdName} desc LIMIT 1
    </select>
	<select id="selectCount" resultType="int" parameterType="com.hx.mybatisTool.SqlParam" >
    		select
    			COUNT(*)
    		from ${fieldData.tableName}
    			WHERE ${whereSentence}
    </select>
	<select id="selectOneByKey" resulType="${packageEntityName}.${entityName}" parameterType="java.lang.Object" >
		select 
			${fieldData.tableIdName}<#list fieldData.fields as item>,${item.tableName}</#list>
		from ${fieldData.tableName}
		WHERE ${fieldData.tableIdName} = ${fieldData.valueData}
	</select>
	<select id="selectOneByKeyBlob" resulType="${packageEntityName}.${entityName}" parameterType="java.lang.Object" >
		select 
			${fieldData.tableIdName}<#list fieldData.fields as item>,${item.tableName}</#list>
		from ${fieldData.tableName}
		WHERE ${fieldData.tableIdName} = ${fieldData.valueData}
	</select>
	<update id="updateWhere" parameterType="com.hx.mybatisTool.SqlParam">
		${updateSentence}
	</update>
	<update id="updateAll" parameterType="${packageEntityName}.${entityName}">
		update ${fieldData.tableName}
			SET <include refid="Update_Column_All"/>
		WHERE ${fieldData.tableIdName} = ${fieldData.entityIdData}
	</update>
	<delete id="deleteWhere"  parameterType="com.hx.mybatisTool.SqlParam">
		delete from ${fieldData.tableName} WHERE ${whereSentence}
	</delete>
	<delete id="deleteById"  parameterType="java.lang.Object">
		delete from ${fieldData.tableName} WHERE ${fieldData.tableIdName} = ${fieldData.valueData}
	</delete>
	${customData}
</mapper>