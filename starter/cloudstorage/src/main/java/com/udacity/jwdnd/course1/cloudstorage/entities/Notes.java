package com.udacity.jwdnd.course1.cloudstorage.entities;



public class Notes {
	
	
	private Integer noteid;
	
	private String notetitle;
	
	private String notedescription;
	

	public Integer getNoteid() {
		return noteid;
	}

	public void setNoteid(Integer noteid) {
		this.noteid = noteid;
	}

	public String getNotetitle() {
		return notetitle;
	}

	public void setNotetitle(String notetitle) {
		this.notetitle = notetitle;
	}

	public String getNotedescription() {
		return notedescription;
	}

	public void setNotedescription(String notedescription) {
		this.notedescription = notedescription;
	}

	@Override
	public String toString() {
		return "Notes [noteid=" + noteid + ", notetitle=" + notetitle + ", notedescription=" + notedescription + "]";
	}


	

}
