package com.udacity.jwdnd.course1.cloudstorage.services;

import javax.servlet.http.HttpServletResponse;

import com.udacity.jwdnd.course1.cloudstorage.entities.Credentials;
import com.udacity.jwdnd.course1.cloudstorage.entities.Notes;

public interface CrudServiceInterface {
	
	
	public Notes editNotes(Notes note);
	
	public String generateRandomKey();

	void writeImageToRespose(Integer id, HttpServletResponse response);

	public Credentials editCredentials(Credentials credential);

}
