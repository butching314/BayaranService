package com;

import java.math.BigDecimal;
import java.sql.Connection;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.dbcp.BasicDataSource;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.MapHandler;

import com.google.common.collect.ImmutableList;

public class FooBean {
	public static void test() {
		List<String> li = ImmutableList.of("ok");
		System.out.println(li);
	}
	
	private final static String username = "marvin";
    private final static String password = "marvin";
    private final static String url = "jdbc:mysql://127.0.0.1:3306/bayarandb";
    public static Connection connection = null;
    public static int connectionCount = 0;
	private static BasicDataSource dataSource;
	
	private static void initDS() throws Exception {
		dataSource = new BasicDataSource();
		dataSource.setDriverClassName("com.mysql.jdbc.Driver");
        dataSource.setUrl(url);
        dataSource.setUsername(username);
        dataSource.setPassword(password);
        dataSource.setMaxActive(100);
        dataSource.setMaxWait(10000);
        dataSource.setMaxIdle(10);
        
	}
	
	private static String qry = "INSERT INTO EXPENSE(trx_date,payor_id,amount,store,description,remark)values(?,?,?,?,?,?)"; 
	
	public static void main(String[] args) throws Exception {
        initDS();
        
        Connection conn = dataSource.getConnection();
        conn.setAutoCommit(false);
        
        QueryRunner run = new QueryRunner();        
        int i = run.update(conn,qry, new Date(), "marvinTest", BigDecimal.valueOf(23.23), "foo","foo","foo");
        conn.commit();
        
        Map<String,Object> result = run.query(conn, "select LAST_INSERT_ID() result from dual", new MapHandler());
        System.out.println(result);
        
        conn.close();
//        Expense result = run.query(qry, new BeanHandler<Expense>(Expense.class));
        System.out.println(i);
//        System.out.println(ToStringBuilder.reflectionToString(u));
        
	}
}
