package com.banco;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySQLConnection {

	private static Connection	con		= null;
	private static String		status	= "N�o conectado";

	public static Connection getCon() {
		if (con == null) {
			con = newConnection();
		}
		return con;
	}

	private static Connection newConnection() {
		try {
			String driverName = "com.mysql.jdbc.Driver"; // Nome Driver
			Class.forName(driverName); // Carregar classe do driver
			String serverName = "localhost"; // caminho do servidor do BD
			String mydatabase = "mysql"; // nome do banco de dados
			String url = "jdbc:mysql://" + serverName + "/" + mydatabase; // url
			String username = "root"; // usu�rio
			String password = "123456789"; // senha
			Connection ret = DriverManager.getConnection(url, username, password); // Connecta
			if (ret != null) {
				status = ("STATUS--->Conectado com sucesso!");
			} else {
				status = ("STATUS--->N�o foi possivel realizar conex�o");
			}
			return ret;
		} catch (ClassNotFoundException e) { // Driver n�o encontrado
			System.err.println(e);
			System.out.println("O driver expecificado nao foi encontrado.");
			e.printStackTrace();
			return null;
		} catch (SQLException e) { // N�o conseguindo se conectar ao banco
			System.err.println(e);
			System.out.println("Nao foi possivel conectar ao Banco de Dados.");
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * @return Status da conex�o
	 */
	public static String getStatus() {
		return status;
	}
}
