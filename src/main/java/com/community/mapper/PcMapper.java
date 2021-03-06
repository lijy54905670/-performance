package com.performane.mapper;

import com.performane.entity.performane;
import com.performane.entity.School;
import com.performane.pojo.City;
import com.performane.pojo.Province;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface PcMapper {
    @Select("select * from province")
    public List<Province> findProvinceList();

    @Select("select * from city where father=#{id}")
    public List<City> findCityListByPid(Integer id);

    @Select("select * from school where city=#{Cname}")
    public List<School> findSchoolListByCname(String Cname);

    @Select("select * from performane where school_name=#{Sname}")
    public List<performane> findperformaneListBySname(String Sname);
}
