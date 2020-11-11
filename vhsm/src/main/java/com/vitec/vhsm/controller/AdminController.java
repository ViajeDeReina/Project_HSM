package com.vitec.vhsm.controller;

import com.sun.deploy.net.HttpResponse;
import com.vitec.vhsm.domain.Admin;
import com.vitec.vhsm.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Controller
public class AdminController {
    private final AdminService adminService;

    @Autowired
    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    @GetMapping("/newuser")
    public String newuser(Model model){
        model.addAttribute("user_id", getUserId());
        return "newuser";
    }

    // 관리자 정보 전체를 findAll 메서드로 불러와서 모두 화면에 렌더링함
    @GetMapping("/infouser")
    public String infouser(Model model){
        model.addAttribute("user_id", getUserId());
        List<Admin> admins = adminService.findMembers();
        model.addAttribute("admins", admins);
        return "infouser";
    }

    // 관리자 정보를 memberId 기준으로 찾아와서 화면에 렌더링
    @GetMapping("/findmemberid")
    public String findmemberid(@RequestParam(value="user_id") String user_id, @RequestParam(value="name") String name, Model model) {
        model.addAttribute("user_id", getUserId());
        Optional<Admin> adminsID = adminService.searchMember(user_id, name);
        model.addAttribute("adminsId", adminsID);
        return "redirectinfouser.html";
    }


    @PostMapping("/adduser")
    public String adduser(@RequestParam("adminId") String id,
                          @RequestParam("password") String password,
                          @RequestParam("password2") String password2,
                          @RequestParam("name") String name,
                          @RequestParam("department") String department,
                          @RequestParam("mobile") String mobile,
                          @RequestParam("email") String email,
                          @RequestParam("use") Boolean use,
                          @RequestParam("approval") String approval,
                          @RequestParam("registeredDate") String registeredDate) {

        if(password.equals(password2)){
            adminService.join(new Admin(id,password,name,department,mobile,email,use,approval,registeredDate));
            return "redirectinfouser.html";//infouser로 redirect 되기는 한데, 화면 빈칸이 나옴. ㅠㅠ 추후 수정할것
        }
        else{
            return "errorpage";
        }
    }

    @PostMapping("/signup")
    public String signup(@RequestParam("user_id") String id,
                         @RequestParam("password") String password,
                         @RequestParam("password_check") String password_check,
                         @RequestParam("name") String name,
                         @RequestParam("department") String department,
                         @RequestParam("mobile") String mobile,
                         @RequestParam("email") String email,
                         @RequestParam("use") Boolean use){

        if(password.equals(password_check)) {
            SimpleDateFormat format1 = new SimpleDateFormat ( "yyyy-MM-dd HH:mm:ss");
            Date time = new Date();
            String str_time = format1.format(time);
            String admin_id = getUserId();

            adminService.join(new Admin(id, password, name, department,mobile,email,use,name,str_time));
        }
        return "redirect:/index";
    }

    public String getUserId(){
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        UserDetails userDetails = (UserDetails)principal;
        return userDetails.getUsername();
    }
}
