package com.community.controller;

import com.community.entity.User;
import com.community.service.UserServiceImp;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;


@Controller
public class UserController {
    @Autowired
    private UserServiceImp userServiceImp;
    //注册功能
    @PostMapping("/register")
    public String register(User user, Model model){
        if(user!=null) {
            boolean flag = (boolean) userServiceImp.Register(user);
            if(flag){
                return "index";
            }else{
                user.setUserName("");
                model.addAttribute("user_reg",user);
                model.addAttribute("msg","用户名已注册");
                return "user/register";
            }
        }else{
            model.addAttribute("msg","用户信息不完整");
            return "user/register";
        }
    }


    //修改资料

    @PostMapping("/update")
    public String update(User user, HttpSession session, MultipartFile file, HttpServletRequest request){
        System.out.println(user.toString()+"update开始");
        if(file!=null) {
            String filePath = userServiceImp.file(file, request);
            System.out.println("有文件,路径为" + filePath);
            if (filePath == null) {
                System.out.println("上传失败");
            } else {
                //否则将文件路径存入user
                user.setImage(filePath);
            }
        }
        if(user!=null) {
            boolean flag = userServiceImp.Update(user);
            if (flag) {
                //修改成功后、更新session里存的user
                userServiceImp.findUSerByUserName(user.getUserName(),session);
                return "user/information";
            }else {
                session.setAttribute("user",user);
                //然后提示修改失败

                return "user/information";
            }
        }else{
            return "user/information";
        }
    }

    //用户加入社团
    @RequestMapping("/join")
    public String joinCommunity(HttpSession session){
        System.out.println("进入");
        User user = (User) session.getAttribute("user");
        Integer userId = user.getId();
        Integer communityId = (Integer) session.getAttribute("communityId");
        userServiceImp.joinCommunity(communityId,userId);
        return "redirect:/community/communityInfo/?id="+communityId;
    }

    //我的社团
    @RequestMapping("/myCommunity")
    public String myCommunity(Model model,HttpSession session){
        User user= (User) session.getAttribute("user");
        int userId=user.getId();
        userServiceImp.joinedCommunity(userId,model);
        System.out.println("用户id:"+userId);
        return "community/myCommunity";
    }
}
