package com.community.service;

import com.community.entity.Community;
import com.community.entity.User;
import com.community.mapper.CommunityMapper;
import com.community.pojo.CommunityUser;
import com.community.pojo.CommunityUserInfo;
import com.community.pojo.PostIsRecommend;
import com.community.service.serviceInterface.CommunityService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Iterator;
import java.util.List;

@Transactional
@Service
public class CommunityServiceImp implements CommunityService {
    @Autowired
    private CommunityMapper communityMapper;
    @Override
    //社团大厅分页
    public void PageFindAllCommunityUser(Model model,Integer pageNum) {
        if (pageNum==null){
            pageNum=1;
        }

        //计算最大页数
        List<CommunityUser> communityUserList = communityMapper.FindAllCommunityUser();
        int total = communityUserList.size();
        int maxPage = (total%8)>0?((total/8)+1):(total/8);
        model.addAttribute("maxPage",maxPage);


        PageHelper.startPage(pageNum,8);
        Page<CommunityUser> communityUserLists=communityMapper.PageFindAllCommunityUser();

        Iterator<CommunityUser> iterator =communityUserLists.iterator();
        while(iterator.hasNext()){
            System.out.println(iterator.next().toString());
        }

        model.addAttribute("communityUserLists",communityUserLists);
        model.addAttribute("pageNum",pageNum);

    }


    //查看社团
    @Override
    public void ShowCommunityInfoById(Model model, Integer id,HttpSession session) {
        User user = (User) session.getAttribute("user");
        Integer userId = user.getId();
        //获取社团信息
        Community community=communityMapper.FindCommunityInfoById(id);
        //查看当前用户是否属于社团
        CommunityUser communityUser = communityMapper.FindNumberByUserId(id,userId);
        if (communityUser==null){
            //不属于当前社团
            //查看当前用户学校和社团学校是否一致
            String userSchool = user.getSchoolName();
            String communitySchool = community.getSchoolName();
            if ((userSchool!=null)&&userSchool.equals(communitySchool)){
                model.addAttribute("isSchool",true);
            }else {
                model.addAttribute("isSchool",false);
            }
            model.addAttribute("isJoin",false);
        }else {
            //存在的情况下判断是待审核还是已经是社团成员
            if (communityUser.getAuditStatus()==0){
                model.addAttribute("isAudit",true);
                model.addAttribute("isJoin",false);
            }else {
                model.addAttribute("isAudit",false);
                model.addAttribute("isJoin",true);
            }
        }



        if(community!=null){
            session.setAttribute("communityName",community.getName());
            model.addAttribute("communityInfo",community);
        }

        //精品贴（0512）
        List<PostIsRecommend> postIsRecommends=communityMapper.FindPostIsRecommendById(id);
        if(!postIsRecommends.isEmpty()){
            model.addAttribute("postIsRecommend",postIsRecommends);
        }
    }

    //查看社团成员（05-13）
    @Override
    public void ShowUserListByCommunityId(Model model, Integer id) {
        //已加入成员
        List<CommunityUserInfo> userList = communityMapper.FindUserByCommunityId(id);
        //待审核成员
        List<User> auditUserList = communityMapper.FindAuditUserByCommunityId(id);
        if(!userList.isEmpty()){
            model.addAttribute("userList",userList);
            model.addAttribute("auditUserList",auditUserList);
            model.addAttribute("flag",0);
            /*Iterator<User> iterator= userList.iterator();
            while(iterator.hasNext()){
                System.out.println(iterator.next().toString());
            }*/
        }
    }

    // 同省市县查询社团
    @Override
    public void ShowCommunityBySchoolName(Model model,Integer pageNum, String Sname) {
        if (pageNum==null){
            pageNum=1;
        }

        //计算最大页数
        List<CommunityUser> communityUserList = communityMapper.FindAllCommunityUser();
        int total = communityUserList.size();
        int maxPage = (total%8)>0?((total/8)+1):(total/8);
        model.addAttribute("maxPage",maxPage);

        PageHelper.startPage(pageNum,8);
        Page<CommunityUser> communityUserLists=communityMapper.FindCommunityBySchoolName(Sname);
        System.out.println("通过学校名查询使用的学校名"+Sname);
        Iterator<CommunityUser> iterator =communityUserLists.iterator();
        while(iterator.hasNext()){
            System.out.println(iterator.next().toString());
        }
        model.addAttribute("communityUserLists",communityUserLists);
        model.addAttribute("pageNum",pageNum);
    }

    //判断是否为社团成员 查看成员等级
    @Override
    public void ShowUserLevel(Integer Uname, Integer Cid,HttpSession session,Model model) {
        String Str = communityMapper.FindUserLevel(Uname, Cid);
        int level =4;
        boolean isMember = false;
        if (Str != null) {
            level = Integer.parseInt(Str);
            isMember = true;
        }else {
            level=4;
        }
        session.setAttribute("level", level);
        session.setAttribute("isMember", isMember);
        System.out.println("用户等级：" + level);
    }

    @Override
    public void SetPermission(Integer Cid, Integer Uid, Integer level, String name) {
        //如果没有给成员分配角色名称，则默认使用部长，副部长，社员代替
        if(name==""){
            if(level==1){
                name="部长";
            }else if(level==2){
                name="副部长";
            }else {
                name="社员";
            }
        }
        int i=communityMapper.UpdatePer(Cid,Uid,level,name);
        if(i>0){
            System.out.println("权限设置成功");
        }
    }

    @Override
    public void DeleteUser(Integer Cid, Integer Uid) {
        int i=communityMapper.DeleteUser(Cid,Uid);
        if(i>0){
            System.out.println("删除成功");
        }else {
            System.out.println("删除失败");
        }
    }

    @Override
    public void AgreeJoin(Integer Cid, Integer Uid) {
        int i=communityMapper.UpdateStatus(Cid,Uid);
        if(i>0){
            System.out.println("加入成功");
        }else {
            System.out.println("加入失败");
        }
    }

    @Override
    public void FindUserByCidUname(Integer Cid, String Uname, Model model) {
        List<CommunityUserInfo> communityUserInfos=communityMapper.SelectUserByCidUname(Cid,Uname);
        if(!communityUserInfos.isEmpty()){
            model.addAttribute("searchUserList",communityUserInfos);
            model.addAttribute("flag",1);

            Iterator<CommunityUserInfo> iterator=communityUserInfos.iterator();
            while(iterator.hasNext()){
                System.out.println(iterator.next().toString());
            }
        }
    }

    @Override
    public void PageFuzzyCommunity(Model model, Integer pageNum,String Cname) {
        if (pageNum==null){
            pageNum=1;
        }

        //计算最大页数
        List<CommunityUser> communityUserList = communityMapper.FindAllCommunityUser();
        int total = communityUserList.size();
        int maxPage = (total%8)>0?((total/8)+1):(total/8);
        model.addAttribute("maxPage",maxPage);

        PageHelper.startPage(pageNum,8);
        Page<CommunityUser> communityUserLists=communityMapper.PageFuzzyCommunity(Cname);

        Iterator<CommunityUser> iterator =communityUserLists.iterator();
        while(iterator.hasNext()){
            System.out.println(iterator.next().toString());
        }
        model.addAttribute("communityUserLists",communityUserLists);
        model.addAttribute("pageNum",pageNum);
    }

    @Override
    public int FindSchoolId(String schoolName) {
        String str=communityMapper.SelectSchoolId(schoolName);
        if(str!=null) {
            int schoolId = Integer.parseInt(communityMapper.SelectSchoolId(schoolName));
            return schoolId;
        }else{
            return 0;
        }
    }

    @Override
    public void CreateCommunity(Community community) {
        int i=communityMapper.insertCommunity(community);
        if(i>0){
            System.out.println("创建成功");
        }else {
            System.out.println("创建失败");
        }
    }

    @Override
    public int FindCommunityId(String Cname) {
        String str=communityMapper.SelectCommunityId(Cname);
        if(str!=null){
            int Cid=Integer.parseInt(str);
            return Cid;
        }else {
            return 0;
        }
    }

    @Override
    public int FindUserId(String Uname) {
        String str=communityMapper.SelectUserId(Uname);
        if(str!=null){
            int Uid=Integer.parseInt(str);
            return Uid;
        }else {
            return 0;
        }
    }

    @Override
    public void InsertCommunityUser(Integer Cid, Integer Uid) {
        int i=communityMapper.InsertCommunityUser(Cid,Uid);
        if(i>0){
            System.out.println("插入成功");
        }else{
            System.out.println("插入失败");
        }
    }
}
