package com.bit.blog.servlet;

import com.bit.blog.entity.Article;
import com.bit.blog.exception.BusinessException;
import com.bit.blog.exception.ParameterException;
import com.bit.blog.util.JSONUtil;
import com.qing.tools.DBTools;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 * @Classname ArticleListServlet
 * @Description TODO
 * @Date 2019/8/23 14:12
 * @Created by AppleTree
 */
public class ArticleAddServlet extends BaseServlet {

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
        // 处理前端请求的数据
        //aplication/json数据需要使用inputStrem来获取
//        String userAccount = req.getParameter("userAccout");
//        String title = req.getParameter("title");
//        String content = req.getParameter("content");

        //获取JSON格式的数据
        Article article = JSONUtil.get(req, Article.class);

        // 处理业务以及数据库操作
        try {
            connection = DBTools.getConnection();
            String sql = "insert into article(title, content, user_id, create_time) " +
                    "select ?, ?, user.id, now() from user " +
                    "where user.name=?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, article.getTitle());
            preparedStatement.setString(2, article.getContent());
            preparedStatement.setString(3, article.getUserAccout());
            int r = preparedStatement.executeUpdate();
            if(r > 0) {
                return r;
            }else {
                throw new BusinessException("没有该用户" + article.getUserAccout());
            }
        } finally {
            DBTools.close(null, preparedStatement, connection);
        }
    }
}
