package com.example.apidemo.service;

import com.example.apidemo.model.User;
import com.example.apidemo.model.response.RepoDataResponseModel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.List;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

class RepoDataServiceTest {

    @InjectMocks
    private RepoDataService repoDataService;

    @Mock
    private RestTemplate restTemplate;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetInfo_UserNotFound() {
        String userName = "nonexistentuser";
        when(restTemplate.getForObject(anyString(), eq(String.class)))
                .thenThrow(new HttpClientErrorException(HttpStatus.NOT_FOUND));

        List<RepoDataResponseModel> response = repoDataService.getInfo(new User(userName));

        assert(response.isEmpty());
    }

}