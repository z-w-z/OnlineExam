package com.exam.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.exam.model.BizTags;
import com.exam.service.BizTagsService;
import com.exam.util.PageUtil;
import com.exam.util.ResultUtil;
import com.exam.vo.base.PageResultVo;
import com.exam.vo.base.ResponseVo;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

@Controller
@RequestMapping("tag")
public class TagController {
    @Autowired
    private BizTagsService tagsService;
    @PostMapping("list")
    @ResponseBody
    public PageResultVo loadTags(BizTags bizTags, Integer limit, Integer offset){
        PageHelper.startPage(PageUtil.getPageNo(limit, offset),limit);
        List<BizTags> tagList= tagsService.selectTags(bizTags);
        PageInfo<BizTags> pages = new PageInfo<>(tagList);
        return ResultUtil.table(tagList,pages.getTotal());
    }
    @PostMapping("/add")
    @ResponseBody
    public ResponseVo add(BizTags bizTags){
        Date date = new Date();
        bizTags.setCreateTime(date);
        bizTags.setUpdateTime(date);
        int i = tagsService.insert(bizTags);
        if(i>0){
            return ResultUtil.success("新增标签成功");
        }else{
            return ResultUtil.error("新增标签失败");
        }
    }

    @GetMapping("/edit")
    public String edit(Model model, Integer id){
        BizTags bizTags = tagsService.selectByPrimaryKey(id);
        model.addAttribute("tag",bizTags);
        return "tag/detail";
    }

    @PostMapping("/edit")
    @ResponseBody
    public ResponseVo edit(BizTags bizTags){
        bizTags.setUpdateTime(new Date());
        int i = tagsService.updateNotNull(bizTags);
        if(i>0){
            return ResultUtil.success("编辑标签成功");
        }else{
            return ResultUtil.error("编辑标签失败");
        }
    }
    @PostMapping("/delete")
    @ResponseBody
    public ResponseVo delete(Integer id){
        int i = tagsService.delete(id);
        if(i>0){
            return ResultUtil.success("删除标签成功");
        }else{
            return ResultUtil.error("删除标签失败");
        }
    }
    @PostMapping("/batch/delete")
    @ResponseBody
    public ResponseVo deleteBatch(@RequestParam("ids[]") Integer[]ids){
        int i = tagsService.deleteBatch(ids);
        if(i>0){
            return ResultUtil.success("删除标签成功");
        }else{
            return ResultUtil.error("删除标签失败");
        }
    }
}
