package com.qing.DBTools;

import com.qing.tools.DBTools;
import org.junit.Assert;
import org.junit.Test;

import java.sql.Connection;
import java.util.Arrays;

/**
 * @Classname DBToolsTest
 * @Description TODO
 * @Date 2019/8/23 12:38
 * @Created by AppleTree
 */
public class DBToolsTest {

    @Test
    public void testConnection() {
        Connection connection = DBTools.getConnection();
        //System.out.println(connection);
        Assert.assertNotNull(connection);
    }

    @Test
    public void TestSpilt() {
        String str = "9,";
        String[] res = str.split(",");
        System.out.println(Arrays.toString(res));
    }

}
