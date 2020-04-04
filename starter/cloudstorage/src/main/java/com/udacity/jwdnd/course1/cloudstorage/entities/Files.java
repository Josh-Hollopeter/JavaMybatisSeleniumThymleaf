package com.udacity.jwdnd.course1.cloudstorage.entities;


public class Files {
	
	
	private Integer fileid;
	
	private String filename;
	
	private String contenttype;
	
	private Long filesize;
	
//	private Integer userid;
	
	private byte [] filedata;

	

	public Integer getFileid() {
		return fileid;
	}

	public void setFileid(Integer fileid) {
		this.fileid = fileid;
	}

	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

	public String getContenttype() {
		return contenttype;
	}

	public void setContenttype(String contenttype) {
		this.contenttype = contenttype;
	}




	public Long getFilesize() {
		return filesize;
	}

	public void setFilesize(Long filesize) {
		this.filesize = filesize;
	}

	public byte[] getFiledata() {
		return filedata;
	}

	public void setFiledata(byte[] bs) {
		this.filedata = bs;
	}
	

}
