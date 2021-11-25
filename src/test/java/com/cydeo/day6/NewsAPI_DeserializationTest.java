package com.cydeo.day6;

import com.cydeo.pojo.Article;
import io.restassured.path.json.JsonPath;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.List;
import static io.restassured.RestAssured.*;

public class NewsAPI_DeserializationTest {

    @AfterAll
    public static void teardown(){
        reset();
    }
    @BeforeAll
    public static void setUp(){
        baseURI="https://newsapi.org";
        basePath="/v2";
    }

    /**
     * GET https://newsapi.org/v2/top-headlines?country=us
     * Header :  Authorization = Bearer cbeb9b369672417ba25af35e95edbb69
     **/
    @Test
    public void testNews(){

        JsonPath jp = given()
                .log().all()
                .queryParam("country","us" )
                .header("Authorization", "Bearer cbeb9b369672417ba25af35e95edbb69").
                when()
                .get("/top-headlines").prettyPeek()
                .jsonPath() ;
        // Create a POJO class to represent Article
        // required fields : source , author , title
        // get a List<Article> from json array
        // print out the name of author and title of article if source id is null

        // try to get first Article into POJO
        Article a1 = jp.getObject("articles[0]", Article.class) ;
        System.out.println("a1 = " + a1);
        // check if the source id is null or not
        // the source id is inside the source Map field
        // we can use getter to private field to get the map
        // the using this map use map get method to get the value of the key
        System.out.println("a1.getSource().get(\"id\") = "
                + a1.getSource().get("id"));

        // get a List<Article> from json array
        List<Article> allArticles = jp.getList("articles", Article.class);

        for (Article each : allArticles) {
            // check if the source id is null
            if(each.getSource().get("id") !=null   ){
//                System.out.println("each.getSource().get(\"id\") = " + each.getSource().get("id"));
                System.out.println(  each.getAuthor()  );

            }


        }



    }




}