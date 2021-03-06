package com.community.mapper;

import com.community.entity.Community;
import com.community.entity.School;
import com.community.pojo.City;
import com.community.pojo.Province;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface PcMapper {
    @Select("select * from province")
    public List<Province> findProvinceList();

    @Select("select * from city where father=#{id}")
    public List<City> findCityListByPid(Integer id);

    @Select("select * from school where city=#{Cname}")
    public List<School> findSchoolListByCname(String Cname);

    @Select("select * from community where school_name=#{Sname}")
    public List<Community> findCommunityListBySname(String Sname);
}
