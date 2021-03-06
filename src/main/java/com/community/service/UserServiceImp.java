package com.community.service;

import com.community.controller.UserController;
import com.community.entity.User;
import com.community.mapper.UserMapper;
import com.community.pojo.UserJoinedCommunity;
import com.community.service.serviceInterface.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

@Transactional
@Service
public class UserServiceImp implements UserService {
    @Autowired
    private UserMapper userMapper;
    @Override
    public boolean Register(User user) {
        boolean flag =false;
        //判断该是否存在用户
        if(user !=null) {
            User u = userMapper.FindUserByUserName(user.getUserName());
            if (u != null) {
                flag=false;
            } else {
                int i = userMapper.InsertUser(user);
                if (i > 0) {
                    flag=true;
                }
            }
        }
        return flag;
    }

    @Override
    public boolean Update(User user) {
        boolean flag=false;
        if(user!=null){
            int i=userMapper.UpdateUser(user);
            if(i>0){
                flag=true;
            }else{
                flag=false;
            }
        }
        return flag;
    }

    @Override
    public void findUSerByUserName(String userName, HttpSession session) {
        User user=userMapper.FindUserByUserName(userName);
        if(user!=null){
            System.out.println(user.toString()+"---------findUser");
            session.setAttribute("user",user);
        }
    }


    // 项目根路径下的目录  -- SpringBoot static 目录相当于是根路径下（SpringBoot 默认）
    public final static String UPLOAD_PATH_PREFIX = "static/uploadFile/";
    @Override
    public String file(MultipartFile uploadFile, HttpServletRequest request) {
        if(uploadFile.isEmpty()){
            //返回选择文件提示
            return null;
        }
        //构建文件上传所要保存的"文件夹路径"--这里是相对路径，保存到项目根路径的文件夹下
        String realPath = new String("src/main/resources/" + UPLOAD_PATH_PREFIX);
        //存放上传文件的文件夹
        File file = new File(realPath);
        if(!file.isDirectory()){
            //递归生成文件夹
            file.mkdirs();
        }
        //获取原始的名字  original:最初的，起始的  方法是得到原来的文件名在客户机的文件系统名称
        String oldName = uploadFile.getOriginalFilename();
        String newName = UUID.randomUUID().toString() + oldName.substring(oldName.lastIndexOf("."),oldName.length());
        try {
            //构建真实的文件路径
            File newFile = new File(file.getAbsolutePath() + File.separator + newName);
            //转存文件到指定路径，如果文件名重复的话，将会覆盖掉之前的文件,这里是把文件上传到 “绝对路径”
            uploadFile.transferTo(newFile);
            String filePath = newName;
            return filePath;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void joinCommunity(Integer communityId, Integer userId) {
        userMapper.InsertCommunityUser(communityId,userId);
    }

    @Override
    public void joinedCommunity(Integer Uid, Model model) {
        List<UserJoinedCommunity> userJoinedCommunities=userMapper.findJoinedCommunity(Uid);

        if(!userJoinedCommunities.isEmpty()){
            model.addAttribute("joinedCommunity",userJoinedCommunities);


            Iterator<UserJoinedCommunity> iterator=userJoinedCommunities.iterator();
            while (iterator.hasNext()){
                System.out.println(iterator.next().toString());
            }


        }

    }

}
