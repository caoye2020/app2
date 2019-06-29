package cn.appinfodb.controller.login;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;

import cn.appinfodb.pojo.BackendUser;
import cn.appinfodb.service.BackendService;

@Controller
@RequestMapping("/backend")
public class BackendloginController {
     
	 @Resource
	 BackendService backendService;
	
	 @RequestMapping("/login")
	 public String login(){
		 return "/backendlogin";
	 }
	 @RequestMapping("dologin")
	 public String dologin(BackendUser user,HttpSession session){
		BackendUser users= backendService.backendLogin(user);
		if(users==null){
			 throw new RuntimeException("用户名或者密码错误!");
		}
		session.setAttribute("backednUser", users);
		return "/backend/main"; 
	 }
	 @RequestMapping("/logout")
	 public String logout(HttpSession session){
		 session.removeAttribute("backendUser");
		 return "/index";
	 }
	 @ExceptionHandler(value={RuntimeException.class})
	 public String handlerException(RuntimeException e,HttpServletRequest request){
		 e.printStackTrace();
		 request.setAttribute("error",e.getMessage());
		 return "/backendlogin";
	 }
}
