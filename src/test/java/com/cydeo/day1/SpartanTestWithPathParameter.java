package com.cydeo.day1;


import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class SpartanTestWithPathParameter {

    @BeforeEach
    public void setUpClass(){
        RestAssured.baseURI= "http://54.236.150.168:8000";

        /*
        Given accept type is Json
        And Id parameter value is 18
        When user sends GET request to api/spartans/{id}
        Then response status code should be 200
        And response content type: application/json
        And Allen should be in response payload
         */
    }

    @Test
    public void  PathTest1(){
        Response response = RestAssured.given().accept(ContentType.JSON)
                .and().pathParam("id", 18)
                .when().get("/api/spartans/{id}");


        Assertions.assertEquals(response.statusCode(), 200);

        //verify content type
        Assertions.assertEquals(response.contentType(), "application/json");

        //verify Allen exists
        Assertions.assertTrue(response.body().asString().contains("Allen"));

        response.body().prettyPrint();

    }

    /*
    Given accept type is Json
    and id parameter value id 1000
    when user sends GET request to api/spartans/{id}
    then response status code should ve 404
    and response content type application/json
    and Spartan not found message should be in response payload
     */

    @Test
    public void negativePathParamTest(){

        Response response = RestAssured.given().accept(ContentType.JSON)
                .pathParam("id", 1000)
                .when().get("api/spartans/{id}");

        //verify content type
        Assertions.assertEquals(response.statusCode(), 404);

        //verify spartan not found
        Assertions.assertTrue(response.body().asString().contains("Not Found"));

        //print body of response
        response.prettyPrint();


    }


}
