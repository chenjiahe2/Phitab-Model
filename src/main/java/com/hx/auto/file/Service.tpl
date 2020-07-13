package ${servicePack};

import ${packageName}.${classNameUP};
import com.hx.mybatisTool.SqlParam;
import com.hx.mybatisTool.SqlSentence;
import java.util.List;
import java.util.Map;

public interface ${classNameUP}Service {

    int selectCount(SqlParam sqlParam);

    void insert(${classNameUP} ${className});

    List<${classNameUP}> selectList(SqlSentence sqlParam);

    List<Map<String,Object>> selectListMap(SqlSentence sqlParam);

    ${classNameUP} selectOne(SqlSentence sqlParam);

    Map<String,Object> selectOneMap(SqlSentence sqlParam);

    ${classNameUP} selectOneByKey(Object object);

    ${classNameUP} selectOneByKeyBlob(Object object);
    
	void updateAll(${classNameUP} ${className});

	void updateWhere(SqlParam sqlParam);
    
	void deleteOne(String delId);


}
