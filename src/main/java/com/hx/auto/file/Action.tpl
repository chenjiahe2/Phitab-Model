package ${packageName};

import java.util.List;
import javax.annotation.Resource;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import com.hx.common.CommonInit;
import ${DAOPackageName}.${entityName}Mapper;
import ${entityPackageName}.${entityName};
import ${servicePackageName}.${entityName}Service;
import com.hx.resultTool.Result;
import com.hx.util.SimpleTool;

@RestController
@RequestMapping("${entityNameSmall}")
public class ${entityName}Controller extends CommonInit{

	@Resource
	private ${entityName}Service service;
	
	/**项目列表*/
	@RequestMapping("/list")
    public Result listData(Integer pageNum,Integer rowCount) {
        //pageNum当前第几页
        //rowCount 拿几条

        //一页几条
        if(!SimpleTool.checkNotNull(rowCount)) {
            rowCount = 20;
        }
        //当前第几页
        if(!SimpleTool.checkNotNull(pageNum)) {
            pageNum = 1;
        }
        //分页插件
        PageHelper.startPage(pageNum,rowCount);
        List<${entityName}> ${entityNameSmall}s = service.selectList(sqlParam);
        PageInfo<${entityName}> pageInfo = new PageInfo<${entityName}>(${entityNameSmall}s);
		return Result.success(pageInfo);
    }
	
	/**新增*/
	@RequestMapping("/add")
    public Result addData(${entityName} ${entityNameSmall}) {
		service.add(${entityNameSmall});
		return Result.success();
    }
	
	/**获取数据*/
	@RequestMapping("/see")
    public Result editeData(String id) {
		${entityName} ${entityNameSmall} = service.selectOneByKeyBlob(id);
		return Result.success(${entityNameSmall});
    }
	
	/**修改数据*/
	@RequestMapping("/update")
    public Result updateData(${entityName} ${entityNameSmall}) {
		service.updateAll(${entityNameSmall});
		return Result.success();
    }
	
	/**删除数据（单个）*/
	@RequestMapping("/delete/one")
	public Result deleteData(String id) {
		service.deleteOne(id);
		return Result.success();
	}
}
