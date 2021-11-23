package com.cydeo.day1;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.get;

public class SpartanTestWithPathParameters {


    @BeforeEach
    public void setUpClass(){
        baseURI= "http://54.236.150.168:8000";
    }

    /*
    given accept type id json
    and path param id is 10
    when user sends a get request to spartans/{id}
    then status code is 200
    and content type is application/json
    and response payload values match the following
       id is 17
       name is Wash
       gender is Male
       phone is 4978976378
     */

    @Test
    public void test1(){

        Response response  = RestAssured.given().accept(ContentType.JSON)
                .pathParam("id", 17)
                .when().get("api/spartans/{id}");

        //verify status code
        Assertions.assertEquals(response.statusCode(), 200);

        //verify content type
        Assertions.assertEquals(response.contentType(), "application/json");

        //printing values of json keys
        System.out.println("id:"+response.body().path("id").toString());
        System.out.println("name:"+response.body().path("name").toString());
        System.out.println("gender:"+response.body().path("gender").toString());
        System.out.println("phone:"+response.body().path("phone").toString());


      int id = response.body().path("id");
        String name = response.body().path("name");
        String gender = response.body().path("gender");
        long phone = response.body().path("phone");

        Assertions.assertEquals(id, 17);
        Assertions.assertEquals(name, "Washible");
        Assertions.assertEquals(gender, "Male");
        Assertions.assertEquals(phone, 4978976378l );

    }


    @Test
    public void test2(){
        Response  response = get("api/spartans");

        int firstId = response.path("id[0]");
        System.out.println("firstId = " + firstId);

        String firstName = response.path("name[0]");
        System.out.println("firstName = " + firstName);

        int lasttId = response.path("id[-1]");
        System.out.println("firstId = " + lasttId);

        String lasttName = response.path("name[-1]");
        System.out.println("firstName = " + lasttName);

        List<String> names = response.path("name");
        for (String name : names) {
            System.out.println(name);
        }
    }

}
