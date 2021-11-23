package com.cydeo.pojo;

public class Spartan {
    /**
     POST /spartans
     content type is json
     body is
     {
     "name":"API POST",
     "gender":"Male",
     "phone":1231231231
     }
     We learned how to represent json body using Map
     and let Jackson data-bind library to serialize it into json when sending to the server

     Another common way of representing json data is using
     special purpose Java Object created from A class
     that have fields matched to json keys
     and have getters and setters
     This type of Object , sole purpose is to represent data ,
     known as POJO , plain old java object
     The class to create POJO known as POJO class
     It's used for creating POJO , just like you create any other object
     **/

    /*    for example : in order to represent a json data with 3 keys , name, gender, phone
    we can create a java class with 3 instance fields with same name
    The only required part of POJO class is
 encapsulated fields :  private instance variables public getters and setters
    no arg constructors
 **/

    private String name;
    private String gender;
    private long phone;



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public long getPhone() {
        return phone;
    }

    public void setPhone(long phone) {
        this.phone = phone;
    }

    public Spartan(String name, String gender, long phone){
        //just have it here no body required
        this.name = name;
        this.gender = gender;
        this.phone = phone;
    }

    @Override
    public String toString() {
        return "Spartan{" +
                "name='" + name + '\'' +
                ", gender='" + gender + '\'' +
                ", phone=" + phone +
                '}';
    }
}
