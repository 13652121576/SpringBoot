package com.ydm.springboot.controller;

import com.ydm.springboot.comm.ServerResponse;
import com.ydm.springboot.comm.util.MapUtils;
import com.ydm.springboot.comm.util.Page;
import com.ydm.springboot.entity.SysPosition;
import com.ydm.springboot.service.SysPositionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/sys/position")
public class SysPositionController {

	 @Autowired
	 private SysPositionService service;

	 /**
	  * 
	  * 新增数据的接口
	  * 
	  */
	 @ResponseBody
	 @RequestMapping(value="/create",method= RequestMethod.POST)
	 public ServerResponse create(@RequestBody(required=false) Map<String,Object> paramMap) {
		 return service.create(paramMap);
	 }

	 /**
	  * 
	  * 修改数据的接口
	  * 
	  */
	 @ResponseBody
	 @RequestMapping(value="/update",method= RequestMethod.POST)
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
	 public SysPosition getById(@PathVariable String id) {
		 return service.getById(id);
	 }

	 /**
	  * 
	  * getList的接口
	  * 
	  */
	 @ResponseBody
	 @RequestMapping(value="/getList")
	 public List<SysPosition> getList(@RequestBody(required=false) Map<String,Object> paramMap) {
		 return service.getList(paramMap);
	 }

	 /**
	  * 
	  * getPageList的接口
	  * 
	  */
	 @ResponseBody
	 @RequestMapping(value="/getPageList",method= RequestMethod.POST)
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

}