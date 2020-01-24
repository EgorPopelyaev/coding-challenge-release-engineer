package com.ebayk.e2e;

import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.when;

public class E2EServiceTest {

    @Before
    public void setup() {
        RestAssured.baseURI = "http://localhost";
        RestAssured.port = 8081;
    }

    @Test
    public void getRatedUsers_ShouldReturnOK() {
        when().get("/users/3/rated-users").then().statusCode(200);
    }

    @Test
    public void getRatedUsers_shouldReturn404IfUserNotFound() {
        when().get("/users/999/rated-users").then().statusCode(404);
    }

    @Test
    public void getRatedUsers_shouldReturn400IfUserIdIsNotInSuitableFormat() {
        when().get("/users/999/rated-users").then().statusCode(400);
    }

    @Test
    public void getRatedUsers_shouldReturn500IfThereIsAnSystemError() {
        when().get("/users/userIdForSystemError/rated-users").then().statusCode(500);
    }

    /*
    Todo: here is a basic set of the E2E cases. we can add also more positive cases, which will check response body.
        For example when the returned user has more than one rated user and so on.
     */

}
