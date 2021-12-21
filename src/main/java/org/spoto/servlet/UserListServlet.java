package org.spoto.servlet;

import com.alibaba.fastjson.JSONObject;
import org.spoto.model.Users;
import org.spoto.service.UserService;
import org.spoto.service.impl.UserServiceImpl;
import org.spoto.utlis.JsonUtils;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(urlPatterns = "/userList.ajax.old")
public class UserListServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");

        Integer index = Integer.parseInt(request.getParameter("index"));

        UserService us = new UserServiceImpl();
        List<Users> list = us.getListUserService(index);
        Integer maxPage = us.getCountUserService();
        JSONObject data = new JSONObject();
        data.put("list", list);
        data.put("maxPage", maxPage);
        JsonUtils.outJson(response, data);
    }
}
