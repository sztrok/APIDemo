package com.example.apidemo.components;

import com.example.apidemo.models.BranchModel;
import com.example.apidemo.models.ResponseModel;
import com.example.apidemo.utils.JSONHandler;
import org.json.simple.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

/**
 * Class responsible for retrieving and processing data from GitHub API
 */
@Component
public class RepositoryHandler {

    private final List<ResponseModel> responseData;
    private final RestTemplate restTemplate;

    public RepositoryHandler() {
        this.responseData = new ArrayList<>();
        this.restTemplate = new RestTemplate();
    }

    /**
     * Main method responsible for handling data
     * @param userName username for which we try to get repository info
     * @return response with data and appropriate status
     */
    public ResponseEntity<String> getInfo(final String userName){
        try{
            List<JSONObject> validRepositories = getValidRepositories(userName);
            for(JSONObject repository : validRepositories) {
                responseData.add(createNewResponse(userName, repository));
            }
        }
        catch (HttpClientErrorException e){
            JSONObject response = new JSONObject();
            response.put("status", 404);
            response.put("message", String.format("User %s not found", userName));
            return new ResponseEntity<>(response.toString(), HttpStatus.NOT_FOUND);
        }
        if(responseData.isEmpty()){
            JSONObject response = new JSONObject();
            response.put("status", 204);
            response.put("message", String.format("No repositories found for user %s", userName));
            return new ResponseEntity<>(response.toString(), HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(createResponse().toString(), HttpStatus.OK);
    }

    /**
     * Creates response body for responseData
     * @return List of JSONObjects
     */
    private List<JSONObject> createResponse(){
        List<JSONObject> responseAsJsonList = new ArrayList<>();
        for(ResponseModel model : responseData){
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("repositoryName", model.getRepositoryName());
            jsonObject.put("ownerLogin", model.getOwnerLogin());
            jsonObject.put("branches", model.getBranches());
            responseAsJsonList.add(jsonObject);
        }
        return responseAsJsonList;
    }

    /**
     * Connects to GitHub Api and processes retreived data
     * @param userName username for which we try to get repository info
     * @return filtered repositories as JSONObject list
     * @throws HttpClientErrorException exception is thrown when no such user is found
     */
    private List<JSONObject> getValidRepositories(final String userName) throws HttpClientErrorException {
        String repoUrl = String.format("https://api.github.com/users/%s/repos", userName);

        String userData = restTemplate.getForObject(repoUrl, String.class);
        return JSONHandler.getJsonData(userData).stream()
                .filter(repo -> !((boolean) repo.get("fork")))
                .toList();
    }

    /**
     * Creates new ResponseModel entry to list
     * @param userName
     * @param repository valid repository for given user
     * @return new ResponseModel
     */
    private ResponseModel createNewResponse(String userName, JSONObject repository){
        String repositoryName = repository.get("name").toString();
        ResponseModel response = new ResponseModel(repositoryName, userName);
        addBranchesToResponseData(userName, repositoryName, response);
        return response;
    }

    /**
     * Adds branch info to ResponseModel entry
     * @param userName
     * @param repositoryName
     * @param response
     */
    private void addBranchesToResponseData(String userName, String repositoryName, ResponseModel response) {
        List<JSONObject> branches = getBranches(userName, repositoryName);
        for(JSONObject branch : branches){
            response.addBranchToList(createNewBranch(branch));
        }
    }

    /**
     * Connects to GitHub Api to retrieve branch info
     * @param userName user for which we get branch info
     * @param repositoryName name of repository for which we get branch info
     * @return List of branch data as JSONObjects
     */
    private List<JSONObject> getBranches(String userName, String repositoryName) {
        String branchUrl = String.format("https://api.github.com/repos/%s/%s/branches", userName, repositoryName);
        String branchData = restTemplate.getForObject(branchUrl, String.class);
        return JSONHandler.getJsonData(branchData);
    }

    /**
     * Creates new BranchModel containing information about given branch
     * @param branch
     * @return new BranchModel
     */
    private BranchModel createNewBranch(JSONObject branch){
        String branchName = branch.get("name").toString();
        JSONObject commitObj = (JSONObject) branch.get("commit");
        String latestCommitSha = commitObj.get("sha").toString();
        return new BranchModel(branchName, latestCommitSha);
    }


}
