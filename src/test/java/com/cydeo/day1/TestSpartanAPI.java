package com.cydeo.day1;


import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;

public class TestSpartanAPI {

    String spartanBaseUrl = "http://54.236.150.168:8000";

    @Test
    public void testHello(){


        System.out.println("Hello world");

        //send the request to below request url and save the response
        //  GET http://54.236.150.168:8000/api/hello
        // RestAssured.get("http://54.236.150.168:8000/api/hello");
       //get method is coming from RestAssured class to send get request to the url defined
        //the result of sending request can be stored in Response object coming from RestAssured
        //get("http://54.236.150.168:8000/api/hello");

       Response response = get("http://54.236.150.168:8000/api/hello");
        System.out.println("response.statusCode() = " + response.statusCode());

        //there are many ways to print the response, easies one will be prettyPrint
        response.prettyPrint();
        Assertions.assertEquals(200, response.statusCode());


    }

    //when user send GET request to api/spartans end point
    // then status code must be 200
    // and body should contains Allen
    @Test
    public void viewSpartanTest2(){
        Response response = RestAssured.get(spartanBaseUrl+"/api/spartans");
        //verify status code 200
        Assertions.assertEquals(response.statusCode(), 200);

        //verify body contains Allen
        Assertions.assertTrue(response.body().asString().contains("Allen"));

    }

    //Given accept type is Json
    // When user sends a get request to spartanAllUrl
    // Then response status code is 200
    // And response body should be json format
    @Test
    public void viewSpartanTest3(){
        Response response = RestAssured.given().accept(ContentType.JSON)
                .when().get(spartanBaseUrl+"/api/spartans");

        Assertions.assertEquals(response.statusCode(), 200);

        //verify response body json
        Assertions.assertEquals(response.contentType(), "application/json");

    }




    @Test
    public void viewLibraryJson(){
        Response response = RestAssured.given().accept(ContentType.JSON)
                .when().get("https://library2.cybertekschool.com/rest/v1/dashboard_stats");
        Assertions.assertEquals(response.statusCode(), 200);

        Assertions.assertEquals(response.contentType(), "application/json; charset=utf-8");
    }

}
