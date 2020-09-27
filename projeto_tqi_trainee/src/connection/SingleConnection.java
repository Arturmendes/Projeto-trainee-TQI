package connection;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 * Responsável por fazer a conexão com o banco de dados
 * 
 * @author Artur
 *
 */

public class SingleConnection {
	
	private static String banco = "jdbc:postgresql://localhost:5432/projeto-tqi?autoReconnect=true";
	private static String usuario = "artur";
	private static String senha = "2468";
	private static Connection conexao = null;
	
	static {
		conectar();
	}
	
	public SingleConnection() {
		conectar();
	}

	private static void conectar() {
		try {
			if(conexao == null) {
				Class.forName("org.postgresql.Driver");
				conexao = DriverManager.getConnection(banco, usuario, senha);
				conexao.setAutoCommit(false);				
			}else {
				System.out.println("Já esta conectado ao banco");
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("Erro ao conectar com o banco de dados");
		}		
	}
	
	public static Connection getConnection() {		
		return conexao;
	}
	

}
