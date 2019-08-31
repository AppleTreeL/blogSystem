package com.bit.blog.servlet;

import com.bit.blog.util.MyActionEnter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Classname UEditorServlet
 * @Description TODO
 * @Date 2019/8/31 11:04
 * @Created by AppleTree
 */

@WebServlet("/ueditor")
public class UEditorServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String path = UEditorServlet.class.getClassLoader().getResource("config.json").getPath();
        MyActionEnter actionEnter = new MyActionEnter(req, path);
        String exec = actionEnter.exec();
        resp.getWriter().write(exec);
    }
}
