package com.bit.blog.servlet;

import com.bit.blog.entity.Article;
import com.bit.blog.exception.ParameterException;
import com.qing.tools.DBTools;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @Classname ArticleListServlet
 * @Description TODO
 * @Date 2019/8/23 14:12
 * @Created by AppleTree
 */
public class ArticleListServlet extends BaseServlet {

//    @Override
//    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
//            throws ServletException, IOException {
//        this.doPost(req, resp);
//    }
//
//    @Override
//    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
//            throws ServletException, IOException {
//        try {
//            req.setCharacterEncoding("UTF-8");
//            resp.setContentType("application/json;charset=UTF-8");
//            resp.setCharacterEncoding("UTF-8");
//            System.out.println(req.getParameter("key1"));
//            Connection connection = DBTools.getConnection();
//            String sql = "select a.id, a.title, a.user_id,a.content, a.create_time " +
//                    "from article a join user u on a.user_id=u.id " +
//                    "where u.id=?";
//            PreparedStatement preparedStatement = connection.prepareStatement(sql);
//            preparedStatement.setInt(1, Integer.parseInt(req.getParameter("id")));
//            ResultSet resultSet = preparedStatement.executeQuery();
//            List<Article> articles = new ArrayList<>();
//            while (resultSet.next()) {
//                Article article = new Article();
//                article.setId(resultSet.getInt("id"));
//                article.setTitle(resultSet.getString("title"));
//                article.setContent(resultSet.getString("content"));
//                article.setUserId(resultSet.getInt("user_id"));
//                article.setCreateTime(resultSet.getTimestamp("create_time"));
//                articles.add(article);
//            }
//            System.out.println(articles);
//            String result = JSONUtil.format(articles);
//            resp.getWriter().write(result);
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }

    @Override
    public Object process(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        //System.out.println(req.getParameter("key1"));
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List<Article> articles = new ArrayList<>();
        String sid = req.getParameter("id");
        Integer id = null;
        // 处理前端请求的数据
        try{
//             id = Integer.parseInt(req.getParameter("id"));
            id = Integer.parseInt(sid);
        }catch (NumberFormatException e) {
            throw new ParameterException("客户端错误：id 错误(" + sid + ")");
        }
        // 处理业务以及数据库操作

        try {
            connection = DBTools.getConnection();
            String sql = "select a.id, a.title, a.user_id,a.content, a.create_time " +
                    "from article a join user u on a.user_id=u.id " +
                    "where u.id=?";
            preparedStatement = connection.prepareStatement(sql);
            //preparedStatement.setInt(1, Integer.parseInt(req.getParameter("id")));
            preparedStatement.setInt(1, id);
            //TODO 处理前端异常
            resultSet = preparedStatement.executeQuery();
            //articles = new ArrayList<>();
            while (resultSet.next()) {
                Article article = new Article();
                article.setId(resultSet.getInt("id"));
                article.setTitle(resultSet.getString("title"));
                article.setContent(resultSet.getString("content"));
                article.setUserId(resultSet.getInt("user_id"));
                article.setCreateTime(resultSet.getTimestamp("create_time"));
                articles.add(article);
            }
            return articles;
        } finally {
            DBTools.close(resultSet, preparedStatement, connection);
        }
    }
}
