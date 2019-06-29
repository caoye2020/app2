package cn.appinfodb.controller.index;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/index")
public class Index {
	 @RequestMapping("/index")
	 public String index(){
		 return "index" ;
	 }
}
