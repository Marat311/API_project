package com.cydeo.day6;

import com.cydeo.pojo.Character;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import java.util.List;

import static io.restassured.RestAssured.*;

public class BreakingBad_DeserializationPracticeTest {

    // https://breakingbadapi.com/api/characters

    @BeforeAll
    public static void setUp(){
        baseURI="https://breakingbadapi.com";
        basePath="/api";
    }

    @AfterAll
    public static void tearDown(){
        reset();
    }

    @Test
    public void testCharacters(){

        JsonPath jp = get("/characters")
                .prettyPeek()
                .jsonPath();

        // get first character and de-ser it into Character POJO   : json path is "[0]"
        Character c1 = jp.getObject("[0]", Character.class) ;
        System.out.println("c1 = " + c1);

        List<Character> allCharacters = jp.getList("", Character.class) ;
    //    System.out.println("allCharacters = " + allCharacters);

        // Java Practice HOMEWORK :
        // find out the character names appearance count is exactly 1
        // find out the name of DEA Agent.

        for (Character character : allCharacters) {
            if(character.getAppearance().contains(1)){
                for (Integer num : character.getAppearance()) {
                    if(num==1 && character.getAppearance().size()==1){
                        System.out.println("character.getName() = " + character.getName());
                    }
                }
            }


        }

        System.out.println("---------------------------------");
        for (Character character : allCharacters) {
            for (String s : character.getOccupation()) {
                if (s.equals("DEA Agent")){
                    System.out.println("character.getName() = " + character.getName());
                }
            }

       }


        }



    }




