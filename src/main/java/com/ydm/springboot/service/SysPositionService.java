package com.ydm.springboot.service;



import com.ydm.springboot.comm.ServerResponse;
import com.ydm.springboot.comm.util.Page;
import com.ydm.springboot.entity.SysPosition;

import java.util.List;
import java.util.Map;

public interface SysPositionService{

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
	 public SysPosition getById(String id);

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
	 public List<SysPosition> getList(Map<String, Object> paramMap);

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

}