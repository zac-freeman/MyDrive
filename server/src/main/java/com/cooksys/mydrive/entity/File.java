package com.cooksys.mydrive.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Arrays;
import java.util.Objects;

@Entity
public class File {
    enum FileType {
        AUDIO, IMAGE, ARCHIVE, TEXT, FILE
    }

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int id;

    @NotNull
    private int parentId;

    @NotNull
    private String fileName;

    @NotNull
    private byte[] rawData;

    @NotNull
    private FileType type;

    @NotNull
    private boolean isTrash;

    public File() {
    }

    public File(int id, @NotNull int parentId, @NotNull String fileName, @NotNull byte[] rawData, @NotNull FileType type, @NotNull boolean isTrash) {
        this.id = id;
        this.parentId = parentId;
        this.fileName = fileName;
        this.rawData = rawData;
        this.type = type;
        this.isTrash = isTrash;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getParentId() {
        return parentId;
    }

    public void setParentId(int parentId) {
        this.parentId = parentId;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public byte[] getRawData() {
        return rawData;
    }

    public void setRawData(byte[] rawData) {
        this.rawData = rawData;
    }

    public FileType getType() {
        return type;
    }

    public void setType(FileType type) {
        this.type = type;
    }

    public boolean isTrash() {
        return isTrash;
    }

    public void setTrash(boolean trash) {
        isTrash = trash;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof File)) return false;
        File file = (File) o;
        return id == file.id &&
                parentId == file.parentId &&
                isTrash == file.isTrash &&
                Objects.equals(fileName, file.fileName) &&
                Arrays.equals(rawData, file.rawData) &&
                type == file.type;
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(id, parentId, fileName, type, isTrash);
        result = 31 * result + Arrays.hashCode(rawData);
        return result;
    }
}
