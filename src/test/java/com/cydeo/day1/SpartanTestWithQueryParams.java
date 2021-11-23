package com.cydeo.day1;


import static  io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.*;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

public class SpartanTestWithQueryParams {

    @BeforeEach
    public void setUpClass(){
        baseURI= "http://54.236.150.168:8000";
    }

    /*
    given accept type is json
    and query parameter value are
    gender/female
    name contains j
    when user sends GET request to api/spartans/search
    then response status code should be 200
    and response content type application/json
    and female should be in response payload
    and janette should be in response payload
     */

    @Test
    public void queryParam(){

        Response response = given().accept(ContentType.JSON)
                .and().queryParam("gender", "Female")  //here we using queryParam for one pair parameter
                .and().queryParam("nameContains", "J")
                .when().get("api/spartans/search");

        //verify the status code
        assertEquals(response.statusCode(), 200);

        //verify content type
        assertEquals(response.contentType(), "application/json");

        //verify female
        assertTrue(response.body().asString().contains("Female"));

        //verify male not exist
        assertFalse(response.body().asString().contains("Male"));

        //verify Janette
        assertTrue(response.body().asString().contains("Janette"));
        response.prettyPrint();


    }


    @Test
    public void queryParams2(){
        Map<String, Object> paramsMap = new HashMap<>();
        paramsMap.put("gender", "Female");
        paramsMap.put("nameContains", "J");

        //send request
        Response response = given().accept(ContentType.JSON)
                .and().queryParams(paramsMap)   //here we using queryParams for putting map parameters
                .when().get("api/spartans/search");

        //verify the status code
        assertEquals(response.statusCode(), 200);

        //verify content type
        assertEquals(response.contentType(), "application/json");

        //verify female
        assertTrue(response.body().asString().contains("Female"));

        //verify male not exist
        assertFalse(response.body().asString().contains("Male"));

        //verify Janette
        assertTrue(response.body().asString().contains("Janette"));
        response.prettyPrint();


    }

}
