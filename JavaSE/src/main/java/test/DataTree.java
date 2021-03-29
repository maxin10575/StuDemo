/**package test;


import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import java.util.ArrayList;
import java.util.Map;

public class DataTreeUtil {
  public  JSONArray treeMenuList2(int parentId,Map menunamemap,MenuService menuService) {
        JSONArray childMenu = new JSONArray();
        List<Menu> menuList = menuService.getMenuByMenuname(menunamemap);
        for (Menu Menu : menuList) {
            JSONObject jsonMenu = JSONObject.fromObject(Menu);
            int menuId = Menu.getMenuid();
            int pid = Menu.getParentid();
            if (parentId == pid) {
                JSONArray c_node = treeMenuList(menuId, menunamemap,menuService);
                jsonMenu.put("children", c_node);
                childMenu.add(jsonMenu);
            }
        }
        return childMenu;
    }

    public  JSONArray treeMenuList1(List<Menu> menuList) {
        JSONArray childMenu = new JSONArray();
        List<Menu> newmenuList = new ArrayList<>();

        for (int i = 0; i < menuList.size(); i++) {
            // 一级菜单没有parentId
            if (StringUtils.isBlank(String.valueOf(menuList.get(i).getParentid()))) {
                newmenuList.add(menuList.get(i));
            }
        }

        for(Menu Menu : newmenuList) {
            for (Menu Menu1 : menuList) {
                JSONObject jsonMenu = JSONObject.fromObject(Menu);
                int pid = Menu.getParentid();
                if (Menu1.getParentid() == pid) {
                    JSONArray c_node = treeMenuList1(menuList);
                    jsonMenu.put("children", c_node);
                    childMenu.add(jsonMenu);
                }
            }
        }
        return childMenu;
    }

    public List<Menu> treeMenuList(List<Menu> allmenuList) {
        // 最后的结果
        List<Menu> menuList = new ArrayList<>();
        // 先找到所有的一级菜单
        for (int i = 0; i < allmenuList.size(); i++) {
            // 一级菜单没有parentId
            if (allmenuList.get(i).getParentid() == null) {
                menuList.add(allmenuList.get(i));
            }
        }
        // 为一级菜单设置子菜单，getChild是递归调用的
        for (Menu menu : menuList) {
            menu.setChildren(getChild(menu.getMenuid(), allmenuList));
        }

//        Map<String,Object> jsonMap = new HashMap<>();
//        jsonMap.put("menu", menuList);
//        System.out.println(gson.toJson(jsonMap));
        return menuList;

    }

    private List<Menu> getChild(Integer id, List<Menu> rootMenu) {
        // 子菜单
        List<Menu> childList = new ArrayList<>();
        for (Menu menu : rootMenu) {
            // 遍历所有节点，将父菜单id与传过来的id比较
            if (menu.getParentid() != null) {
                if (menu.getParentid() == id) {
                    childList.add(menu);
                }
            }
        }
        // 把子菜单的子菜单再循环一遍
        for (Menu menu : childList) {// 没有url子菜单还有子菜单
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
}
 */