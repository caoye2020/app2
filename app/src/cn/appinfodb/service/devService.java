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
	  
		 //�����ߵ�¼
		 int devLogin(DevUser devUser);
		 //��ѯ�������
		 Page page(@Param("appName")String appName,@Param("status")Integer status,
			       @Param("flatformId")Integer flatformId,@Param("yiji")Integer yiji,@Param("erji")Integer erji,@Param("sanji")Integer sanji,@Param("currPageNo")Integer currPageNo,
			       @Param("pageSize")Integer pageSize);
		 //��ѯ״̬
		  List<DataDictionary>listStatus();
		//����ƽ̨
		  List<DataDictionary>flatformList();
		  //��ѯ��������
		  List<AppCategory>listCategory(Integer parendId);  
		//��ѯ��������
		  List<AppCategory>listCategory2(@Param("parentId")Integer parendId);
		//����id��ѯ�汾
		  List<AppVersion> getVersionList(Integer id);
		  //�����汾
		  int addversion(@Param("version")AppVersion version);
		  //��ѯ���һ�εİ汾
		  AppVersion getVersionDate(Integer id);
		  //�޸�����汾
		  int updateApp(Integer id,Integer versionId);
		  //�޸İ汾��Ϣ
		  int updateVersion(@Param("version")AppVersion version); 
		  //����app��Ϣ
		  int appInfoadd(AppInfo appInfo);
		  ///��֤apk�����Ƿ����
		  int existApkName(@Param("name")String name);
		  //��ѯ����app
		  AppInfo getAppInfo(@Param("id")Integer id);
		  //�޸�app
		  int modifyApp(@Param("appInfo")AppInfo appInfo); 
		  //�鿴app
		  AppInfo getApp(@Param("id")Integer id); 
		  //ɾ��app�Ͱ汾
		  int deleteApp(Integer id);
		  //�޸�app״̬
		  public int updateAppstatus(@Param("id")Integer id,@Param("status")Integer status);
  }
