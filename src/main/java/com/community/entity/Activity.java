package com.performane.entity;

import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class Activity {
    private Integer id;
    private String title;
    private String description;
    private Integer performaneId;
    private String image;
    private Integer userId;
    private Integer isHistory;
    //@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    private Date applystartDate;
    private Date  applyendDate;
    private String location;
    private Date activityDate;
    private Integer flag;

    public Date getApplystartDate() {
        return applystartDate;
    }

    public void setApplystartDate(Date applystartDate) {
        this.applystartDate = applystartDate;
    }

    public Date getApplyendDate() {
        return applyendDate;
    }

    public void setApplyendDate(Date applyendDate) {
        this.applyendDate = applyendDate;
    }

    public Date getActivityDate() {
        return activityDate;
    }

    public void setActivityDate(Date activityDate) {
        this.activityDate = activityDate;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getperformaneId() {
        return performaneId;
    }

    public void setperformaneId(Integer performaneId) {
        this.performaneId = performaneId;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getIsHistory() {
        return isHistory;
    }

    public void setIsHistory(Integer isHistory) {
        this.isHistory = isHistory;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Integer getFlag() {
        return flag;
    }

    public void setFlag(Integer flag) {
        this.flag = flag;
    }

    @Override
    public String toString() {
        return "Activity{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", performaneId=" + performaneId +
                ", image='" + image + '\'' +
                ", userId=" + userId +
                ", isHistory=" + isHistory +
                ", applystartDate=" + applystartDate +
                ", applyendDate=" + applyendDate +
                ", loaction='" + location + '\'' +
                ", activityDate='" + activityDate + '\'' +
                ", flag=" + flag +
                '}';
    }
}
