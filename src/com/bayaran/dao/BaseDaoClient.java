package com.bayaran.dao;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Map;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.MapHandler;
import org.apache.commons.lang3.Validate;
import org.springframework.util.Assert;
import org.yaml.snakeyaml.Yaml;

public abstract class BaseDaoClient {
	@SuppressWarnings("unchecked")
	protected static Map<String,Object> loadYamlFile(final String fileName) {
		final InputStream is = BaseDaoClient.class.getResourceAsStream(fileName);
		
		Assert.notNull(is, "Unable to read Yaml file. Stream is null. [fileName=" + fileName + "]");
		
		return (Map<String, Object>) new Yaml().load(is);
	}
	
	private static MapHandler MAP_HANDLER = new MapHandler();
	
	@SuppressWarnings("unchecked")
	protected static <T> T queryLastInsertedId(final Connection connection) throws SQLException {
		Validate.notNull(connection);
		
		return (T) new QueryRunner().query(connection, "select LAST_INSERT_ID() last_id from dual", MAP_HANDLER).get("last_id");
	}
}
