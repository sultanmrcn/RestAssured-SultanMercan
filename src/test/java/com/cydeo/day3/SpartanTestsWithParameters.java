package com.cydeo.day3;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;

public class SpartanTestsWithParameters {

    @BeforeAll
    public static void init(){
        //save baseurl inside this variable so that we don`t need to type each http method.
        baseURI = "http://100.25.147.218:8000";
    }
    /*
             Given accept type is Json
             And Id parameter value is 5
             When user sends GET request to /api/spartans/{id}
             Then response status code should be sta200
             And response content-type: application/json
             And "Blythe" should be in response payload
          */
    @DisplayName("GET request to/api/spartans/{id}with ID 5")
    @Test
    public void test1() {

        Response response = given().accept(ContentType.JSON).
                and().pathParam("id", 5)
                .when().get(" /api/spartans/{id}");

        assertEquals(200,response.statusCode());

        assertEquals("application/json",response.contentType());

        assertTrue(response.body().asString().contains("Blythe"));

    }
    /*
        TASK
        Given accept type is Json
        And Id parameter value is 500
        When user sends GET request to /api/spartans/{id}
        Then response status code should be 404
        And response content-type: application/json
        And "Not Found" message should be in response payload
     */
    @DisplayName("GET request to /api/spartans/{id} with ID 500")
    @Test
    public void test2(){
        Response response = given().accept(ContentType.JSON).and().pathParam("id", 500)
                .get("/api/spartans/{id}");

        assertEquals(404,response.statusCode());

        assertEquals("application/json",response.contentType());

        assertTrue(response.body().asString().contains("Not Found"));

    }

     /*
        Given accept type is Json
        And query parameter values are:
        gender|Female
        nameContains|e
        When user sends GET request to /api/spartans/search
        Then response status code should be 200
        And response content-type: application/json
        And "Female" should be in response payload
        And "Janette" should be in response payload
     */
    @DisplayName("GET request to /api/spartans/search with Query Params")
    @Test
    public void test3() {

        Response response = given().accept(ContentType.JSON).and()
                .queryParam("gender", "Female")
                .queryParam("nameContains", "e")
                .get("/api/spartans/search");

        assertEquals(200,response.statusCode());
        assertEquals("application/json",response.contentType());
        assertTrue(response.body().asString().contains("Female"));
        assertTrue(response.body().asString().contains("Janette"));







    }

}
