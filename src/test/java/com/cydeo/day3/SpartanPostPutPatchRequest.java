package com.cydeo.day3;

import com.utility.SpartanTestBase;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.*;

import java.util.List;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;


@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class SpartanPostPutPatchRequest extends SpartanTestBase {

       List<String>size;
    Object id;

    @Test
    @Order(1)
    public void testAddDataStringBody(){
        /**
         POST /spartans
         content type is json
         body is
         {
         "name":"API POST",
         "gender":"Male",
         "phone":1231231231
         }
         *
         * expect status 201
         * body json format
         * response body
         * {
         *     "success": "A Spartan is Born!",
         *     "data": {
         *         "id": 544,
         *         "name": "API POST",
         *         "gender": "Male",
         *         "phone": 1231231231
         *     }
         * }
         * verify the success value is A Spartan is Born!
         * "name": "API POST",
         * "gender": "Male",
         * "phone": 1231231231
         */

        String strBody = "{\n" +
                "                    \"name\":\"Akbar\",\n" +
                "                    \"gender\":\"Male\",\n" +
                "                    \"phone\":1231231231\n" +
                "          }" ;

                 given().log().all()
                .contentType(ContentType.JSON)
                .body(strBody)
                .when()
                .post("/spartans")
                .then()
                .log().all().statusCode(201)
                .contentType(ContentType.JSON)
                .body("success", is("A Spartan is Born!"))
                .body("data.name", is("Akbar"))
                .body("data.gender", is("Male"))
                .body("data.phone", is(1231231231));




    }

    @Test
    @Order(2)
    public void testUpdateSpartan(){

        String newBody = "{\n" +
                "                    \"name\":\"Akbarushka\",\n" +
                "                    \"gender\":\"Male\",\n" +
                "                    \"phone\":1002003040\n" +
                "          }" ;

        Response response = given().log().all().when().get("/spartans");
        size = response.path("id");
     //   System.out.println("size = " + size);

       id = size.get(size.size()-1);



        given().log().all()
                .contentType(ContentType.JSON)
                .pathParam("id",id)
                .body(newBody)
                .when()
                .put("spartans/{id}")
                .then()
                .log().all().statusCode(204)
        ;


     given().log().all()
                .contentType(ContentType.JSON)
                .pathParam("id", id)
                .when().get("spartans/{id}")
                .then()
                .log().all().statusCode(200)
                .body("name", is("Akbarushka"))
                .body("gender", is("Male"))
                .body("phone", is(1002003040))
                ;


    }

    @Test
    @Order(3)
    public void testPartiallyUpdateSpartan(){
        Response response = given().log().all().when().get("/spartans");
        size = response.path("id");
        id = size.get(size.size()-1);

        System.out.println("id "+id);
        String phone = "{\n" +
                "    \"phone\" :9998887766\n" +
                "}";

        given().log().all()
                .contentType(ContentType.JSON)
            //    .pathParam("id", size.size())
                .body(phone)
        .when()
                .patch("spartans/"+id+"")
        .then()
                .log().all().statusCode(204)


                ;






    }


    @Test
    @Order(4)
    public void testDeleteSpartan(){
        Response response = given().log().all().when().get("/spartans");
        size = response.path("id");
        id = size.get(size.size()-1);

        given().log().all()
                .contentType(ContentType.JSON)
                .pathParam("id", id)
        .when().delete("/spartans/{id}")
                .then().log().all().statusCode(204)
        ;

        given().log().all()
                .contentType(ContentType.JSON)
                .pathParam("id", id)
        .when().get("spartans/{id}")
        .then()
                .log().all().statusCode(404)
                .body("error", is("Not Found"))
        ;




    }


}
