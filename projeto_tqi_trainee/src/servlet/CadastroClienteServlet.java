package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.UsuarioBean;
import classesUteis.ValidaCPF;
import dao.ClienteDao;

@WebServlet("/CadastroClienteServlet")
public class CadastroClienteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	ClienteDao clienteDao = new ClienteDao();
	UsuarioBean usuario = new UsuarioBean();
       
    public CadastroClienteServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String acao = request.getParameter("acao");
		String id = request.getParameter("idCliente");
		
		
		
		if(acao == null || acao.equalsIgnoreCase("reset") || acao.equalsIgnoreCase("listarTodos")) {
			
			request.setAttribute("clientes", clienteDao.listarTodos());
			
		}else if(acao.equalsIgnoreCase("delete")) {
			
			if(clienteDao.verificarEmprestimos(Long.parseLong(id))) {
				clienteDao.delete(Long.parseLong(id));
				request.setAttribute("clientes", clienteDao.listarTodos());				
			}else {
				request.setAttribute("msg", "Cliente possui empréstimos");
				request.setAttribute("clientes", clienteDao.listarTodos());	
			}			
			
		}else if(acao.equalsIgnoreCase("editar")){
			
			UsuarioBean clienteConsultado = clienteDao.consultar(Long.parseLong(id));
			request.setAttribute("cliente", clienteConsultado);
			
		}else if(acao.equalsIgnoreCase("voltar")) {
			request.setAttribute("clientes", clienteDao.listarTodos());
		}
		
		
		request.getRequestDispatcher("/cadastroCliente.jsp").forward(request, response);	
		
		
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String id = request.getParameter("id");
		String login = request.getParameter("login");
		String senha = request.getParameter("senha");
		String tipo = request.getParameter("tipo");
		String nome = request.getParameter("nome");
		String telefone = request.getParameter("fone");		
		String rg = request.getParameter("rg");
		String cep = request.getParameter("cep");
		String endereco = request.getParameter("endereco");
		String numero = request.getParameter("numero");
		String complemento = request.getParameter("complemento");
		String cidade = request.getParameter("cidade");
		String bairro = request.getParameter("bairro");
		String estado = request.getParameter("estado");
		
		
		
		usuario.setLogin(login);
		usuario.setSenha(senha);
		usuario.setTipo(tipo);
		usuario.setNome(nome);
		usuario.setTelefone(telefone);
		usuario.setCpf(login);
		usuario.setCep(cep);
		usuario.setEndereco(endereco);
		usuario.setNumero(numero);
		usuario.setComplemento(complemento);
		usuario.setCidade(cidade);
		usuario.setBairro(bairro);
		usuario.setEstado(estado);
		usuario.setRg(rg);
		
		if(login.isEmpty() || login == null) {
			request.setAttribute("msg", "Preencher Login");
			request.setAttribute("cliente", usuario);
		}else if(senha.isEmpty() || senha == null) {
			request.setAttribute("msg", "Preencher senha");
			request.setAttribute("cliente", usuario);
		}else if(nome.isEmpty() || nome == null) {
			request.setAttribute("msg", "Preencher nome");
			request.setAttribute("cliente", usuario);
		}else if(telefone.isEmpty() || telefone == null) {
			request.setAttribute("msg", "Preencher telefone");
			request.setAttribute("cliente", usuario);
		}else if(rg.isEmpty() || rg == null) {
			request.setAttribute("msg", "Preencher RG");
			request.setAttribute("cliente", usuario);
		}else if(cep.isEmpty() || cep == null) {
			request.setAttribute("msg", "Preencher cep");
			request.setAttribute("cliente", usuario);
		}else if(numero.isEmpty() || numero == null) {
			request.setAttribute("msg", "Preencher número");
			request.setAttribute("cliente", usuario);
		}else if(id.isEmpty() || id == null) {
			if(clienteDao.validarLogin(login)) {
				if(ValidaCPF.isCPF(login)) {										
					usuario.setLogin(login);
					usuario.setCpf(ValidaCPF.imprimeCPF(login));
					clienteDao.cadastrarCliente(usuario);	
					request.setAttribute("msg", "Cadastro realizado com sucesso!");
				}else {
					request.setAttribute("cliente", usuario);
					request.setAttribute("msg", "CPF Inválido!");
				}				
			}else {
				request.setAttribute("msg", "Login duplicado");
				request.setAttribute("cliente", usuario);
			}
		}else if(!id.isEmpty() || id != null){
			usuario.setLogin(login);
			usuario.setCpf(ValidaCPF.imprimeCPF(login));
			usuario.setId(Long.parseLong(id));
			if(clienteDao.validarLoginUpdate(login, Long.parseLong(id))) {
				if(ValidaCPF.isCPF(login)) {
					clienteDao.update(usuario);
				}
				else {
					request.setAttribute("cliente", usuario);
					request.setAttribute("msg", "CPF Inválido!");
				}
				
			}else {
				request.setAttribute("msg", "Login já existe");
				request.setAttribute("cliente", usuario);
			}						
		}		
		request.setAttribute("clientes", clienteDao.listarTodos());	
		RequestDispatcher pagina = request.getRequestDispatcher("/cadastroCliente.jsp");		
		pagina.forward(request, response);
	}

}
