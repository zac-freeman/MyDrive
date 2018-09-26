package com.cooksys.mydrive.dto;

import com.cooksys.mydrive.entity.File;
import com.cooksys.mydrive.entity.Folder;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class FileFolderDto {
    String path;
    List<String> files = new ArrayList<String>();
    List<String> folders = new ArrayList<String>();

    public FileFolderDto() {
    }

    public FileFolderDto(String path, File[] files, Folder[] folders) {
        this.path = path;
        this.files = getNames(files);
        this.folders = getNames(folders);
    }

    public List<String> getNames(Folder[] folders) {
        List<String> folderNames = new ArrayList<String>();
        for (Folder file : folders) {
            folderNames.add(file.getName());
        }
        return folderNames;
    }

    public List<String> getNames(File[] files) {
        List<String> fileNames = new ArrayList<String>();
        for (File file : files) {
            fileNames.add(file.getName());
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
        return files;
    }

    public void setFiles(List<String> files) {
        this.files = files;
    }

    public List<String> getFolders() {
        return folders;
    }

    public void setFolders(List<String> folders) {
        this.folders = folders;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof FileFolderDto)) return false;
        FileFolderDto that = (FileFolderDto) o;
        return Objects.equals(path, that.path) &&
                Objects.equals(files, that.files) &&
                Objects.equals(folders, that.folders);
    }

    @Override
    public int hashCode() {
        return Objects.hash(path, files, folders);
    }
}
