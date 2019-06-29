package cn.appinfodb.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.appinfodb.pojo.AppCategory;
import cn.appinfodb.pojo.AppInfo;
import cn.appinfodb.pojo.AppVersion;
import cn.appinfodb.pojo.DataDictionary;
import cn.appinfodb.pojo.DevUser;
import cn.appinfodb.util.Page;

public interface devService {
	  
		 //开发者登录
		 int devLogin(DevUser devUser);
		 //查询所有软件
		 Page page(@Param("appName")String appName,@Param("status")Integer status,
			       @Param("flatformId")Integer flatformId,@Param("yiji")Integer yiji,@Param("erji")Integer erji,@Param("sanji")Integer sanji,@Param("currPageNo")Integer currPageNo,
			       @Param("pageSize")Integer pageSize);
		 //查询状态
		  List<DataDictionary>listStatus();
		//所属平台
		  List<DataDictionary>flatformList();
		  //查询三级分类
		  List<AppCategory>listCategory(Integer parendId);  
		//查询二级分类
		  List<AppCategory>listCategory2(@Param("parentId")Integer parendId);
		//根据id查询版本
		  List<AppVersion> getVersionList(Integer id);
		  //新增版本
		  int addversion(@Param("version")AppVersion version);
		  //查询最近一次的版本
		  AppVersion getVersionDate(Integer id);
		  //修改软件版本
		  int updateApp(Integer id,Integer versionId);
		  //修改版本信息
		  int updateVersion(@Param("version")AppVersion version); 
		  //新增app信息
		  int appInfoadd(AppInfo appInfo);
		  ///验证apk名字是否存在
		  int existApkName(@Param("name")String name);
		  //查询单个app
		  AppInfo getAppInfo(@Param("id")Integer id);
		  //修改app
		  int modifyApp(@Param("appInfo")AppInfo appInfo); 
		  //查看app
		  AppInfo getApp(@Param("id")Integer id); 
		  //删除app和版本
		  int deleteApp(Integer id);
		  //修改app状态
		  public int updateAppstatus(@Param("id")Integer id,@Param("status")Integer status);
  }
