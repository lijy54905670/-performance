package com.performane.controller;


import com.performane.entity.School;
import com.performane.pojo.City;
import com.performane.service.PcServiceImp;
import net.sf.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

@Controller
public class PcController {
    @Autowired
    private PcServiceImp pcServiceImp;


    // 根据ajax返回到控制器的省id来查询相对应的城市名
    @RequestMapping("/showCityListByPid")
    public void showCityListByPid(HttpServletRequest request, HttpServletResponse response, Integer pid) throws IOException {
        response.setContentType("text/html;charset=UTF-8");
        System.out.println(pid);// 打印前台ajax传来的省id
        List<City> city = pcServiceImp.showCityListByPid(pid);
        Iterator<City> iterator=city.iterator();
        while(iterator.hasNext()){
            System.out.println(iterator.next().toString());
        }
        JSONArray json = JSONArray.fromObject(city);// 将集合转化为json格式
        response.getWriter().print(json.toString());
        response.getWriter().flush();
        response.getWriter().close();

    }

    // 根基ajax返回到控制器的城市id来查询相对应的区、县
    @RequestMapping("/showSchoolListByCname")
    public void showTownListByCname(HttpServletRequest request, HttpServletResponse response, String cid) throws IOException {
        response.setContentType("text/html;charset=UTF-8");
        System.out.println("城市名字"+cid);// 打印前台ajax传来的城市id
        List<School> school = pcServiceImp.showSchoolListByCname(cid);

        JSONArray json = JSONArray.fromObject(school);// 将集合转化为json格式
        response.getWriter().print(json.toString());
        response.getWriter().flush();
        response.getWriter().close();

    }
    @RequestMapping("/showperformaneListBySname")
    public void showperformaneListBySname(HttpServletRequest request,HttpSession session,HttpServletResponse response, String sname) throws IOException {
        response.setContentType("text/html;charset=UTF-8");
        System.out.println("城市名字"+sname);// 打印前台ajax传来的学校名字
        session.setAttribute("schoolName",sname);
    }

}
