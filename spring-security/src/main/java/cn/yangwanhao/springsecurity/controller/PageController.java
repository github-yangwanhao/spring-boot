package cn.yangwanhao.springsecurity.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class PageController {

    @RequestMapping("/")
    public String toLogin() {
        return "login";
    }

    @RequestMapping("/page/{name}")
    public String goToPage(@PathVariable("name") String pageName) {
        return pageName;
    }
}
