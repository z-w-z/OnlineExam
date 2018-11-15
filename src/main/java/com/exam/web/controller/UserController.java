package com.exam.web.controller;

import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.exam.web.common.AjaxResult;
import com.exam.web.common.ExamConst;
import com.exam.web.exception.ExamWebError;
import com.exam.web.model.User;
import com.exam.web.service.UserService;
import com.exam.web.util.MD5;

@Controller
@RequestMapping("/index")
public class UserController {
	private static Log LOG = LogFactory.getLog(UserController.class);

	@Autowired
	private UserService userService;

	/**
	 * 个人信息页面
	 */
	@RequestMapping(value = "/profile", method = RequestMethod.GET)
	public String profile(HttpServletRequest request, Model model) {
		User currentUser = (User) request.getSession().getAttribute(ExamConst.CURRENT_ACCOUNT);
		// TODO::拦截器过滤处理
		if (currentUser == null) {
			// 用户未登录直接返回首页面
			return "redirect:/";
		}
		model.addAttribute(ExamConst.CURRENT_ACCOUNT, currentUser);
		return "/user/profile";
	}

	/**
	 * 更改密码页面
	 */
	@RequestMapping(value = "/password", method = RequestMethod.GET)
	public String password(HttpServletRequest request, Model model) {
		User currentUser = (User) request.getSession().getAttribute(ExamConst.CURRENT_ACCOUNT);
		// TODO::拦截器过滤处理
		if (currentUser == null) {
			// 用户未登录直接返回首页面
			return "redirect:/";
		}
		model.addAttribute(ExamConst.CURRENT_ACCOUNT, currentUser);
		return "/user/password";
	}

	/**
	 * 更新密码
	 */
	@RequestMapping(value = "/api/updatePassword", method = RequestMethod.POST)
	@ResponseBody
	public AjaxResult updatePassword(HttpServletRequest request, HttpServletResponse response) {
		AjaxResult ajaxResult = new AjaxResult();
		try {
			String oldPassword = request.getParameter("oldPassword");
			String newPassword = request.getParameter("newPassword");
			String confirmNewPassword = request.getParameter("confirmNewPassword");
			String md5OldPassword = MD5.md5(ExamConst.MD5_SALT + oldPassword);
			String md5NewPassword = MD5.md5(ExamConst.MD5_SALT + newPassword);
			if (StringUtils.isNotEmpty(newPassword) && StringUtils.isNotEmpty(confirmNewPassword)
					&& !newPassword.equals(confirmNewPassword)) {
				return AjaxResult.fixedError(ExamWebError.NOT_EQUALS_CONFIRM_PASSWORD);
			}
			User currentUser = (User) request.getSession().getAttribute(ExamConst.CURRENT_ACCOUNT);
			if (!currentUser.getPassword().equals(md5OldPassword)) {
				return AjaxResult.fixedError(ExamWebError.WRONG_PASSWORD);
			}
			currentUser.setPassword(md5NewPassword);
			boolean result = userService.updateUser(currentUser);
			ajaxResult.setSuccess(result);
		} catch (Exception e) {
			LOG.error(e.getMessage(), e);
			return AjaxResult.fixedError(ExamWebError.COMMON);
		}
		return ajaxResult;
	}
	
	/**
     * 更新个人信息
     */
    @RequestMapping(value = "/api/updateUser", method = RequestMethod.POST)
    @ResponseBody
    public AjaxResult updateUser(HttpServletRequest request, HttpServletResponse response) {
        AjaxResult ajaxResult = new AjaxResult();
        try {
            String phone = request.getParameter("phone");
            String qq = request.getParameter("qq");
            String email = request.getParameter("email");
            String description = request.getParameter("description");
            String avatarImgUrl = request.getParameter("avatarImgUrl");

            User currentUser = (User) request.getSession().getAttribute(ExamConst.CURRENT_ACCOUNT);
            currentUser.setPhone(phone);
            currentUser.setQq(qq);
            currentUser.setEmail(email);
            currentUser.setDescription(description);
            currentUser.setAvatarImgUrl(avatarImgUrl);
            boolean result = userService.updateUser(currentUser);
            ajaxResult.setSuccess(result);
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
            return AjaxResult.fixedError(ExamWebError.COMMON);
        }
        return ajaxResult;
    }
    
    /**
     * 验证登录
     */
    @RequestMapping(value = "/api/login", method = RequestMethod.POST)
    @ResponseBody
    public AjaxResult login(HttpServletRequest request, HttpServletResponse response) {
        AjaxResult ajaxResult = new AjaxResult();
        try {
            String username = request.getParameter("username");
            String password = request.getParameter("password");
            User current_user = userService.getUserByUsername(username);
            if(current_user != null) {
                String pwd = MD5.md5(ExamConst.MD5_SALT+password);
                if(pwd.equals(current_user.getPassword())) {
					// 设置单位为秒，设置为-1永不过期
                    //request.getSession().setMaxInactiveInterval(180*60);    //3小时
                    request.getSession().setAttribute(ExamConst.CURRENT_ACCOUNT,current_user);
                    ajaxResult.setData(current_user);
                } else {
                    return AjaxResult.fixedError(ExamWebError.WRONG_PASSWORD);
                }
            } else {
                return AjaxResult.fixedError(ExamWebError.WRONG_USERNAME);
            }
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
            return AjaxResult.fixedError(ExamWebError.COMMON);
        }
        return ajaxResult;
    }
    
    /**
     * 用户退出
     * @param request
     * @return
     */
    @RequestMapping(value = "/logout", method= RequestMethod.GET)
    public String logout(HttpServletRequest request) {
        request.getSession().setAttribute(ExamConst.CURRENT_ACCOUNT,null);
        String url=request.getHeader("Referer");
        LOG.info("url = " + url);
        return "redirect:"+url;
    }

    /**
     * 上传头像
     */
    @RequestMapping(value = "/api/uploadAvatar", method = RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> uploadAvatar(HttpServletRequest request, @RequestParam("file") MultipartFile file) throws IllegalStateException, IOException{
        AjaxResult ajaxResult = new AjaxResult();
        try {
            //原始名称
            String oldFileName = file.getOriginalFilename(); //获取上传文件的原名
            //存储图片的物理路径
            String file_path = ExamConst.UPLOAD_FILE_IMAGE_PATH;
            LOG.info("file_path = " + file_path);
            //上传图片
            if(file!=null && oldFileName!=null && oldFileName.length()>0){
                //新的图片名称
                String newFileName = UUID.randomUUID() + oldFileName.substring(oldFileName.lastIndexOf("."));
                //新图片
                File newFile = new File(file_path+newFileName);
                //将内存中的数据写入磁盘
                file.transferTo(newFile);
                //将新图片名称返回到前端
                ajaxResult.setData(newFileName);
            }else{
                return AjaxResult.fixedError(ExamWebError.UPLOAD_FILE_IMAGE_NOT_QUALIFIED);
            }
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
            return AjaxResult.fixedError(ExamWebError.UPLOAD_FILE_IMAGE_ANALYZE_ERROR);
        }
        return ajaxResult;
    }
    
    /**
     * API:添加用户
     */
    @RequestMapping(value="/api/addAccount", method= RequestMethod.POST)
    @ResponseBody
    public AjaxResult addAccount(@RequestBody User account) {
        AjaxResult ajaxResult = new AjaxResult();
        User existAccount = userService.getUserByUsername(account.getUsername());
        if(existAccount == null) {//检测该用户是否已经注册
            account.setPassword(MD5.md5(ExamConst.MD5_SALT+account.getPassword()));
            account.setAvatarImgUrl(ExamConst.DEFAULT_AVATAR_IMG_URL);
            account.setDescription("");
            int accountId = userService.addUser(account);
            return new AjaxResult().setData(accountId);
        }
        return AjaxResult.fixedError(ExamWebError.AREADY_EXIST_USERNAME);
    }

    /**
     * API:更新用户
     */
    @RequestMapping(value="/api/updateManegeAccount", method= RequestMethod.POST)
    @ResponseBody
    public AjaxResult updateAccount(@RequestBody User user) {
        AjaxResult ajaxResult = new AjaxResult();
        user.setPassword(MD5.md5(ExamConst.MD5_SALT+user.getPassword()));
        boolean result = userService.updateUser(user);
        return new AjaxResult().setData(result);
    }

    /**
     * API:删除用户
     */
    @DeleteMapping("/api/deleteAccount/{id}")
    @ResponseBody
    public AjaxResult deleteAccount(@PathVariable int id) {
        AjaxResult ajaxResult = new AjaxResult();
        boolean result = userService.deleteUser(id);
        return new AjaxResult().setData(result);
    }

    /**
     * API:禁用账号
     */
    @RequestMapping(value="/api/disabledAccount/{id}", method= RequestMethod.POST)
    @ResponseBody
    public AjaxResult disabledAccount(@PathVariable int id) {
        AjaxResult ajaxResult = new AjaxResult();
        boolean result = userService.disabledUser(id);
        return new AjaxResult().setData(result);
    }

	/**
	 * API:解禁账号
	 */
    @RequestMapping(value="/api/abledAccount/{id}", method= RequestMethod.POST)
    @ResponseBody
    public AjaxResult abledAccount(@PathVariable int id) {
        AjaxResult ajaxResult = new AjaxResult();
        boolean result = userService.abledUser(id);
        return new AjaxResult().setData(result);
    }

}
