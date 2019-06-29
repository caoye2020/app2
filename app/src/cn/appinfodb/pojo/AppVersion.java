package cn.appinfodb.pojo;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

public class AppVersion {
	private Integer id;//主键id
	private Integer appId;//appId
	private String versionNo;//版本�?
	private String versionInfo;//版本描述
	private Integer publishStatus;//发布状�?id
	private String downloadLink;//apk文件下载链接
	private float versionSize;//版本大小
	private Integer createdBy;//创建�?
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private Date creationDate;//创建时间
	private Integer modifyBy;//更新�?
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private Date modifyDate;//更新时间
	private String apkLocPath;//apk文件的服务器存储路径
	
	private String appName;//APP软件名称
	private String publishStatusName;//发布状�?名称
	private String apkFileName;//上传的apk文件名称
	
	public String getApkFileName() {
		return apkFileName;
	}
	public void setApkFileName(String apkFileName) {
		this.apkFileName = apkFileName;
	}
	public String getPublishStatusName() {
		return publishStatusName;
	}
	public void setPublishStatusName(String publishStatusName) {
		this.publishStatusName = publishStatusName;
	}
	public String getAppName() {
		return appName;
	}
	public void setAppName(String appName) {
		this.appName = appName;
	}
	public String getApkLocPath() {
		return apkLocPath;
	}
	public void setApkLocPath(String apkLocPath) {
		this.apkLocPath = apkLocPath;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getAppId() {
		return appId;
	}
	public void setAppId(Integer appId) {
		this.appId = appId;
	}
	public String getVersionNo() {
		return versionNo;
	}
	public void setVersionNo(String versionNo) {
		this.versionNo = versionNo;
	}
	public String getVersionInfo() {
		return versionInfo;
	}
	public void setVersionInfo(String versionInfo) {
		this.versionInfo = versionInfo;
	}
	public Integer getPublishStatus() {
		return publishStatus;
	}
	public void setPublishStatus(Integer publishStatus) {
		this.publishStatus = publishStatus;
	}
	public String getDownloadLink() {
		return downloadLink;
	}
	public void setDownloadLink(String downloadLink) {
		this.downloadLink = downloadLink;
	}
	
	public float getVersionSize() {
		return versionSize;
	}
	public void setVersionSize(float versionSize) {
		this.versionSize = versionSize;
	}
	public Integer getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(Integer createdBy) {
		this.createdBy = createdBy;
	}
	public Date getCreationDate() {
		return creationDate;
	}
	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}
	public Integer getModifyBy() {
		return modifyBy;
	}
	public void setModifyBy(Integer modifyBy) {
		this.modifyBy = modifyBy;
	}
	public Date getModifyDate() {
		return modifyDate;
	}
	public void setModifyDate(Date modifyDate) {
		this.modifyDate = modifyDate;
	}
}
