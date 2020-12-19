package com.server.tradedoc.logic.service;

import com.server.tradedoc.logic.dto.ImageDTO;
import org.springframework.web.multipart.MultipartFile;

import java.net.URISyntaxException;

public interface ImageService {
    ImageDTO createImage(MultipartFile image) throws URISyntaxException;
}
