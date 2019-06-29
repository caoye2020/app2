package cn.appinfodb.controller.dev;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/logout")
public class LogOutController {

	 @RequestMapping("/logout")
	 public String logout(HttpSession session){
		 session.removeAttribute("user");
		 return "index";
	 }
}
