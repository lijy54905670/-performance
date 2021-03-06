package com.community.controller;

import com.community.entity.Community;
import com.community.entity.User;
import com.community.service.CommunityServiceImp;
import com.community.service.PcServiceImp;
import com.community.service.UserServiceImp;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/community")
public class CommunityController {
    @Autowired
    CommunityServiceImp communityServiceImp;
    @Autowired
    PcServiceImp pcServiceImp;
    @Autowired
    UserServiceImp userServiceImp;

    @RequestMapping("/communityHall/{pageNum}")
    public String FindAllCommunityUser(Model model,@PathVariable("pageNum") Integer pageNum,HttpServletRequest request, HttpServletResponse response){

        response.setContentType("text/html;charset=UTF-8");
        pcServiceImp.showProvinceList(model);

        communityServiceImp.PageFindAllCommunityUser(model,pageNum);
        return "community/communityHall";
    }

    //查看社团信息 （05-12）（05-14）
    @GetMapping("/communityInfo{id}")
    public String ShowCommunityInfoById(Model model, HttpServletRequest request, HttpSession session){
        String str=request.getParameter("id");
        int id=Integer.parseInt(str);
        //获取当前登录用户信息
        User user = (User) session.getAttribute("user");
        System.out.println("当前登录用户名字："+user.getUserName());

        //判断该用户是否为社团成员
        //查看用户级别
        communityServiceImp.ShowUserLevel(user.getId(),id,session,model);


        //方便之后点击活动时传参
        session.setAttribute("communityId",id);
        communityServiceImp.ShowCommunityInfoById(model,id,session);
        return "community/communityInfo";
    }

    //查看社团成员列表（05-13）
    @GetMapping("/findUser{id}")
    public String ShowUserListByCommunityId(Model model,HttpServletRequest request){
        String str=request.getParameter("id");
        int id=Integer.parseInt(str);
        communityServiceImp.ShowUserListByCommunityId(model,id);
        return "community/number";
    }

    //通过省市学校查询社团
    @RequestMapping("/showCommunityBySname/{pageNum}")
    public String ShowCommunityBySchoolName(Model model,@PathVariable("pageNum") Integer pageNum,
                                            HttpServletRequest request, HttpServletResponse response,
                                            HttpSession session){
        String sname= (String) session.getAttribute("schoolName");
        System.out.println(sname);
        communityServiceImp.ShowCommunityBySchoolName(model,pageNum,sname);
        return "community/communityHall";
    }

    //设置权限
    @PostMapping("/SetPermissions")
    public String SetPermissions(HttpServletRequest request,HttpServletResponse response,HttpSession session){
        int communityId = (int) session.getAttribute("communityId");
        int Uid=Integer.parseInt(request.getParameter("Uid"));
        int level=Integer.parseInt(request.getParameter("level"));
        String name=request.getParameter("name");
        communityServiceImp.SetPermission(communityId,Uid,level,name);
        return "redirect:/community/findUser?id="+communityId;
    }

    //删除社员
    @GetMapping("/DeleteUser")
    public String DeleteUser(HttpServletRequest request,HttpServletResponse response,HttpSession session){
        int Uid = Integer.parseInt(request.getParameter("Uid"));
        int Cid = Integer.parseInt(request.getParameter("Cid"));
        communityServiceImp.DeleteUser(Cid,Uid);
        return "redirect:/community/findUser?id="+Cid;
    }

    //同意加入社团
    @GetMapping("/agreeJoin")
    public String agreeJoin(HttpServletRequest request,HttpServletResponse response,HttpSession session){
        int Cid= (int) session.getAttribute("communityId");
        int Uid=Integer.parseInt(request.getParameter("Uid"));
        communityServiceImp.AgreeJoin(Cid,Uid);
        return "redirect:/community/findUser?id="+Cid;
    }

    //模糊查询社员
    @PostMapping("/fuzzySearch")
    public String FuzzySearch(Model model,HttpServletRequest request,HttpServletResponse response,HttpSession session){
        int Cid= (int) session.getAttribute("communityId");
        String Uname=request.getParameter("Uname");
        communityServiceImp.FindUserByCidUname(Cid,Uname,model);
        return "community/number";
    }
    //社团模糊搜索
    @PostMapping("/searchCommunity/{pageNum}")
    public String FuzzyCommunity(Model model,@PathVariable("pageNum") Integer pageNum,HttpServletRequest request, HttpServletResponse response){

        response.setContentType("text/html;charset=UTF-8");
        pcServiceImp.showProvinceList(model);

        String Cname=request.getParameter("Cname");

        communityServiceImp.PageFuzzyCommunity(model,pageNum,Cname);
        return "community/communityHall";
    }

    //创建社团
    @PostMapping("/CreateCommunity")
    public String CreateCommunity(Model model,HttpServletRequest request,HttpServletResponse response,HttpSession session){
        String Uname=request.getParameter("Uname");
        User user= (User) session.getAttribute("user");
        Integer teacherId=user.getId();
        //String schoolName=user.getSchoolName();
        String schoolName=request.getParameter("schoolName");
        System.out.println(schoolName);
        int schoolId=communityServiceImp.FindSchoolId(schoolName);


        Community community=new Community();
        community.setUserTeacherId(teacherId);
        community.setName(request.getParameter("Cname"));
        community.setSchoolId(schoolId);
        community.setSchoolName(schoolName);

        //创建社团
        communityServiceImp.CreateCommunity(community);
        //获取新建社团id
        int Cid=communityServiceImp.FindCommunityId(request.getParameter("Cname"));
        int Uid=communityServiceImp.FindUserId(Uname);

        communityServiceImp.InsertCommunityUser(Cid,Uid);
        System.out.println(community.toString());
        return "community/myCommunity";
    }
}
