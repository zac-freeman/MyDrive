package com.cooksys.mydrive.services;

import com.cooksys.mydrive.dto.FolderChildrenDto;
import com.cooksys.mydrive.entity.DBFile;
import com.cooksys.mydrive.entity.DBFolder;
import com.cooksys.mydrive.repository.DBFileRepository;
import com.cooksys.mydrive.repository.DBFolderRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DBFolderService {
    private DBFolderRepository dbFolderRepository;
    private DBFileRepository dbFileRepository;

    public DBFolderService(DBFolderRepository dbFolderRepository, DBFileRepository dbFileRepository) {
        this.dbFolderRepository = dbFolderRepository;
        this.dbFileRepository = dbFileRepository;
    }

    public DBFolder[] get() {
        return dbFolderRepository.findByIsRoot(true);
    }

    public FolderChildrenDto get(String[] path) {
        DBFolder folder = dbFolderRepository.findByName(path[path.length - 1]);
        List<DBFile> files = dbFileRepository.findByParentId(folder.getId());
        return new FolderChildrenDto(folder.getName(), files);
    }

    public DBFolder add(String folderName) {
        return dbFolderRepository.save(new DBFolder(folderName));
    }
}
