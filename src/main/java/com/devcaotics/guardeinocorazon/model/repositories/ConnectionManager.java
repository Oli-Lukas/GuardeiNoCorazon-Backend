package com.devcaotics.guardeinocorazon.model.repositories;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionManager
{
	private static Connection connection = null;
	
	private static final String URL      = "jdbc:mysql://localhost:3306/GuardeiNoCorazon";
 	private static final String USER     = "root";
	private static final String PASSWORD = "Web2-root";
	
	static Connection getCurrentConnection() throws SQLException
	{
		if (connection == null)
			connection = DriverManager.getConnection(URL, USER, PASSWORD);
		
		return connection;
	}
	
	static Connection getNewConnection() throws SQLException
	{
		return DriverManager.getConnection(URL, USER, PASSWORD);
	}
}
