package com.performane.service.serviceInterface;

import com.performane.entity.performane;
import com.performane.entity.School;
import com.performane.pojo.City;
import org.springframework.ui.Model;

import java.util.List;

public interface PcService {
    public void showProvinceList(Model model);

    public List<City> showCityListByPid(Integer id);

    public List<School> showSchoolListByCname(String Cname);

    public List<performane> showperformaneListBySname(String Sname);
}
