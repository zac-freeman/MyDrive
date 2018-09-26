package com.cooksys.mydrive.controllers;

import com.cooksys.mydrive.entity.File;
import com.cooksys.mydrive.services.FileService;

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
public class FileController {
    private FileService fileService;

    public FileController(FileService fileService) {
        this.fileService = fileService;
    }

    @GetMapping("{path}")
    public ResponseEntity<Resource> get(@PathVariable(value = "path") String[] path) {
    	File dbFile = fileService.get(path);
    	String filename = dbFile.getName();
    	String fileType = dbFile.getContentType();
    	
    	return ResponseEntity.ok()
    			.contentType(MediaType.parseMediaType(fileType))
    			.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + filename + "\"")
    			.body(new ByteArrayResource(dbFile.getRawData()));
    }

    @PostMapping("{path}")
    public String uploadFile(@PathVariable(value = "path") String[] path, @RequestBody MultipartFile file) {
        fileService.add(path, file);
        return "this is a placeholder, to keep things professional!";
    }

//    @PostMapping("{path}")
//    public File get(@PathVariable(value = "path") String[] path, @RequestBody File file) {
//
//        return fileService.add(path, file);
//    }
}
