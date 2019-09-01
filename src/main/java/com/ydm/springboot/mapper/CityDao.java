package com.ydm.springboot.mapper;


import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 */
@Mapper
public interface CityDao {
	List findAll();
}
