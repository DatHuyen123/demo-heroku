package com.server.tradedoc.logic.api.admin;

import com.server.tradedoc.logic.service.BlogsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.net.URISyntaxException;
import java.util.List;
import java.util.Map;

/**
 * BlogsAdminAPI
 *
 * @author DatDV
 */
@RestController
@RequestMapping("/api/admin")
public class BlogsAdminAPI {

    @Autowired
    private BlogsService blogsService;

    @RequestMapping(value = "/create-blog", method = RequestMethod.POST)
    public ResponseEntity<?> create(@RequestParam("avatar") MultipartFile avatar,
                                    @RequestParam("data") String data,
                                    @RequestParam("fileBlogs") List<MultipartFile> blogFiles) {
        return ResponseEntity.ok(blogsService.create(data, avatar , blogFiles));
    }

    @RequestMapping(value = "/update-blog", method = RequestMethod.PUT)
    public ResponseEntity<?> update(@RequestParam("avatar") MultipartFile avatar,
                                    @RequestParam("data") String data,
                                    @RequestParam("fileBlogs") List<MultipartFile> blogFiles) {
        return ResponseEntity.ok(blogsService.update(data, avatar, blogFiles));
    }

    @RequestMapping(value = "/delete-blogs", method = RequestMethod.DELETE)
    public ResponseEntity<?> delete(@RequestParam("ids") List<Long> ids) {
        return ResponseEntity.ok(blogsService.delete(ids));
    }

    @RequestMapping(value = "/find-one-blog", method = RequestMethod.GET)
    public ResponseEntity<?> findOne(@RequestParam("id") Long id) {
        return ResponseEntity.ok(blogsService.findOne(id));
    }

    @RequestMapping(value = "/find-all-blog" ,method = RequestMethod.GET)
    public ResponseEntity<?> findAll(@RequestParam Map<String , String> model) throws URISyntaxException {
        Pageable pageable = PageRequest.of(Integer.parseInt(model.get("page")) - 1 , Integer.parseInt(model.get("maxPageItem")));
        return ResponseEntity.ok(blogsService.findAll(pageable));
    }

    @RequestMapping(value = "/blogs-count" , method = RequestMethod.GET)
    public ResponseEntity<?> count() {
        return ResponseEntity.ok(blogsService.count());
    }
}
