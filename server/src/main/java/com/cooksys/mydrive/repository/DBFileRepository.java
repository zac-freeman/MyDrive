package com.cooksys.mydrive.repository;

import com.cooksys.mydrive.entity.DBFile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DBFileRepository extends JpaRepository<DBFile, Integer> {
    DBFile[] findByIsRoot(boolean b);

    DBFile findByName(String s);

    List<DBFile> findByParentId(int id);
}
