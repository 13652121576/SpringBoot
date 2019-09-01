package com.ydm.springboot.service.impl;


import com.ydm.springboot.comm.ServerResponse;
import com.ydm.springboot.comm.util.Page;
import com.ydm.springboot.mapper.SysPositionMapper;
import com.ydm.springboot.entity.SysPosition;
import com.ydm.springboot.service.SysPositionService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class SysPositionServiceImpl implements SysPositionService {

	 @Autowired
	 private SysPositionMapper mapper;

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
		 String departmentId=(String)paramMap.get("departmentId");
		 if(StringUtils.isBlank(departmentId)) {
			 return ServerResponse.createByErrorMessage("所属部门不能为空！");
		 }
		 String positionName=(String)paramMap.get("positionName");
		 if(StringUtils.isBlank(positionName)) {
			 return ServerResponse.createByErrorMessage("职位名称不能为空！");
		 }
		 Map<String, Object> map=new HashMap<String, Object>();
		 map.put("positionName", positionName);
		 map.put("departmentId", departmentId);
		 List<SysPosition> list = mapper.getList(map);
		 if(list.size()>0) {
			 return ServerResponse.createByErrorMessage("该部门职位名称已存在！");
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
		 String departmentId=(String)paramMap.get("departmentId");
		 if(StringUtils.isBlank(departmentId)) {
			 return ServerResponse.createByErrorMessage("所属部门不能为空！");
		 }
		 String positionName=(String)paramMap.get("positionName");
		 if(StringUtils.isBlank(positionName)) {
			 return ServerResponse.createByErrorMessage("职位名称不能为空！");
		 }
		 Map<String, Object> map=new HashMap<String, Object>();
		 map.put("positionName", positionName);
		 map.put("departmentId", departmentId);
		 List<SysPosition> list = mapper.getList(map);
		 if(list.size()>0&&!list.get(0).getId().equals(id)) {
			 return ServerResponse.createByErrorMessage("该部门职位名称已存在！");
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
	 public SysPosition getById(String id) {
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
	 public List<SysPosition> getList(Map<String, Object> paramMap) {
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
		 List<SysPosition> list = mapper.getList(paramMap);
		 Map<String, Object> responseData = new HashMap<String, Object>();
		 responseData.put("list", list);
		 return ServerResponse.createBySuccess(responseData);
	 }

}