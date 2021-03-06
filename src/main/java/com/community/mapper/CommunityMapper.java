package com.community.mapper;

import com.community.entity.Community;
import com.community.entity.User;
import com.community.pojo.CommunityUser;
import com.community.pojo.CommunityUserInfo;
import com.community.pojo.PostIsRecommend;
import com.github.pagehelper.Page;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface CommunityMapper {
    //分页查找社团总人数，社团名称，所属学校，大厅分页
    @Select("SELECT community_user.`community_id` as id,community.`name` as communityName,count(`community_id`)as personNum,school.`name` as schoolName FROM `community_user`,community,school where community_user.`community_id`=community.id and community.school_id=school.id and audit_status<>0 group by community.`name`,`community_id`,school_id ORDER BY schoolName DESC")
    public Page<CommunityUser> PageFindAllCommunityUser();

    //分页查找社团总人数，社团名称，所属学校，大厅分页
    @Select("SELECT community_user.`community_id` as id,community.`name` as communityName,count(`community_id`)as personNum,school.`name` as schoolName FROM `community_user`,community,school where community_user.`community_id`=community.id and community.school_id=school.id and audit_status<>0 group by community.`name`,`community_id`,school_id ORDER BY schoolName DESC")
    public List<CommunityUser> FindAllCommunityUser();

    //查看社团信息
    @Select("select * from community where id=#{id}")
    public Community FindCommunityInfoById(Integer id);

    //查找精品贴(通过ID查找，并且判断是否失效)
    @Select("select id,title from post where community_id=#{id} and is_recommend<>0")
    public List<PostIsRecommend> FindPostIsRecommendById(Integer id);

    //查看社团成员  (05-13)
    @Select("select user.id as userId,user_name as userName,number_level as numberLevel,level_name as levelName from user,community_user as cu\n" +
            "where user.id=cu.user_number_id and community_id=#{id} and audit_status=1")
    public List<CommunityUserInfo> FindUserByCommunityId(Integer id);

    //查看待审核成员
    @Select("select user.* from user,community_user where user.id=community_user.user_number_id and community_id=#{id} and audit_status=0")
    public List<User> FindAuditUserByCommunityId(Integer id);

    //查看当前用户是否是社团成员
    @Select("SELECT u.school_name,u.user_name,c.`name`,cu.audit_status FROM community_user cu INNER JOIN community c on c.id=cu.community_id\n" +
            "INNER JOIN `user` u on u.id=cu.user_number_id WHERE c.id=#{communityId} and u.id=#{userId}")
    public CommunityUser FindNumberByUserId(@Param("communityId") Integer communityId,@Param("userId") Integer userId);

    //通过学校名查看社团
    @Select("SELECT community_user.`community_id` as id,community.`name` as communityName,count(`community_id`)as personNum,school.`name` \n"+
            "as schoolName FROM `community_user`,community,school where community_user.`community_id`=community.id and community.school_id=school.id \n"+
            "and audit_status=1 and school_name like CONCAT('%',#{Sname},'%') group by community.`name`,`community_id`,school_id ORDER BY schoolName DESC")
    public Page<CommunityUser> FindCommunityBySchoolName(String Sname);

    //查看用户级别
    @Select("select number_level as level from community_user where user_number_id=#{Uid} and community_id=#{Cid}")
    public String FindUserLevel(Integer Uid,Integer Cid);

    //更新用户权限
    @Update("update community_user set number_level=#{level},level_name=#{name} where user_number_id=#{Uid} and community_id=#{Cid}")
    public int UpdatePer(Integer Cid,Integer Uid,Integer level,String name);

    //删除社员
    @Delete("delete from community_user where community_id=#{Cid} and user_number_id=#{Uid}")
    public int DeleteUser(Integer Cid,Integer Uid);

    //同意加入
    @Update("update community_user set audit_status=1,number_level=3 where community_id=#{Cid} and user_number_id=#{Uid}")
    public int UpdateStatus(Integer Cid,Integer Uid);

    //模糊查询社员
    @Select("select user.id as userId,user_name as userName,number_level as numberLevel,level_name as levelName from user,community_user \n"+
            "as cu where user.id=cu.user_number_id and community_id=#{Cid} and audit_status=1 and user_name like CONCAT('%',#{Uname},'%')")
    public List<CommunityUserInfo> SelectUserByCidUname(Integer Cid,String Uname);

    //模糊查询社团
    //分页查找社团总人数，社团名称，所属学校，大厅分页
    @Select("SELECT community_user.`community_id` as id,community.`name` as communityName,count(`community_id`)as personNum,school.`name` \n"+
            "as schoolName FROM `community_user`,community,school where community_user.`community_id`=community.id and community.school_id=school.id \n"+
            "and audit_status=1 and community.name like CONCAT('%',#{Cname},'%') group by community.`name`,`community_id`,school_id ORDER BY schoolName DESC")
    public Page<CommunityUser> PageFuzzyCommunity(String Cname);

    //根据学校名查找学校id
    @Select("select id from school where name=#{schoolName}")
    public String SelectSchoolId(String schoolName);

    //创建社团
    @Insert("insert into community(name,user_teacher_id,school_id,school_name) values(#{name},#{userTeacherId},#{schoolId},#{schoolName})")
    public int insertCommunity(Community community);

    //通过社团名查找社团id
    @Select("select id from community where name=#{Cname}")
    public String SelectCommunityId(String Cname);

    //通过社团名查找社团id
    @Select("select id from user where user_name=#{Uname}")
    public String SelectUserId(String Uname);

    //插入社长
    @Insert("insert into community_user(community_id,user_number_id,number_level,level_name,activity_permission,post_permission,audit_status) \n"+
            "values(#{Cid},#{Uid},0,'社长',1,0,1)")
    public int InsertCommunityUser(Integer Cid,Integer Uid);
}
