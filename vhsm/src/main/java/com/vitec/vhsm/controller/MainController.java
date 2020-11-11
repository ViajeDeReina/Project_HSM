package com.vitec.vhsm.controller;

import com.vitec.vhsm.domain.Hsmlog;
import com.vitec.vhsm.service.HsmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
public class MainController {

    private final HsmService hsmService;

    @Autowired
    public MainController(HsmService hsmService) {
        this.hsmService = hsmService;
    }

    @GetMapping("/index")
    public String index(Model model){
        List<Hsmlog> list = hsmService.findCmdProcessingRate();
        List<String> x_data = new ArrayList<>(); // 날짜데이터는 chart 1, 2 공통
        List<String> y_data = new ArrayList<>(); // CPU Usage
        List<Integer> transac_data = new ArrayList<>(); // transaction per 5 secs

        List<Hsmlog> lastTemp = hsmService.lastTemper();//LAST TEMPER STATE as 1 line
        String statname = lastTemp.get(0).getTemper_cause();
        String stattime = lastTemp.get(0).getTemper_date();

        int n = list.size();
        // 기본적으로 마지막 15개만 화면에 띄울건데, 에러 방지를 위해 15개 이하는 전체 DB가 반영되도록함.
        if (n >=15) {
            for(int i=n-15; i<n; i++){
                x_data.add(list.get(i).getSys_date());
                y_data.add(list.get(i).getCpu_usage());
                transac_data.add(list.get(i).getTransac());
            }
        }
        else {
            for(int i=0; i<n; i++){
                x_data.add(list.get(i).getSys_date());
                y_data.add(list.get(i).getCpu_usage());
                transac_data.add(list.get(i).getTransac());
            }
        }

        String temper_cause = list.get(n-1).getTemper_cause();
        if (temper_cause.equals("Unavailable")) {
            temper_cause = "";
        }

        /**
         * 스프링에서 html로 데이터를 보내려면 model에 데이터를 넘겨야함
         */
        model.addAttribute("user_id", getUserId());
        model.addAttribute("mode", list.get(n-1).getMode());
        model.addAttribute("temper", list.get(n-1).getTemper());
        model.addAttribute("temper_cause", temper_cause);
        // Chart 1 : CPU Usage (%)
        model.addAttribute("x_data", x_data);
        model.addAttribute("y_data", y_data);
        // Chart 2 : Transaction per sec
        model.addAttribute("transac_data", transac_data);
        // Latest Temper state
        model.addAttribute("statname", statname);
        model.addAttribute("stattime", stattime);
        return "index";
    }

    @GetMapping("/systemlog")
    public String systemlog(Model model){
        model.addAttribute("user_id", getUserId());
        List<Hsmlog> hsmlog = hsmService.findHsmLogLimit(50);//여기에서 사용자 입력을 받아보자.
        model.addAttribute("hsmlog", hsmlog);
        return "systemlog";
    }
    //이걸 아마 매개변수로 받는 것 같은데 재확인 할 것. @RequestParam(name="logcounts") String logcounts,

    // * 이런 로직으로 가야할 것 같은데.... (이걸로 했을 때 오류 페이지가 뜨진 않지만,,, 그냥 systemlog 로 연결, 50개가 여전히 뜸
    @GetMapping("/syslog")
    public String syslog(@RequestParam(value="logcounts") String logcounts, Model model){
        model.addAttribute("user_id", getUserId());
        int logcountsInt = Integer.parseInt(logcounts);
        List<Hsmlog> hsmlog = hsmService.findHsmLogLimit(logcountsInt);//여기에서 사용자 입력을 받아보자.
        model.addAttribute("hsmlog", hsmlog);
        return "syslog";
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public void handle(Exception e) {
        System.out.println(e);
    }

    @GetMapping("/infohsm")
    public String infohsm(Model model){
        model.addAttribute("user_id", getUserId());
        return "infohsm";
    }

    @GetMapping("/register")
    public String register(Model model){
        model.addAttribute("user_id", getUserId());
        return "register";
    }

    @GetMapping("/newequip")
    public String newequip(Model model){
        model.addAttribute("user_id", getUserId());
        return "newequip";
    }


    public String getUserId(){
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        UserDetails userDetails = (UserDetails)principal;
        return userDetails.getUsername();
    }

    /*

    // Don't erase
    @GetMapping("hello-mvc")
    public String helloMvc(@RequestParam("name") String name, Model model){
        model.addAttribute("name",name);
        return "hello-template";
    }
    */

}
