package test;
/*package com.cmcc.baseportal.Util;

import com.ai.aif.csf.common.utils.StringUtils;
import com.cmcc.baseportal.dao.entity.PortalMenu;
import com.cmcc.baseportal.service.MenuService;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class DataTreeUtil {
    public  JSONArray treeMenuList2(int parentId,Map menunamemap,MenuService menuService) {
        JSONArray childMenu = new JSONArray();
        List<PortalMenu> menuList = menuService.getMenuByMenuname(menunamemap);
        for (PortalMenu portalMenu : menuList) {
            JSONObject jsonMenu = JSONObject.fromObject(portalMenu);
            int menuId = portalMenu.getMenuid();
            int pid = portalMenu.getParentid();
            if (parentId == pid) {
                JSONArray c_node = treeMenuList(menuId, menunamemap,menuService);
                jsonMenu.put("children", c_node);
                childMenu.add(jsonMenu);
            }
        }
        return childMenu;
    }

    public  JSONArray treeMenuList1(List<PortalMenu> menuList) {
        JSONArray childMenu = new JSONArray();
        List<PortalMenu> newmenuList = new ArrayList<>();

        for (int i = 0; i < menuList.size(); i++) {
            // 一级菜单没有parentId
            if (StringUtils.isBlank(String.valueOf(menuList.get(i).getParentid()))) {
                newmenuList.add(menuList.get(i));
            }
        }

        for(PortalMenu portalMenu : newmenuList) {
            for (PortalMenu portalMenu1 : menuList) {
                JSONObject jsonMenu = JSONObject.fromObject(portalMenu);
                int pid = portalMenu.getParentid();
                if (portalMenu1.getParentid() == pid) {
                    JSONArray c_node = treeMenuList1(menuList);
                    jsonMenu.put("children", c_node);
                    childMenu.add(jsonMenu);
                }
            }
        }
        return childMenu;
    }

    public List<PortalMenu> treeMenuList(List<PortalMenu> allmenuList) {
        // 最后的结果
        List<PortalMenu> menuList = new ArrayList<>();
        // 先找到所有的一级菜单
        for (int i = 0; i < allmenuList.size(); i++) {
            // 一级菜单没有parentId
            if (allmenuList.get(i).getParentid() == null) {
                menuList.add(allmenuList.get(i));
            }
        }
        // 为一级菜单设置子菜单，getChild是递归调用的
        for (PortalMenu menu : menuList) {
            menu.setChildren(getChild(menu.getMenuid(), allmenuList));
        }

//        Map<String,Object> jsonMap = new HashMap<>();
//        jsonMap.put("menu", menuList);
//        System.out.println(gson.toJson(jsonMap));
        return menuList;

    }

    private List<PortalMenu> getChild(Integer id, List<PortalMenu> rootMenu) {
        // 子菜单
        List<PortalMenu> childList = new ArrayList<>();
        for (PortalMenu menu : rootMenu) {
            // 遍历所有节点，将父菜单id与传过来的id比较
            if (menu.getParentid() != null) {
                if (menu.getParentid() == id) {
                    childList.add(menu);
                }
            }
        }
        // 把子菜单的子菜单再循环一遍
        for (PortalMenu menu : childList) {// 没有url子菜单还有子菜单
            if (StringUtils.isBlank(menu.getUrl())) {
                // 递归
                menu.setChildren(getChild(menu.getMenuid(), rootMenu));
            }
        } // 递归退出条件
        if (childList.size() == 0) {
            return null;
        }
        return childList;
    }
}*/