package com.blog.user.UserService.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHeaders;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClients;

@Service
public class UserServiceInOkta {

private Logger logger= LoggerFactory.getLogger(UserServiceInOkta.class);
    private  String oktaApiUrl="https://dev-90740018.okta.com/oauth2/ausb6gvvxiOYVubbP5d7";
    private String oktaApiToken= "00T1aivjcaJGkWpn85d7";

    public void createUserInOkta(String username, String password) {
        try {
            HttpClient httpClient = HttpClients.createDefault();
            HttpPost request = new HttpPost(oktaApiUrl + "/api/v1/users?activate=true");

            // Set headers
            request.addHeader(HttpHeaders.AUTHORIZATION, "SSWS " + oktaApiToken);
            request.addHeader(HttpHeaders.CONTENT_TYPE, "application/json");

            // Create JSON request body
            String jsonBody = "{\"profile\":{\"login\":\"" + username + "\",\"firstName\":\"John\",\"lastName\":\"Doe\"},\"credentials\":{\"password\":{\"value\":\"" + password + "\"}}}";
            StringEntity entity = new StringEntity(jsonBody);
            request.setEntity(entity);

            // Execute the request
            HttpResponse response = httpClient.execute(request);
            int statusCode = response.getStatusLine().getStatusCode();

            if (statusCode == HttpStatus.SC_CREATED) {
               logger.info("user Succesfully add on okta");
            } else {

                throw new RuntimeException("Failed to create user in Okta. Status code: " + statusCode);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Error while creating user in Okta: " + e.getMessage());
        }
    }
}
