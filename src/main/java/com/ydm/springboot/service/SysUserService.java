package com.ydm.springboot.service;


import java.util.List;
import java.util.Map;

import com.ydm.springboot.comm.ServerResponse;
import com.ydm.springboot.comm.util.Page;
import com.ydm.springboot.entity.SysPower;
import com.ydm.springboot.entity.SysUser;
import org.springframework.web.multipart.MultipartFile;


public interface SysUserService{

	 /**
	  * 
	  * 新增数据的方法
	  * 
	  */
	 public ServerResponse create(Map<String, Object> paramMap);

	 /**
	  * 
	  * 修改对象的方法
	  * 
	  */
	 public ServerResponse update(Map<String, Object> paramMap);

	 /**
	  * 
	  * 根据id获取对象的方法
	  * 
	  */
	 public SysUser getById(String id);
	/**
	 *
	 * 根据用户名获取对象的方法
	 *
	 */
	public SysUser getByUserName(String userName);

	 /**
	  * 
	  * 获取数据条数的方法
	  * 
	  */
	 public int getCount(Map<String, Object> paramMap);

	 /**
	  * 
	  * getList的方法
	  * 
	  */
	 public List<SysUser> getList(Map<String, Object> paramMap);

	 /**
	  *
	  * getPageList的方法
	  *
	  */
	 public Page getPageList(Map<String, Object> paramMap);

	 /**
	  *
	  * getMapList的方法
	  *
	  */
	 public ServerResponse getMapList(Map<String, Object> paramMap);

	 /**
	  *
	  * 用户修改个人头像方法
	 * @throws Exception
	  *
	  */
	 ServerResponse updatePic(MultipartFile file, String id) throws Exception;

	 /**
	  *
	  * 用户修改个人信息方法
	  *
	  */
	 ServerResponse updateBaseInfo(Map<String, Object> paramMap);

	 /**
	  *
	  * 修改用户密码的方法
	  *
	  */
	 ServerResponse updatePassword(Map<String, Object> paramMap);

	 /**
	  * 获取用户操作权限
	  *
	  */
	 List<SysPower> getPowerList();

	 /**
	  * 获取用户导航权限
	  *
	  */
	 List<Object> getMenuList();

}