package com.performane.mapper;

import com.performane.entity.Comment;
import com.performane.entity.Post;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface PostMapper {
    //查看精品贴详细
    @Select("select * from post where id=#{id}")
    public Post FindPostInfoById(Integer id);

    //查看帖子评论(05-12)
    @Select("select * from comment where post_id=#{id}")
    public List<Comment> FindCommentByPostId(Integer id);

    //查看某个社团所有帖子列表（05-13）
    @Select("select * from post where performane_id=#{id}")
    public List<Post> FindPostListById(Integer id);

    //添加帖子
    @Insert("insert into post(title,status,description,performane_id,user_id,is_recommend) values(#{title},#{status},#{description},#{performaneId},#{userId},0)")
    public int insertPost(Post post);

    //为帖子加精
    @Update("update post set is_recommend=#{flag} where id=#{Pid}")
    public int updateRecommend(Integer Pid,Integer flag);

    //根据某个社员查找帖子
    @Select("SELECT post.*,c.`name` from post inner JOIN `user` u on u.id = post.user_id \n" +
            "inner JOIN `performane` c on c.id=post.performane_id\n" +
            "where u.id=#{userId}")
    public List<Post> FinAllPostByUserId(Integer userId);

    //添加评论
    @Insert("insert into comment(description,post_id,user_name) values(#{description},#{postId},#{userName})")
    public int insertComment(Comment comment);
}
