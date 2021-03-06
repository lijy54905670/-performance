package com.performane.pojo;

public class performaneUser {
    private Integer id;
    private String performaneName;
    //社团总人数
    private Integer personNum;
    private String schoolName;
    private Integer auditStatus;

    public Integer getAuditStatus() {
        return auditStatus;
    }

    public void setAuditStatus(Integer auditStatus) {
        this.auditStatus = auditStatus;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getperformaneName() {
        return performaneName;
    }

    public void setperformaneName(String performaneName) {
        this.performaneName = performaneName;
    }

    public Integer getPersonNum() {
        return personNum;
    }

    public void setPersonNum(Integer personNum) {
        this.personNum = personNum;
    }

    public String getSchoolName() {
        return schoolName;
    }

    public void setSchoolName(String schoolName) {
        this.schoolName = schoolName;
    }

    @Override
    public String toString() {
        return "performaneUser{" +
                "id=" + id +
                ", performaneName='" + performaneName + '\'' +
                ", personNum=" + personNum +
                ", schoolName='" + schoolName + '\'' +
                ", auditStatus=" + auditStatus +
                '}';
    }
}
