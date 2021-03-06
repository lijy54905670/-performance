package com.performane.mapper;

import com.performane.entity.performane;
import com.performane.entity.User;
import com.performane.pojo.performaneUser;
import com.performane.pojo.performaneUserInfo;
import com.performane.pojo.PostIsRecommend;
import com.github.pagehelper.Page;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface performaneMapper {
    //分页查找社团总人数，社团名称，所属学校，大厅分页
    @Select("SELECT performane_user.`performane_id` as id,performane.`name` as performaneName,count(`performane_id`)as personNum,school.`name` as schoolName FROM `performane_user`,performane,school where performane_user.`performane_id`=performane.id and performane.school_id=school.id and audit_status<>0 group by performane.`name`,`performane_id`,school_id ORDER BY schoolName DESC")
    public Page<performaneUser> PageFindAllperformaneUser();

    //分页查找社团总人数，社团名称，所属学校，大厅分页
    @Select("SELECT performane_user.`performane_id` as id,performane.`name` as performaneName,count(`performane_id`)as personNum,school.`name` as schoolName FROM `performane_user`,performane,school where performane_user.`performane_id`=performane.id and performane.school_id=school.id and audit_status<>0 group by performane.`name`,`performane_id`,school_id ORDER BY schoolName DESC")
    public List<performaneUser> FindAllperformaneUser();

    //查看社团信息
    @Select("select * from performane where id=#{id}")
    public performane FindperformaneInfoById(Integer id);

    //查找精品贴(通过ID查找，并且判断是否失效)
    @Select("select id,title from post where performane_id=#{id} and is_recommend<>0")
    public List<PostIsRecommend> FindPostIsRecommendById(Integer id);

    //查看社团成员  (05-13)
    @Select("select user.id as userId,user_name as userName,number_level as numberLevel,level_name as levelName from user,performane_user as cu\n" +
            "where user.id=cu.user_number_id and performane_id=#{id} and audit_status=1")
    public List<performaneUserInfo> FindUserByperformaneId(Integer id);

    //查看待审核成员
    @Select("select user.* from user,performane_user where user.id=performane_user.user_number_id and performane_id=#{id} and audit_status=0")
    public List<User> FindAuditUserByperformaneId(Integer id);

    //查看当前用户是否是社团成员
    @Select("SELECT u.school_name,u.user_name,c.`name`,cu.audit_status FROM performane_user cu INNER JOIN performane c on c.id=cu.performane_id\n" +
            "INNER JOIN `user` u on u.id=cu.user_number_id WHERE c.id=#{performaneId} and u.id=#{userId}")
    public performaneUser FindNumberByUserId(@Param("performaneId") Integer performaneId,@Param("userId") Integer userId);

    //通过学校名查看社团
    @Select("SELECT performane_user.`performane_id` as id,performane.`name` as performaneName,count(`performane_id`)as personNum,school.`name` \n"+
            "as schoolName FROM `performane_user`,performane,school where performane_user.`performane_id`=performane.id and performane.school_id=school.id \n"+
            "and audit_status=1 and school_name like CONCAT('%',#{Sname},'%') group by performane.`name`,`performane_id`,school_id ORDER BY schoolName DESC")
    public Page<performaneUser> FindperformaneBySchoolName(String Sname);

    //查看用户级别
    @Select("select number_level as level from performane_user where user_number_id=#{Uid} and performane_id=#{Cid}")
    public String FindUserLevel(Integer Uid,Integer Cid);

    //更新用户权限
    @Update("update performane_user set number_level=#{level},level_name=#{name} where user_number_id=#{Uid} and performane_id=#{Cid}")
    public int UpdatePer(Integer Cid,Integer Uid,Integer level,String name);

    //删除社员
    @Delete("delete from performane_user where performane_id=#{Cid} and user_number_id=#{Uid}")
    public int DeleteUser(Integer Cid,Integer Uid);

    //同意加入
    @Update("update performane_user set audit_status=1,number_level=3 where performane_id=#{Cid} and user_number_id=#{Uid}")
    public int UpdateStatus(Integer Cid,Integer Uid);

    //模糊查询社员
    @Select("select user.id as userId,user_name as userName,number_level as numberLevel,level_name as levelName from user,performane_user \n"+
            "as cu where user.id=cu.user_number_id and performane_id=#{Cid} and audit_status=1 and user_name like CONCAT('%',#{Uname},'%')")
    public List<performaneUserInfo> SelectUserByCidUname(Integer Cid,String Uname);

    //模糊查询社团
    //分页查找社团总人数，社团名称，所属学校，大厅分页
    @Select("SELECT performane_user.`performane_id` as id,performane.`name` as performaneName,count(`performane_id`)as personNum,school.`name` \n"+
            "as schoolName FROM `performane_user`,performane,school where performane_user.`performane_id`=performane.id and performane.school_id=school.id \n"+
            "and audit_status=1 and performane.name like CONCAT('%',#{Cname},'%') group by performane.`name`,`performane_id`,school_id ORDER BY schoolName DESC")
    public Page<performaneUser> PageFuzzyperformane(String Cname);

    //根据学校名查找学校id
    @Select("select id from school where name=#{schoolName}")
    public String SelectSchoolId(String schoolName);

    //创建社团
    @Insert("insert into performane(name,user_teacher_id,school_id,school_name) values(#{name},#{userTeacherId},#{schoolId},#{schoolName})")
    public int insertperformane(performane performane);

    //通过社团名查找社团id
    @Select("select id from performane where name=#{Cname}")
    public String SelectperformaneId(String Cname);

    //通过社团名查找社团id
    @Select("select id from user where user_name=#{Uname}")
    public String SelectUserId(String Uname);

    //插入社长
    @Insert("insert into performane_user(performane_id,user_number_id,number_level,level_name,activity_permission,post_permission,audit_status) \n"+
            "values(#{Cid},#{Uid},0,'社长',1,0,1)")
    public int InsertperformaneUser(Integer Cid,Integer Uid);
}
