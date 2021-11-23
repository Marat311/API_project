package com.cydeo.homework;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;

import java.util.List;


import static io.restassured.RestAssured.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class swapiTest {

    Response response;

    @BeforeAll
    public static void setup(){
        RestAssured.baseURI= "https://swapi.dev";
        RestAssured.basePath= "/api";
    }

    @AfterAll
    public static void teardown(){
        reset();
    }

    @Test
    public void starWars(){
        response = get("/people");
        System.out.println("response.statusCode() = " + response.statusCode());
        assertEquals(response.statusCode(), 200);

        assertEquals(ContentType.JSON.toString(), response.contentType());

        System.out.println("response.body().path(\"count\") = " + response.body().path("count"));
        System.out.println("response.body().path(\"name\") = " + response.body().path("results[0].name"));
        System.out.println("response.body().path(\"hair_color\") = " + response.body().path("results[3].hair_color"));
        System.out.println("response.body().path(\"birth_year\") = " + response.body().path("results[-1].birth_year"));
        List<String> list = response.path("results.name");
       System.out.println("list.size() = " + list.size());

       List<String> height = response.path("results.height");
       Integer maxValue = Integer.MIN_VALUE;
        for (int i = 0; i<height.size(); i++) {
            if(maxValue<Integer.valueOf(height.get(i))){
                maxValue=Integer.valueOf(height.get(i));
            }
        }
        System.out.println(maxValue);

    }

    @Test
    public void Yoda(){
        response = given().accept(ContentType.JSON)
                        .and().queryParam("page", 2)
                        .when().get("/people");


        System.out.println("response.body().path(\"results[3].name\") = " + response.body().path("results[3].name"));
        assertEquals("Yoda", response.body().path("results[8].name"));


    }

    @Test
    public void returnOfTheJedi(){
        response = given().accept(ContentType.JSON)
                .and().pathParam("id", 3)
                .when().get("/films/{id}");
        assertEquals(response.statusCode(), 200);
        assertEquals(response.contentType(), ContentType.JSON.toString());
        System.out.println("response.body().path(\"name\") = " + response.body().path("title"));

        List<String> characters = response.path("characters");
        System.out.println("characters.size() = " + characters.size());
    }

}
/*
## Homework 1 : StarWars API

Create a class called SwapiTest
- Add  @BeforeAll  @AfterAll sections to set up
- baseURI as https://swapi.dev
- basePath as /api

1. Send request to GET https://swapi.dev/api/people
    - Verify status code is 200
    - header Content-Type is application/json
    - in json body ,
      - count field value is 82
      - name of first person in the result is Luke Skywalker
      - hair_color of fourth person in the result is none
      - birth_year of last person is 57BBY
      - save the name of all characters in the result into List and verify the count is 10 (10 per page)
      - Optional Java Practice : Save the height of all people from the result and find out the max height.

2.  in swapi api , it return 10 result per page and you can use page query param to define which page you want to go for example ,
second page will be GET https://swapi.dev/api/people?page=2 .
- in separate test , use given() section to provide query param page value 2
-  print the name of 4th person.
-  verify the 9th person name is Yoda

3. Send request to GET https://swapi.dev/api/films/3 (3 in here is path parameter to get single film)
   - use pathparam in given section for film id
   - verify you get 200
   - verify response is json format
   - verify the name of film is
   - save characters field value into List<String> and verify count is 20
 */