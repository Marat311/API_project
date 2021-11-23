package com.cydeo.day3;

import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class HamcrestMatcher {


    /**
     * Hamcrest is a framework for writing matcher objects allowing ‘match’ rules
     * to be defined declaratively.
     * It is an assertion library that can be used additionally to make assertion
     * readable and it comes with a lot of pre-written matchers to make it easier to write and read
     *
     * The method chain of RestAssured then section use the hamcrest matcher library
     * extensively to assert the response components in one chain
     * RestAssured dependency already contains Hamcrest Assertion library
     * so no separate dependency needed
     * All we need is to add static imports and start using it's matchers
     * to make the assertions great again
     */

    @Test
    public void testNumbers(){

        // in junit 5 assertions  3 + 6 = 9
        // import static org.junit.jupiter.api.Assertions.assertEquals;
        assertEquals( 9 , 3+6) ;

        // Hamcrest , it can be written in this way
        // import static org.hamcrest.MatcherAssert.assertThat;
        // import static org.hamcrest.Matchers.*;

        assertThat( 3 + 6 ,  equalTo(9 )  ) ;
        // Hamcrest use matchers to express the intention clearly,
        // it's coming from  org.hamcrest.Matchers.*
        // for equality , there are options like
        /**
         * equalTo(some value)
         * is(some value)
         * is( equalTo(somevalue)  )   // just for readability
         */
        assertThat(4+6 , is(10) );
        /**
         * number comparision :
         * greaterThan()  lessThan()
         */
        assertThat(  5+6 , greaterThan(10)  );

        assertThat(10+10 , lessThanOrEqualTo(20) ) ;

    }

    @Test
    public void testString(){

        String msg = "B23 is Excellent!";
        assertThat(msg,  is("B23 is Excellent!")    ) ;

        assertThat(msg,  equalTo("B23 is Excellent!")    ) ;
        assertThat(msg,  equalToIgnoringCase("b23 is excellent!") );

        assertThat(msg,  startsWith("B23") );
        assertThat(msg,  startsWithIgnoringCase("b23")  );

        assertThat(msg, endsWith("Excellent!") ) ;
        assertThat(msg, endsWithIgnoringCase("excellent!") ) ;

        assertThat(msg, containsString("is")  ) ;
        assertThat(msg, containsStringIgnoringCase("IS") ) ;

        // How to say not in Hamcrest Matchers
        assertThat(msg,  not("B24 is Excellent!")    ) ;
        assertThat(msg,  is(    not("B24 is Excellent!")  )  ) ;


    }




}

