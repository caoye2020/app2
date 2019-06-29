package cn.appinfodb.controller.dev;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import cn.appinfodb.pojo.DevUser;
import cn.appinfodb.service.devService;

@Controller
@RequestMapping("/devindex")
public class DevindexController {   
	 @Resource
	 devService devService;	 
	 @RequestMapping("/devindex")
	 public String devIndex(HttpSession session,DevUser user){
		 if(user.getDevName()==null||"".equals(user.getDevName())){
			  user.setDevName("admin");
			  user.setDevPassword("123456");
		 }
		 int i=devService.devLogin(user);
		 if(i<=0){
			throw new RuntimeException();
		 }
		 session.setAttribute("user",user);
		 return "developer/main";
	 }
	 @ExceptionHandler(value={RuntimeException.class})
	 public String handlerException(RuntimeException e,HttpServletRequest request){
		 System.out.println(e.getMessage());
		 e.printStackTrace();
		 request.setAttribute("error","用户名或密码错误！");
		 return "forward:/dev/login";
	 }
	
}