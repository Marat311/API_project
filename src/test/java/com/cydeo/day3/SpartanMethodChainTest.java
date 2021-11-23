package com.cydeo.day3;


import com.utility.SpartanTestBase;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.List;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class SpartanMethodChainTest extends SpartanTestBase {

    @Test
    public void getOneSpartanTest(){

        // in one shot ,
        // send request to GET /spartans/{id}  with id of 1
        // log everything
        // verify statusCode is 200
        // contentType is json
        // json body  : id is 1 , name OliverTheKing,
        given()
                .log().all()
                .pathParam("id",1)
                .accept( ContentType.JSON ).
          when()
                .get("/spartans/{id}").
          then()  // this is where assertions start
                .statusCode(is(200))//asserting status code 200
               // .header("Content-Type", "application/json") //asserting header is application json
                //.header("Content-Type", ContentType.JSON.toString())
                .contentType(ContentType.JSON)//this does same thing as above lines
                .body("id", is(1))
                .body("name", is("OliverTheKing"))

        ;


    }

    @Test
    public void testSearch(){
        // http://54.236.150.168:8000/api/spartans/search?nameContains=Ea&gender=Male
        // verify status code is 200
        // content type is json
        //  "totalElement": 3
        //  jsonArray hasSize of 3
        //  all names  has item Sean
        //  every gender is Male
        //  every name should contain ignoring case ea

        given().log().all()
                .queryParam("nameContains", "Ea")
                .queryParam("gender", "Male")
        .when()
                .get("/spartans/search")
        .then()
                .log().all() //this is ligging everything about response
                .statusCode(200)
                .contentType(ContentType.JSON)
                .body("totalElement", is(3))
                .body("content", hasSize(3))
                .body("content.name", hasItem("Sean"))
                .body("content.gender", hasItem("Male"))
                .body("content.name", everyItem(containsStringIgnoringCase("ea")))
        ;


    }


}
