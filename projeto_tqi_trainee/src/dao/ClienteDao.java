package dao;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import beans.EmprestimoBean;
import beans.UsuarioBean;
import connection.SingleConnection;

public class ClienteDao {
	
	private Connection conexao;
	
	private EmprestimoDao emprestimoDao;
	
	public ClienteDao() {
		conexao = SingleConnection.getConnection();
	}
	
	public void cadastrarCliente(UsuarioBean usuario) {
		String sql = "insert into usuario(login, senha, tipo, nome, telefone, cpf, cep, endereco, numero, complemento, cidade, bairro, estado, rg) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
		
		try {
			PreparedStatement insert = conexao.prepareStatement(sql);
			insert.setString(1, usuario.getLogin());
			insert.setString(2, usuario.getSenha());
			insert.setString(3, usuario.getTipo());
			insert.setString(4, usuario.getNome());
			insert.setString(5, usuario.getTelefone());
			insert.setString(6, usuario.getCpf());
			insert.setString(7, usuario.getCep());
			insert.setString(8, usuario.getEndereco());
			insert.setString(9, usuario.getNumero());
			insert.setString(10, usuario.getComplemento());
			insert.setString(11, usuario.getCidade());
			insert.setString(12, usuario.getBairro());
			insert.setString(13, usuario.getEstado());
			insert.setString(14, usuario.getRg());
			
			insert.execute();
			conexao.commit();			
		} catch (SQLException e) {
			e.printStackTrace();
			try {
				conexao.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			
		}		
	}
	
	public List<UsuarioBean> listarTodos(){
		
		String sql = "select * from usuario";
		
		List<UsuarioBean> clientes = new ArrayList<UsuarioBean>();
		
		try {
			PreparedStatement select = conexao.prepareStatement(sql);
			ResultSet resultado = select.executeQuery();
			
			while(resultado.next()) {
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
				cliente.setStatusEmprestimo(verificarStatus(resultado.getLong("id")));
						
				
				clientes.add(cliente);				
			}			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return clientes;		
	}
	
	private String verificarStatus(Long idCliente) {
		emprestimoDao = new EmprestimoDao();
		
		if(emprestimoDao.listarTodos(idCliente) != null) {
			List<EmprestimoBean> emprestimosCliente = emprestimoDao.listarTodos(idCliente);
			int cont = 0;
			
			if(emprestimosCliente != null) {
				for (EmprestimoBean emprestimoBean : emprestimosCliente) {
					if(emprestimoBean.getSituacao().equalsIgnoreCase("Em análise")) {
						cont++;				
					}
					
				}
				
				if(cont > 0) {
					return "analisar";
				}		
				return "Reprovado";			
			}
		}
		
		
		return "nao possui emprestimos";
	}
	
	public void delete(Long id) {
		//String sql = "delete from usuario where login = '" + login + "'";
		String sql = "delete from usuario where id = " + id ;
		
		try {
			PreparedStatement delete = conexao.prepareStatement(sql);
			delete.execute();
			conexao.commit();
		} catch (SQLException e) {
			e.printStackTrace();
			
			try {
				conexao.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}		
	}
	
	

	public UsuarioBean consultar(Long id) {
		//String sql = "select * from usuario where login = '" + login + "'";
		String sql = "select * from usuario where id = " + id;
		
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

	public void update(UsuarioBean usuario) {
		String sql = "UPDATE usuario SET login = ?, senha = ?, tipo = ?, nome = ?, telefone = ?, cpf = ?, cep = ?, endereco = ?, numero = ?, complemento = ?, cidade = ?, bairro = ?, estado = ?, rg = ?  where id = " + usuario.getId();
		
		try {
			PreparedStatement update = conexao.prepareStatement(sql);
			update.setString(1, usuario.getLogin());
			update.setString(2, usuario.getSenha());
			update.setString(3, usuario.getTipo());
			update.setString(4, usuario.getNome());
			update.setString(5, usuario.getTelefone());
			update.setString(6, usuario.getCpf());
			update.setString(7, usuario.getCep());
			update.setString(8, usuario.getEndereco());
			update.setString(9, usuario.getNumero());
			update.setString(10, usuario.getComplemento());
			update.setString(11, usuario.getCidade());
			update.setString(12, usuario.getBairro());
			update.setString(13, usuario.getEstado());
			update.setString(14, usuario.getRg());
			
			update.executeUpdate();
			conexao.commit();
		} catch (SQLException e) {
			e.printStackTrace();
			
			try {
				conexao.rollback();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}		
	}
	
	public boolean validarLogin(String login) {
		
		String sql = "select count(1) as qtd from usuario where login = '" + login + "'";
		
		try {
			PreparedStatement select = conexao.prepareStatement(sql);
			ResultSet resultado = select.executeQuery();
			
			if(resultado.next()) {				
				return (resultado.getInt("qtd") <= 0);// return true
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return false;
		
	}
	
	
	public boolean validarLoginUpdate(String login, Long id) {
		
		String sql = "SELECT COUNT(1) as qtd FROM usuario WHERE login = '" + login + "' AND id <> " + id;
		
		try {
			PreparedStatement select = conexao.prepareStatement(sql);
			ResultSet resultado = select.executeQuery();
			
			if(resultado.next()) {				
				return (resultado.getInt("qtd") <= 0);// return true
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return false;
		
	}

	public boolean verificarEmprestimos(long idCliente) {
		String sql = "SELECT COUNT(1) as qtd FROM emprestimo WHERE idcliente = " + idCliente;
		try {
			PreparedStatement select = conexao.prepareStatement(sql);
			ResultSet resultado = select.executeQuery();
			
			if(resultado.next()) {				
				return (resultado.getInt("qtd") <= 0);// return true
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
				
		return false;
	}
	
	

}
