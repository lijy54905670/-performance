package com.performane.controller;

import com.performane.entity.Comment;
import com.performane.entity.Post;
import com.performane.entity.User;
import com.performane.service.PostServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/post")
public class PostController {
    @Autowired
    private PostServiceImp postServiceImp;

    //查看帖子详细
    @GetMapping("/postInfo{id}")
    public String ShowPostInfoById(Model model, HttpServletRequest request){
        String str=request.getParameter("id");
        int id=Integer.parseInt(str);
        //查看帖子信息
        postServiceImp.ShowPostInfoById(model,id);
        //查看评论信息
        postServiceImp.ShowCommentInfoByPostId(model,id);
        return "postcomment/postInfo";
    }

    //列出社团所有帖子（05-13）
    @GetMapping("/postList{id}")
    public String ShowPostListById(Model model ,HttpServletRequest request,HttpSession session){
        String str=request.getParameter("id");
        int id=Integer.parseInt(str);
        postServiceImp.ShowPostListById(model,id);

        boolean isMember = (boolean) session.getAttribute("isMember");
        if(isMember){
            System.out.println("是社团成员");
        }else {
            System.out.println("不是社团成员");
        }
        return "postcomment/performanePost";
    }

    //添加帖子
    @PostMapping("/addPost")
    public String AddPost(HttpServletRequest request, HttpSession session){
        Post post=new Post();
        post.setTitle(request.getParameter("title"));
        post.setStatus(Integer.parseInt(request.getParameter("postType")));
        post.setDescription(request.getParameter("text"));
        User user = (User) session.getAttribute("user");
        post.setUserId(user.getId());
        int performaneId=(int)session.getAttribute("performaneId");
        post.setperformaneId(performaneId);
        System.out.println(post.toString());
        postServiceImp.addPost(post);
        //String uri="/post/postList(id="+performaneId+")";
        return "redirect:/post/postList?id="+performaneId;
    }

    //给帖子加精
    @RequestMapping("/addRecommend")
    public void addRecommend(HttpServletRequest request, HttpServletResponse response, Integer id,Integer flag){
        System.out.println(id+"     ajax传出"+flag);
        //flag为0就是取消加精  flag为1就是加精
        postServiceImp.addRecommend(id,flag);
    }

    //查看某个用户发布的所有帖子
    @GetMapping("/userPost")
    public String userAllPost(HttpSession session,Model model){
        User user = (User) session.getAttribute("user");
        Integer userId = user.getId();
        List<Post> postList = postServiceImp.FindAllUserPost(userId);
        model.addAttribute("postLists",postList);
        return "postcomment/myPost";
    }

    //添加评论
    @PostMapping("/addComment")
    public String addComment(HttpServletRequest request, HttpServletResponse response){
        Comment comment=new Comment();
        comment.setDescription(request.getParameter("comment"));
        int Pid = Integer.parseInt(request.getParameter("postId"));
        comment.setPostId(Pid);
        comment.setUserName(request.getParameter("userName"));
        postServiceImp.addComment(comment);
        return "redirect:/post/postInfo?id="+Pid;
    }
}
