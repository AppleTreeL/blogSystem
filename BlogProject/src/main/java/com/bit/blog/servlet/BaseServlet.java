package com.bit.blog.servlet;

import com.bit.blog.entity.JSON;
import com.bit.blog.exception.ParameterException;
import com.bit.blog.exception.SystemException;
import com.bit.blog.util.JSONUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Classname BaseServlet
 * @Description TODO
 * @Date 2019/8/23 17:08
 * @Created by AppleTree
 */
public abstract class BaseServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setContentType("application/json;charset=UTF-8");
        resp.setCharacterEncoding("UTF-8");

        JSON resultJSON = new JSON();
        try {
            Object data = process(req, resp);
            resultJSON.setSuccess(true);
            resultJSON.setCode("200");
            resultJSON.setMessage("operation success...");
            resultJSON.setData(data);
            //String result = JSONUtil.format(resultJSON);
            //resp.getWriter().write(result);
        } catch (Exception e) {
            e.printStackTrace();
            if(e instanceof ParameterException){
                resultJSON.setCode(((ParameterException)e).getCode());
                resultJSON.setMessage(((ParameterException)e).getMessage());
            }else if(e instanceof SystemException){
                resultJSON.setCode(((SystemException)e).getCode());
                resultJSON.setMessage(((SystemException)e).getMessage());
            }
            /*
            if(ParameterException.class.isAssignableFrom(e.getClass()){}
             */
            else {
                resultJSON.setCode("500");
                resultJSON.setMessage("服务器错误！");
            }
        }
        //TODO 待处理异常
        resp.getWriter().write(JSONUtil.format(resultJSON));
    }

    public abstract Object process(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException, Exception;
}
