package com.cooksys.mydrive.repository;

import com.cooksys.mydrive.entity.Folder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FolderRepository extends JpaRepository<Folder, Integer> {
    Folder[] findByIsRoot(boolean b);

    Folder findByName(String s);
}
