package com.ydm.springboot.service.impl;


import com.ydm.springboot.comm.ServerResponse;
import com.ydm.springboot.comm.util.Page;
import com.ydm.springboot.mapper.SysPowerMapper;
import com.ydm.springboot.mapper.SysRoleMapper;
import com.ydm.springboot.mapper.SysRolePowerMapper;
import com.ydm.springboot.entity.SysPower;
import com.ydm.springboot.entity.SysRole;
import com.ydm.springboot.entity.SysRolePower;
import com.ydm.springboot.service.SysRoleService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class SysRoleServiceImpl implements SysRoleService {

	 @Autowired
	 private SysRoleMapper mapper;
	 
	 @Autowired
	 private SysPowerMapper sysPowerMapper;
	 
	 @Autowired
	 private SysRolePowerMapper sysRolePowerMapper;

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
		 String roleName=(String)paramMap.get("roleName");
		 if(StringUtils.isBlank(roleName)) {
			 return ServerResponse.createByErrorMessage("角色名称不能为空！");
		 }
		 Map<String, Object> map=new HashMap<String, Object>();
		 map.put("roleName", roleName);
		 List<SysRole> list = mapper.getList(map);
		 if(list.size()>0) {
			 return ServerResponse.createByErrorMessage("该部门角色名称已存在！");
		 }
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
		 String roleName=(String)paramMap.get("roleName");
		 if(StringUtils.isBlank(roleName)) {
			 return ServerResponse.createByErrorMessage("角色名称不能为空！");
		 }
		 Map<String, Object> map=new HashMap<String, Object>();
		 map.put("roleName", roleName);
		 List<SysRole> list = mapper.getList(map);
		 if(list.size()>0&&!list.get(0).getId().equals(id)) {
			 return ServerResponse.createByErrorMessage("该部门角色名称已存在！");
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
	 public SysRole getById(String id) {
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
	 public List<SysRole> getList(Map<String, Object> paramMap) {
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
		 List<SysRole> list = mapper.getList(paramMap);
		 Map<String, Object> responseData = new HashMap<String, Object>();
		 responseData.put("list", list);
		 return ServerResponse.createBySuccess(responseData);
	 }
	 
	 /**
	  * 
	  * 获取角色权限
	  */
	 @Override
	 public ServerResponse getPowerList(Map<String, Object> paramMap) {
		 List<SysRolePower> rolePowerList = sysRolePowerMapper.getList(paramMap);
		 paramMap.remove("roleId");
		 List<SysPower> powerList = sysPowerMapper.getList(paramMap);
		 Map<String, Object> responseData = new HashMap<String, Object>();
		 responseData.put("powerList", getPowerList(powerList,null,rolePowerList));
		 return ServerResponse.createBySuccess(responseData);
	 }
	 
	 /**
	  * 递归循环组装数据
	  * @param list
	  * @return
	  */
	 private List<Object> getPowerList(List<SysPower> list,SysPower power,List<SysRolePower> rolePowerList){
		 List<Object> powerList=new ArrayList<Object>();
		 Map<String, Object> map=null;
		 if(power==null) {
			 for (SysPower sysPower : list) {
				 if(sysPower.getLevel()==1&&sysPower.getIsFinal()==0) {
					 map=new HashMap<String, Object>();
					 map.put("title", sysPower.getLableName());
					 map.put("value", sysPower.getId());
					 map.put("data", getPowerList(list,sysPower,rolePowerList));
					 for (SysRolePower sysRolePower : rolePowerList) {
						 if(sysPower.getId().equals(sysRolePower.getPowerId())) {
							 map.put("checked", true);
						 }
					 }
					 powerList.add(map);
				 }else if(sysPower.getLevel()==1&&sysPower.getIsFinal()==1){
					 map=new HashMap<String, Object>();
					 map.put("title", sysPower.getLableName());
					 map.put("value", sysPower.getId());
					 map.put("data", new ArrayList<Object>());
					 for (SysRolePower sysRolePower : rolePowerList) {
						 if(sysPower.getId().equals(sysRolePower.getPowerId())) {
							 map.put("checked", true);
						 }
					 }
					 powerList.add(map);
				 }
			 }
		 }else {
			 for (SysPower sysPower : list) {
				 if(sysPower.getIsFinal()==0&&power.getId().equals(sysPower.getPid())) {
					 map=new HashMap<String, Object>();
					 map.put("title", sysPower.getLableName());
					 map.put("value", sysPower.getId());
					 map.put("data", getPowerList(list,sysPower,rolePowerList));
					 for (SysRolePower sysRolePower : rolePowerList) {
						 if(sysPower.getId().equals(sysRolePower.getPowerId())) {
							 map.put("checked", true);
						 }
					 }
					 powerList.add(map);
				 }else if(sysPower.getIsFinal()==1&&power.getId().equals(sysPower.getPid())){
					 map=new HashMap<String, Object>();
					 map.put("title", sysPower.getLableName());
					 map.put("value", sysPower.getId());
					 map.put("data", new ArrayList<Object>());
					 for (SysRolePower sysRolePower : rolePowerList) {
						 if(sysPower.getId().equals(sysRolePower.getPowerId())) {
							 map.put("checked", true);
						 }
					 }
					 powerList.add(map);
				 }
				 
			 }
		 }
		return powerList;
	 }

}