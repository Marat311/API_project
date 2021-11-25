package com.cydeo.day1;

public class Spartan {

    private int id;
    private String gender;
    private String name;
    private long phone;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getPhone() {
        return phone;
    }

    public void setPhone(long phone) {
        this.phone = phone;
    }

    public Spartan(int id, String gender, String name, long phone) {
        this.id = id;
        this.gender = gender;
        this.name = name;
        this.phone = phone;
    }

    public Spartan() {
    }

    @Override
    public String toString() {
        return "Spartan{" +
                "id=" + id +
                ", gender='" + gender + '\'' +
                ", name='" + name + '\'' +
                ", phone=" + phone +
                '}';
    }
}
