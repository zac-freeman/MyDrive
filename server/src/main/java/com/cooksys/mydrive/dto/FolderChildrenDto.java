package com.cooksys.mydrive.dto;

import com.cooksys.mydrive.entity.DBFile;
import com.cooksys.mydrive.entity.DBFolder;

import java.util.ArrayList;
import java.util.List;

public class FolderChildrenDto extends DBFolder {

    private String name;
    private List<String> dbFiles;


    public FolderChildrenDto(String name, List<DBFile> dbFiles) {
        this.name = name;
        this.dbFiles = getNames(dbFiles);
    }

    public List<String> getNames(List<DBFile> dbFiles) {
        List<String> fileNames = new ArrayList<String>();
        for (DBFile dbFile : dbFiles) {
            fileNames.add(dbFile.getName());
        }
        return fileNames;
    }
}
