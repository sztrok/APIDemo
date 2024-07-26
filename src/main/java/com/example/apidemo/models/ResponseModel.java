package com.example.apidemo.models;


import java.util.ArrayList;
import java.util.List;

/**
 * Class used for gathering and presenting information wanted as a response when consuming Api
 */
public class ResponseModel {

    private String repositoryName;
    private String ownerLogin;
    private List<BranchModel> branches;

    public ResponseModel(String repositoryName, String ownerLogin) {
        this.repositoryName = repositoryName;
        this.ownerLogin = ownerLogin;
        this.branches = new ArrayList<>();
    }

    public void addBranchToList(BranchModel branch){
        branches.add(branch);
    }

    public String getRepositoryName() {
        return repositoryName;
    }

    public void setRepositoryName(String repositoryName) {
        this.repositoryName = repositoryName;
    }

    public String getOwnerLogin() {
        return ownerLogin;
    }

    public void setOwnerLogin(String ownerLogin) {
        this.ownerLogin = ownerLogin;
    }

    public List<BranchModel> getBranches() {
        return branches;
    }

    public void setBranches(List<BranchModel> branches) {
        this.branches = branches;
    }

    @Override
    public String toString() {
        return "{" +
                "\"repositoryName\":\"" + repositoryName + '\"' +
                ", \"ownerLogin\":\"" + ownerLogin + '\"' +
                ", \"branches\":" + branches +
                '}';
    }
}
