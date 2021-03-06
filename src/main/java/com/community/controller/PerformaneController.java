package com.performane.controller;

import com.performane.entity.performane;
import com.performane.entity.User;
import com.performane.service.performaneServiceImp;
import com.performane.service.PcServiceImp;
import com.performane.service.UserServiceImp;
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
@RequestMapping("/performane")
public class PerformaneController {
    @Autowired
    performaneServiceImp performaneServiceImp;
    @Autowired
    PcServiceImp pcServiceImp;
    @Autowired
    UserServiceImp userServiceImp;

    @RequestMapping("/performaneHall/{pageNum}")
    public String FindAllperformaneUser(Model model,@PathVariable("pageNum") Integer pageNum,HttpServletRequest request, HttpServletResponse response){

        response.setContentType("text/html;charset=UTF-8");
        pcServiceImp.showProvinceList(model);

        performaneServiceImp.PageFindAllperformaneUser(model,pageNum);
        return "performane/performaneHall";
    }

    //查看社团信息 （05-12）（05-14）
    @GetMapping("/performaneInfo{id}")
    public String ShowperformaneInfoById(Model model, HttpServletRequest request, HttpSession session){
        String str=request.getParameter("id");
        int id=Integer.parseInt(str);
        //获取当前登录用户信息
        User user = (User) session.getAttribute("user");
        System.out.println("当前登录用户名字："+user.getUserName());

        //判断该用户是否为社团成员
        //查看用户级别
        performaneServiceImp.ShowUserLevel(user.getId(),id,session,model);


        //方便之后点击活动时传参
        session.setAttribute("performaneId",id);
        performaneServiceImp.ShowperformaneInfoById(model,id,session);
        return "performane/performaneInfo";
    }

    //查看社团成员列表（05-13）
    @GetMapping("/findUser{id}")
    public String ShowUserListByperformaneId(Model model,HttpServletRequest request){
        String str=request.getParameter("id");
        int id=Integer.parseInt(str);
        performaneServiceImp.ShowUserListByperformaneId(model,id);
        return "performane/number";
    }

    //通过省市学校查询社团
    @RequestMapping("/showperformaneBySname/{pageNum}")
    public String ShowperformaneBySchoolName(Model model,@PathVariable("pageNum") Integer pageNum,
                                            HttpServletRequest request, HttpServletResponse response,
                                            HttpSession session){
        String sname= (String) session.getAttribute("schoolName");
        System.out.println(sname);
        performaneServiceImp.ShowperformaneBySchoolName(model,pageNum,sname);
        return "performane/performaneHall";
    }

    //设置权限
    @PostMapping("/SetPermissions")
    public String SetPermissions(HttpServletRequest request,HttpServletResponse response,HttpSession session){
        int performaneId = (int) session.getAttribute("performaneId");
        int Uid=Integer.parseInt(request.getParameter("Uid"));
        int level=Integer.parseInt(request.getParameter("level"));
        String name=request.getParameter("name");
        performaneServiceImp.SetPermission(performaneId,Uid,level,name);
        return "redirect:/performane/findUser?id="+performaneId;
    }

    //删除社员
    @GetMapping("/DeleteUser")
    public String DeleteUser(HttpServletRequest request,HttpServletResponse response,HttpSession session){
        int Uid = Integer.parseInt(request.getParameter("Uid"));
        int Cid = Integer.parseInt(request.getParameter("Cid"));
        performaneServiceImp.DeleteUser(Cid,Uid);
        return "redirect:/performane/findUser?id="+Cid;
    }

    //同意加入社团
    @GetMapping("/agreeJoin")
    public String agreeJoin(HttpServletRequest request,HttpServletResponse response,HttpSession session){
        int Cid= (int) session.getAttribute("performaneId");
        int Uid=Integer.parseInt(request.getParameter("Uid"));
        performaneServiceImp.AgreeJoin(Cid,Uid);
        return "redirect:/performane/findUser?id="+Cid;
    }

    //模糊查询社员
    @PostMapping("/fuzzySearch")
    public String FuzzySearch(Model model,HttpServletRequest request,HttpServletResponse response,HttpSession session){
        int Cid= (int) session.getAttribute("performaneId");
        String Uname=request.getParameter("Uname");
        performaneServiceImp.FindUserByCidUname(Cid,Uname,model);
        return "performane/number";
    }
    //社团模糊搜索
    @PostMapping("/searchperformane/{pageNum}")
    public String Fuzzyperformane(Model model,@PathVariable("pageNum") Integer pageNum,HttpServletRequest request, HttpServletResponse response){

        response.setContentType("text/html;charset=UTF-8");
        pcServiceImp.showProvinceList(model);

        String Cname=request.getParameter("Cname");

        performaneServiceImp.PageFuzzyperformane(model,pageNum,Cname);
        return "performane/performaneHall";
    }

    //创建社团
    @PostMapping("/Createperformane")
    public String Createperformane(Model model,HttpServletRequest request,HttpServletResponse response,HttpSession session){
        String Uname=request.getParameter("Uname");
        User user= (User) session.getAttribute("user");
        Integer teacherId=user.getId();
        //String schoolName=user.getSchoolName();
        String schoolName=request.getParameter("schoolName");
        System.out.println(schoolName);
        int schoolId=performaneServiceImp.FindSchoolId(schoolName);


        performane performane=new performane();
        performane.setUserTeacherId(teacherId);
        performane.setName(request.getParameter("Cname"));
        performane.setSchoolId(schoolId);
        performane.setSchoolName(schoolName);

        //创建社团
        performaneServiceImp.Createperformane(performane);
        //获取新建社团id
        int Cid=performaneServiceImp.FindperformaneId(request.getParameter("Cname"));
        int Uid=performaneServiceImp.FindUserId(Uname);

        performaneServiceImp.InsertperformaneUser(Cid,Uid);
        System.out.println(performane.toString());
        return "performane/myperformane";
    }
}
