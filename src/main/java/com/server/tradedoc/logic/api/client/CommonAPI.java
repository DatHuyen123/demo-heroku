package com.server.tradedoc.logic.api.client;

import com.server.tradedoc.logic.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/client")
public class CommonAPI {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/get-my-profile" , method = RequestMethod.GET)
    public ResponseEntity<?> getMyProFile(){
        return ResponseEntity.ok(userService.findOne());
    }
}
