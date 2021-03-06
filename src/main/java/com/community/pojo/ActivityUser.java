package com.performane.pojo;

public class ActivityUser {
    private String userName;
    private Integer userId;
    private Integer activityId;
    private String title;
    private Integer isHistory;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getIsHistory() {
        return isHistory;
    }

    public void setIsHistory(Integer isHistory) {
        this.isHistory = isHistory;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getActivityId() {
        return activityId;
    }

    public void setActivityId(Integer activityId) {
        this.activityId = activityId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Override
    public String toString() {
        return "ActivityUser{" +
                "userName='" + userName + '\'' +
                ", userId=" + userId +
                ", activityId=" + activityId +
                ", title='" + title + '\'' +
                ", isHistory=" + isHistory +
                '}';
    }
}
