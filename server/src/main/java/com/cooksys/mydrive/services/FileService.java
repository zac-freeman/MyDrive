package com.cooksys.mydrive.services;

import com.cooksys.mydrive.entity.File;
import com.cooksys.mydrive.repository.FileRepository;
import com.cooksys.mydrive.repository.FolderRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class FileService {
    private FileRepository fileRepository;
    private FolderRepository folderRepository;

    public FileService(FileRepository fileRepository) {
        this.fileRepository = fileRepository;
    }

    public File[] get() {
        return fileRepository.findByIsRoot(true);
    }

    public File get(String[] path) {
        return fileRepository.findByName(path[path.length - 1]);
    }

    public File add(File file) {
        return fileRepository.save(file);
    }

    public File add(String[] path, MultipartFile file) {
        System.out.println(file);
        File fileConstruction = new File();
        try {
            fileConstruction.setRawData(file.getBytes());
            fileConstruction.setRoot(true);
            fileConstruction.setName(file.getOriginalFilename());
        } catch (Exception e) {
            System.out.println(e);
        }
        return fileRepository.save(fileConstruction);
    }
}
