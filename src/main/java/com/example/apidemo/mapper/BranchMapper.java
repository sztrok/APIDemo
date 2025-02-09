package com.example.apidemo.mapper;

import com.example.apidemo.model.request.BranchRequestModel;
import com.example.apidemo.model.response.BranchResponseModel;

import java.util.List;

public class BranchMapper {

    private BranchMapper() {
    }

    public static BranchResponseModel toResponse(BranchRequestModel request) {
        BranchResponseModel response = new BranchResponseModel();
        response.setBranchName(request.getName());
        response.setSha(request.getCommit().getSha());
        return response;
    }

    public static List<BranchResponseModel> toResponseList(List<BranchRequestModel> requests) {
        return requests.stream().map(BranchMapper::toResponse).toList();
    }
}
