package ${servicePack};

import ${packageName}.${classNameUP};
import com.hx.mybatisTool.SqlParam;
import java.util.List;

public interface ${classNameUP}Service {

    int selectCount(SqlParam sqlParam);

    void insert(${classNameUP} ${className});

    List<${classNameUP}> selectList(SqlParam sqlParam);

    List<Map<String,Object>> selectListMap(SqlParam sqlParam);

    ${classNameUP} selectOne(SqlParam sqlParam);

    ${classNameUP} selectOneBlob(SqlParam sqlParam);

    ${classNameUP} selectOneByKey(Object object);

    ${classNameUP} selectOneByKeyBlob(Object object);
    
	void updateAll(${classNameUP} ${className});

	void updateWhere(SqlParam sqlParam);
    
	void deleteOne(String delId);


}
