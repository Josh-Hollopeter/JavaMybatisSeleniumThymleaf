package com.udacity.jwdnd.course1.cloudstorage.services;

import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.udacity.jwdnd.course1.cloudstorage.entities.Credentials;
import com.udacity.jwdnd.course1.cloudstorage.entities.Files;
import com.udacity.jwdnd.course1.cloudstorage.entities.Notes;
import com.udacity.jwdnd.course1.cloudstorage.mappers.CredentialsMapper;
import com.udacity.jwdnd.course1.cloudstorage.mappers.FilesMapper;
import com.udacity.jwdnd.course1.cloudstorage.mappers.NotesMapper;

@Service
public class CrudServiceImpl implements CrudServiceInterface {
	
	@Autowired
	NotesMapper notesMapper;
	
	@Autowired 
	FilesMapper filesMapper;
	
	@Autowired
	CredentialsMapper credentialsMapper;

	@Override
	public Notes editNotes(Notes note) {
		notesMapper.updateNote(note);
		
		return null;
	}
	@Override
	public Credentials editCredentials(Credentials credential) {
		credentialsMapper.updateCredential(credential);
		
		return null;
	}
	
	public String generateRandomKey() {
		  
	    int length = 16;
	    boolean useLetters = true;
	    boolean useNumbers = false;
	    String generatedString = RandomStringUtils.random(length, useLetters, useNumbers);
	 
	    return generatedString;
	}
	
	@Override
	public void writeImageToRespose(Integer id, HttpServletResponse response) {
	        //store image in browser cache
//	        response.setContentType("image/jpeg, image/jpg, image/png, image/gif");
	        response.setHeader("Cache-Control", "max-age=2628000");

	        //obtaining bytes from DB
	        Files file = filesMapper.findFilesByFileId(id);
	        byte[] imageData = file.getFiledata();
	        response.setContentType(file.getContenttype());

	        //Some conversion
	        //Maybe to base64 string or something else
	        //Pay attention to encoding (UTF-8, etc)
	        byte[] convertedStringBytes = imageData;
	        //write result to http response
	        try (OutputStream out = response.getOutputStream()) {
	            try {
					out.write(convertedStringBytes);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	        } catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
	}
	
	

}
