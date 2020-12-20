package com.server.tradedoc.logic.service;

import com.server.tradedoc.logic.dto.ImageDTO;
import com.server.tradedoc.logic.dto.reponse.ImageResponse;
import org.springframework.web.multipart.MultipartFile;

import java.net.URISyntaxException;

public interface ImageService {
    ImageResponse createImage(MultipartFile image) throws URISyntaxException;
}
