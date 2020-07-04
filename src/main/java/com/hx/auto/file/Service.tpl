package ${servicePack};

import ${packageName}.${classNameUP};
import com.hx.mybatisTool.SqlParam;
import java.util.List;

public interface ${classNameUP}Service {

    List<${classNameUP}> selectList(SqlParam sqlParam);

    List<${classNameUP}> selectListBlob(SqlParam sqlParam);

    ${classNameUP} selectOne(SqlParam sqlParam);

    ${classNameUP} selectOneBlob(SqlParam sqlParam);

    ${classNameUP} selectOneByKey(Object object);

    ${classNameUP} selectOneByKeyBlob(Object object);

	void add(${classNameUP} ${className});
    
	void updateAll(${classNameUP} ${className});

	void updateWhere(SqlParam sqlParam);
    
	void deleteOne(String delId);

	int selectCount(SqlParam sqlParam);

}
