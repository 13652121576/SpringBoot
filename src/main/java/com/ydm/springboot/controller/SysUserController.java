package com.ydm.springboot.controller;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.ydm.springboot.comm.ServerResponse;
import com.ydm.springboot.comm.util.MapUtils;
import com.ydm.springboot.comm.util.Page;
import com.ydm.springboot.config.Security.UserInfo;
import com.ydm.springboot.entity.SysPower;
import com.ydm.springboot.entity.SysUser;
import com.ydm.springboot.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;


@Controller
@RequestMapping("/sys/user")
public class SysUserController {

	 @Autowired 
	 private SysUserService service;

	 /**
	  * 
	  * 新增数据的接口
	  * 
	  */
	 @ResponseBody
	 @RequestMapping(value="/create",method=RequestMethod.POST) 
	 public ServerResponse create(@RequestBody(required=false) Map<String,Object> paramMap) {
		 return service.create(paramMap);
	 }

	 /**
	  *
	  * 修改数据的接口
	  *
	  */
	 @ResponseBody
	 @RequestMapping(value="/update",method=RequestMethod.POST)
	 public ServerResponse update(@RequestBody(required=false) Map<String,Object> paramMap) {
		 return service.update(paramMap);
	 }

	 /**
	  * 
	  * 根据id获取数据的接口
	  * 
	  */
	 @ResponseBody
	 @RequestMapping(value="/getById/{id}") 
	 public SysUser getById(@PathVariable String id) {
		 return service.getById(id);
	 }


	 /**
	  * 
	  * getList的接口
	  * 
	  */
	 @ResponseBody
	 @RequestMapping(value="/getList") 
	 public List<SysUser> getList(@RequestBody(required=false) Map<String,Object> paramMap) {
		 return service.getList(paramMap);
	 }

	 /**
	  *
	  * getPageList的接口
	  *
	  */
	 @ResponseBody
	 @RequestMapping(value="/getPageList",method=RequestMethod.POST)
	 public Page getPageList(String queryJson, int page, int limit) {
		 return service.getPageList(MapUtils.JsonToMap(queryJson, page, limit));
	 }

	 /**
	  *
	  * getMapList的接口
	  *
	  */
	 @ResponseBody
	 @RequestMapping(value="/getMapList")
	 public ServerResponse getMapList(@RequestBody(required=false) Map<String,Object> paramMap) {
		 return service.getMapList(paramMap);
	 }

	 /**
	  *
	  * 用户修改个人信息
	  *
	  */
	 @ResponseBody
	 @RequestMapping(value="/updateBaseInfo",method=RequestMethod.POST)
	 public ServerResponse updateBaseInfo(@RequestBody(required=false) Map<String,Object> paramMap) {
		 return service.updateBaseInfo(paramMap);
	 }

	 /**
	  *
	  * 修改用户密码
	  *
	  */
	 @ResponseBody
	 @RequestMapping(value="/updatePassword",method=RequestMethod.POST)
	 public ServerResponse updatePassword(@RequestBody(required=false) Map<String,Object> paramMap) {
		 return service.updatePassword(paramMap);
	 }

	 /**
	  * 判断用户是否登录
	  * @param paramMap
	  * @return
	  */
	 @ResponseBody
	 @RequestMapping(value="/getIsLogin")
	 public ServerResponse getIsLogin(HttpServletRequest request) {
		 /**
		  SecurityContextHolder.getContext()获取安全上下文对象，就是那个保存在 ThreadLocal 里面的安全上下文对象
		  总是不为null(如果不存在，则创建一个authentication属性为null的empty安全上下文对象)
		  获取当前认证了的 principal(当事人),或者 request token (令牌)
		  如果没有认证，会是 null,该例子是认证之后的情况
		  */
		 Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		 //有登陆用户就返回登录用户，没有就返回null
		 if (authentication != null) {
			 if (authentication instanceof AnonymousAuthenticationToken) {
				 return null;
			 }

			 if (authentication instanceof UsernamePasswordAuthenticationToken) {
				 return ServerResponse.createBySuccess((UserInfo) authentication.getPrincipal());
			 }
		 }
		return null;
	 }

	 /**
	  * 获取用户操作权限数据
	  */
	 @ResponseBody
	 @RequestMapping(value="/getPowerList")
	 public List<SysPower> getPowerList() {
		 return service.getPowerList();
	 }

	 /**
	  * 获取导航菜单数据
	  */
	 @ResponseBody
	 @RequestMapping(value="/getMenuList")
	 public List<Object> getMenuList() {
		 return service.getMenuList();
	 }

	/**
	 * 上传文件会自动绑定到MultipartFile中 前后端分离文件上传
	 * @param request
	 * @param file
	 * @return
	 * @throws Exception
	 */
    @ResponseBody
    @RequestMapping(value="/updatePic",method=RequestMethod.POST)
    public ServerResponse updatePic(@RequestParam("file") MultipartFile file,String id) throws Exception {
    	return service.updatePic(file, id);
    }
}