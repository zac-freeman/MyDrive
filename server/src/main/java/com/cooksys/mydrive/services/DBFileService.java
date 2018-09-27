package com.cooksys.mydrive.services;

import com.cooksys.mydrive.entity.DBFile;
import com.cooksys.mydrive.repository.DBFileRepository;
import com.cooksys.mydrive.repository.DBFolderRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class DBFileService {
    private DBFileRepository dbFileRepository;
    private DBFolderRepository folderRepository;

    public DBFileService(DBFileRepository dbFileRepository) {
        this.dbFileRepository = dbFileRepository;
    }

    public DBFile[] get() {
        return dbFileRepository.findByIsRoot(true);
    }

    public DBFile get(String[] path) {
        return dbFileRepository.findByName(path[path.length - 1]);
    }

    public DBFile add(DBFile file) {
        return dbFileRepository.save(file);
    }

    public DBFile add(String[] path, MultipartFile file) {
        System.out.println(file);
        DBFile dbFileConstruction = new DBFile();
        try {
        	dbFileConstruction.setName(file.getOriginalFilename());
        	dbFileConstruction.setContentType(file.getContentType());
        	dbFileConstruction.setData(file.getBytes());
        	dbFileConstruction.setRoot(true);
        } catch (Exception e) {
            System.out.println(e);
        }
        return dbFileRepository.save(dbFileConstruction);
    }
}
