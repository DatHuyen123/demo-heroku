package com.server.tradedoc.logic.service.impl;

import com.server.tradedoc.logic.converter.ImagesConverter;
import com.server.tradedoc.logic.dto.ImageDTO;
import com.server.tradedoc.logic.entity.ImageEntity;
import com.server.tradedoc.logic.repository.ImageRepository;
import com.server.tradedoc.logic.service.ImageService;
import com.server.tradedoc.utils.CommonUtils;
import com.server.tradedoc.utils.FilesUtils;
import com.server.tradedoc.utils.error.CustomException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.net.URISyntaxException;

@Service
public class ImageServiceImpl implements ImageService {

    @Autowired
    private FilesUtils filesUtils;

    @Autowired
    private ImageRepository imageRepository;

    @Autowired
    private ImagesConverter imagesConverter;

    @Override
    public ImageDTO createImage(MultipartFile image) throws URISyntaxException {
        if (image.isEmpty()) {
            throw new CustomException("image undefined", CommonUtils.putError("imageFiles", "ERR_0034"));
        }
        String nameFileServer = filesUtils.generateFileName(image.getOriginalFilename());
        String fileName = filesUtils.save(image, "/thumbnail/", nameFileServer);
        ImageEntity imageEntity = new ImageEntity();
        imageEntity.setName(nameFileServer);
        imageEntity.setPathFile(fileName);
        imageEntity = imageRepository.save(imageEntity);
        imageEntity.setPathFile(filesUtils.genFilePath(imageEntity.getPathFile()));
        return imagesConverter.toDto(imageEntity);
    }
}
