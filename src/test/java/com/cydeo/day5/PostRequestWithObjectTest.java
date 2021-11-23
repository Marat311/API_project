package com.cydeo.day5;

import com.github.javafaker.Faker;
import com.utility.SpartanTestBase;
import com.utility.SpartanUtil;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;

import java.util.LinkedHashMap;
import java.util.Map;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;


public class PostRequestWithObjectTest extends SpartanTestBase {


    /**
     * Serilization : Converting from Java object to Json (or any other text)
     * Deserilization: Converting from Json (any text) to Java object
     POST /spartans
     content type is json
     body is
     {
     "name":"API POST",
     "gender":"Male",
     "phone":1231231231
     }


     instead of providing the body in String
     We want to be able to provide the body as java object (like map or POJO_)
     and let any kind of Serilialization library convert that into String for us
     while sending the request

     **/

    @Test
    public void testPostWithMap(){
        Map<String, Object> bodyMap = new LinkedHashMap<>();
        bodyMap.put("name", "Asya");
        bodyMap.put("gender", "Female");
        bodyMap.put("phone", "5004003020");

        given()
                .log().all()
                .contentType(ContentType.JSON)
                .body(bodyMap)
        .when()
                .post("/spartans").
        then()
                .log().all()
                .contentType(ContentType.JSON)

                ;


    }

    @Test
    public void testPostWithRandomData(){
     /*   Faker faker = new Faker();
        //create random data instead of always creating with body
        //optionally create a utility out of it

        Map<String, Object> bodyMap = new LinkedHashMap<>();
        bodyMap.put("name", faker.name().firstName());
        bodyMap.put("gender", faker.demographic().sex());
        bodyMap.put("phone", faker.number().numberBetween(5000000000L,9999999999L));

       System.out.println("bodyMap = " + bodyMap);
 */
        // having utility, so we can just call a method as below
        // 1. create a class under utility package with name SpartanUtil
        // 2. create a public static method with return type of Map<String, Object>
        // 3. add above code you already wrote for method body and return the bodyMap from the method
        // 4. call the method here to get the random body

        Map< String, Object> bodyMap = SpartanUtil.getRandomSpartanMapBody();

        /*
        Jackson databind is the library for serilialization and de-serilialization
        rest assered need it in dependensy so it van autamatically
        convert the java object to json without additional code
         */

        given()
                .log().all()
                .contentType(ContentType.JSON)
                .body(bodyMap)
                .when()
                .post("/spartans").
                then()
                .log().all()
                .contentType(ContentType.JSON)
                .statusCode(201)

        ;


    }
    // Send request to PUT /spartans/{id}  with Random Map Body
    @Test
    public void testUpdate1DataWithRandomBody(){

        // instead of guessing what id exists in my server
        // I will send request to get all spartans and get last json object id

        int lastId = get("/spartans").path("id[-1]") ;
        System.out.println("lastId = " + lastId);


         //prepare updater body
        Map<String, Object> updatedBodyMap = SpartanUtil.getRandomSpartanMapBody();


        given()
                .log().all()
                .pathParam("id", lastId)
                .contentType(ContentType.JSON)
                .body(updatedBodyMap)
                .put("/spartans/{id}")
                .then().log().all()
                .statusCode(204);
    }

    @Test
    public void testGetSpartanData(){

        int lastId = get("/spartans").path("id[-1]") ;
        System.out.println("lastId = " + lastId);

        given().log().all()
                .pathParam("id", lastId)
                .contentType(ContentType.JSON)
                .when(). get("/spartans/{id}")
                .then()
                .log().all().statusCode(200);


    }




}
