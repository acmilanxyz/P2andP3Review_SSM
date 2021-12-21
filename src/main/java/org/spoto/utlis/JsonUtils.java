package org.spoto.utlis;

import com.alibaba.fastjson.JSONObject;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class JsonUtils {
    /**
     * Json输出类
     * @param response
     * @param data
     */
    public static void outJson(HttpServletResponse response, JSONObject data) {
        try {
            PrintWriter writer = response.getWriter();
            writer.write(data.toJSONString());
            writer.flush();
        } catch (IOException e) {
            System.out.println("Json输出异常！");
            e.printStackTrace();
        }
    }
}
