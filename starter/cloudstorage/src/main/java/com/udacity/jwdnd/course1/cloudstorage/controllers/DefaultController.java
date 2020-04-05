package com.udacity.jwdnd.course1.cloudstorage.controllers;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@ControllerAdvice
public abstract class DefaultController extends ResponseEntityExceptionHandler implements InitializingBean  {

	@Override
	public void afterPropertiesSet() throws Exception {
		// TODO Auto-generated method stub

	}
	
	@ExceptionHandler({Exception.class})
    public ResponseEntity<Object>handleError(Exception ex, RedirectAttributes redirectAttributes, Model model, WebRequest webRequest) {
        String message = "General Error";

        return handleExceptionInternal(ex, message, new HttpHeaders(), HttpStatus.BAD_REQUEST, webRequest);
    }

}
