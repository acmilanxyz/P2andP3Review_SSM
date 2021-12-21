package org.spoto.servlet;

import com.alibaba.fastjson.JSONObject;
import org.spoto.model.Users;
import org.spoto.service.UserService;
import org.spoto.service.impl.UserServiceImpl;
import org.spoto.utlis.JsonUtils;
import org.spoto.utlis.SpringUtils;
import org.spoto.utlis.StringUtils;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(urlPatterns = "/save.ajax.old")
public class SaveServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String id = request.getParameter("id");
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String nickname = request.getParameter("nickname");
        String email = request.getParameter("email");
        int code = 0;
        if (StringUtils.isAllEmpty(id, username, password, nickname, email)) {
            Users user = new Users();
            user.setId(Integer.parseInt(id));
            user.setUsername(username);
            user.setPassword(password);
            user.setNickname(nickname);
            user.setEmail(email);
            UserService us = new UserServiceImpl();
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
        JsonUtils.outJson(response, data);

    }
}
