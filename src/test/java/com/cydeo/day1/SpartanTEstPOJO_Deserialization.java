package com.cydeo.day1;

import com.utility.SpartanTestBase;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class SpartanTEstPOJO_Deserialization extends SpartanTestBase {

    @Test
    public void test1(){

        Response response = given().accept(ContentType.JSON)
                .pathParam("id", 15)
                .when().get("/spartans/{id}");
        response.prettyPrint();

        //how to convert json response to our spartan class
        Spartan spartan = response.body().as(Spartan.class);

        assertEquals(spartan.getName(), "Meta");
        assertEquals(spartan.getId(), 15);
        assertEquals(spartan.getGender(), "Female");
        assertEquals(spartan.getPhone(), 1938695106);
    }

@Test
    public void jacksonExample(){

}


}
