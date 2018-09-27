package com.cooksys.mydrive.repository;

import com.cooksys.mydrive.entity.DBFolder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DBFolderRepository extends JpaRepository<DBFolder, Integer> {
    DBFolder[] findByIsRoot(boolean b);

    DBFolder findByName(String s);
}
