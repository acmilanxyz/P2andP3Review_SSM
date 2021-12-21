package org.spoto.servlet;

import com.alibaba.fastjson.JSONObject;
import org.spoto.utlis.JdbcUtils;
import org.spoto.utlis.JsonUtils;
import org.spoto.utlis.SpringUtils;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(urlPatterns = "/jdbc")
public class JdbcServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        JdbcUtils jdbcUtils = (JdbcUtils) SpringUtils.getBean("applicationContext_Before_Spring.xml", "jdbcUtils");
        JSONObject data = new JSONObject();
        data.put("msg", jdbcUtils.toString());

        System.out.println("msg: " + jdbcUtils.toString());
        JsonUtils.outJson(response, data);
    }
}
