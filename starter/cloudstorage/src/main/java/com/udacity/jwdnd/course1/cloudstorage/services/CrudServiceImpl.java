package com.udacity.jwdnd.course1.cloudstorage.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import org.apache.commons.lang3.RandomStringUtils;
import com.udacity.jwdnd.course1.cloudstorage.entities.Notes;
import com.udacity.jwdnd.course1.cloudstorage.mappers.NotesMapper;

@Service
public class CrudServiceImpl implements CrudServiceInterface {
	
	@Autowired
	NotesMapper notesMapper;

	@Override
	public Notes editNotes(Notes note) {
		notesMapper.updateNote(note);
		
		return null;
	}
	
	public String generateRandomKey() {
		  
	    int length = 16;
	    boolean useLetters = true;
	    boolean useNumbers = false;
	    String generatedString = RandomStringUtils.random(length, useLetters, useNumbers);
	 
	    return generatedString;
	}
	
	

}
