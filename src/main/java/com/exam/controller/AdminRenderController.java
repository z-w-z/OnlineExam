package com.exam.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.exam.model.BizCategory;
import com.exam.service.BizCategoryService;
import com.exam.service.SysConfigService;
import com.exam.util.CoreConst;


@Controller
public class AdminRenderController {
    @Autowired
    private BizCategoryService categoryService;
    @Autowired
    private SysConfigService sysConfigService;
    /*网站基本信息*/
    @GetMapping("/siteinfo")
    public String siteinfo(Model model){
        Map<String, String> map = sysConfigService.selectAll();
        model.addAttribute("siteinfo", map);
        return "site/siteinfo";
    }
    /*友情链接*/
    @GetMapping("/links")
    public String links(){
        return "link/list";
    }
    /*分类*/
    @GetMapping("/categories")
    public String categories(){
        return "category/list";
    }
    /*标签*/
    @GetMapping("/tags")
    public String tags(){
        return "tag/list";
    }

    /*文章*/
    @GetMapping("/articles")
    public String articles(Model model){
        BizCategory bizCategory = new BizCategory();
        bizCategory.setStatus(CoreConst.STATUS_VALID);
        List<BizCategory> categories = categoryService.select(bizCategory);
        model.addAttribute("categories",categories);
        return "article/list";
    }
    /*评论*/
    @GetMapping("/comments")
    public String comments(){
        return "comment/list";
    }

    @GetMapping("themes")
    public String themes(){
        return "systheme/list";
    }

}
