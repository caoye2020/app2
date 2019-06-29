package cn.appinfodb.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import cn.appinfodb.pojo.BackendUser;

public class BackednInterceptors extends HandlerInterceptorAdapter {
  @Override
public boolean preHandle(HttpServletRequest request,
		HttpServletResponse response, Object handler) throws Exception {
	   //String userName=((BackendUser)request.getSession().getAttribute("backendUser")).getUserName();
	   if(((BackendUser)request.getSession().getAttribute("backednUser"))==null){
		   request.setAttribute("error","Î´µÇÂ¼ÇëÏÈµÇÂ¼!");
		   request.getRequestDispatcher("/backend/login").forward(request, response);
		   return false;
	   }
	   return true;
}
}
