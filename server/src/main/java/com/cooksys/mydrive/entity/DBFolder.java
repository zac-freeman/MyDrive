package com.cooksys.mydrive.entity;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Arrays;
import java.util.Objects;
import javax.persistence.Entity;

@Entity
public class Folder {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int id;

    @OneToOne
    private Folder parent;

    @NotNull
    private String name;

    @NotNull
    private boolean isRoot;

    private boolean inTrash;

    public Folder() {
    }

    public Folder(int id, Folder parent, @NotNull String name, @NotNull boolean isRoot, @NotNull boolean inTrash) {
        this.id = id;
        this.parent = parent;
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

    public Folder getParent() {
        return parent;
    }

    public void setParent(Folder parent) {
        this.parent = parent;
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
        if (!(o instanceof Folder)) return false;
        Folder folder = (Folder) o;
        return id == folder.id &&
                parent == folder.parent &&
                isRoot == folder.isRoot &&
                inTrash == folder.inTrash &&
                Objects.equals(name, folder.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, parent, name, isRoot, inTrash);
    }
}