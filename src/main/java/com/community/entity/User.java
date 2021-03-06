package com.performane.entity;

import java.util.Date;

public class User {
    private Integer id;
    private String userName;
    private String password;
    private String name;
    private Integer flag;
    private Integer sex;
    private String image;
    private String Email;
    private String address;
    private Date birth;
    private String phone;
    private String styleSignature;
    private String schoolName;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSchoolName() {
        return schoolName;
    }

    public void setSchoolName(String schoolName) {
        this.schoolName = schoolName;
    }

    public String getStyleSignature() {
        return styleSignature;
    }

    public void setStyleSignature(String styleSignature) {
        this.styleSignature = styleSignature;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Date getBirth() {
        return birth;
    }

    public void setBirth(Date birth) {
        this.birth = birth;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getFlag() {
        return flag;
    }

    public void setFlag(Integer flag) {
        this.flag = flag;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "User{" +
                "userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", name='" + name + '\'' +
                ", flag=" + flag +
                ", sex=" + sex +
                ", image='" + image + '\'' +
                ", Email='" + Email + '\'' +
                ", address='" + address + '\'' +
                ", birth=" + birth +
                ", phone='" + phone + '\'' +
                ", style_signature='" + styleSignature + '\'' +
                '}';
    }
}
