package com.cydeo.day2;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class HrGetRequests {

    @BeforeAll
    public static void init (){

        RestAssured.baseURI = "http://100.25.147.218:1000/ords/hr";
    }

    @DisplayName("GET request to / Regions")
    @Test
    public void test1(){

        Response response = RestAssured.get("/regions");



    }
    @DisplayName("GET request to / Regions/2")
    @Test
    public void test2(){

        Response response = RestAssured.get("/regions/2");

        assertEquals(200,response.statusCode());

        assertEquals("application/json",response.contentType());

        response.prettyPrint();

        assertTrue(response.body().asString().contains("Americas"));

    }

     /*
        Given accept type is application/json
        When user sends get request to /regions/2
        Then response status code must be 200
        and content type equals to application/json
        and response body contains   Americas
     */



}
