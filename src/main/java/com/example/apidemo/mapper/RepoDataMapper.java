package com.example.apidemo.mapper;

import com.example.apidemo.model.request.RepoDataRequestModel;
import com.example.apidemo.model.response.RepoDataResponseModel;

public class RepoDataMapper {
    private RepoDataMapper() {
    }

    public static RepoDataResponseModel toResponse(RepoDataRequestModel request) {
        RepoDataResponseModel responseModel = new RepoDataResponseModel();
        responseModel.setRepositoryName(request.getName());
        responseModel.setBranches(BranchMapper.toResponseList(request.getBranches()));
        responseModel.setOwnerLogin(request.getOwner().getLogin());
        return responseModel;
    }
}
