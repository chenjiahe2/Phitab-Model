package ${DAOPackageName};

import java.util.List;
import com.hx.mybatisTool.SqlParam;
import ${entityPackageName}.${entityName};

public interface ${entityName}Mapper {
	/**新增，返回主键*/
    int insert(${entityName} ${entityNameSmall});
    /**查询条数*/
    int selectCount(SqlParam sqlParam);
    /**查询，返回数组没有大数据的*/
    List<${entityName}> selectList(SqlParam sqlParam);
    /**查询，返回数组有大数据的*/
    List<${entityName}> selectListBlob(SqlParam sqlParam);
    /**查询，返回实体类没有大数据的*/
    ${entityName} selectOne(SqlParam sqlParam);
    /**查询，返回实体类有大数据的*/
    ${entityName} selectOneBlob(SqlParam sqlParam);
    /**查询，返回实体类没有大数据的*/
    ${entityName} selectOneByKey(Object object);
    /**查询，返回实体类有大数据的*/
    ${entityName} selectOneByKeyBlob(Object object);
    /**更新，返回更新数量*/
    int updateWhere(SqlParam sqlParam);
    /**更新，返回更新数量*/
    int updateAll(${entityName} ${entityNameSmall});
    /**删除，返回删除数量*/
    int deleteWhere(SqlParam sqlParam);
    /**删除，返回删除数量*/
    int deleteById(Object object);
}