package com.performane.entity;

public class Post {
    private Integer id;
    private String title;
    private String description;
    private Integer performaneId;
    private Integer userId;
    private Integer status;
    private String isRecommend;

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

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getIsRecommend() {
        return isRecommend;
    }

    public void setIsRecommend(String isRecommend) {
        this.isRecommend = isRecommend;
    }

    @Override
    public String toString() {
        return "Post{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", performaneId=" + performaneId +
                ", userId=" + userId +
                ", status=" + status +
                ", isRecommend='" + isRecommend + '\'' +
                '}';
    }
}
