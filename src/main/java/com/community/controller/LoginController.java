package com.community.controller;

import com.community.entity.User;
import com.community.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;


@Controller
public class LoginController {
    @Autowired
    private UserMapper userMapper;

    @PostMapping("/login")
    public String Login(String userName, String password, Model model,
                        HttpSession session){
        User user = userMapper.FindUserByUserName(userName);
        if (user != null) {
            if(user.getPassword().equals(password)){
                session.setAttribute("user",user);
                return "redirect:/community/communityHall/1";
            }else{
                model.addAttribute("msg","密码错误");
                return "index";
            }

        }else{
            model.addAttribute("msg","用户名不存在");
            return "index";
        }
    }
}
