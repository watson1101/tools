package com.hong.test;

import java.util.Date;

import com.hong.tools.PostgreSQLUtils;

public class TestPostgresql {

	public static void main(String[] args) {
		connect();

	}

	private static void connect() {
//		PostgreSQLUtils utils = new PostgreSQLUtils();
		String sql = "INSERT INTO \"public\".\"aaatable\" (\"id\", \"code\", \"o2oprice\", \"test1\", \"test2\", \"test3\", \"pdate\") VALUES "
				+ "('1000', '0312788', '1.00', NULL, NULL, NULL, "+new Date()+");\r\n";
		PostgreSQLUtils.save(sql);
		
	}

}
