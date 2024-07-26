package com.example.apidemo.controllers;

import com.example.apidemo.components.RepositoryHandler;
import com.example.apidemo.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;



@RestController
@RequestMapping("/api")
public class GetDataController {

    private final RepositoryHandler repositoryHandler;

    @Autowired
    public GetDataController(final RepositoryHandler repositoryHandler){
        this.repositoryHandler = repositoryHandler;
    }

    @GetMapping(value = "/get_repositories", consumes = "application/json")
    public ResponseEntity<String> getRepositoriesInfo(@RequestBody User user){
        return repositoryHandler.getInfo(user.getUsername());
    }

}
