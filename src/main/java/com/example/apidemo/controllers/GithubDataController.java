package com.example.apidemo.controllers;

import com.example.apidemo.model.User;
import com.example.apidemo.model.response.RepoDataResponseModel;
import com.example.apidemo.service.RepoDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api")
public class GithubDataController {

    private final RepoDataService repoDataService;

    @Autowired
    public GithubDataController(final RepoDataService repoDataService) {
        this.repoDataService = repoDataService;
    }

    @GetMapping(value = "/repository/all", consumes = "application/json")
    public ResponseEntity<List<RepoDataResponseModel>> getRepositoriesInfo(@RequestBody User user) {
        return ResponseEntity.ok(repoDataService.getInfo(user));
    }

}
