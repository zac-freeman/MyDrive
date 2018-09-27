package com.cooksys.mydrive.services;

import com.cooksys.mydrive.dto.FolderChildrenDto;
import com.cooksys.mydrive.entity.File;
import com.cooksys.mydrive.entity.Folder;
import com.cooksys.mydrive.repository.FileRepository;
import com.cooksys.mydrive.repository.FolderRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FolderService {
    private FolderRepository folderRepository;
    private FileRepository fileRepository;

    public FolderService(FolderRepository folderRepository, FileRepository fileRepository) {
        this.folderRepository = folderRepository;
        this.fileRepository = fileRepository;
    }

    public Folder[] get() {
        return folderRepository.findByIsRoot(true);
    }

    public FolderChildrenDto get(String[] path) {
        Folder folder = folderRepository.findByName(path[path.length - 1]);
        List<File> files = fileRepository.findByParentId(folder.getId());
        return new FolderChildrenDto(folder.getName(), files);
    }
}
