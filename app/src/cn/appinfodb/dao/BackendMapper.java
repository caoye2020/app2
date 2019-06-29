package cn.appinfodb.dao;
import java.util.List;
import org.apache.ibatis.annotations.Param;

import cn.appinfodb.pojo.AppCategory;
import cn.appinfodb.pojo.AppInfo;
import cn.appinfodb.pojo.BackendUser;
import cn.appinfodb.pojo.DataDictionary;
public interface BackendMapper {
	   //后台登录
		BackendUser backendLogin(BackendUser user);
		//查询所有数据
		List<AppInfo> listA(@Param("appName")String appName,@Param("status")Integer status,
		       @Param("flatformId")Integer flatformId,@Param("yiji")Integer yiji,@Param("erji")Integer erji,@Param("sanji")Integer sanji,@Param("currPageNo")Integer currPageNo,
		       @Param("pageSize")Integer pageSize);
		//查询数量
		int pageCount(@Param("appName")String appName,@Param("status")Integer status,
	          @Param("flatformId")Integer flatformId,@Param("yiji")Integer yiji,@Param("erji")Integer erji,@Param("sanji")Integer sanji);    
		//所属平台
		List<DataDictionary>flatformList();
		//查询三级分类
		List<AppCategory>listD(@Param("parentId")Integer parentId);
		//查询app以及最新版本
		public AppInfo getAppp(@Param("aid")Integer aid);
}