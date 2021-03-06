package com.community.service;

import com.community.entity.Comment;
import com.community.entity.Post;
import com.community.mapper.PostMapper;
import com.community.service.serviceInterface.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;

import java.util.Iterator;
import java.util.List;

@Transactional
@Service
public class PostServiceImp implements PostService {
    @Autowired
    private PostMapper postMapper;
    @Override
    public void ShowPostInfoById(Model model, Integer id) {
        Post post = postMapper.FindPostInfoById(id);
        if(post!=null){
            model.addAttribute("postInfo",post);
            System.out.println(post.toString());
        }
    }
    //查看帖子评论
    @Override
    public void ShowCommentInfoByPostId(Model model, Integer id) {
        List<Comment> comments=postMapper.FindCommentByPostId(id);
        if(!comments.isEmpty()){
           model.addAttribute("commentInfo",comments);
        }
    }

    @Override
    public void ShowPostListById(Model model, Integer id) {
        List<Post> postList =postMapper.FindPostListById(id);
        if(!postList.isEmpty()){

            model.addAttribute("postLists",postList);

            /*Iterator<Post> iterator=postList.iterator();
            while(iterator.hasNext()){
                System.out.println(iterator.next().toString());
            }*/
        }
    }

    //创建帖子
    @Override
    public void addPost(Post post) {
        int i=postMapper.insertPost(post);
        if(i>0){
            System.out.println("添加成功");
        }else{
            System.out.println("添加失败");
        }
    }

    @Override
    public void addRecommend(Integer Pid, Integer flag) {
        int i=postMapper.updateRecommend(Pid,flag);
        if(i>0){
            System.out.println("加精或取消成功");
        }else {
            System.out.println("加精或取消失败");
        }
    }

    @Override
    public List<Post> FindAllUserPost(Integer userId) {
        List<Post> postList = postMapper.FinAllPostByUserId(userId);
        return postList;
    }

    @Override
    public void addComment(Comment comment) {
        int i=postMapper.insertComment(comment);
        if(i>0){
            System.out.println("添加成功");
        }else {
            System.out.println("添加失败");
        }
    }
}
