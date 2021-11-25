package com.cydeo.day6;


import com.cydeo.pojo.Driver;
import io.restassured.RestAssured;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import java.util.List;

import static io.restassured.RestAssured.*;

public class FormulaOneAPI_Test {

    /**
     * Here is the import link for whole collection
     * https://www.getpostman.com/collections/4ea3cf2262b1b19a6d29
     *
     * It's for historical formula one race information
     * In this particular api , it decided to give you ml by default for response type
     * and In this particular api , it decided to give you json if you add .json at the  end of url
     * for example
     * http://ergast.com/api/f1/drivers --->return xml
     * http://ergast.com/api/f1/drivers.json ---> return json
     *
     * Our objective is to practice json path to find the values in json result
     * also practice deserialization by converting single driver json to POJO
     * practice converting driver json array in to List<Driver>
     *
     *
     */
    // SEND GET http://ergast.com/api/f1/drivers.json
    @BeforeAll
    public static void init(){
        RestAssured.baseURI = "http://ergast.com" ;
        RestAssured.basePath = "/api/f1" ;

    }
    @AfterAll
    public static void cleanup(){
        RestAssured.reset();
    }

    @Test
    public void testDriverDeserialization(){

        JsonPath jp = get("/drivers.json")
                .prettyPeek()
                .jsonPath();

        // first driver json path  : "MRData.DriverTable.Drivers[0]
        Driver d1 = jp.getObject("MRData.DriverTable.Drivers[0]", Driver.class) ;
        System.out.println("d1 = " + d1);

        // all driver json array json path  : "MRData.DriverTable.Drivers"
        List<Driver> allDrivers = jp.getList("MRData.DriverTable.Drivers", Driver.class) ;
        System.out.println("allDrivers = " + allDrivers);

        // In class practice :
        // Find out all driver given name if their nationality is Italian from above List

        // loop through the driver list
        for (Driver eachDriver : allDrivers) {

            // check if nationality is Italian
            if(eachDriver.getNationality().equals("Italian")   ){
                System.out.println("eachDriver.getGivenName() = " + eachDriver.getGivenName());
            }

        }


    }


}