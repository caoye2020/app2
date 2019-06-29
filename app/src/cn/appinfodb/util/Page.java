package cn.appinfodb.util;

import java.util.List;

import cn.appinfodb.pojo.AppInfo;

public class Page {
 
	 private int currPageNo;
	 private int pageSize;
	 private int totalPageCount;
	 private int totalCount;
	 private List<AppInfo>listA;
	public int getCurrPageNo() {
		return currPageNo;
	}
	public void setCurrPageNo(int currPageNo) {
		this.currPageNo = currPageNo;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public int getTotalPageCount() {
		return totalPageCount;
	}
	public void setTotalPageCount(int totalPageCount) {
		this.totalPageCount = totalPageCount;
	}
	public int getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
		if(totalCount>0){
			this.totalPageCount=this.totalCount%this.pageSize==0?this.totalCount/this.pageSize:this.totalCount/this.pageSize+1;
		}
	}
	public List<AppInfo> getListA() {
		return listA;
	}
	public void setListA(List<AppInfo> listA) {
		this.listA = listA;
	}
	 
}
