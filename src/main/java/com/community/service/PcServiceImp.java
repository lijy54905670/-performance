package com.community.service;

import com.community.entity.Community;
import com.community.entity.School;
import com.community.mapper.PcMapper;
import com.community.pojo.City;
import com.community.pojo.Province;
import com.community.service.serviceInterface.PcService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;

import java.util.Iterator;
import java.util.List;

@Transactional
@Service
public class PcServiceImp implements PcService {
    @Autowired
    private PcMapper pcMapper;

    @Override
    public void showProvinceList(Model model) {
        List<Province> provinceList=pcMapper.findProvinceList();
        if(!provinceList.isEmpty()) {
            model.addAttribute("proList",provinceList);
            Iterator<Province> iterator = provinceList.iterator();
            while (iterator.hasNext()) {
                System.out.println(iterator.next().toString());
            }
        }
    }

    @Override
    public List<City> showCityListByPid(Integer id) {
        return pcMapper.findCityListByPid(id);
    }

    @Override
    public List<School> showSchoolListByCname(String Cname) {
        List<School> schools=pcMapper.findSchoolListByCname(Cname);
        if(!schools.isEmpty()){
            Iterator<School> iterator=schools.iterator();
            while(iterator.hasNext()){
                System.out.println(iterator.next().toString());
            }
        }
        return pcMapper.findSchoolListByCname(Cname);
    }

    @Override
    public List<Community> showCommunityListBySname(String Sname) {
        return pcMapper.findCommunityListBySname(Sname);
    }
}
