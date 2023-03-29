package com.cydeo.day2;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class HrGetRequests {

    @BeforeAll
    public static void init (){

        RestAssured.baseURI = "http://100.25.147.218:1000/ords/hr";
    }

    @DisplayName("GET request to / Regions")
    @Test
    public void test1(){

        Response response = get("/regions");



    }
    @DisplayName("GET request to / Regions/2")
    @Test
    public void test2(){


        Response response = given().accept(ContentType.JSON).
                when().get("/regions/2");

        assertEquals(200,response.statusCode());

        assertEquals("application/json",response.contentType());

        response.prettyPrint();

        assertEquals(response.body().asString().contains("Americas"),true);

    }

     /*
        Given accept type is application/json
        When user sends get request to /regions/2
        Then response status code must be 200
        and content type equals to application/json
        and response body contains   Americas
     */



}
