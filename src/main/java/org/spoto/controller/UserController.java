package org.spoto.controller;

import com.alibaba.fastjson.JSONObject;
import org.spoto.model.Users;
import org.spoto.service.UserService;
import org.spoto.service.impl.UserServiceImpl;
import org.spoto.utlis.JsonUtils;
import org.spoto.utlis.SpringUtils;
import org.spoto.utlis.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;
/**
 *
 * SSM整合之后的Controller代码
 *
 */
@Controller
public class UserController {

    // Controller类更改1：摒弃SpringUtils，定义一个全局变量UserService，并加上@Resource
    @Resource
    private UserService us;

    @RequestMapping("userList.ajax")
    @ResponseBody
    public JSONObject getUserList(String index) {
        JSONObject data = new JSONObject();
        if (StringUtils.isNotEmpty(index)) {
            Integer indexInt = Integer.parseInt(index);

            // Controller类更改2：删除SpringUtils相关代码
//            UserService us = (UserService) SpringUtils.getBean("applicationContext_Before_Spring.xml", "userService");
            List<Users> list = us.getListUserService(indexInt);

            Integer maxPage = us.getCountUserService();

            data.put("list", list);
            data.put("maxPage", maxPage);

            return data;
        } else {
            System.out.println("非法操作！");
            return data;
        }
    }

    @RequestMapping("del.ajax")
    @ResponseBody
    public JSONObject delUser(String userId) {
        JSONObject data = new JSONObject();
        if (StringUtils.isNotEmpty(userId)) {
            int userIdInt = Integer.parseInt(userId);
//            UserService us = (UserService) SpringUtils.getBean("applicationContext_Before_Spring.xml", "userService");
            boolean del = us.deleteUserService(userIdInt);
            data.put("del", del);
            return data;
        } else {
            System.out.println("非法操作！");
            return data;
        }
    }

    @RequestMapping("save.ajax")
    @ResponseBody
    public JSONObject saveUser(String id, String username, String password, String nickname, String email) {
        int code = 0;
        if (StringUtils.isAllEmpty(id, username, password, nickname, email)) {
            Users user = new Users();
            user.setId(Integer.parseInt(id));
            user.setUsername(username);
            user.setPassword(password);
            user.setNickname(nickname);
            user.setEmail(email);
//            UserService us = (UserService) SpringUtils.getBean("applicationContext_Before_Spring.xml", "userService");
            boolean save = us.saveUserService(user);
            if (save) {
                code = 1;
                System.out.println("Servlet执行新增或更新成功！");
            } else {
                code = -1;
                System.out.println("Servlet执行新增或更新失败！");
            }
        } else {
            code = -2;
            System.out.println("非法操作！");
        }
        JSONObject data = new JSONObject();
        data.put("code", code);
        return data;
    }
}
