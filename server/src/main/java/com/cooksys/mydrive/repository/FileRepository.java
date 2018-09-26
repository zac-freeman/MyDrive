package com.cooksys.mydrive.repository;

import com.cooksys.mydrive.entity.File;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FileRepository extends JpaRepository<File, Integer> {
    File[] findByIsRoot(boolean b);

    File findByName(String s);

    List<File> findByParentId(int id);
}
