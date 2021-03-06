package com.performane.service.serviceInterface;

import com.performane.entity.Activity;
import com.performane.pojo.ActivityUser;
import org.apache.ibatis.annotations.Select;
import org.springframework.ui.Model;

import javax.servlet.http.HttpSession;

public interface ActivityService {
    //查看活动信息
    public void ShowActivityInfo(Model model, Integer id);

    //查看历史活动信息
    public void ShowHistoryActivityInfo(Model model, Integer id);

    //创建社团
    public void CreateActivity(Activity activity);

    //查看活动详细
    public void ShowOneActivityInfo(Model model, Integer Aid, Integer Uid, HttpSession session);

    //报名加入
    public void JoinActivity(Integer Uid,Integer Aid);

    //
    public int FindActivityId(String Aname,Integer Cid);

    //显示已报名人员
    public void ShowApplyedUser(Model model, Integer Aid);

    //我的活动
    public void myActicity(Model model,Integer Uid);

    //停止活动
    public void stopActicity(Integer Aid);

}
