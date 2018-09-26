package com.cooksys.mydrive.dto;

import com.cooksys.mydrive.entity.File;
import com.cooksys.mydrive.entity.Folder;

import java.util.ArrayList;
import java.util.List;

public class FolderChildrenDto extends Folder {

    private String name;
    private List<String> files;


    public FolderChildrenDto(String name, List<File> files) {
        this.name = name;
        this.files = getNames(files);
    }

    public List<String> getNames(List<File> files) {
        List<String> fileNames = new ArrayList<String>();
        for (File file : files) {
            fileNames.add(file.getName());
        }
        return fileNames;
    }
}
