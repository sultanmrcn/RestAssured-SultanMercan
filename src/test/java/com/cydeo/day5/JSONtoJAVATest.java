package com.cydeo.day5;

import com.cydeo.utilities.SpartanTestBase;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;


import java.util.*;


import static io.restassured.RestAssured.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import com.cydeo.utilities.SpartanTestBase;

public class JSONtoJAVATest extends SpartanTestBase {

      /*
  Converting JSON Response to Java Collection/Data structure
we convert (with as method)json response body to java collection (map),
because json body is key and value format with matches with map
In map key always String, but value part should be Object, because value can be number, string or array of json object...
We can say Deserializaiton when we convert from json to java.
Map<String,Object> jsonDataMap = response.body().as(Map.class);
Jackson and Gson : these are two libraries that allows you to do this convertion.
                 : without adding these libraries this as() method will not work. Do it in pom.xml
                 : also known as objectMapper,jsonparser, databinding libraries
     */


    @DisplayName("GET one Spartan and deserialize to Map")
    @Test
    public void oneSpartanToMap(){

        Response response = given().pathParam("id",15)
                .when().get("/api/spartans/{id}")
                .then().statusCode(200).extract().response();
        //get the json and convert it to the Map

        Map<String, Object> jsonMap = response.as(Map.class);

        //System.out.println("jsonMap = " + jsonMap);
        System.out.println("jsonMap.toString() = " + jsonMap.toString());

        //after we got the map, we can use hamcrest or junit assertions to do assertion
        String actualName = (String) jsonMap.get("name");
        assertThat(actualName,is("Meta"));

    }
    @DisplayName("GET all spartans to JAVA data structure")
    @Test
    public void getAllSpartan(){
        Response response = get("/api/spartans").then().statusCode(200)
                .extract().response();

        //we need to convert json to java  which is deserialize

        List<Map<String,Object>> jsonList = response.as(List.class);

        System.out.println("jsonList.get(1).get(\"name\") = " + jsonList.get(1).get("name"));
        //list      map

        //get 3. index from List<Map<String,Object>> jsonList = response.as(List.class);
        Map<String,Object> spartan3 = jsonList.get(2);
        System.out.println(spartan3);



    }
}
