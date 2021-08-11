package com.hp.bean.controller;

import com.alibaba.fastjson.JSONObject;
import com.hp.bean.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

@WebServlet(name = "UserSelectServlet",urlPatterns = "/UserSelectServlet")
public class UserSelectServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1.修正编码
        req.setCharacterEncoding("utf-8");
        resp.setContentType("text/html;charset=UTF-8");
        //2.接收2个参数page limit
        String page = req.getParameter("page");
        String limit = req.getParameter("limit");
        //3.调用service
        UserService userService = new UserService();
        Map map = userService.selectAllByParam(Integer.parseInt(page), Integer.parseInt(limit));
        //4.把map变为json
        String s = JSONObject.toJSONString(map);
        //5.使用输出流
        PrintWriter writer = resp.getWriter();
        writer.write(s);
        writer.close();
    }
}
