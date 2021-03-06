package com.performane.service;

import com.performane.entity.Activity;
import com.performane.mapper.ActivityMapper;
import com.performane.pojo.ActivityUser;
import com.performane.service.serviceInterface.ActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;

import javax.servlet.http.HttpSession;
import java.util.Iterator;
import java.util.List;

@Transactional
@Service
public class ActivityServiceImp implements ActivityService {
    @Autowired
    private ActivityMapper activityMapper;
    @Override
    public void ShowActivityInfo(Model model, Integer id) {
        List<Activity> activities = activityMapper.FindActivityInfoByperformaneId(id);
        if(!activities.isEmpty()){
            model.addAttribute("activities",activities);

            Iterator<Activity> iterator =activities.iterator();
            while(iterator.hasNext()){
                System.out.println(iterator.next().toString());
            }
        }
    }

    //查看历史活动
    @Override
    public void ShowHistoryActivityInfo(Model model, Integer id) {
        List<Activity> activities = activityMapper.FindHistoryActivityInfoByperformaneId(id);
        if(!activities.isEmpty()){
            model.addAttribute("historyActivity",activities);

            Iterator<Activity> iterator =activities.iterator();
            while(iterator.hasNext()){
                System.out.println(iterator.next().toString());
            }
        }
    }

    //创建社团
    @Override
    public void CreateActivity(Activity activity) {
        int i=activityMapper.CreateActivity(activity);
        if(i>0){
            System.out.println("创建成功");
        }else {
            System.out.println("创建失败");
        }
    }

    //查看活动详细
    @Override
    public void ShowOneActivityInfo(Model model, Integer Aid, Integer Uid, HttpSession session) {
        Activity activity=activityMapper.FindActivityInfo(Aid);
        System.out.println(activity.toString());
        ActivityUser activityUser=activityMapper.IsApply(Aid,Uid);
        boolean flag=false;
        if(activityUser!=null){
            System.out.println(activityUser.toString());
            flag=true;
        }
        session.setAttribute("isApply",flag);
        model.addAttribute("oneActivityInfo",activity);
    }

    //报名加入活动
    @Override
    public void JoinActivity(Integer Uid, Integer Aid) {
        int i=activityMapper.JoinActivity(Uid,Aid);
        if(i>0){
            System.out.println("报名成功");
        }else {
            System.out.println("报名失败");
        }
    }

    //查看活动id（用于向activity_user插入数据）
    @Override
    public int FindActivityId(String Aname, Integer Cid) {
        String str=activityMapper.FindActivityIdByName(Aname,Cid);
        if(str!=null){
            int id=Integer.parseInt(str);
            return id;
        }else {
            return 0;
        }
    }

    //查看已报名用户
    @Override
    public void ShowApplyedUser(Model model, Integer Aid) {
        List<ActivityUser> activityUsers=activityMapper.FindAllApplyUser(Aid);
        if(!activityUsers.isEmpty()){
            System.out.println("查看");
            model.addAttribute("activityUser",activityUsers);
            Iterator<ActivityUser> iterator=activityUsers.iterator();
            while(iterator.hasNext()){
                System.out.println(iterator.next().toString());
            }
        }
    }

    //我的活动
    @Override
    public void myActicity(Model model, Integer Uid) {
        List<ActivityUser> activityUsers=activityMapper.SelectMyActivity(Uid);

        if(!activityUsers.isEmpty()){
            Iterator<ActivityUser> iterator=activityUsers.iterator();
            while(iterator.hasNext()){
                System.out.println(iterator.next().toString());
            }
        }
    }

    //取消活动
    @Override
    public void stopActicity(Integer Aid) {
        int i=activityMapper.UpdateIsHistory(Aid);
        if(i>0){
            System.out.println("取消成功");
        }else {
            System.out.println("取消失败");
        }
    }
}
