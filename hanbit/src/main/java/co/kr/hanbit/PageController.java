package co.kr.hanbit;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class PageController {
    @RequestMapping("/writePage")
    public String writePage(){
        return "6-4-1.html";
    }
    @RequestMapping("/writePage2")
    public String writePage2(){
        return "6-4-2.html";
    }
    @RequestMapping("/writePage3")
    public String writePage3(){
        return "6-4-3.html";
    }
    @RequestMapping("/no-param-ajax")
    public String ajaxPage1(){
        return "7-3-1.html";
    }
    @RequestMapping("/bookmark")
    public String bookmarkPage(){
        return "7-3-2.html";
    }
}
