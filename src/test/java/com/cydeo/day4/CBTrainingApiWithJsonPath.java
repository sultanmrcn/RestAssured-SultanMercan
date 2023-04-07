package com.cydeo.day4;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;

public class CBTrainingApiWithJsonPath {
    @BeforeAll
    public static void init(){
        //save baseurl inside this variable so that we don`t need to type each http method.
        baseURI = "https://api.training.cydeo.com";

    }

    @DisplayName("GET Request to individual student")
    @Test
    public void test1() {
        //send a get request to student id 15 as a path parameter and accept header application/json
        //verify status code=200 /content type=application/json;charset=UTF-8 /Content-Encoding = gzip
        //verify Date header exists
        //assert that
            /*
                firstName Leopold
                batch 22
                section N/A
                emailAddress ocolliber1@apache.org
                companyName Jayo
                state Kentucky
                zipCode 31560
                using JsonPath
             */

        Response response =given().accept(ContentType.JSON)
                        .and().pathParam("id",15)
                .when().get(baseURI +"/student/{id}");
        assertEquals(200,response.statusCode());
        assertEquals("application/json;charset=UTF-8",response.contentType());
        assertTrue(response.headers().hasHeaderWithName("date"));

        JsonPath jsonPath = response.jsonPath();
        String name = jsonPath.getString("students[0].firstName");
        int batch = jsonPath.getInt("students[0].batch");
        String section = jsonPath.getString("students[0].section");
        String emailAdres = jsonPath.getString("students[0].contact.emailAddress");
        String companyName = jsonPath.getString("students[0].company.companyName");
        String state = jsonPath.getString("students[0].company.address.state");
        int zipCode = jsonPath.getInt("students[0].company.address.zipCode");


        System.out.println("name = " + name);
        System.out.println("batch = " + batch);
        System.out.println("section = " + section);
        System.out.println("emailAdres = " + emailAdres);
        System.out.println("companyName = " + companyName);
        System.out.println("state = " + state);
        System.out.println("zipCode = " + zipCode);


    }

}
