package com.cydeo.day5;

import io.restassured.http.ContentType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class HamcrestMatchersApiTest {

     /*
       given accept type is Json
       And path param id is 15
       When user sends a get request to spartans/{id}
       Then status code is 200
       And content type is Json
       And json data has following
           "id": 15,
           "name": "Meta",
           "gender": "Female",
           "phone": 1938695106
        */

    @DisplayName("OneSpartan with Hamcrest and chaining")
    @Test
    public void test1(){

        given().accept(ContentType.JSON)
                .and().pathParam("id",15)
                .when().get("http://100.25.147.218:8000/api/spartans/{id}")
                .then().statusCode(200)
                .and().assertThat()
                .contentType("application/json")
                .and()
                .body("id",is(15),
                        "name",equalTo("Meta")
                        ,"gender",is("Female"),
                        "phone",is(1938695106));

    }

}