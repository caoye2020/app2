package cn.appinfodb.service;

import java.io.File;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.appinfodb.dao.DevMapper;
import cn.appinfodb.pojo.AppCategory;
import cn.appinfodb.pojo.AppInfo;
import cn.appinfodb.pojo.AppVersion;
import cn.appinfodb.pojo.DataDictionary;
import cn.appinfodb.pojo.DevUser;
import cn.appinfodb.util.Page;

@Service
@Transactional
public class devServiceImpl implements devService {
    @Resource
    DevMapper devMapper;  
	@Override
	public int devLogin(DevUser devUser) {
		// TODO Auto-generated method stub
		return devMapper.devLogin(devUser);
	}

	@Override
	public Page page(String appName, Integer status, Integer flatformId,
			Integer yiji, Integer erji, Integer sanji, Integer currPageNo,
			Integer pageSize) {
		Page page=new Page();
		page.setCurrPageNo(currPageNo);
		page.setPageSize(pageSize);
		page.setTotalCount(devMapper.pageCount(appName, status, flatformId, yiji, erji, sanji));
		page.setListA(devMapper.listA(appName, status, flatformId, yiji, erji, sanji, (currPageNo-1)*pageSize, pageSize));
		return page;
	}
		@Override
		public List<DataDictionary> listStatus() {
			// TODO Auto-generated method stub
			return devMapper.listStatus();
		}
		@Override
		public List<DataDictionary> flatformList() {
			// TODO Auto-generated method stub
			return devMapper.flatformList();
		}
		@Override
		public List<AppCategory> listCategory(Integer parendId) {
			// TODO Auto-generated method stub
			return devMapper.listCategory(parendId);
		}
		@Override
		public List<AppCategory> listCategory2(Integer parendId) {
			// TODO Auto-generated method stub
			return devMapper.listCategory2(parendId);
		}
		@Override
		public int addversion(AppVersion version) {
			// TODO Auto-generated method stub
			return devMapper.addversion(version);
		}

		@Override
		public List<AppVersion> getVersionList(Integer id) {
			// TODO Auto-generated method stub
			return devMapper.getVersionList(id);
		}
	@Override
	public AppVersion getVersionDate(Integer id) {
		// TODO Auto-generated method stub
		return devMapper.getVersionDate(id);
	}
	@Override
	public int updateApp(Integer id, Integer versionId) {
		// TODO Auto-generated method stub
		return devMapper.updateApp(id, versionId);
	}
	@Override
	public int updateVersion(AppVersion version) {
		// TODO Auto-generated method stub
		return devMapper.updateVersion(version);
	}
	@Override
	public int appInfoadd(AppInfo appInfo) {
		// TODO Auto-generated method stub
		return devMapper.appInfoadd(appInfo);
	}
	@Override
	public int existApkName(String name) {
		// TODO Auto-generated method stub
		return devMapper.existApkName(name);
	}
	@Override
	public AppInfo getAppInfo(Integer id) {
		// TODO Auto-generated method stub
		return devMapper.getAppInfo(id);
	}
	@Override
	public int modifyApp(AppInfo appInfo) {
		// TODO Auto-generated method stub
		return devMapper.modifyApp(appInfo);
	}
	@Override
	public AppInfo getApp(Integer id) {
		// TODO Auto-generated method stub
		return devMapper.getApp(id);
	}
	@Transactional
	@Override
	public int deleteApp(Integer id) {
		    int r=0;
			//删除版本的apk文件
			List<AppVersion>listA=devMapper.getVersionList(id);
			for (int i = 0; i < listA.size(); i++) {
				 if(listA.get(i).getApkLocPath()!=null&&listA.get(i).getApkLocPath()!=""&&listA.get(i).getApkFileName()!=null&&listA.get(i).getApkFileName()!=""){
					 File file=new File(listA.get(i).getApkLocPath()+"\\"+listA.get(i).getApkFileName());
						if(file.isFile()&&file.exists()){
							file.delete();
							System.out.println("删除版本成功");
						}		
				 }
			}
			r=devMapper.deleteVersion(id);
			//删除app的图片
		    if(null!=devMapper.getAppById(id).getLogoPicPath()&&!"".equals(devMapper.getAppById(id).getLogoPicPath())){
		    	  File file=new File("E:/Tomcat2/webapps/"+devMapper.getAppById(id).getLogoPicPath());
					if(file.isFile()&&file.exists()){
						file.delete();
						System.out.println("删除app成功");
					}		
		    }
		    r=devMapper.deleteApp(id);
		return r;
	}
	@Override
	public int updateAppstatus(Integer id, Integer status) {
		// TODO Auto-generated method stub
		return devMapper.updateAppstatus(id, status);
	}
}