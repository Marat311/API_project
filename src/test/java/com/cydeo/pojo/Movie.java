package com.cydeo.pojo;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Movie {

    //obsorve thid id not a common java naming convention
    //bc in jave we start variable with lowercase
    //however we still want to instruct Jackson data-bind to match the fields we want
    //in order to instruct jackson library what json field to match what java field
    //you can use the annotation coming from jackson library @JsonProperty

    @JsonProperty("Title")
    private String anyName; //we can write any name bc we provide annotation to catch the title
    @JsonProperty("Year")
    private String year;

    private String imdbID;
    @JsonProperty("Type")
    private String type;
    @JsonProperty("Poster")
    private String poster;

    public String getAnyName() {
        return anyName;
    }

    public void setAnyName(String anyName) {
        this.anyName = anyName;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getImdbID() {
        return imdbID;
    }

    public void setImdbID(String imdbID) {
        this.imdbID = imdbID;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPoster() {
        return poster;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }

    @Override
    public String toString() {
        return "Movie{" +
                "Title='" + anyName + '\'' +
                ", Year='" + year + '\'' +
                ", imdbID='" + imdbID + '\'' +
                ", Type='" + type + '\'' +
                ", Poster='" + poster + '\'' +
                '}';
    }
}
