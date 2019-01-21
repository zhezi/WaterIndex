package com.jieshuizhibiao.waterindex.beans;

public class UploadFileResponseBean {
	String url;	//图片地址	字符串(string)		图片相对地址 20181214/e35aebda9dcc21472a2c79db61eb0234.PNG
	String filename;	//文件名称	字符串(string)		IMG_1728.PNG

	public String getUrl() {
		return url == null ? "" : url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getFilename() {
		return filename == null ? "" : filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}
}