package com.example.apidemo.model.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BranchRequestModel {
    private String name;
    private Commit commit;

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Commit {
        private String sha;
    }
}
