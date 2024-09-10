package co.kr.hanbit;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SimpleRestController {
    @RequestMapping("/")
    public String hello(){
        return "hello <strong>BACKEND</strong>";
    }
    @RequestMapping("/bye")
    public String bye(){
        return "bye";
    }

    @RequestMapping("/article")
    public String createArticle(@RequestParam("title") String title,
                                @RequestParam("content")String content){
        return String.format("title=%s / content=%s", title,content);
    }
    @RequestMapping("/article2")
    public String createArticle2(@RequestParam("title") String title,
                                @RequestParam("content")String content){
        return String.format("title=%s / content=%s", title,content);
    }
    @RequestMapping("/article3")
    public String createArticle3(@RequestParam("title") String title,
                                 @RequestParam("content")String content){
        return String.format("title=%s / content=%s", title,content);
    }

}
