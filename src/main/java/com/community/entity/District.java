package com.community.entity;

public class District {
    private Integer pid;
    private String districtName;
    private Integer type;
    private Integer hierarchy;
    private String districtSqe;

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    public String getDistrictName() {
        return districtName;
    }

    public void setDistrictName(String districtName) {
        this.districtName = districtName;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getHierarchy() {
        return hierarchy;
    }

    public void setHierarchy(Integer hierarchy) {
        this.hierarchy = hierarchy;
    }

    public String getDistrictSqe() {
        return districtSqe;
    }

    public void setDistrictSqe(String districtSqe) {
        this.districtSqe = districtSqe;
    }

    public String toString() {
        return "District{" +
                "pid=" + pid +
                ", districtName='" + districtName + '\'' +
                ", type=" + type +
                ", hierarchy=" + hierarchy +
                ", districtSqe='" + districtSqe + '\'' +
                '}';
    }
}

