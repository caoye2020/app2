package cn.appinfodb.controller.login;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/dev")
public class DevloginController {
 
	 @RequestMapping("/login")
	 public String login(){
		 return "/devlogin";
	 }
}
