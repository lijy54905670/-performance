package com.performane.pojo;

//查看社团成员
public class performaneUserInfo {
    private Integer userId;
    private String userName;
    private Integer numberLevel;
    private String levelName;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Integer getNumberLevel() {
        return numberLevel;
    }

    public void setNumberLevel(Integer numberLevel) {
        this.numberLevel = numberLevel;
    }

    public String getLevelName() {
        return levelName;
    }

    public void setLevelName(String levelName) {
        this.levelName = levelName;
    }

    @Override
    public String toString() {
        return "performaneUserInfo{" +
                "UserId=" + userId +
                ", userName='" + userName + '\'' +
                ", numberLevel=" + numberLevel +
                ", levelName='" + levelName + '\'' +
                '}';
    }
}
