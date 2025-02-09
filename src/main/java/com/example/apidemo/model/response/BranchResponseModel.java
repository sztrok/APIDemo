package com.example.apidemo.model.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Class used for presenting basic information about branch with constructor, getters and setters
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BranchResponseModel {
    private String branchName;
    private String sha;
}
