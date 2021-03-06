package com.community.pojo;

public class CommunityUser {
    private Integer id;
    private String communityName;
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

    public String getCommunityName() {
        return communityName;
    }

    public void setCommunityName(String communityName) {
        this.communityName = communityName;
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
        return "CommunityUser{" +
                "id=" + id +
                ", communityName='" + communityName + '\'' +
                ", personNum=" + personNum +
                ", schoolName='" + schoolName + '\'' +
                ", auditStatus=" + auditStatus +
                '}';
    }
}
