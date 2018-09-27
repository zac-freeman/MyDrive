package com.cooksys.mydrive.dto;

import com.cooksys.mydrive.entity.DBFile;
import com.cooksys.mydrive.entity.DBFolder;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class FileFolderDto {
    String path;
    List<String> dbFiles = new ArrayList<String>();
    List<String> dbFolders = new ArrayList<String>();

    public FileFolderDto() {
    }

    public FileFolderDto(String path, DBFile[] dbFiles, DBFolder[] dbFolders) {
        this.path = path;
        this.dbFiles = getNames(dbFiles);
        this.dbFolders = getNames(dbFolders);
    }

    public List<String> getNames(DBFolder[] dbFolders) {
        List<String> folderNames = new ArrayList<String>();
        for (DBFolder dbFolder : dbFolders) {
            folderNames.add(dbFolder.getName());
        }
        return folderNames;
    }

    public List<String> getNames(DBFile[] dbFiles) {
        List<String> fileNames = new ArrayList<String>();
        for (DBFile dbFile : dbFiles) {
            fileNames.add(dbFile.getName());
        }
        return fileNames;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public List<String> getFiles() {
        return dbFiles;
    }

    public void setFiles(List<String> dbFiles) {
        this.dbFiles = dbFiles;
    }

    public List<String> getFolders() {
        return dbFolders;
    }

    public void setFolders(List<String> dbFolders) {
        this.dbFolders = dbFolders;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof FileFolderDto)) return false;
        FileFolderDto that = (FileFolderDto) o;
        return Objects.equals(path, that.path) &&
                Objects.equals(dbFiles, that.dbFiles) &&
                Objects.equals(dbFolders, that.dbFolders);
    }

    @Override
    public int hashCode() {
        return Objects.hash(path, dbFiles, dbFolders);
    }
}
