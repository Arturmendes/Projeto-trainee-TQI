package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import beans.UsuarioBean;
import connection.SingleConnection;

public class LoginDao {
	
	private Connection conexao;
	
	public LoginDao() {
		conexao = SingleConnection.getConnection();
	}
	
	public boolean validarLoginSenha(String login, String senha) {
		String sql = "select * from usuario where login = '" + login + "' and senha = '" + senha + "'";
		
		
		
		try {
			PreparedStatement statement = conexao.prepareStatement(sql);
			ResultSet resultSet = statement.executeQuery();
			if(resultSet.next()) {
				return true; // possui usuário.
			}else {
				return false; // usuário não encontrado;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;		
	}
	
	public UsuarioBean consultar(String login) {
		String sql = "select * from usuario where login = '" + login + "'";
		//String sql = "select * from usuario where id = " + id;
		
		try {
			PreparedStatement select = conexao.prepareStatement(sql);
			ResultSet resultado = select.executeQuery();
			
			if(resultado.next()) {
				UsuarioBean cliente = new UsuarioBean();
				cliente.setLogin(resultado.getString("login"));
				cliente.setSenha(resultado.getString("senha"));
				cliente.setTipo(resultado.getString("tipo"));
				cliente.setId(resultado.getLong("id"));
				cliente.setNome(resultado.getString("nome"));
				cliente.setTelefone(resultado.getString("telefone"));
				cliente.setCpf(resultado.getString("cpf"));
				cliente.setCep(resultado.getString("cep"));
				cliente.setEndereco(resultado.getString("endereco"));
				cliente.setNumero(resultado.getString("numero"));
				cliente.setComplemento(resultado.getString("complemento"));
				cliente.setCidade(resultado.getString("cidade"));
				cliente.setBairro(resultado.getString("bairro"));
				cliente.setEstado(resultado.getString("estado"));
				cliente.setRg(resultado.getString("rg"));
				return cliente;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return null;
	}

}
