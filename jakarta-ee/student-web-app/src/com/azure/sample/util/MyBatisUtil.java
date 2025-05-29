package com.azure.sample.util;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import javax.naming.InitialContext;
import javax.sql.DataSource;
import java.io.Reader;

public class MyBatisUtil {
    private static SqlSessionFactory sqlSessionFactory;

    static {
        try {
            Reader reader = Resources.getResourceAsReader("sql-map-config.xml");
            sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);

            InitialContext ctx = new InitialContext();
            DataSource ds = (DataSource) ctx.lookup("java:comp/env/jdbc/StudentDB");
            sqlSessionFactory.getConfiguration().setEnvironment(
                    new org.apache.ibatis.mapping.Environment(
                            "liberty",
                            sqlSessionFactory.getConfiguration().getEnvironment().getTransactionFactory(),
                            ds
                    )
            );
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static SqlSessionFactory getSqlSessionFactory() {
        return sqlSessionFactory;
    }
}
