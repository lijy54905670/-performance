package com.community.controller;

import com.community.entity.Activity;
import com.community.entity.User;
import com.community.service.ActivityServiceImp;
import com.sun.org.apache.xpath.internal.operations.Mod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.net.ssl.HttpsURLConnection;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.rmi.server.UID;
import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
@RequestMapping("/activitys")
public class ActivityController {
    @Autowired
    private ActivityServiceImp activityServiceImp;

    @GetMapping("/communityActivity{id}")
    public String ShowCommunityActivity(Model model, HttpServletRequest request){
        String str = request.getParameter("id");
        int id=Integer.parseInt(str);
        activityServiceImp.ShowActivityInfo(model,id);
        activityServiceImp.ShowHistoryActivityInfo(model,id);
        return "activity/communityActivity";
    }

    //创建活动
    @PostMapping("/createActivity")
    public String CreateActivity(Activity activity , Model model, HttpSession session){
        int Cid= (int) session.getAttribute("communityId");
        //生成系统当前时间
        Date applyStartDate1 = new Date();
        User user= (User) session.getAttribute("user");
        int Uid=user.getId();
        activity.setUserId(Uid);
        activity.setCommunityId(Cid);
        activity.setApplystartDate(applyStartDate1);
        activityServiceImp.CreateActivity(activity);

        int Aid=activityServiceImp.FindActivityId(activity.getTitle(),Cid);
        //插入活动创建人
        activityServiceImp.JoinActivity(Uid,Aid);
        //System.out.println(activity.toString()+"    "+sld.format(new Date()));
        return "redirect:communityActivity?id="+Cid;
    }

    //活动详细
    @GetMapping("/activityInfo")
    public String ActivityInfo(Model model, HttpServletRequest request, HttpSession session){
        int Aid=Integer.parseInt(request.getParameter("id"));
        User user= (User) session.getAttribute("user");
        int Uid=user.getId();
        activityServiceImp.ShowOneActivityInfo(model,Aid,Uid,session);
        activityServiceImp.ShowApplyedUser(model,Aid);
        return "activity/activityInfo";
    }

    //报名加入
    @GetMapping("/apply")
    public String Apply(HttpServletRequest request,HttpSession session){
        int Aid=Integer.parseInt(request.getParameter("id"));
        User user= (User) session.getAttribute("user");
        int Uid=user.getId();
        activityServiceImp.JoinActivity(Uid,Aid);
        return "redirect:/activitys/activityInfo?id="+Aid;
    }

    //我的活动
    @GetMapping("/myActivity")
    public String myActivity(HttpServletRequest request,Model model){
        int Uid=Integer.parseInt(request.getParameter("id"));
        System.out.println(Uid);
        activityServiceImp.myActicity(model,Uid);
        return "activity/myActivity";
    }

    //结束活动
    @GetMapping("/stopActivity")
    public String stopActivity(HttpServletRequest request,Model model){
        int Aid=Integer.parseInt(request.getParameter("id"));
        activityServiceImp.stopActicity(Aid);
        return "redirect:/activitys/activityInfo?id="+Aid;
    }
}
