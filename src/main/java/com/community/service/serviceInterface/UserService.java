package com.performane.service.serviceInterface;

import com.performane.entity.User;
import org.springframework.http.HttpRequest;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Map;

public interface UserService {
    //注册
    public boolean Register(User user);
    //修改资料
    public boolean Update(User user);
    //通过用户名查找
    public void findUSerByUserName(String userName, HttpSession session);
    //文件路径转换
    public String file(MultipartFile file, HttpServletRequest request);
    //加入某个社团
    public void joinperformane(Integer performaneId, Integer userId);
    //查看用户已加入社团
    public void joinedperformane(Integer Uid, Model model);
}
