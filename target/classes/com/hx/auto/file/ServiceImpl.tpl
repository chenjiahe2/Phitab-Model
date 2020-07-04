package ${serviceImpPack};

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hx.exception.TipsException;
import ${daoPack}.${classNameUP}Mapper;
import ${packageName}.${classNameUP};
import ${servicePack}.${classNameUP}Service;
import com.hx.mybatisTool.SqlParam;
import java.util.List;

@Transactional
@Service
public class ${classNameUP}ServiceImpl implements ${classNameUP}Service {

    @Resource
    private ${classNameUP}Mapper ${className}Mapper;

    /**查询列表，大数据类型不拿取*/
    @Override
    public List<${classNameUP}> selectList(SqlParam sqlParam) {
        return ${className}Mapper.selectList(sqlParam);
    }

    /**查询列表，大数据类型拿取*/
    @Override
    public List<${classNameUP}> selectListBlob(SqlParam sqlParam) {
        return ${className}Mapper.selectListBlob(sqlParam);
    }

    /**查询单个，大数据不拿取*/
    @Override
    public ${classNameUP} selectOne(SqlParam sqlParam) {
        return ${className}Mapper.selectOne(sqlParam);
    }

    /**查询单个，大数据拿取*/
    @Override
    public ${classNameUP} selectOneBlob(SqlParam sqlParam) {
        return ${className}Mapper.selectOneBlob(sqlParam);
    }

    /**查询单个，大数据不拿取*/
    @Override
    public ${classNameUP} selectOneByKey(Object object) {
        return ${className}Mapper.selectOneByKey(object);
    }

    /**查询单个，大数据拿取*/
    @Override
    public ${classNameUP} selectOneByKeyBlob(Object object) {
        return ${className}Mapper.selectOneByKeyBlob(object);
    }

    /**新增*/
	@Override
	public void add(${classNameUP} ${className}) {
		int count = ${className}Mapper.insert(${className});
		if(count != 1) {
			throw new TipsException("新增失败！");
		}
	}

	/**修改*/
	@Override
	public void updateAll(${classNameUP} ${className}) {
		int count = ${className}Mapper.updateAll(${className});
		if(count!=1) {
			throw new TipsException("保存失败！");
		}
	}

	/**修改*/
    @Override
    public void updateWhere(SqlParam sqlParam) {
        int count = ${className}Mapper.updateWhere(sqlParam);
        if(count!=1) {
            throw new TipsException("保存失败！");
        }
    }
	
	/**删除一个*/
	@Override
	public void deleteOne(String delId) {
		int count = ${className}Mapper.deleteById(delId);
		if(count!=1) {
			throw new TipsException("删除失败！");
		}
	}

	/**查询条数*/
    @Override
    public int selectCount(SqlParam sqlParam) {
        int count = ${className}Mapper.selectCount(sqlParam);
        return count;
    }
}
