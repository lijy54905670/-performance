package com.community.pojo;

public class UserJoinedCommunity {
    private Integer communityId;
    private Integer numberLevel;
    private String communityName;
    private String levelName;
    private Integer auditStatus;

    public Integer getCommunityId() {
        return communityId;
    }

    public void setCommunityId(Integer communityId) {
        this.communityId = communityId;
    }

    public Integer getNumberLevel() {
        return numberLevel;
    }

    public void setNumberLevel(Integer numberLevel) {
        this.numberLevel = numberLevel;
    }

    public String getCommunityName() {
        return communityName;
    }

    public void setCommunityName(String communityName) {
        this.communityName = communityName;
    }

    public String getLevelName() {
        return levelName;
    }

    public void setLevelName(String levelName) {
        this.levelName = levelName;
    }

    public Integer getAuditStatus() {
        return auditStatus;
    }

    public void setAuditStatus(Integer auditStatus) {
        this.auditStatus = auditStatus;
    }

    @Override
    public String toString() {
        return "UserJoinedCommunity{" +
                "communityId=" + communityId +
                ", numberLevel=" + numberLevel +
                ", communityName='" + communityName + '\'' +
                ", levelName='" + levelName + '\'' +
                ", auditStatus=" + auditStatus +
                '}';
    }
}
