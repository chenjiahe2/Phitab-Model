package ${DAOPackageName};

import java.util.List;
import java.util.Map;
import com.hx.mybatisTool.SqlParam;
import ${entityPackageName}.${entityName};

public interface ${entityName}Mapper {
	/**新增，返回主键*/
    int insert(${entityName} ${entityNameSmall});
    /**查询条数*/
    int selectCount(SqlParam sqlParam);
    /**查询列表，返回实体类的List*/
    List<${entityName}> selectList(SqlParam sqlParam);
    /**查询列表，返回Map的List*/
    List<Map<String,Object>> selectListMap(SqlParam sqlParam);
    /**查询，返回单个实体*/
    ${entityName} selectOne(SqlParam sqlParam);
    /**查询，返回单个map*/
    Map<String,Object> selectOneMap(SqlParam sqlParam);
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