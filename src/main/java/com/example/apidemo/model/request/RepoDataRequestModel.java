package com.example.apidemo.model.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RepoDataRequestModel {
    private String name;
    private Boolean fork;
    private List<BranchRequestModel> branches;
    private OwnerModel owner;

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class OwnerModel {
        private String login;
    }

}
