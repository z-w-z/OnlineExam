package com.exam.controller;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.exam.model.BizArticleLook;
import com.exam.model.BizComment;
import com.exam.model.BizLove;
import com.exam.service.BizArticleLookService;
import com.exam.service.BizCommentService;
import com.exam.service.BizLoveService;
import com.exam.util.CoreConst;
import com.exam.util.DateUtil;
import com.exam.util.IpUtil;
import com.exam.util.MD5;
import com.exam.util.ResultUtil;
import com.exam.util.XssKillerUtil;
import com.exam.vo.CommentConditionVo;
import com.exam.vo.base.ResponseVo;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

@RestController
@RequestMapping("blog/api")
public class BlogApiController {
    @Autowired
    private BizCommentService commentService;
    @Autowired
    private BizArticleLookService articleLookService;
    @Autowired
    private BizLoveService loveService;

    @PostMapping("comments")
    public PageInfo<BizComment> getComments(CommentConditionVo vo){
        PageHelper.startPage(vo.getPageNumber(), vo.getPageSize());
        List<BizComment> comments = commentService.selectComments(vo);
        PageInfo<BizComment> pages = new PageInfo<>(comments);
        return pages;
    }

    @PostMapping("comment/save")
    public ResponseVo saveComment(HttpServletRequest request, BizComment comment) throws UnsupportedEncodingException {
        if (org.springframework.util.StringUtils.isEmpty(comment.getNickname())) {
            return ResultUtil.error("请输入昵称");
        }
        String content = comment.getContent();
        if (!XssKillerUtil.isValid(content)) {
            return ResultUtil.error("内容不合法");
        }
        content = XssKillerUtil.clean(content.trim()).replaceAll("(<p><br></p>)|(<p></p>)", "");
        Date date = new Date();
        comment.setContent(content);
        comment.setIp(IpUtil.getIpAddr(request));
        comment.setCreateTime(date);
        comment.setUpdateTime(date);
        if(StringUtils.isNotBlank(comment.getQq())){
            comment.setAvatar("http://q1.qlogo.cn/g?b=qq&nk="+comment.getQq()+"&s=100");
        }else if(StringUtils.isNotBlank(comment.getEmail())){
            String entry = MD5.md5Hex(comment.getEmail());
            comment.setAvatar("http://www.gravatar.com/avatar/"+entry+"?d=mp");
        }
        int a = commentService.insertSelective(comment);
        if(a>0){
            return ResultUtil.success("评论提交成功,系统正在审核");
        }else{
            return ResultUtil.error("评论提交失败");
        }
    };
    @PostMapping("article/look")
    public ResponseVo checkLook(HttpServletRequest request, Integer articleId){
        /*浏览次数*/
        Date date = new Date();
        String ip = IpUtil.getIpAddr(request);
        int checkCount = articleLookService.checkArticleLook(articleId, ip, DateUtil.addHours(date,-1));
        if(checkCount==0){
            BizArticleLook articleLook = new BizArticleLook();
            articleLook.setArticleId(articleId);
            articleLook.setUserIp(ip);
            articleLook.setLookTime(date);
            articleLook.setCreateTime(date);
            articleLook.setUpdateTime(date);
            articleLookService.insert(articleLook);
            return ResultUtil.success("浏览次数+1");
        }else{
            return ResultUtil.error("一小时内重复浏览不增加次数哦");
        }
    };
    @PostMapping("love")
    public ResponseVo love(HttpServletRequest request, Integer bizId,Integer bizType){
        Date date = new Date();
        String ip = IpUtil.getIpAddr(request);
        BizLove bizLove = loveService.checkLove(bizId, ip);
        if (bizLove == null) {
            bizLove=new BizLove();
            bizLove.setBizId(bizId);
            bizLove.setBizType(bizType);
            bizLove.setUserIp(ip);
            bizLove.setStatus(CoreConst.STATUS_VALID);
            bizLove.setCreateTime(date);
            bizLove.setUpdateTime(date);
            loveService.insert(bizLove);
            return ResultUtil.success("点赞成功");
        }else{
            return ResultUtil.error("您已赞过了哦~");
        }
    }

}
