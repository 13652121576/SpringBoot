package com.ydm.springboot.service.impl;

;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import java.util.HashMap;

import com.ydm.springboot.comm.ServerResponse;
import com.ydm.springboot.comm.util.MD5;
import com.ydm.springboot.comm.util.Page;
import com.ydm.springboot.comm.util.UploadUtils;
import com.ydm.springboot.config.Security.UserInfo;
import com.ydm.springboot.entity.SysPower;
import com.ydm.springboot.mapper.SysPowerMapper;
import com.ydm.springboot.mapper.SysUserMapper;
import com.ydm.springboot.entity.SysUser;
import com.ydm.springboot.service.SysUserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;


@Service
public class SysUserServiceImpl implements SysUserService {

	 @Autowired 
	 private SysUserMapper mapper;

	 
	 @Autowired
	 private HttpServletRequest request;
	@Autowired
	private SysPowerMapper sysPowerMapper;

	//	 @Value("${default.initial.password}")
	 private String initPassword="123456";//初始密码
	 
	 /**
	  * 
	  * 新增数据的方法
	  * 
	  */
	 @Override
	 public ServerResponse create(Map<String, Object> paramMap) {

		 if(paramMap==null||paramMap.isEmpty()){
			 return ServerResponse.createByErrorMessage("参数不能为空！");
		 }
		 String userName=(String)paramMap.get("userName");
		 if(StringUtils.isBlank(userName)) {
			  return ServerResponse.createByErrorMessage("用户名不能为空！");
		 }
		 Map<String, Object> map=new HashMap<String, Object>();
		 map.put("userName", userName);
		 List<SysUser> list = mapper.getList(map);
		 if(list.size()>0) {
			 return ServerResponse.createByErrorMessage("该用户名已存在！");
		 }
		 paramMap.put("password", MD5.encrypt3Times(initPassword));//默认初始化密码
		 mapper.create(paramMap);
		 return ServerResponse.createBySuccess();
	 }
	 
	 /**
	  *
	  * 修改数据的方法
	  *
	  */
	 @Override
	 public ServerResponse update(Map<String, Object> paramMap) {
		 if(paramMap==null||paramMap.isEmpty()){
			 return ServerResponse.createByErrorMessage("参数不能为空！");
		 }
		 String id=(String)paramMap.get("id");
		 if(StringUtils.isBlank(id)) {
			 return ServerResponse.createByErrorMessage("非正常操作，请正常操作数据！");
		 }
		 paramMap.remove("userName");//去掉不能修改的字段 防止数据被修改
		 paramMap.remove("password");//去掉不能修改的字段 防止数据被修改
		 mapper.update(paramMap);
		 return ServerResponse.createBySuccess();
	 }

	 /**
	  * 
	  * 根据id获取数据的方法
	  * 
	  */
	 @Override
	 public SysUser getById(String id) {
		 return mapper.getById(id);
	 }

	@Override
	public SysUser getByUserName(String userName) {
		return mapper.getByUserName(userName);
	}

	/**
	  * 
	  * 获取数据条数的方法
	  * 
	  */
	 @Override
	 public int getCount(Map<String, Object> paramMap) {
		 return mapper.getCount(paramMap);
	 }

	 /**
	  * 
	  * getList的方法
	  * 
	  */
	 @Override
	 public List<SysUser> getList(Map<String, Object> paramMap) {
		 return mapper.getList(paramMap);
	 }

	 /**
	  *
	  * getPageList的方法
	  *
	  */
	 @Override
	 public Page getPageList(Map<String, Object> paramMap) {
		 Page page = new Page();
		 page.setData(mapper.getList(paramMap));
		 page.setCount(mapper.getCount(paramMap));
		 return page;
	 }

	 /**
	  *
	  * getMapList的方法
	  *
	  */
	 @Override
	 public ServerResponse getMapList(Map<String, Object> paramMap) {
		 List<SysUser> list = mapper.getList(paramMap);
		 Map<String, Object> responseData = new HashMap<String, Object>();
		 responseData.put("list", list);
		 return ServerResponse.createBySuccess(responseData);
	 }
	 /**
	  *
	  * 用户修改个人头像
	  * @throws Exception
	  *
	  */
	 @Override
	 public ServerResponse updatePic(@RequestParam("file") MultipartFile file, String id) throws Exception {
		 if(StringUtils.isBlank(id)) {
			return ServerResponse.createByErrorMessage("非正常操作，请正常操作数据！");
		 }
		 Map<String, Object> map = new HashMap<String, Object>();
		 String uploadUrl = UploadUtils.upload(request, file, "/upload/images/");
		 map.put("id", id);
		 map.put("picUrl", uploadUrl);
		 mapper.update(map);
		 return ServerResponse.createBySuccess();
	 }
	 /**
	  *
	  * 用户修改个人信息方法
	  *
	  */
	 @Override
	 public ServerResponse updateBaseInfo(Map<String, Object> paramMap) {
		 if(paramMap==null||paramMap.isEmpty()){
			 return ServerResponse.createByErrorMessage("参数不能为空！");
		 }
		 String id=(String)paramMap.get("id");
		 if(StringUtils.isBlank(id)) {
			 return ServerResponse.createByErrorMessage("非正常操作，请正常操作数据！");
		 }
		 paramMap.remove("userName");//去掉不能修改的字段 防止数据被修改
		 paramMap.remove("password");//去掉不能修改的字段 防止数据被修改
		 mapper.update(paramMap);
		 return ServerResponse.createBySuccess();
	 }
	 /**
	  *
	  * 修改用户密码的方法
	  *
	  */
	 @Override
	 public ServerResponse updatePassword(Map<String, Object> paramMap) {
		 if(paramMap==null||paramMap.isEmpty()){
			 return ServerResponse.createByErrorMessage("参数不能为空！");
		 }
		 String id=(String)paramMap.get("id");
		 if(StringUtils.isBlank(id)) {
			 return ServerResponse.createByErrorMessage("非正常操作，请正常操作数据！");
		 }
		 String password=(String)paramMap.get("password");
		 String newPassword=(String)paramMap.get("newPassword");
		 String newPasswordTwo=(String)paramMap.get("newPasswordTwo");
		 if(StringUtils.isBlank(password)) {
			 return ServerResponse.createByErrorMessage("密码为空，请正常操作数据！");
		 }
		 if(StringUtils.isBlank(newPassword)) {
			 return ServerResponse.createByErrorMessage("新密码为空，请正常操作数据！");
		 }
		 if(!newPassword.equals(newPasswordTwo)) {
			 return ServerResponse.createByErrorMessage("两次密码不一致，请正常操作数据！");
		 }
		 SysUser sysUser = mapper.getById(id);
		 if(sysUser==null) {
			 return ServerResponse.createByErrorMessage("该用户不存在，请联系管理员！");
		 }
		 if(sysUser.getIsValid()==1&&sysUser.getStatus()==1) {
			 String password3Times = MD5.encrypt3Times(password);
			 if(!password3Times.equals(sysUser.getPassword())) {
				 return ServerResponse.createByErrorMessage("密码有误，请重新输入！");
			 }
			 Map<String, Object> map=new HashMap<String, Object>();
			 map.put("id", id);
			 map.put("password", MD5.encrypt3Times(newPassword));
			 mapper.update(map);
			 return ServerResponse.createBySuccess();
		 }else {
			 return ServerResponse.createByErrorMessage("不能操作该用户数据，请联系管理员！");
		 }
	 }

	 /**
	  * 获取用户操作权限
	  * @return
	  */
	 @Override
	 public List<SysPower> getPowerList(){
		 Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		 SysUser sysUser = this.getByUserName(((UserInfo)authentication.getPrincipal()).getUsername());
		 HashMap<String, Object> map = new HashMap<String, Object>();
		 map.put("roleId", sysUser.getRoleId());
		 List<SysPower> list = sysPowerMapper.getList(map);
		 return list;
	 }

	 /**
	  * 获取用户导航权限
	  * @return
	  */
	 @Override
	 public List<Object> getMenuList(){
		 Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		 SysUser sysUser = this.getByUserName(((UserInfo)authentication.getPrincipal()).getUsername());
		 HashMap<String, Object> map = new HashMap<String, Object>();
		 map.put("roleId", sysUser.getRoleId());
		 map.put("style", "导航");
		 map.put("limitType", 1);
		 List<SysPower> list = sysPowerMapper.getList(map);
		 return getPowerList(list, null);
	 }

	 /**
	  * 递归循环组装数据
	  * @param list
	  * @return
	  */
	 private List<Object> getPowerList(List<SysPower> list,SysPower power){
		 List<Object> powerList=new ArrayList<Object>();
		 Map<String, Object> map=null;
		 if(power==null) {
			 for (SysPower sysPower : list) {
				 if(sysPower.getLevel()==1) {
					 map=new HashMap<String, Object>();
					 map.put("id", sysPower.getId());
					 map.put("title", sysPower.getLableName());
					 map.put("icon", sysPower.getImageUrl());
					 map.put("url", sysPower.getWebUrl());
					 map.put("children", getPowerList(list, sysPower));
					 powerList.add(map);
				 }
			 }
		 }else {
			 for (SysPower sysPower : list) {
				 if(power.getId().equals(sysPower.getPid())) {
					 map=new HashMap<String, Object>();
					 map.put("id", sysPower.getId());
					 map.put("title", sysPower.getLableName());
					 map.put("icon", sysPower.getImageUrl());
					 map.put("url", sysPower.getWebUrl());
					 map.put("children", getPowerList(list, sysPower));
					 powerList.add(map);
				 }

			 }
		 }
		return powerList;
	 }
}