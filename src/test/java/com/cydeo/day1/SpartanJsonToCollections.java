package com.cydeo.day1;

import com.utility.SpartanTestBase;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class SpartanJsonToCollections extends SpartanTestBase {

    /*
    Given accept type is json
    and path param spartan id is 11
    When user sends a get request to spartans/{id}
    Then status code is 200
    And content type is Json
    And "id" 11,
    "name": "Nona"
    "gender": "Female"
    "phone": 7959094216
     */
    @Test
    public  void test1(){

        Response response = given().accept(ContentType.JSON)
                .pathParam("id", 11)
                .and().when().get("/spartans/{id}");

        //convert Json response to Java Collection(MAp)
        Map<String, Object> spartanMap = response.body().as(Map.class);

        System.out.println("spartanMap.get(\"name\") = " + spartanMap.get("name"));
        System.out.println("spartanMap.get(\"id\") = " + spartanMap.get("id"));
        assertEquals(spartanMap.get("name"), "Nona" );

    }

    @Test
    public void test2(){
        Response response= given().accept(ContentType.JSON)
                .when().get("/spartans");

     //   response.prettyPrint();
        //convert full json body to list maps
        List<Map<String, Object>> listOfMap = response.body().as(List.class);

        //print all data of first spaartan
        System.out.println("listOfMap.get(0) = " + listOfMap.get(0));

        Map<String, Object> firstSpartan = listOfMap.get(0);
        System.out.println("firstSpartan.get(\"name\") = " + firstSpartan.get("name"));

        int count = 0;
        for (Map<String, Object> map : listOfMap) {
            System.out.println(count+" - spartan "+map);
            count++;
        }
    }
}
