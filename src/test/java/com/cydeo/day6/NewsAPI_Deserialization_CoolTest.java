package com.cydeo.day6;
import com.cydeo.pojo.Article;
import com.cydeo.pojo.Article2;
import io.restassured.path.json.JsonPath;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.List;

import static io.restassured.RestAssured.*;

public class NewsAPI_Deserialization_CoolTest {

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
                .get("/top-headlines")//.prettyPeek()
                .jsonPath() ;
        // save first article json into Article2 Pojo
        Article2 a2 = jp.getObject("articles[0]", Article2.class);

        System.out.println("a2 = " + a2);
        // how to get source id from here
        System.out.println("a2.getSource().getId() = "
                + a2.getSource().getId() );

        // find out all article if source id is not null
        List<Article2> allArticles = jp.getList("articles", Article2.class ) ;

        for (Article2 each : allArticles) {

//            System.out.println( each.getSource().getId()==null  );
            // while jackson converting null , in this case , it's storing null as "null" from what we observed
            if(each.getSource().getId() != null  ){
                System.out.println("each.getSource().getId() = " + each.getSource().getId());
                System.out.println("each.getAuthor() = " + each.getAuthor());
            }

        }


    }




}