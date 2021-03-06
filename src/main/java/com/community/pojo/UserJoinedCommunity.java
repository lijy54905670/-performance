package com.performane.pojo;

public class UserJoinedperformane {
    private Integer performaneId;
    private Integer numberLevel;
    private String performaneName;
    private String levelName;
    private Integer auditStatus;

    public Integer getperformaneId() {
        return performaneId;
    }

    public void setperformaneId(Integer performaneId) {
        this.performaneId = performaneId;
    }

    public Integer getNumberLevel() {
        return numberLevel;
    }

    public void setNumberLevel(Integer numberLevel) {
        this.numberLevel = numberLevel;
    }

    public String getperformaneName() {
        return performaneName;
    }

    public void setperformaneName(String performaneName) {
        this.performaneName = performaneName;
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
        return "UserJoinedperformane{" +
                "performaneId=" + performaneId +
                ", numberLevel=" + numberLevel +
                ", performaneName='" + performaneName + '\'' +
                ", levelName='" + levelName + '\'' +
                ", auditStatus=" + auditStatus +
                '}';
    }
}
