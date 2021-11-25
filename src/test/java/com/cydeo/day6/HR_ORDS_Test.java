package com.cydeo.day6;


import com.cydeo.pojo.Car;
import com.cydeo.pojo.Job;
import com.cydeo.pojo.Job2;
import com.utility.HrORDSTestBase;

import io.restassured.path.json.JsonPath;

import org.junit.jupiter.api.Test;

import java.util.List;

import static io.restassured.RestAssured.*;
public class HR_ORDS_Test extends HrORDSTestBase {

    // GET /jobs
    @Test
    public void testJobs(){

        JsonPath jp =  given()
                .log().uri().
                when()
                .get("/jobs")
                //.prettyPeek()
                .jsonPath()
                ;
        // we want to de-serialize first json object from json array
        // we want to be able to follow java naming convention for pojo fields
        // we want to ignore the json field we do not care about : link field

        Job j1 = jp.getObject("items[0]", Job.class) ;
        System.out.println("j1 = " + j1);

        // save all result into List<Job>
        List<Job> allJobs =  jp.getList("items", Job.class) ;
        System.out.println("allJobs = " + allJobs);

        Job2 j2 = jp.getObject("items[0]", Job2.class) ;
        System.out.println("j2 = " + j2);


        // AS A HOMEWORK :
        // find out all Jobs name with min_salary more than 5000




    }


    @Test
    public void testJobs2(){
        JsonPath js = given().log().uri().

                when().get("/jobs")
                .prettyPeek()
                .jsonPath();



        List<Job2> jobs = js.getList("items", Job2.class);

        for (Job2 job : jobs) {
            if(job.getMinSalary()>5000){
                System.out.println("job.getJobTitle() = " + job.getJobTitle());
            }
        }

    }

    @Test
    public void testCarPOJO_Class(){

        Car c1 = new Car("Model3", "Tesla",2020, true) ;
        System.out.println("c1 = " + c1);

        c1.setModel("CyberTruck");
        System.out.println("c1.getModel() = " + c1.getModel());


    }




}