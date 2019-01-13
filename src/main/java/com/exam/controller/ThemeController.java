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

import com.exam.model.BizTheme;
import com.exam.service.BizThemeService;
import com.exam.util.CoreConst;
import com.exam.util.PageUtil;
import com.exam.util.ResultUtil;
import com.exam.vo.base.PageResultVo;
import com.exam.vo.base.ResponseVo;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

@Controller
@RequestMapping("theme")
public class ThemeController {
    @Autowired
    private BizThemeService bizThemeService;
    @PostMapping("list")
    @ResponseBody
    public PageResultVo loadTheme(Integer limit, Integer offset){
        PageHelper.startPage(PageUtil.getPageNo(limit, offset),limit);
        List<BizTheme> themes = bizThemeService.selectAll();
        PageInfo<BizTheme> pages = new PageInfo<>(themes);
        return ResultUtil.table(themes,pages.getTotal());
    }
    @PostMapping("/add")
    @ResponseBody
    public ResponseVo add(BizTheme bizTheme){
        Date date = new Date();
        bizTheme.setCreateTime(date);
        bizTheme.setUpdateTime(date);
        bizTheme.setStatus(CoreConst.STATUS_INVALID);
        int i = bizThemeService.insert(bizTheme);
        if(i>0){
            return ResultUtil.success("新增主题成功");
        }else{
            return ResultUtil.error("新增主题失败");
        }
    }

    @GetMapping("/edit")
    public String edit(Model model, Integer id){
        BizTheme bizTheme = bizThemeService.selectByPrimaryKey(id);
        model.addAttribute("theme",bizTheme);
        return "systheme/detail";
    }

    @PostMapping("/edit")
    @ResponseBody
    public ResponseVo edit(BizTheme bizTheme){
        bizTheme.setUpdateTime(new Date());
        int i = bizThemeService.updateNotNull(bizTheme);
        if(i>0){
            return ResultUtil.success("编辑主题成功");
        }else{
            return ResultUtil.error("编辑主题失败");
        }
    }

    @PostMapping("/use")
    @ResponseBody
    public ResponseVo use(Integer id){
        int i = bizThemeService.useTheme(id);
        if(i>0){
            return ResultUtil.success("启用主题成功");
        }else{
            return ResultUtil.error("启用主题失败");
        }
    }

    @PostMapping("/delete")
    @ResponseBody
    public ResponseVo delete(Integer id){
        int i = bizThemeService.delete(id);
        if(i>0){
            return ResultUtil.success("删除主题成功");
        }else{
            return ResultUtil.error("删除主题失败");
        }
    }
    @PostMapping("/batch/delete")
    @ResponseBody
    public ResponseVo deleteBatch(@RequestParam("ids[]") Integer[]ids){
        int i = bizThemeService.deleteBatch(ids);
        if(i>0){
            return ResultUtil.success("删除主题成功");
        }else{
            return ResultUtil.error("删除主题失败");
        }
    }

}
