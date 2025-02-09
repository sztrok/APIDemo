package com.example.apidemo.model.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RepoDataResponseModel {
    private String repositoryName;
    private List<BranchResponseModel> branches;
    private String ownerLogin;
}
