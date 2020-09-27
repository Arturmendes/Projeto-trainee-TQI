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

public class EmprestimoDao {
	
	private Connection conexao;
	
	public EmprestimoDao() {
		conexao = SingleConnection.getConnection();
	}
	
	public void cadastrarEmprestimo(EmprestimoBean emprestimo) {
		String sql = "insert into emprestimo(valor, qtdparcelas, situacao, idcliente, parcelas) values (?, ?, ?, ?, ?)";
		
		try {
			PreparedStatement insert = conexao.prepareStatement(sql);
			insert.setDouble(1, emprestimo.getValor());
			insert.setLong(2, emprestimo.getQuantidadeParcelas());
			insert.setString(3, emprestimo.getSituacao());
			insert.setLong(4, emprestimo.getIdCliente());
			insert.setDouble(5, emprestimo.getParcelas());
			
			
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
	
	public List<EmprestimoBean> listarTodos(Long idCliente){
		String sql = "select * from emprestimo where idcliente = " + idCliente;
		
		List<EmprestimoBean> emprestimos = new ArrayList<EmprestimoBean>();
		
		try {
			PreparedStatement select = conexao.prepareStatement(sql);
			ResultSet resultado = select.executeQuery();
			
			while(resultado.next()) {
				EmprestimoBean emprestimo = new EmprestimoBean();
				emprestimo.setId(resultado.getLong("id"));
				emprestimo.setValor(resultado.getDouble("valor"));
				emprestimo.setQuantidadeParcelas(resultado.getLong("qtdparcelas"));
				emprestimo.setSituacao(resultado.getString("situacao"));
				emprestimo.setIdCliente(resultado.getLong("idcliente"));
				emprestimo.setParcelas(resultado.getDouble("parcelas"));
				
				emprestimos.add(emprestimo);				
			}			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return emprestimos;		
	}
	
	public EmprestimoBean consultar(Long id) {
		
		String sql = "select * from emprestimo where id = " + id;
		
		try {
			PreparedStatement select = conexao.prepareStatement(sql);
			ResultSet resultado = select.executeQuery();
			
			if(resultado.next()) {
				EmprestimoBean emprestimo = new EmprestimoBean();
				emprestimo.setId(resultado.getLong("id"));
				emprestimo.setId(resultado.getLong("id"));
				emprestimo.setValor(resultado.getDouble("valor"));
				emprestimo.setQuantidadeParcelas(resultado.getLong("qtdparcelas"));
				emprestimo.setSituacao(resultado.getString("situacao"));
				emprestimo.setIdCliente(resultado.getLong("idcliente"));
				emprestimo.setParcelas(resultado.getDouble("parcelas"));
				return emprestimo;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	public UsuarioBean consultarCliente(Long idCliente) {
		
		String sql = "select * from usuario where id = " + idCliente;
		
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
				return cliente;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	public boolean verificarEmprestimoCliente(Long idCliente) {
		
		String sql = "select count(1) as qtd from emprestimo where idcliente = '" + idCliente + "'";
		
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
	
	public void delete(Long id) {
		
		String sql = "delete from emprestimo where id = " + id ;
		
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
	
	public void update(EmprestimoBean emprestimo) {
		String sql = "UPDATE emprestimo SET situacao = ? where id = " + emprestimo.getId();
		
		try {
			PreparedStatement update = conexao.prepareStatement(sql);
			update.setString(1, emprestimo.getSituacao());
			
			
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
	
	
	
	

}
