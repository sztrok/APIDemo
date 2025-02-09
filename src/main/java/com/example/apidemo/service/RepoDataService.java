package com.example.apidemo.service;

import com.example.apidemo.mapper.RepoDataMapper;
import com.example.apidemo.model.User;
import com.example.apidemo.model.request.BranchRequestModel;
import com.example.apidemo.model.request.RepoDataRequestModel;
import com.example.apidemo.model.response.RepoDataResponseModel;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

/**
 * Class responsible for retrieving and processing data from GitHub API
 */
@Service
public class RepoDataService {

    private final RestTemplate restTemplate;

    public RepoDataService() {
        this.restTemplate = new RestTemplate();
    }

    /**
     * Main method responsible for handling data
     *
     * @param user user for which we try to get repository info
     * @return response with data and appropriate status
     */
    public List<RepoDataResponseModel> getInfo(final User user) {
        final String username = user.getUsername();
        final List<RepoDataResponseModel> result = new ArrayList<>();
        try {
            List<RepoDataRequestModel> validRepositories = getValidRepositories(username);
            for (RepoDataRequestModel repo : validRepositories) {
                repo.setBranches(getBranches(username, repo.getName()));
                result.add(RepoDataMapper.toResponse(repo));
            }
        } catch (HttpClientErrorException e) {
            return List.of();
        }

        return result;
    }

    /**
     * Connects to GitHub Api and processes retreived data
     *
     * @param userName username for which we try to get repository info
     * @return filtered repositories as JSONObject list
     * @throws HttpClientErrorException exception is thrown when no such user is found
     */
    private List<RepoDataRequestModel> getValidRepositories(final String userName) throws HttpClientErrorException {
        String repoUrl = String.format("https://api.github.com/users/%s/repos", userName);
        ResponseEntity<List<RepoDataRequestModel>> response = restTemplate.exchange(
                repoUrl,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<>() {
                }
        );
        List<RepoDataRequestModel> userData = response.getBody();
        if (userData == null || userData.isEmpty()) {
            return List.of();
        }
        return userData.stream()
                .filter(repo -> !repo.getFork())
                .toList();
    }

    /**
     * Connects to GitHub Api to retrieve branch info
     *
     * @param userName       user for which we get branch info
     * @param repositoryName name of repository for which we get branch info
     * @return List of branch data as JSONObjects
     */
    private List<BranchRequestModel> getBranches(String userName, String repositoryName) {
        String branchUrl = String.format("https://api.github.com/repos/%s/%s/branches", userName, repositoryName);
        ResponseEntity<List<BranchRequestModel>> response = restTemplate.exchange(
                branchUrl,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<>() {
                }
        );
        List<BranchRequestModel> branches = response.getBody();
        if (branches == null || branches.isEmpty()) {
            return List.of();
        }
        return branches;
    }

}
