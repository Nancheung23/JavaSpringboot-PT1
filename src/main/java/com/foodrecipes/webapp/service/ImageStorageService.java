package com.foodrecipes.webapp.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import com.foodrecipes.webapp.exception.StorageException;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;


@Service
public class ImageStorageService {

    @Value("${file.upload-dir}")
    private String uploadDir;

    private static final List<String> ALLOW_EXTENSIONS = Arrays.asList("jpg", "jpeg", "png", "gif", "webp");

    // log
    private static final Logger logger = LoggerFactory.getLogger(ImageStorageService.class);

    /**
     * Store a file to specific location where set up in properties
     * @param file
     * @return
     */
    public String fileStore(MultipartFile file) {
        try {
            // verify extension name
            String filename = file.getOriginalFilename();
            if (filename == null) {
                throw new StorageException("File name is null");
            }
            logger.info("File name: {}", filename);

            // check extension type
            if (!filename.matches("^.*\\.(jpg|jpeg|png|gif|webp)$")) {
                throw new StorageException(filename + " doesn't fulfill requirement: \"jpg\", \"jpeg\", \"png\", \"gif\", \"webp\"");
            }

            // file size requirement in application properties
            if (file.isEmpty()) {
                throw new StorageException("Can not store empty file");
            }
            
            // get root path
            Path rootFileLocation = Paths.get(uploadDir);
            // make sure path valid
            Files.createDirectories(rootFileLocation);
            // get dest path
            Path destinationFileLocation = rootFileLocation
                .resolve(Paths.get(file.getOriginalFilename()))
                .normalize().toAbsolutePath();
            // compare 2 paths
            if (!destinationFileLocation.getParent().equals(rootFileLocation.toAbsolutePath())) {
                throw new StorageException("Cannot store file outside current directory");
            }
            // transfer to dest location
            file.transferTo(destinationFileLocation);

            // return string value
            return destinationFileLocation.toString();
        } catch (Exception e) {
            throw new StorageException("Can not load file", e);
        }
    }
}
