package com.exam.controller;

import java.util.ArrayList;
import java.util.List;

import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.exam.enmus.SysConfigKey;
import com.exam.model.BizArticle;
import com.exam.model.BizCategory;
import com.exam.model.BizTags;
import com.exam.model.User;
import com.exam.service.BizArticleService;
import com.exam.service.BizArticleTagsService;
import com.exam.service.BizCategoryService;
import com.exam.service.BizTagsService;
import com.exam.service.SysConfigService;
import com.exam.util.CoreConst;
import com.exam.util.PageUtil;
import com.exam.util.PushArticleUtil;
import com.exam.util.ResultUtil;
import com.exam.vo.ArticleConditionVo;
import com.exam.vo.BaiduPushResVo;
import com.exam.vo.base.PageResultVo;
import com.exam.vo.base.ResponseVo;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

@Controller
@RequestMapping("article")
public class ArticleController {
    @Autowired
    private BizArticleService articleService;
    @Autowired
    private BizArticleTagsService articleTagsService;
    @Autowired
    private BizCategoryService categoryService;
    @Autowired
    private BizTagsService tagsService;
    @Autowired
    private SysConfigService configService;

    @PostMapping("list")
    @ResponseBody
    public PageResultVo loadArticle(ArticleConditionVo articleConditionVo, Integer limit, Integer offset){
        articleConditionVo.setSliderFlag(true);
        PageHelper.startPage(PageUtil.getPageNo(limit, offset),limit);
        List<BizArticle> articleList= articleService.findByCondition(articleConditionVo);
        PageInfo<BizArticle> pages = new PageInfo<>(articleList);
        return ResultUtil.table(articleList,pages.getTotal());
    }
    /*文章*/
    @GetMapping("/add")
    public String addArticle(Model model){
        BizCategory bizCategory = new BizCategory();
        bizCategory.setStatus(CoreConst.STATUS_VALID);
        List<BizCategory> bizCategories = categoryService.selectCategories(bizCategory);
        List<BizTags> tags = tagsService.select(new BizTags());
        model.addAttribute("categories", JSON.toJSONString(bizCategories));
        model.addAttribute("tags",tags);
        return "article/publish";
    }
    @PostMapping("/add")
    @ResponseBody
    public ResponseVo add(BizArticle bizArticle, Integer[]tag){
        try{
            User user = (User)SecurityUtils.getSubject().getPrincipal();
            bizArticle.setUserId(user.getUserId());
            bizArticle.setAuthor(user.getNickname());
            BizArticle article = articleService.insertArticle(bizArticle);
            articleTagsService.insertList(tag, article.getId());
            return ResultUtil.success("保存文章成功");
        }catch (Exception e){
            return ResultUtil.error("保存文章失败");
        }
    }

    @GetMapping("/edit")
    public String edit(Model model, Integer id){
        BizArticle bizArticle= articleService.selectById(id);
        model.addAttribute("article",bizArticle);
        BizCategory bizCategory = new BizCategory();
        bizCategory.setStatus(CoreConst.STATUS_VALID);
        List<BizCategory> bizCategories = categoryService.selectCategories(bizCategory);
        model.addAttribute("categories", JSON.toJSONString(bizCategories));
        List<BizTags> sysTags = tagsService.select(new BizTags());
        /*方便前端处理回显*/
        List<BizTags> aTags = new ArrayList<>();
        List<BizTags> sTags = new ArrayList<>();
        boolean flag;
        for(BizTags sysTag:sysTags){
            flag=false;
            for(BizTags articleTag: bizArticle.getTags()){
                if(articleTag.getId().equals(sysTag.getId())){
                    BizTags tempTags = new BizTags();
                    tempTags.setId(sysTag.getId());
                    tempTags.setName(sysTag.getName());
                    aTags.add(tempTags);
                    sTags.add(tempTags);
                    flag=true;
                    break;
                }
            }
            if(!flag){
                sTags.add(sysTag);
            }
        }
        bizArticle.setTags(aTags);
        model.addAttribute("tags",sTags);
        return "article/detail";
    }

    @PostMapping("/edit")
    @ResponseBody
    public ResponseVo edit(BizArticle article,Integer[]tag){
        articleService.updateNotNull(article);
        articleTagsService.removeByArticleId(article.getId());
        articleTagsService.insertList(tag, article.getId());
        return ResultUtil.success("编辑文章成功");
    }
    @PostMapping("/delete")
    @ResponseBody
    public ResponseVo delete(Integer id){
        int i = articleService.deleteBatch(new Integer[]{id});
        if(i>0){
            return ResultUtil.success("删除文章成功");
        }else{
            return ResultUtil.error("删除文章失败");
        }
    }
    @PostMapping("/batch/delete")
    @ResponseBody
    public ResponseVo deleteBatch(@RequestParam("ids[]") Integer[]ids){
        int i = articleService.deleteBatch(ids);
        if(i>0){
            return ResultUtil.success("删除文章成功");
        }else{
            return ResultUtil.error("删除文章失败");
        }
    }
    @PostMapping("/batch/push")
    @ResponseBody
    public ResponseVo pushBatch(@RequestParam("urls[]") String[]urls){
        try{
            String url = configService.selectAll().get(SysConfigKey.BAIDU_PUSH_URL.getValue());
            BaiduPushResVo baiduPushResVo = JSONObject.parseObject(PushArticleUtil.postBaidu(url,urls),BaiduPushResVo.class);
            if(baiduPushResVo.getNotSameSite()==null&&baiduPushResVo.getNotValid()==null){
                return ResultUtil.success("推送文章成功");
            }else{
                return ResultUtil.error("推送文章失败",baiduPushResVo);
            }
        }catch (Exception e){
            return ResultUtil.error("推送文章失败,请检查百度推送接口！");
        }

    }
}
