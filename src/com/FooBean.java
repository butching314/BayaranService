package com;

import java.sql.Connection;
import java.util.List;

import org.apache.commons.dbcp.BasicDataSource;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;

import com.bayaran.dao.Expense;
import com.google.common.collect.ImmutableList;

public class FooBean {
	public static void test() {
		List<String> li = ImmutableList.of("ok");
		System.out.println(li);
	}
	
	private final static String username = "marvin";
    private final static String password = "marvin";
    private final static String url = "jdbc:mysql://localhost:3306/bayarandb";
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
	
	public static void main(String[] args) throws Exception {
        initDS();
        
        String qry = "insert into expense (trx_date) values :";
        QueryRunner run = new QueryRunner(dataSource);
        
        Expense result = run.query(qry, new BeanHandler<Expense>(Expense.class));
        
//        System.out.println(ToStringBuilder.reflectionToString(u));
        
	}
}
