package com.performane.mapper;

import com.performane.entity.Activity;
import com.performane.pojo.ActivityUser;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.ui.Model;

import java.util.List;

public interface ActivityMapper {
    //查看活动  (1代表历史活动)
    @Select("select * from activity where performane_id=#{id} and is_history<>1 ")
    public List<Activity> FindActivityInfoByperformaneId(Integer id);
    //查看历史活动  (0代表历史活动)
    @Select("select * from activity where performane_id=#{id} and is_history<>0 ")
    public List<Activity> FindHistoryActivityInfoByperformaneId(Integer id);

    //创建活动
    @Insert("insert into activity(title,description,performane_id,image,user_id,is_history,\n"+
                "applystart_date,applyend_date,location,activity_date,flag) values(#{title},#{description},\n"+
            "#{performaneId},#{image},#{userId},0,#{applystartDate},#{applyendDate},#{location},#{activityDate},#{flag})")
    public int CreateActivity(Activity activity);

    //查看活动详细
    @Select("select * from activity where id=#{Aid}")
    public Activity FindActivityInfo(Integer Aid);

    //报名加入
    @Insert("insert into activity_user values(#{Uid},#{Aid})")
    public int JoinActivity(Integer Uid,Integer Aid);

    //通过活动名查找活动ID
    @Select("select id from activity where title=#{Aname} and performane_id=#{Cid}")
    public String FindActivityIdByName(String Aname,Integer Cid);

    //显示已报名成员
    @Select("select ac.*,user.user_name from activity_user as ac,user where ac.user_id=user.id and activity_id=#{Aid}")
    public List<ActivityUser> FindAllApplyUser(Integer Aid);

    //判断是否已加入
    @Select("select * from activity_user where activity_id=#{Aid} and user_id=#{Uid}")
    public ActivityUser IsApply(Integer Aid,Integer Uid);

    //我的活动
    @Select("select title,au.*,is_history from activity_user as au,activity as a where au.activity_id=a.id \n"+
            "and au.user_id=#{Uid}")
    public List<ActivityUser> SelectMyActivity(Integer Uid);

    //停止活动
    @Update("update activity set is_history=1 where id=#{Aid}")
    public int UpdateIsHistory(Integer Aid);

}
