package com.cydeo.day6;


import com.cydeo.pojo.*;
import com.cydeo.utilities.*;
import io.restassured.path.json.*;
import org.junit.jupiter.api.*;

import java.util.*;

import static io.restassured.RestAssured.*;
import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;
public class ORDSPojoGetRequestTest extends HrTestBase {


    /*
    create pojo classes for this response
    then test it with sending get request to regions endpoint
    and only pointing first region and converting your pojos
    Start from the inner part (inner array)
    "items": [
        {
            "region_id": 1,
            "region_name": "Europe",
            "links": [
                {
                    "rel": "self",
                    "href": "http://100.24.240.163:1000/ords/hr/regions/1"
                }
            ]
        }
    ]
     */
    @Test
    public void regionTest(){
        JsonPath jsonPath = get("/regions").then().statusCode(200)
                .log().body().extract().jsonPath();
        Region region1 = jsonPath.getObject("items[0]", Region.class);
        System.out.println("region1 = " + region1);

        System.out.println("region1.getRegion_id() = " + region1.getRId());
        System.out.println("region1.getRegion_name() = " + region1.getRegion_name());
        System.out.println("region1.getLinks().get(0).getHref() = " + region1.getLinks().get(0).getHref());

        /* also you can do like this;
        Link link1 =region1.getLinks().get(0);
        link1.getHref();
         */


    }
}