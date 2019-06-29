package cn.appinfodb.interceptor;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import cn.appinfodb.pojo.DevUser;
public class Sysinterceptor extends HandlerInterceptorAdapter{
	 @Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		 System.out.println("À¹½ØÇëÇó");
   	      if(((DevUser)request.getSession().getAttribute("user"))==null){
 	    	 request.setAttribute("error","Î´µÇÂ¼ÇëÏÈµÇÂ¼!");
 	    	 request.getRequestDispatcher("/dev/login").forward(request, response);
 	    	 return false;
 	      }
		 return true;			   
	}
	 @Override
	public void afterCompletion(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
	      
	}
	 
}