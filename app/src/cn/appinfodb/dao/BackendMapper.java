package cn.appinfodb.dao;
import java.util.List;
import org.apache.ibatis.annotations.Param;

import cn.appinfodb.pojo.AppCategory;
import cn.appinfodb.pojo.AppInfo;
import cn.appinfodb.pojo.BackendUser;
import cn.appinfodb.pojo.DataDictionary;
public interface BackendMapper {
	   //��̨��¼
		BackendUser backendLogin(BackendUser user);
		//��ѯ��������
		List<AppInfo> listA(@Param("appName")String appName,@Param("status")Integer status,
		       @Param("flatformId")Integer flatformId,@Param("yiji")Integer yiji,@Param("erji")Integer erji,@Param("sanji")Integer sanji,@Param("currPageNo")Integer currPageNo,
		       @Param("pageSize")Integer pageSize);
		//��ѯ����
		int pageCount(@Param("appName")String appName,@Param("status")Integer status,
	          @Param("flatformId")Integer flatformId,@Param("yiji")Integer yiji,@Param("erji")Integer erji,@Param("sanji")Integer sanji);    
		//����ƽ̨
		List<DataDictionary>flatformList();
		//��ѯ��������
		List<AppCategory>listD(@Param("parentId")Integer parentId);
		//��ѯapp�Լ����°汾
		public AppInfo getAppp(@Param("aid")Integer aid);
}