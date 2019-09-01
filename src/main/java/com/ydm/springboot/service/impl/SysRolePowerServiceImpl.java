package com.ydm.springboot.service.impl;


import com.ydm.springboot.comm.ServerResponse;
import com.ydm.springboot.comm.util.Page;
import com.ydm.springboot.mapper.SysRolePowerMapper;
import com.ydm.springboot.entity.SysRolePower;
import com.ydm.springboot.service.SysRolePowerService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class SysRolePowerServiceImpl implements SysRolePowerService {

	 @Autowired
	 private SysRolePowerMapper mapper;

	 /**
	  * 
	  * 新增数据的方法
	  * 
	  */
	 @SuppressWarnings("unchecked")
	 @Override
	 public ServerResponse create(Map<String, Object> paramMap) {
		 if(paramMap==null||paramMap.isEmpty()){
			 return ServerResponse.createByErrorMessage("参数不能为空！");
		 }
		 List<String> list = (List<String>) paramMap.get("powerList");
		 String roleId = (String)paramMap.get("roleId");
		 if(list.size()>0&&StringUtils.isNotBlank(roleId)) {
			 mapper.delete(roleId);
			 Map<String, Object> map=null;
			 for (String str : list) {
				 map = new HashMap<String, Object>();
				 map.put("powerId", str);
				 map.put("roleId", roleId);
				 mapper.create(map);
			 }
		 }else {
			 return ServerResponse.createByErrorMessage("请确认必传参数不能为空！");
		 }
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
		 mapper.update(paramMap);
		 return ServerResponse.createBySuccess();
	 }

	 /**
	  * 
	  * 根据id获取数据的方法
	  * 
	  */
	 @Override
	 public SysRolePower getById(String id) {
		 return mapper.getById(id);
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
	 public List<SysRolePower> getList(Map<String, Object> paramMap) {
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
		 List<SysRolePower> list = mapper.getList(paramMap);
		 Map<String, Object> responseData = new HashMap<String, Object>();
		 responseData.put("list", list);
		 return ServerResponse.createBySuccess(responseData);
	 }

}