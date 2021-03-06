package com.performane.service.serviceInterface;

import com.performane.entity.Comment;
import com.performane.entity.Post;
import org.springframework.ui.Model;

import java.util.List;


public interface PostService {

    //查看精品贴详细（05-12）
    public void ShowPostInfoById(Model model, Integer id);

    //查看帖子评论（05-12）
    public void ShowCommentInfoByPostId(Model model, Integer id);

    //显示社团所有帖子列表(05-13)
    public void ShowPostListById(Model model, Integer id);

    //创建新的帖子
    public void addPost(Post post);

    //为帖子加精或取消
    public void addRecommend(Integer Pid,Integer flag);

    //查找当前用户所有帖子
    public List<Post> FindAllUserPost(Integer userId);

    //添加评论
    public void addComment(Comment comment);
}
