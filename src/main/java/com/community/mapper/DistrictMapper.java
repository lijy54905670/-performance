package com.performane.mapper;

import com.performane.entity.District;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

public interface DistrictMapper {
    @Select("select * from district where id=#{id}")
    public District findDistrictById(@Param("id") Integer id);
}
