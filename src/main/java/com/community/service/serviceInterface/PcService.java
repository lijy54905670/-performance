package com.community.service.serviceInterface;

import com.community.entity.Community;
import com.community.entity.School;
import com.community.pojo.City;
import org.springframework.ui.Model;

import java.util.List;

public interface PcService {
    public void showProvinceList(Model model);

    public List<City> showCityListByPid(Integer id);

    public List<School> showSchoolListByCname(String Cname);

    public List<Community> showCommunityListBySname(String Sname);
}
