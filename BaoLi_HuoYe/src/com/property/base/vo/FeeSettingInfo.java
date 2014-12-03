package com.property.base.vo;

import java.util.Date;

public class FeeSettingInfo {
	
	/**
	 * 条目名称
	 */
	private  String itemName;
	/**
	 * 收费标准ID
	 */
	private  int itemId;
	/**]
	 * 开始收费时间
	 */
	private  Date startdate;
	/**
	 * 结束时间
	 */
	private  Date enddate;
	public String getItemName() {
		return itemName;
	}
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
	public int getItemId() {
		return itemId;
	}
	public void setItemId(int itemId) {
		this.itemId = itemId;
	}
	public Date getStartdate() {
		return startdate;
	}
	public void setStartdate(Date startdate) {
		this.startdate = startdate;
	}
	public Date getEnddate() {
		return enddate;
	}
	public void setEnddate(Date enddate) {
		this.enddate = enddate;
	}

}
