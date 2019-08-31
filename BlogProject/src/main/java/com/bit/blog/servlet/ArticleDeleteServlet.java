package com.bit.blog.servlet;

import com.bit.blog.exception.BusinessException;
import com.bit.blog.exception.ParameterException;
import com.qing.tools.DBTools;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Connection;
import java.sql.PreparedStatement;

/**
 * @Classname ArticleListServlet
 * @Description TODO
 * @Date 2019/8/23 14:12
 * @Created by AppleTree
 */

@WebServlet("/articleDelete")
public class ArticleDeleteServlet extends BaseServlet {

    @Override
    public Object process(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        //System.out.println(req.getParameter("key1"));
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        String ids = null;
        int[] intIds = null;
        try {
            ids = req.getParameter("ids");
            String[] idsArray = ids.split(",");
            intIds = new int[idsArray.length];
            for (int i = 0; i < idsArray.length; i++) {
                intIds[i] = Integer.parseInt(idsArray[i]);
            }
        }catch (Exception e) {
            throw new ParameterException("请求参数错误 ids = " + ids);
        }

        // 处理业务以及数据库操作
        try{
            connection = DBTools.getConnection();
            StringBuilder sql = new StringBuilder("delete from article where id in (");

            for (int i = 0; i < intIds.length; i ++){
                if(i == 0) {
                    sql.append("?");
                }else {
                    sql.append(", ?");
                }
            }
            sql.append(")");

            System.out.println("sql=" + sql);

            preparedStatement = connection.prepareStatement(sql.toString());

            for (int i = 0; i < intIds.length; i++) {
                preparedStatement.setInt(i+1, intIds[i]);
            }

            int r = preparedStatement.executeUpdate();
            if(r > 0) {
                return r;
            }else {
                throw new BusinessException("没有该文章");
            }
        } finally {
            DBTools.close(null, preparedStatement, connection);
        }
    }
}
