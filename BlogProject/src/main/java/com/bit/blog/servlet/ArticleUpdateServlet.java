package com.bit.blog.servlet;

import com.bit.blog.entity.Article;
import com.bit.blog.exception.BusinessException;
import com.bit.blog.util.JSONUtil;
import com.qing.tools.DBTools;

import javax.jws.WebService;
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

@WebServlet("/articleUpdate")
public class ArticleUpdateServlet extends BaseServlet {

    @Override
    public Object process(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        //System.out.println(req.getParameter("key1"));
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        // 处理前端请求的数据{"id":1...}
        //获取JSON格式的数据
        Article article = JSONUtil.get(req, Article.class);

        // 处理业务以及数据库操作
        try {
            connection = DBTools.getConnection();
            String sql = "update article set title=?, content=? where id=?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, article.getTitle());
            preparedStatement.setString(2, article.getContent());
            preparedStatement.setInt(3, article.getId());
            int r = preparedStatement.executeUpdate();
            if(r > 0) {
                return r;
            }else {
                throw new BusinessException("没有该文章" + article.getId());
            }
        } finally {
            DBTools.close(null, preparedStatement, connection);
        }
    }
}
