package cn.appinfodb.controller.backend;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.appinfodb.pojo.AppCategory;
import cn.appinfodb.pojo.AppInfo;
import cn.appinfodb.service.BackendService;
import cn.appinfodb.service.devService;
import cn.appinfodb.util.Page;

import com.alibaba.fastjson.JSONArray;

@Controller
@RequestMapping("/sys/backend")
public class SysBackendController {
	@Resource
	BackendService backendService;
	@Resource
	devService devService;
	@RequestMapping("/list")
	 public String list(HttpServletRequest request,Model model,String appName,String status,String flatform,
			            String yiji,String erji,String sanji,String currPageNo,String pageSize,String parentId){
		 Page page=backendService.page(appName==null||"".equals(appName)?null:appName,
				         status==null||"".equals(status)?null:Integer.parseInt(status),
				        		 flatform==null||"".equals(flatform)?null:Integer.parseInt(flatform),
				         yiji==null||"".equals(yiji)?null:Integer.parseInt(yiji),
				         erji==null||"".equals(erji)?null:Integer.parseInt(erji),
				         sanji==null||"".equals(sanji)?null:Integer.parseInt(sanji),
				         currPageNo==null||"".equals(currPageNo)?1:Integer.parseInt(currPageNo),
				         5);
		 request.setAttribute("appName", appName);
		 request.setAttribute("status", status);
		 request.setAttribute("flatform", flatform);
/*		 request.setAttribute("statusList", backendService.listStatus());*/
		 request.setAttribute("flatformList", backendService.flatformList());
		 request.setAttribute("yiji",yiji);
		 request.setAttribute("erji",erji);
		 request.setAttribute("sanji",sanji);
		 model.addAttribute("page",page);
		 List<AppCategory> listD=backendService.listD(parentId==null||"".equals(parentId)?null:Integer.parseInt(parentId));
		 model.addAttribute("listD",listD);
		 if(yiji!=null&&!"".equals(yiji)){
			 List<AppCategory> listD2=backendService.listD(yiji==null||"".equals(yiji)?null:Integer.parseInt(yiji));
			 model.addAttribute("listD2",listD2);
		 }
		 if(erji!=null&&!"".equals(erji)){
			 List<AppCategory> listD3=backendService.listD(erji==null||"".equals(erji)?null:Integer.parseInt(erji));
			 model.addAttribute("listD3",listD3);
		 }
		 return "backend/applist";
	 } 
	//查询三级分类
	@ResponseBody
	@RequestMapping(value="/feilei",produces={"application/json;charset=utf-8"})
	public String fenlei(String parentId){
		List<AppCategory> listD=backendService.listD(parentId==null||"".equals(parentId)?null:Integer.parseInt(parentId));
		return JSONArray.toJSONString(listD);
	} 
	//审核
	@RequestMapping("/check")
	public String check(String aid,Model model){
		AppInfo a=backendService.getAppp(Integer.parseInt(aid));
		model.addAttribute("appInfo",a);
		return "/backend/appcheck";
	}
	@RequestMapping("/checksave")
	public String checksave(String id,String status){
		int r=devService.updateAppstatus(Integer.parseInt(id),Integer.parseInt(status));
        
		return "redirect:/sys/backend/list";
	}
} 