package com.community.mapper;

import com.community.entity.User;
import com.community.pojo.UserJoinedCommunity;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface UserMapper {
    //查找用户
    @Select("select * from user where user_name=#{userName}")
    public User FindUserByUserName(@Param("userName") String userName);
    //用户注册
    @Insert("insert into user(user_name,name,password,flag,sex)values(#{userName},#{name},#{password},#{flag},#{sex})")
    public int InsertUser(User user);
    //修改个人资料
    @Update("update user set name=#{name},phone=#{phone},address=#{address},style_signature=#{styleSignature},email=#{email},birth=#{birth},sex=#{sex},image=#{image} where user_name=#{userName}")
    public int UpdateUser(User user);

    //用户加入社团,状态待审核
    @Insert("insert into community_user (community_id,user_number_id,audit_status) values(#{communityId},#{userId},0)")
    public int InsertCommunityUser(@Param("communityId") Integer communityId,@Param("userId") Integer userId);

    //查找用户加入的社团信息
    @Select("select community_id,number_level,`name` as communityName,level_name,audit_status from community_user \n"+
            "as cu,community as c where cu.community_id=c.id and user_number_id=#{Uid}")
    public List<UserJoinedCommunity> findJoinedCommunity(Integer Uid);

}
