package com.cooksys.mydrive.controllers;

import com.cooksys.mydrive.dto.FileFolderDto;
import com.cooksys.mydrive.dto.FolderChildrenDto;
import com.cooksys.mydrive.entity.DBFolder;
import com.cooksys.mydrive.services.DBFileService;
import com.cooksys.mydrive.services.DBFolderService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/folders")

public class DBFolderController {
    private DBFolderService dbFolderService;
    private DBFileService dbFileService;

    public DBFolderController(DBFolderService dbFolderService, DBFileService dbFileService) {
        this.dbFolderService = dbFolderService;
        this.dbFileService = dbFileService;
    }

    @GetMapping
    public FileFolderDto get() {
        return new FileFolderDto("/", dbFileService.get(), dbFolderService.get());
    }

    @GetMapping("{path}")
    public FolderChildrenDto get(@PathVariable(value = "path") String[] path) {
        return dbFolderService.get(path);
    }

    @PostMapping("{folderName}")
    public DBFolder addFolder (@PathVariable(value = "folderName") String folderName){
        return dbFolderService.add(folderName);
    }
}
