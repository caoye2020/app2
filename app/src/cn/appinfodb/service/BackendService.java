package cn.appinfodb.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.appinfodb.pojo.AppCategory;
import cn.appinfodb.pojo.AppInfo;
import cn.appinfodb.pojo.BackendUser;
import cn.appinfodb.pojo.DataDictionary;
import cn.appinfodb.util.Page;

public interface BackendService {
	 //��̨��¼
	BackendUser backendLogin(BackendUser user);
	//��ѯ�������
	 Page page(@Param("appName")String appName,@Param("status")Integer status,
		       @Param("flatformId")Integer flatformId,@Param("yiji")Integer yiji,@Param("erji")Integer erji,@Param("sanji")Integer sanji,@Param("currPageNo")Integer currPageNo,
		       @Param("pageSize")Integer pageSize);	
	 //��ѯ����ƽ̨
	 List<DataDictionary>flatformList(); 
		//��ѯ��������
	List<AppCategory>listD(Integer parentId);
		//��ѯapp�Լ����°汾
	public AppInfo getAppp(@Param("aid")Integer aid);		
}
