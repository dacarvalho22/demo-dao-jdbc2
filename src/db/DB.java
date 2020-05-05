package db;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class DB {
	
	private static Connection conexao = null;
	
	// MÉTODO PARA CONECTAR COM O BANCO DE DADOS
	public static Connection getConnetion() {
		if(conexao == null) {
			try {
				Properties props = loadProperties();
				String url = props.getProperty("dburl");
				conexao = DriverManager.getConnection(url, props);
			}catch(SQLException e) {
				throw new DbException("Erro ao conectar ao Banco de dados. ");
			}
		}	
		return conexao;
	}
	
	// MÉTODO PARA FECHAR UMA CONEXÃO COM BANCO DE DADOS
	public static void closeConnection()  {
		if(conexao != null) {
			try {
			conexao.close();
			}catch(SQLException e) {
				throw new DbException("Erro ao fechar o Banco de dados. ");
			}
		}
	}	
	
	// Método para CARREGAR AS PROPRIEDADES DO 
	// ARQUIVO  "db.properties"
	private static Properties loadProperties() {
		try(FileInputStream fs = new FileInputStream("db.properties")){
			Properties prop = new Properties();
			prop.load(fs);
			return prop;
		}catch(IOException e) {
			throw new DbException("Erro ao carre driver do banco " + e.getMessage());
		}
		
	}
	
	public static void closeStatement(Statement st, ResultSet rs) {
		if(st != null) {
			try {
				st.close();
			} catch(SQLException e) {
				throw new DbException("Erro ao fechar a conexão Statement" + e.getMessage());
			}
		}
		if(rs != null) {
			try {
				rs.close();
			} catch(SQLException e) {
				throw new DbException("Erro ao fechar a conexão ResultSet" + e.getMessage());
			}
		}	
	}

}
