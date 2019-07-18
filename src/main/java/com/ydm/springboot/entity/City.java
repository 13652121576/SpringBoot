package com.ydm.springboot.entity;


import java.io.Serializable;

/**
 *
 * @date 2019-05-29 17:41:48
 */
public class City implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	private Integer id;
	/**
	 * 
	 */
	private String name;
	/**
	 * 
	 */
	private String parentid;
	/**
	 * 
	 */
	private Integer level;
	/**
	 * 首字母
	 */
	private String szm;
	/**
	 * 全拼音
	 */
	private String pingyin;

	/**
	 * 设置：
	 */
	public void setId(Integer id) {
		this.id = id;
	}
	/**
	 * 获取：
	 */
	public Integer getId() {
		return id;
	}
	/**
	 * 设置：
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * 获取：
	 */
	public String getName() {
		return name;
	}
	/**
	 * 设置：
	 */
	public void setParentid(String parentid) {
		this.parentid = parentid;
	}
	/**
	 * 获取：
	 */
	public String getParentid() {
		return parentid;
	}
	/**
	 * 设置：
	 */
	public void setLevel(Integer level) {
		this.level = level;
	}
	/**
	 * 获取：
	 */
	public Integer getLevel() {
		return level;
	}
	/**
	 * 设置：首字母
	 */
	public void setSzm(String szm) {
		this.szm = szm;
	}
	/**
	 * 获取：首字母
	 */
	public String getSzm() {
		return szm;
	}
	/**
	 * 设置：全拼音
	 */
	public void setPingyin(String pingyin) {
		this.pingyin = pingyin;
	}
	/**
	 * 获取：全拼音
	 */
	public String getPingyin() {
		return pingyin;
	}
}
