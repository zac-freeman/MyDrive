package com.cooksys.mydrive.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Arrays;
import java.util.Objects;

@Entity
public class DBFile {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id")
    private int id;

    @ManyToOne
    @JoinColumn(name = "parent")
    private DBFolder parent;

    @NotNull
    @Column(name = "name")
    private String name;
    
    @NotNull
    @Column(name = "content_type")
    private String contentType;

    @NotNull
    @Column(name = "data")
    private byte[] data;

    @NotNull
    @Column(name = "is_root")
    private boolean isRoot;

    @NotNull
    @Column(name = "in_trash")
    private boolean inTrash = false;

    public DBFile() {
    }

    public DBFile(int id, DBFolder parent, @NotNull String name, @NotNull byte[] data, @NotNull boolean isRoot, @NotNull boolean inTrash) {
        this.id = id;
        this.parent = parent;
        this.name = name;
        this.data = data;
        this.isRoot = isRoot;
        this.inTrash = inTrash;
    }

    public boolean isRoot() {
        return isRoot;
    }

    public void setRoot(boolean root) {
        isRoot = root;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public DBFolder getParent() {
        return parent;
    }

    public void setParent(DBFolder parent) {
        this.parent = parent;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContentType() {
    	return contentType;
    }
    
    public void setContentType(String contentType) {
    	this.contentType = contentType;
    }
    
    public byte[] getData() {
        return data;
    }

    public void setData(byte[] rawData) {
        this.data = rawData;
    }

    public boolean isInTrash() {
        return inTrash;
    }

    public void setInTrash(boolean inTrash) {
        this.inTrash = inTrash;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof DBFile)) return false;
        DBFile file = (DBFile) o;
        return id == file.id &&
                parent == file.parent &&
                inTrash == file.inTrash &&
                Objects.equals(name, file.name) &&
                Arrays.equals(data, file.data);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(id, parent, name, inTrash);
        result = 31 * result + Arrays.hashCode(data);
        return result;
    }
}
