package cn.appinfodb.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.appinfodb.dao.BackendMapper;
import cn.appinfodb.pojo.AppCategory;
import cn.appinfodb.pojo.AppInfo;
import cn.appinfodb.pojo.BackendUser;
import cn.appinfodb.pojo.DataDictionary;
import cn.appinfodb.util.Page;

@Service
public class BackendServiceImpl implements BackendService {
     @Resource
     private BackendMapper backendMapper;	  
	@Override
	public BackendUser backendLogin(BackendUser user) {
		// TODO Auto-generated method stub
		return backendMapper.backendLogin(user);
	}
	@Override
	public Page page(String appName, Integer status, Integer flatformId,
			Integer yiji, Integer erji, Integer sanji, Integer currPageNo,
			Integer pageSize) {
		Page page=new Page();
		page.setCurrPageNo(currPageNo);
		page.setPageSize(pageSize);
		page.setTotalCount(backendMapper.pageCount(appName, status, flatformId, yiji, erji, sanji));
		page.setListA(backendMapper.listA(appName, status, flatformId, yiji, erji, sanji, (currPageNo-1)*pageSize, pageSize));
		return page;
	}
	@Override
	public List<DataDictionary> flatformList() {
		// TODO Auto-generated method stub
		return backendMapper.flatformList();
	}
	@Override
	public List<AppCategory> listD(Integer parentId) {
		// TODO Auto-generated method stub
		return backendMapper.listD(parentId);
	}
	@Override
	public AppInfo getAppp(Integer aid) {
		// TODO Auto-generated method stub
		return backendMapper.getAppp(aid);
	}
}