package com.example.apidemo.models;

/**
 * Class used for presenting basic information about branch with constructor, getters and setters
 */
public class BranchModel {
    private String branchName;
    private String sha;

    public BranchModel(String branchName, String sha) {
        this.branchName = branchName;
        this.sha = sha;
    }

    public String getBranchName() {
        return branchName;
    }

    public void setBranchName(String branchName) {
        this.branchName = branchName;
    }

    public String getSha() {
        return sha;
    }

    public void setSha(String sha) {
        this.sha = sha;
    }

    @Override
    public String toString() {
        return "{" +
                "\"branchName\":\"" + branchName + '\"' +
                ", \"sha\"=\"" + sha + '\"' +
                '}';
    }
}
