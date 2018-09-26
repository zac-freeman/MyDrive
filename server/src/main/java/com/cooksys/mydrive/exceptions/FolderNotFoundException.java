package com.cooksys.mydrive.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value= HttpStatus.NOT_FOUND, reason = "The requested Folder does not exist.")
public class FolderNotFoundException extends RuntimeException {

}