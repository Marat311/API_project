package com.cydeo.day3;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.List;

import static io.restassured.RestAssured.*;

public class HR_ORDS_API_Test {

    @BeforeAll
    public static void setUp(){

        RestAssured.baseURI = "http://54.236.150.168:1000";
        RestAssured.basePath = "/ords/hr/" ;

    }

    @AfterAll
    public static void teardown(){
        reset();
    }

    @Test
    public void testGetAllJobs(){
        // GET /jobs

        Response response =  given()
                .log().all().  // this will print everything about request
                        when()
                .get("/jobs");
        response.prettyPrint() ;

        Assertions.assertEquals(200,  response.statusCode() );
        Assertions.assertEquals(ContentType.JSON.toString() , response.contentType() );
        // json filed count  value
        int countValue =  response.path("count");
        Assertions.assertEquals( 19 , countValue  );

        /*
           6. save second `job_id` into String
        7   . print 4th `min_salary` and
            8. save all the job_title into `List<String>`
         */
        String secondJobId = response.path("items[1].job_id") ;
        System.out.println("secondJobId = " + secondJobId);

        int fourthMinSalary =  response.path("items[3].min_salary") ;
        System.out.println("fourthMinSalary = " + fourthMinSalary);

        List<String> allJobTitles = response.path("items.job_title") ;
        System.out.println("allJobTitles = " + allJobTitles);

    }

    /**
     *      * 4. Create a test called `testJobsWithQueryParam`
     *      *    1. Send request to `GET /jobs?limit=5`
     *      *    2. log everything about the request
     *      *    3. verify `count` value is `5`
     *      *    4. verify the value of last `job_id` is `AD_VP`
     */
    @Test
    public void testJobsWithQueryParam(){
        Response response = given().log().all()
                .queryParam("limit", 5)
                .when().get("/jobs");
        response.prettyPrint();
        int actualCount = response.path("count");
        Assertions.assertEquals(5, actualCount);

        // String lastJobId = response.path("items[4].job_id");
        // RestAssured use a language called groovy when working with jsonpath
        // so any valid groovy syntax will work
        // in groovy , collection index can use minus to start from the back
        // so -1 will last item , -2 will be second from the last item
        String lastJobId = response.path("items[-1].job_id");
        System.out.println("lastJobId = " + lastJobId);

        Assertions.assertEquals("AD_VP" ,lastJobId );

    }

    /**
     * 5. create a test called testSingleJobWithPathParam
     *    1. Send request to `GET /jobs/AD_VP`
     *    2. log everything about the request
     *    3. verify response is json and `job_title` is `Administration Vice President`
     */

    @Test
    public void testSingleJobWithPathParam(){
        Response response = given().log().all()
                .pathParam("id", "AD_VP")
                .when().get("/jobs/{id}")
                .prettyPeek() //it will print entire response and return same Response object
                ;
       // response.prettyPrint();
      //  response.prettyPeek();
        String actualTitle = response.path("job_title");
        Assertions.assertEquals("Administration Vice President", actualTitle);
    }


}