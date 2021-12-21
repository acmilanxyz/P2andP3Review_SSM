package org.spoto.servlet;

import com.alibaba.fastjson.JSONObject;
import org.spoto.service.UserService;
import org.spoto.service.impl.UserServiceImpl;
import org.spoto.utlis.JsonUtils;
import org.spoto.utlis.StringUtils;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(urlPatterns = "/del.ajax.old")
public class DeleteServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String userIdStr = request.getParameter("userId");
        if (StringUtils.isNotEmpty(userIdStr)) {
            int userId = Integer.parseInt(userIdStr);
            UserService us = new UserServiceImpl();
            boolean del = us.deleteUserService(userId);
            JSONObject data = new JSONObject();
            data.put("del", del);
            JsonUtils.outJson(response, data);
        }
    }
}
