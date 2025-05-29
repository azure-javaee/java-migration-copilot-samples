package ca.on.gov.edu.coreft.util;

import com.ibatis.common.resources.Resources;
import com.ibatis.sqlmap.client.SqlMapClient;
import com.ibatis.sqlmap.client.SqlMapClientBuilder;

import java.io.Reader;

public class MyBatisUtil {
    private static SqlMapClient sqlMapClient;

    static {
        try {
            Reader reader = Resources.getResourceAsReader("sql-map-config.xml");
            sqlMapClient = SqlMapClientBuilder.buildSqlMapClient(reader);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static SqlMapClient getSqlMapClient() {
        return sqlMapClient;
    }
}