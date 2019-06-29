package cn.appinfodb.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.appinfodb.pojo.AppCategory;
import cn.appinfodb.pojo.AppInfo;
import cn.appinfodb.pojo.BackendUser;
import cn.appinfodb.pojo.DataDictionary;
import cn.appinfodb.util.Page;

public interface BackendService {
	 //后台登录
	BackendUser backendLogin(BackendUser user);
	//查询所有软件
	 Page page(@Param("appName")String appName,@Param("status")Integer status,
		       @Param("flatformId")Integer flatformId,@Param("yiji")Integer yiji,@Param("erji")Integer erji,@Param("sanji")Integer sanji,@Param("currPageNo")Integer currPageNo,
		       @Param("pageSize")Integer pageSize);	
	 //查询所有平台
	 List<DataDictionary>flatformList(); 
		//查询三级分类
	List<AppCategory>listD(Integer parentId);
		//查询app以及最新版本
	public AppInfo getAppp(@Param("aid")Integer aid);		
}
