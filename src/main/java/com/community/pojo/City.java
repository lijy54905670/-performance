package com.community.pojo;

public class City {
    private Integer id;

    private Integer cityID;

    private String city;

    private Integer father;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCityID() {
        return cityID;
    }

    public void setCityID(Integer cityID) {
        this.cityID = cityID;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Integer getFather() {
        return father;
    }

    public void setFather(Integer father) {
        this.father = father;
    }

    @Override
    public String toString() {
        return "City{" +
                "id=" + id +
                ", cityID=" + cityID +
                ", city='" + city + '\'' +
                ", father=" + father +
                '}';
    }
}
