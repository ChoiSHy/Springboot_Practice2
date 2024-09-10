package co.kr.hanbit.Bookmark;

import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(path="/bookmark")
public class BookmarkAjaxRestController {
    private List<Bookmark> bookmarks = new ArrayList<>();

    @RequestMapping(method= RequestMethod.POST, path = "/register")
    public String registerBookmark(@RequestBody Bookmark bookmark){
        bookmarks.add(bookmark);
        return "registered";
    }
    @RequestMapping(method = RequestMethod.GET, path="/list")
    public List<Bookmark> getBookmarks(){
        return bookmarks;
    }


}
