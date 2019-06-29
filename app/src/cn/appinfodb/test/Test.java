package cn.appinfodb.test;


import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import javax.annotation.Resource;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import cn.appinfodb.dao.DevMapper;
import cn.appinfodb.pojo.AppCategory;
import cn.appinfodb.pojo.AppInfo;
import cn.appinfodb.pojo.AppVersion;
import cn.appinfodb.service.BackendService;
import cn.appinfodb.service.devService;
import cn.appinfodb.util.Page;

public class Test {

	 @org.junit.Test
	 public void ss(){
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext-mybatis.xml");
//		   String[] beanDefinitionNames = context.getBeanDefinitionNames();
//		   for (String string : beanDefinitionNames) {
//			System.out.println(string);
//		}
		devService bean = (devService) context.getBean("devServiceImpl");
//		Page page = bean.page(null, null, null, null, null, null, 1, 5);
//		System.out.println(page.getCurrPageNo());
//		int deleteApp = bean.deleteApp(60);
//		System.out.println(deleteApp);
		List<AppVersion> versionList = bean.getVersionList(60);
		System.out.println(versionList.size());
	 }
	 
	 //测试三级分类是否能执行
	 @org.junit.Test
	 public void Show() throws IOException{
		 ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext-mybatis.xml");
		 BackendService b=(BackendService)context.getBean("backendServiceImpl");
		 List<AppCategory>listD=b.listD(29);
		 for(AppCategory a:listD){
			System.out.println(a.getCategoryName());
		 }
	 }
}
