package com.bayaran.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import org.apache.commons.dbutils.BasicRowProcessor;
import org.apache.commons.dbutils.BeanProcessor;

public abstract class CustomRowProcessor extends BasicRowProcessor {
	public CustomRowProcessor() {
		super();
	}
	
	public CustomRowProcessor(BeanProcessor convert) {
		super(convert);
	}

	@Override
	public <T> List<T> toBeanList(ResultSet rs, Class<T> clazz)
			throws SQLException {
		try {
            List<T> newlist = new LinkedList<T>();
            while (rs.next()) {
                newlist.add(toBean(rs, clazz));
            }
            return newlist;
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
	}
}
