package com.cooksys.mydrive.controllers;

import com.cooksys.mydrive.entity.DBFile;
import com.cooksys.mydrive.services.DBFileService;

import java.nio.file.Files;
import java.nio.file.Path;

import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping(value = "/files")
public class DBFileController {
    private DBFileService dbFileService;

    public DBFileController(DBFileService dbFileService) {
        this.dbFileService = dbFileService;
    }

    @GetMapping("{path}")
    public ResponseEntity<Resource> get(@PathVariable(value = "path") String[] path) {
    	DBFile dbFile = dbFileService.get(path);
    	String filename = dbFile.getName();
    	String fileType = dbFile.getContentType();
    	
    	return ResponseEntity.ok()
    			.contentType(MediaType.parseMediaType(fileType))
    			.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + filename + "\"")
    			.body(new ByteArrayResource(dbFile.getData()));
    }

    @PostMapping("{path}")
    public String uploadFile(@PathVariable(value = "path") String[] path, @RequestBody MultipartFile file) {
        dbFileService.add(path, file);
        return "this is a placeholder, to keep things professional!";
    }
}
