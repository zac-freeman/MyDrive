package com.cooksys.mydrive.entity;
import javax.persistence.*;
import javax.validation.constraints.NotNull;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import javax.persistence.Entity;

@Entity
public class DBFolder {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id")
    private int id;

    @OneToMany
    @Column(name = "db_files")
    private List<DBFile> dbFiles = new ArrayList<DBFile>();

    @NotNull
    @Column(name = "name")
    private String name;

    @NotNull
    @Column(name = "is_root")
    private boolean isRoot = true;

    @NotNull
    @Column(name = "in_trash")
    private boolean inTrash = false;

    public DBFolder() {
    }

    public DBFolder(@NotNull String name) {
        this.name = name;
    }

    public DBFolder(int id, @NotNull String name, @NotNull boolean isRoot, @NotNull boolean inTrash) {
        this.id = id;
        this.name = name;
        this.isRoot = isRoot;
        this.inTrash = inTrash;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isRoot() {
        return isRoot;
    }

    public void setRoot(boolean root) {
        isRoot = root;
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
        if (!(o instanceof DBFolder)) return false;
        DBFolder folder = (DBFolder) o;
        return id == folder.id &&
                isRoot == folder.isRoot &&
                inTrash == folder.inTrash &&
                Objects.equals(name, folder.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, isRoot, inTrash);
    }
}