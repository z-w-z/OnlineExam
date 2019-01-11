package com.exam.controller;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.exam.model.Permission;
import com.exam.service.PermissionService;
import com.exam.shiro.ShiroService;
import com.exam.util.CoreConst;
import com.exam.util.ResultUtil;
import com.exam.vo.base.ResponseVo;

@Controller
@RequestMapping("/permission")
public class PermissionController{
    private static final Logger logger = LoggerFactory.getLogger(PermissionController.class);
    /**1:全部资源，2：菜单资源*/
    private static final String[] MENU_FLAG ={"1","2"};
    @Autowired
    private PermissionService permissionService;
    @Autowired
    private ShiroService shiroService;



    /*权限列表数据*/
    @PostMapping("/list")
    @ResponseBody
    public List<Permission>  loadPermissions(String flag){
        List<Permission> permissionListList = new ArrayList<Permission>();
        if(StringUtils.isBlank(flag) || MENU_FLAG[0].equals(flag)){
            permissionListList = permissionService.selectAll(CoreConst.STATUS_VALID);
        }else if(MENU_FLAG[1].equals(flag)){
            permissionListList = permissionService.selectAllMenuName(CoreConst.STATUS_VALID);
        }
        return permissionListList;
    }

    /*添加权限*/
    @ResponseBody
    @PostMapping("/add")
    public ResponseVo addPermission(Permission permission){
        try {
            int a = permissionService.insert(permission);
            if (a > 0) {
                shiroService.updatePermission();
                return ResultUtil.success("添加权限成功");
            } else {
                return ResultUtil.error("添加权限失败");
            }
        } catch (Exception e) {
            logger.error(String.format("PermissionController.addPermission%s", e));
            throw e;
        }
    }

    /*删除权限*/
    @ResponseBody
    @PostMapping("/delete")
    public ResponseVo deletePermission(String permissionId){
        try {
            int subPermsByPermissionIdCount = permissionService.selectSubPermsByPermissionId(permissionId);
            if(subPermsByPermissionIdCount>0){
                return ResultUtil.error("改资源存在下级资源，无法删除！");
            }
            int a = permissionService.updateStatus(permissionId,CoreConst.STATUS_INVALID);
            if (a > 0) {
                shiroService.updatePermission();
                return ResultUtil.success("删除权限成功");
            } else {
                return ResultUtil.error("删除权限失败");
            }
        } catch (Exception e) {
            logger.error(String.format("PermissionController.deletePermission%s", e));
            throw e;
        }
    }

    /*权限详情*/
    @GetMapping("/edit")
    public String detail(Model model, String permissionId) {
        Permission permission = permissionService.findByPermissionId(permissionId);
        if(null!=permission){
            if(permission.getParentId().equals(CoreConst.TOP_MENU_ID)){
                model.addAttribute("parentName", CoreConst.TOP_MENU_NAME);
            }else{
                Permission parent = permissionService.findById(permission.getParentId());
                model.addAttribute("parentName", parent.getName());
            }
        }
        model.addAttribute("permission", permission);
        return "permission/detail";
    }

    /*编辑权限*/
    @ResponseBody
    @PostMapping("/edit")
    public ResponseVo editPermission(@ModelAttribute("permission")Permission permission){
        int a = permissionService.updateByPermissionId(permission);
        if (a > 0) {
            shiroService.updatePermission();
            return ResultUtil.success("编辑权限成功");
        } else {
            return ResultUtil.error("编辑权限失败");
        }
    }

}
