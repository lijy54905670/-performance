package com.performane.service.serviceInterface;

import com.performane.entity.performane;
import org.springframework.ui.Model;

import javax.servlet.http.HttpSession;

public interface performaneService {
    //社团大厅分页
    public void PageFindAllperformaneUser(Model model, Integer pageNum);
    //查看社团
    public void ShowperformaneInfoById(Model model, Integer id, HttpSession session);

    //查看社团成员 (05-13)
    public void ShowUserListByperformaneId(Model model, Integer id);

    //通过学校名查看社团
    public void ShowperformaneBySchoolName(Model model,Integer pageNum,String Sname);

    //查看用户级别
    public void ShowUserLevel(Integer Uname,Integer Cid,HttpSession session,Model model);

    //设置用户权限
    public void SetPermission(Integer Cid,Integer Uid,Integer level,String name);

    //删除社员
    public void DeleteUser(Integer Uid,Integer Cid);

    //同意加入
    public void AgreeJoin(Integer Cid,Integer Uid);

    //模糊查询社团成员
    public void FindUserByCidUname(Integer Cid,String Uname,Model model);

    //模糊查询社团
    public void PageFuzzyperformane(Model model, Integer pageNum,String Cname);

    //根据学校名查找学校id
    public int FindSchoolId(String schoolName);

    //创建社团
    public void Createperformane(performane performane);

    //根据社团名查找社团id
    public int FindperformaneId(String Cname);

    //根据用户名查找用户id
    public int FindUserId(String Uname);

    //插入社长
    public void InsertperformaneUser(Integer Cid,Integer Uid);

}
