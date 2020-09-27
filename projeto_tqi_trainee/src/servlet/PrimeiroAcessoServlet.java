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

@WebServlet("/PrimeiroAcessoServlet")
public class PrimeiroAcessoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	ClienteDao clienteDao = new ClienteDao();
	UsuarioBean usuario = new UsuarioBean();
       
    public PrimeiroAcessoServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String acao = request.getParameter("acao");
		
		
		
		if(acao == null || acao.equalsIgnoreCase("reset")) {			
			request.getRequestDispatcher("/primeiroAcesso.jsp").forward(request, response);				
		}
		
		
		
		
		
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String id = request.getParameter("id");
		String login = request.getParameter("login");
		String senha = request.getParameter("senha");		
		String nome = request.getParameter("nome");
		String telefone = request.getParameter("fone");
		String cpf = request.getParameter("login");
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
		usuario.setTipo("Cliente");
		usuario.setNome(nome);
		usuario.setTelefone(telefone);		
		usuario.setCpf(cpf);
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
			request.setAttribute("msg", "Preencher rg");
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
				request.setAttribute("msg", "Login já cadastrado!");
				request.setAttribute("cliente", usuario);
			}
		}
		
		request.setAttribute("clientes", clienteDao.listarTodos());	
		RequestDispatcher pagina = request.getRequestDispatcher("/primeiroAcesso.jsp");		
		pagina.forward(request, response);
	}

}
