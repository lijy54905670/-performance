package com.performane.service;

import com.performane.entity.performane;
import com.performane.entity.User;
import com.performane.mapper.performaneMapper;
import com.performane.pojo.performaneUser;
import com.performane.pojo.performaneUserInfo;
import com.performane.pojo.PostIsRecommend;
import com.performane.service.serviceInterface.performaneService;
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
public class performaneServiceImp implements performaneService {
    @Autowired
    private performaneMapper performaneMapper;
    @Override
    //社团大厅分页
    public void PageFindAllperformaneUser(Model model,Integer pageNum) {
        if (pageNum==null){
            pageNum=1;
        }

        //计算最大页数
        List<performaneUser> performaneUserList = performaneMapper.FindAllperformaneUser();
        int total = performaneUserList.size();
        int maxPage = (total%8)>0?((total/8)+1):(total/8);
        model.addAttribute("maxPage",maxPage);


        PageHelper.startPage(pageNum,8);
        Page<performaneUser> performaneUserLists=performaneMapper.PageFindAllperformaneUser();

        Iterator<performaneUser> iterator =performaneUserLists.iterator();
        while(iterator.hasNext()){
            System.out.println(iterator.next().toString());
        }

        model.addAttribute("performaneUserLists",performaneUserLists);
        model.addAttribute("pageNum",pageNum);

    }


    //查看社团
    @Override
    public void ShowperformaneInfoById(Model model, Integer id,HttpSession session) {
        User user = (User) session.getAttribute("user");
        Integer userId = user.getId();
        //获取社团信息
        performane performane=performaneMapper.FindperformaneInfoById(id);
        //查看当前用户是否属于社团
        performaneUser performaneUser = performaneMapper.FindNumberByUserId(id,userId);
        if (performaneUser==null){
            //不属于当前社团
            //查看当前用户学校和社团学校是否一致
            String userSchool = user.getSchoolName();
            String performaneSchool = performane.getSchoolName();
            if ((userSchool!=null)&&userSchool.equals(performaneSchool)){
                model.addAttribute("isSchool",true);
            }else {
                model.addAttribute("isSchool",false);
            }
            model.addAttribute("isJoin",false);
        }else {
            //存在的情况下判断是待审核还是已经是社团成员
            if (performaneUser.getAuditStatus()==0){
                model.addAttribute("isAudit",true);
                model.addAttribute("isJoin",false);
            }else {
                model.addAttribute("isAudit",false);
                model.addAttribute("isJoin",true);
            }
        }



        if(performane!=null){
            session.setAttribute("performaneName",performane.getName());
            model.addAttribute("performaneInfo",performane);
        }

        //精品贴（0512）
        List<PostIsRecommend> postIsRecommends=performaneMapper.FindPostIsRecommendById(id);
        if(!postIsRecommends.isEmpty()){
            model.addAttribute("postIsRecommend",postIsRecommends);
        }
    }

    //查看社团成员（05-13）
    @Override
    public void ShowUserListByperformaneId(Model model, Integer id) {
        //已加入成员
        List<performaneUserInfo> userList = performaneMapper.FindUserByperformaneId(id);
        //待审核成员
        List<User> auditUserList = performaneMapper.FindAuditUserByperformaneId(id);
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
    public void ShowperformaneBySchoolName(Model model,Integer pageNum, String Sname) {
        if (pageNum==null){
            pageNum=1;
        }

        //计算最大页数
        List<performaneUser> performaneUserList = performaneMapper.FindAllperformaneUser();
        int total = performaneUserList.size();
        int maxPage = (total%8)>0?((total/8)+1):(total/8);
        model.addAttribute("maxPage",maxPage);

        PageHelper.startPage(pageNum,8);
        Page<performaneUser> performaneUserLists=performaneMapper.FindperformaneBySchoolName(Sname);
        System.out.println("通过学校名查询使用的学校名"+Sname);
        Iterator<performaneUser> iterator =performaneUserLists.iterator();
        while(iterator.hasNext()){
            System.out.println(iterator.next().toString());
        }
        model.addAttribute("performaneUserLists",performaneUserLists);
        model.addAttribute("pageNum",pageNum);
    }

    //判断是否为社团成员 查看成员等级
    @Override
    public void ShowUserLevel(Integer Uname, Integer Cid,HttpSession session,Model model) {
        String Str = performaneMapper.FindUserLevel(Uname, Cid);
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
        int i=performaneMapper.UpdatePer(Cid,Uid,level,name);
        if(i>0){
            System.out.println("权限设置成功");
        }
    }

    @Override
    public void DeleteUser(Integer Cid, Integer Uid) {
        int i=performaneMapper.DeleteUser(Cid,Uid);
        if(i>0){
            System.out.println("删除成功");
        }else {
            System.out.println("删除失败");
        }
    }

    @Override
    public void AgreeJoin(Integer Cid, Integer Uid) {
        int i=performaneMapper.UpdateStatus(Cid,Uid);
        if(i>0){
            System.out.println("加入成功");
        }else {
            System.out.println("加入失败");
        }
    }

    @Override
    public void FindUserByCidUname(Integer Cid, String Uname, Model model) {
        List<performaneUserInfo> performaneUserInfos=performaneMapper.SelectUserByCidUname(Cid,Uname);
        if(!performaneUserInfos.isEmpty()){
            model.addAttribute("searchUserList",performaneUserInfos);
            model.addAttribute("flag",1);

            Iterator<performaneUserInfo> iterator=performaneUserInfos.iterator();
            while(iterator.hasNext()){
                System.out.println(iterator.next().toString());
            }
        }
    }

    @Override
    public void PageFuzzyperformane(Model model, Integer pageNum,String Cname) {
        if (pageNum==null){
            pageNum=1;
        }

        //计算最大页数
        List<performaneUser> performaneUserList = performaneMapper.FindAllperformaneUser();
        int total = performaneUserList.size();
        int maxPage = (total%8)>0?((total/8)+1):(total/8);
        model.addAttribute("maxPage",maxPage);

        PageHelper.startPage(pageNum,8);
        Page<performaneUser> performaneUserLists=performaneMapper.PageFuzzyperformane(Cname);

        Iterator<performaneUser> iterator =performaneUserLists.iterator();
        while(iterator.hasNext()){
            System.out.println(iterator.next().toString());
        }
        model.addAttribute("performaneUserLists",performaneUserLists);
        model.addAttribute("pageNum",pageNum);
    }

    @Override
    public int FindSchoolId(String schoolName) {
        String str=performaneMapper.SelectSchoolId(schoolName);
        if(str!=null) {
            int schoolId = Integer.parseInt(performaneMapper.SelectSchoolId(schoolName));
            return schoolId;
        }else{
            return 0;
        }
    }

    @Override
    public void Createperformane(performane performane) {
        int i=performaneMapper.insertperformane(performane);
        if(i>0){
            System.out.println("创建成功");
        }else {
            System.out.println("创建失败");
        }
    }

    @Override
    public int FindperformaneId(String Cname) {
        String str=performaneMapper.SelectperformaneId(Cname);
        if(str!=null){
            int Cid=Integer.parseInt(str);
            return Cid;
        }else {
            return 0;
        }
    }

    @Override
    public int FindUserId(String Uname) {
        String str=performaneMapper.SelectUserId(Uname);
        if(str!=null){
            int Uid=Integer.parseInt(str);
            return Uid;
        }else {
            return 0;
        }
    }

    @Override
    public void InsertperformaneUser(Integer Cid, Integer Uid) {
        int i=performaneMapper.InsertperformaneUser(Cid,Uid);
        if(i>0){
            System.out.println("插入成功");
        }else{
            System.out.println("插入失败");
        }
    }
}
