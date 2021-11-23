package com.cydeo.day5;

import com.cydeo.pojo.Spartan;
import com.utility.SpartanTestBase;
import org.junit.jupiter.api.Test;
import com.cydeo.pojo.Spartan;

import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;

import java.util.LinkedHashMap;
import java.util.Map;

import static io.restassured.RestAssured.*;

public class PostPutRequestWithPOJO extends SpartanTestBase {

/**
 POST /spartans
 content type is json
 body is
 {
 "name":"API POST",
 "gender":"Male",
 "phone":1231231231
 }
 We learned how to represent json body using Map
 and let Jackson data-bind library to serialize it into json when sending to the server

 Another common way of representing json data is using
 special purpose Java Object created from A class
 that have fields matched to json keys
 and have getters and setters
 This type of Object , sole purpose is to represent data ,
 known as POJO , plain old java object
 The class to create POJO known as POJO class
 It's used for creating POJO , just like you create any other object
 **/

@Test
    public void testPostWithPOJOBody(){
//preparing the post body asPOJO
    Spartan spartan = new Spartan("Akbar", "Male", 1236547899l);
    System.out.println("spartan = " + spartan);

    // now we are going to use this body in post request
    // and expect jackson-databind to convert that to json sting when sending as body
    // so it can be added to the server as new data
    // only thing different in here is using PJO as buddy

    given()
            .log().all()
            .contentType(ContentType.JSON)
            .body( spartan ).
            when()
            .post("/spartans").
            then()
            .log().all()
            .statusCode(201) ;

}

    /**
     given()
     .log().all()
     .contentType(ContentType.JSON)
     .body( sp1 ).
     when()
     .post("/spartans").
     then()
     .log().all()
     .statusCode(201) ;
     **/


    // HOMEWORK : Create a method under SpartanUtil class
    // called getRandomSpartanPOJO()
    // return Spartan object with randomized field values
    // HOMEWORK : Use POJO for Update 1 Data PUT Request
    // use your getRandomSpartanPOJO utility method for body




}
