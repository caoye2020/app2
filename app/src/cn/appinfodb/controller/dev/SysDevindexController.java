package cn.appinfodb.controller.dev;

import java.io.File;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang.math.RandomUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import cn.appinfodb.pojo.AppCategory;
import cn.appinfodb.pojo.AppInfo;
import cn.appinfodb.pojo.AppVersion;
import cn.appinfodb.pojo.DevUser;
import cn.appinfodb.service.devService;
import cn.appinfodb.util.Page;

import com.alibaba.fastjson.JSONArray;

@Controller
@RequestMapping("/sys/devindex")
public class SysDevindexController {
	 @Resource
	 devService devService;
	 //分页查询
	 @RequestMapping("/list")
	 public String list(HttpServletRequest request,Model model,String appName,String status,String flatform,
			            String yiji,String erji,String sanji,String currPageNo,String pageSize,String parentId){
		 Page page=devService.page(appName==null||"".equals(appName)?null:appName,
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
		 request.setAttribute("statusList", devService.listStatus());
		 request.setAttribute("flatformList", devService.flatformList());
		 request.setAttribute("yiji",yiji);
		 request.setAttribute("erji",erji);
		 request.setAttribute("sanji",sanji);
		 model.addAttribute("page",page);
		 //查询一级分类
		 List<AppCategory>listCategory= devService.listCategory(parentId!=null&&!"".equals(parentId)?Integer.parseInt(parentId):null);
		 request.setAttribute("categoryLevel1List", listCategory);
			 List<AppCategory> list=devService.listCategory2(yiji==null||"".equals(yiji)?null:Integer.parseInt(yiji));
				 model.addAttribute("list2",list);
				 if(erji!=null&&!"".equals(erji)&&erji!="-1"){
					 List<AppCategory> list3=devService.listCategory2(erji==null||"".equals(erji)?null:Integer.parseInt(erji)); 
					 model.addAttribute("list3",list3);
				 }
		 return "developer/appinfolist";
	 }
	 //查询一级分类
	 @RequestMapping(value="/categorylevellist1",produces={"application/json;charset=utf-8"})
	 @ResponseBody
	 public String categorylevellist1(String parentId){
		 List<AppCategory>listCategory= devService.listCategory(parentId!=null&&!"".equals(parentId)?Integer.parseInt(parentId):null);
	    String json= JSONArray.toJSONString(listCategory);
	    System.out.println(json);
	     return json;
	 }
	 //查询二级分类
	 @RequestMapping(value="/categorylevellist.json",produces={"application/json;charset=utf-8"})
	 @ResponseBody
	 public String listCategor2(@RequestParam("pid") String pid,HttpServletRequest request){		
		 //System.out.println("pid"+pid);
			 List<AppCategory> list=devService.listCategory2(Integer.parseInt(pid));
			// System.out.println(JSONArray.toJSONString(list));
			 return JSONArray.toJSONString(list);  
	 }
	 //新增版本信息
	 @RequestMapping("/addversionadd")
	 public String addversionadd(String id,Model model,HttpServletRequest reqeust){
		 if(id!=null&&!"".equals(id)){
			 System.out.println("版本id"+id);
			 List<AppVersion> appVersionList=devService.getVersionList(Integer.parseInt(id));
			 model.addAttribute("appVersionList",appVersionList);
				 reqeust.setAttribute("appId",id);
		 }
				 return "/developer/appversionadd";
	 }
	//新增版本信息
	 @RequestMapping("/addversionsave")
	 public String addversionsave(AppVersion version,Model model,@RequestParam("a_downloadLink")MultipartFile multipart,HttpSession session
			                      ,HttpServletRequest request){
		 String apklocation="";
		 String apkName="";
		 String downfile="";
		 if(!multipart.isEmpty()){
			 //路径 
			 String path=request.getSession().getServletContext().getRealPath("statics"+File.separator+"uploadFiles");
			 //原文件名
			 String oldFileName=multipart.getOriginalFilename();
			 //后缀
			 String prefix=FilenameUtils.getExtension(oldFileName);
			 int fileSize=5000000;
			 if(multipart.getSize()>fileSize){
				 List<AppVersion> appVersionList=devService.getVersionList(version.getAppId());
				 model.addAttribute("appVersionList",appVersionList);
				 model.addAttribute("error","文件大小不能超过500kb");
			     return "developer/appversionadd";
				 //throw new RuntimeException("文件大小不能超过500kb");
			 }else if(!prefix.equalsIgnoreCase("txt")){
				 List<AppVersion> appVersionList=devService.getVersionList(version.getAppId());
				 model.addAttribute("appVersionList",appVersionList);
				 model.addAttribute("error","类型不匹配应该是txt类型的");
			     return "developer/appversionadd";
				 //throw new RuntimeException("类型不匹配应该是txt类型的");
			 }else{
				 //文件名
				 String fileName=System.currentTimeMillis()+RandomUtils.nextInt(1000000)+"_apk."+prefix;
				 apklocation=path;
				 apkName=fileName;
				 downfile=path.substring(path.lastIndexOf("app"))+File.separator+fileName;
				 File file=new File(path,fileName);
			     if(!file.exists()){
			    	 file.mkdirs();
			     }
			     //写入磁盘
			     try {
					multipart.transferTo(file);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					 List<AppVersion> appVersionList=devService.getVersionList(version.getAppId());
					 model.addAttribute("appVersionList",appVersionList);
					request.setAttribute("error","上传失败");
					 return "/developer/appversionadd";
				} 			     
			 }			 
		 }
		 version.setCreatedBy(((DevUser)session.getAttribute("user")).getId());
		 version.setCreationDate(new Date());
		 version.setApkLocPath(apklocation);
		 version.setApkFileName(apkName);
		 version.setDownloadLink(downfile);
		 //version.setAppId(appId!=null&&!"".equals(appId)?);
		 int r=devService.addversion(version);
		 System.out.println("当前新增的主键"+version.getId());
		 if(r>0){
			 //修改软件版本
			 devService.updateApp(version.getAppId(), version.getId());
		 }
		 return "redirect:/sys/devindex/list";
	 }
/*	  //处理局部异常
	  @ExceptionHandler(value={RuntimeException.class})
	  public String exctption(RuntimeException e ,Model model){
		  model.addAttribute("error",e.getMessage());
		  return "redirect:/sys/devindex/addversionadd";
	  }*/
	 //跳转版本修改
	 @RequestMapping("/addversionmodify")
	 public String modify(String id,Model model,HttpServletRequest request){
		 if(id!=null&&!"".equals(id)){
			 List<AppVersion> appVersionList=devService.getVersionList(Integer.parseInt(id));
			 model.addAttribute("appVersionList",appVersionList);
			 request.setAttribute("appId",id);
			 request.setAttribute("appVersion",devService.getVersionDate(Integer.parseInt(id)));
		 }	     
		 return "/developer/appversionmodify";
	 }
	 //修改版本信息
	 @RequestMapping("/modifysave")
	 public String modifysave(AppVersion version,String appId,HttpSession session,@RequestParam("attach")MultipartFile multipart,HttpServletRequest request,Model model){
		 String apklocation="";
		 String apkName="";
		 String downfile="";
		 if(!multipart.isEmpty()){ //文件不为空
			  String path=request.getSession().getServletContext().getRealPath("statics"+File.separator+"uploadFiles");
			  String oldFileName=multipart.getOriginalFilename();
			  String prefix=FilenameUtils.getExtension(oldFileName);
			  int fileSize=5000000;
			  if(multipart.getSize()>fileSize){
					 List<AppVersion> appVersionList=devService.getVersionList(version.getAppId());
					 model.addAttribute("appVersionList",appVersionList);
					 model.addAttribute("error","文件大小不能超过500kb");
					 request.setAttribute("appVersion",devService.getVersionDate(Integer.parseInt(appId)));
				     return "developer/appversionmodify";
					 //throw new RuntimeException("文件大小不能超过500kb");
				 }else if(!prefix.equalsIgnoreCase("txt")){
					 List<AppVersion> appVersionList=devService.getVersionList(version.getAppId());
					 model.addAttribute("appVersionList",appVersionList);
					 AppVersion versions=devService.getVersionDate(Integer.parseInt(appId));
					 request.setAttribute("appVersion",versions);
					 model.addAttribute("error","类型不匹配应该是txt类型的");
				     return "developer/appversionmodify";
					 //throw new RuntimeException("类型不匹配应该是txt类型的");
				 }else{
					 String fileName=System.currentTimeMillis()+RandomUtils.nextInt(1000000)+"_apk."+prefix;
					 apklocation=path;
					 apkName=fileName;
					 downfile=path.substring(path.lastIndexOf("app"))+File.separator+fileName;
					 File file=new File(path,fileName);
					 if(!file.isFile()){
						 file.mkdirs();
					 }
					 //写入磁盘
					 try {
						multipart.transferTo(file);
					} catch (Exception e) {
						List<AppVersion> appVersionList=devService.getVersionList(version.getAppId());
						 model.addAttribute("appVersionList",appVersionList);
						 model.addAttribute("error","上传失败");
						 request.setAttribute("appVersion",devService.getVersionDate(Integer.parseInt(appId)));
					     return "developer/appversionmodify";
					} 
				 }
		 }
		 version.setModifyBy(((DevUser)session.getAttribute("user")).getId());
		 version.setModifyDate(new Date());
		 version.setApkFileName(apkName);
		 version.setApkLocPath(apklocation);
		 version.setDownloadLink(downfile);
		 devService.updateVersion(version);
		 return "redirect:/sys/devindex/list";
	 }
	 //跳入app新增页面
     @RequestMapping("/appinfoadd")
	 public String appinfoadd(Model model){
    	 return "/developer/appinfoadd";
	 }
     //查询所有平台
     @RequestMapping(value="/sysdatadictionarylist",produces={"application/json;charset=utf-8"})
     @ResponseBody
     public String sysdatadictionarylist(){
        String json= JSONArray.toJSONString(devService.flatformList()); 
    	 return json;
     }
     //验证apk是否存在
     @RequestMapping(value="/apkexist",produces={"application/json;charset=utf-8"})
     @ResponseBody
     public String apkexist(@RequestParam("APKName")String name){
    	 int r=0;
    	 if(name==null||"".equals(name)){
    		 r=-1;
    	 }else{
    		  r=devService.existApkName(name);        	
    	 }
    	 return "{\"result\":\""+r+"\"}";
     }
     
	 //跳入app修改页面
	 @RequestMapping("/appinfomodify")
	 public String appinfomodify(String id,Model model){
		 AppInfo appInfo= devService.getAppInfo(Integer.parseInt(id));
		 model.addAttribute("appInfo", appInfo);
		 System.out.println("文件路径:"+appInfo.getLogoPicPath());
		 return "/developer/appinfomodify";
	 }
	 @RequestMapping("/appinfoaddsave")
	 public String appinfosave(AppInfo appinfo,Model model,HttpServletRequest request,HttpSession session,@RequestParam("a_logoPicPath")MultipartFile multipart){
		 String apklocation="";
		 String downfile="";
		 if(!multipart.isEmpty()){ //文件不为空
			  String path=request.getSession().getServletContext().getRealPath("statics"+File.separator+"uploadFiles");
			  String oldFileName=multipart.getOriginalFilename();
			  String prefix=FilenameUtils.getExtension(oldFileName);
			  int fileSize=5000000;
			  if(multipart.getSize()>fileSize){
					 model.addAttribute("error","文件大小不能超过500kb");
				     return "developer/appinfoadd";
					 //throw new RuntimeException("文件大小不能超过500kb");
				 }else if(!prefix.equalsIgnoreCase("jpg")){
					 model.addAttribute("error","类型不匹配应该是jpg类型的");
				     return "developer/appinfoadd";
					 //throw new RuntimeException("类型不匹配应该是txt类型的");
				 }else{
					 String fileName=System.currentTimeMillis()+RandomUtils.nextInt(1000000)+"_apk."+prefix;
					 apklocation=path;
					 downfile=path.substring(path.lastIndexOf("app"))+File.separator+fileName;
					 File file=new File(path,fileName);
					 if(!file.isFile()){
						 file.mkdirs();
					 }
					 //写入磁盘
					 try {
						multipart.transferTo(file);
					} catch (Exception e) {
						
						
						 model.addAttribute("error","上传失败");
						
					     return "developer/appinfoadd";
					} 
				 }
		 }
		 appinfo.setCreatedBy(((DevUser)session.getAttribute("user")).getId());
		 appinfo.setCreationDate(new Date());
		 appinfo.setLogoPicPath(downfile);
		 appinfo.setLogoLocPath(apklocation);
		 devService.appInfoadd(appinfo);
		 return "redirect:/sys/devindex/list";
	 }
	 //修改app
	 @RequestMapping("/appinfomodifysave")
	 public String modifyApp(AppInfo appinfo,Model model,HttpServletRequest request,HttpSession session,@RequestParam("attach")MultipartFile multipart){
		 String apklocation="";
		 String downfile="";
		 if(!multipart.isEmpty()){ //文件不为空
			  String path=request.getSession().getServletContext().getRealPath("statics"+File.separator+"uploadFiles");
			  String oldFileName=multipart.getOriginalFilename();
			  String prefix=FilenameUtils.getExtension(oldFileName);
			  int fileSize=5000000;
			  if(multipart.getSize()>fileSize){
					 model.addAttribute("error","文件大小不能超过500kb");
				     return "developer/appinfoadd";
					 //throw new RuntimeException("文件大小不能超过500kb");
				 }else if(!prefix.equalsIgnoreCase("jpg")){
					 model.addAttribute("error","类型不匹配应该是jpg类型的");
				     return "developer/appinfoadd";
					 //throw new RuntimeException("类型不匹配应该是txt类型的");
				 }else{
					 String fileName=System.currentTimeMillis()+RandomUtils.nextInt(1000000)+"_apk."+prefix;
					 apklocation=path;
					 downfile=path.substring(path.lastIndexOf("app"))+File.separator+fileName;
					 File file=new File(path,fileName);
					 if(!file.isFile()){
						 file.mkdirs();
					 }
					 //写入磁盘
					 try {
						multipart.transferTo(file);
					} catch (Exception e) {
						
						
						 model.addAttribute("error","上传失败");
						
					     return "developer/appinfoadd";
					} 
				 }
		 }
		 appinfo.setModifyBy(((DevUser)session.getAttribute("user")).getId());
		 appinfo.setModifyDate(new Date());
		 appinfo.setLogoPicPath(downfile);
		 appinfo.setLogoLocPath(apklocation);
		 devService.modifyApp(appinfo);
		 return "redirect:/sys/devindex/list";
	 }
	 //查看app
	 @RequestMapping("/view/{id}")
	 public String  appinfoview(@PathVariable("id") String id,Model model){
		 AppInfo a=devService.getApp(Integer.parseInt(id));
		 model.addAttribute("appInfoandversion",devService.getApp(Integer.parseInt(id)));
		 model.addAttribute("versionList",devService.getVersionList(Integer.parseInt(id)));
		 return "/developer/appinfoview";
	 }
	 @ResponseBody
	 @RequestMapping("/deleteApp")
	 public String deleteApp(String id){
		 int r=devService.deleteApp(Integer.parseInt(id));
		 return "{\"result\":\""+r+"\"}";
	 }
	 @ResponseBody
	 @RequestMapping("/updateStatus")
	 public String updateStatus(Integer status,Integer id){
		 int r=devService.updateAppstatus(id, status);
		 return "{\"result\":\""+r+"\"}";
	 }
}