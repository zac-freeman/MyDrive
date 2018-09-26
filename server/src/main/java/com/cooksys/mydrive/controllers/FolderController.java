package com.cooksys.mydrive.controllers;

import com.cooksys.mydrive.dto.FileFolderDto;
import com.cooksys.mydrive.dto.FolderChildrenDto;
import com.cooksys.mydrive.services.FileService;
import com.cooksys.mydrive.services.FolderService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/folders")

public class FolderController {
    private FolderService folderService;
    private FileService fileService;

    public FolderController(FolderService folderService, FileService fileService) {
        this.folderService = folderService;
        this.fileService = fileService;
    }

    @GetMapping
    public FileFolderDto get() {
        return new FileFolderDto("/", fileService.get(), folderService.get());
    }

    @GetMapping("{path}")
    public FolderChildrenDto get(@PathVariable(value = "path") String[] path) {
        return folderService.get(path);
    }
}
