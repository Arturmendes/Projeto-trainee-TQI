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
import dao.EmprestimoDao;

@WebServlet("/PaginaClienteServlet")
public class PaginaClienteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	UsuarioBean cliente = new UsuarioBean();
	ClienteDao clienteDao = new ClienteDao();
	EmprestimoDao emprestimoDao = new EmprestimoDao();
       
    public PaginaClienteServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String acao = request.getParameter("acao");
		String id = request.getParameter("id");
		
		if(!id.isEmpty() || id != null ) {
			cliente = clienteDao.consultar(Long.parseLong(id));
			if(acao.equalsIgnoreCase("reset")) {				
				request.setAttribute("cliente", cliente);
				request.setAttribute("Emprestimos", emprestimoDao.listarTodos(cliente.getId()));
			}
		}
		
		
		RequestDispatcher pagina = request.getRequestDispatcher("/paginaCliente.jsp");
		pagina.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("id");
		String login = request.getParameter("login");
		String senha = request.getParameter("senha");
		String tipo = request.getParameter("tipo");
		String nome = request.getParameter("nome");
		String telefone = request.getParameter("fone");		
		String cep = request.getParameter("cep");
		String endereco = request.getParameter("endereco");
		String numero = request.getParameter("numero");
		String complemento = request.getParameter("complemento");
		String cidade = request.getParameter("cidade");
		String bairro = request.getParameter("bairro");
		String estado = request.getParameter("estado");
		String rg = request.getParameter("rg");
		
		cliente.setId(Long.parseLong(id));
		cliente.setLogin(login);
		cliente.setSenha(senha);
		cliente.setTipo(tipo);
		cliente.setNome(nome);
		cliente.setTelefone(telefone);
		cliente.setCpf(ValidaCPF.imprimeCPF(login));
		cliente.setCep(cep);
		cliente.setEndereco(endereco);
		cliente.setNumero(numero);
		cliente.setComplemento(complemento);
		cliente.setCidade(cidade);
		cliente.setBairro(bairro);
		cliente.setEstado(estado);
		cliente.setRg(rg);
		
		
		
		if(!id.isEmpty() && id != null) {
			if(clienteDao.validarLoginUpdate(login, Long.parseLong(id))) {				
				clienteDao.update(cliente);	
				request.setAttribute("cliente", cliente);
				request.setAttribute("Emprestimos", emprestimoDao.listarTodos(cliente.getId()));
				request.setAttribute("msg", "Editado com Sucesso.");
			}else {
				request.setAttribute("msg", "Login já existe");
				request.setAttribute("cliente", cliente);
			}			
		}
		
		
		
		RequestDispatcher pagina = request.getRequestDispatcher("/paginaCliente.jsp");
		pagina.forward(request, response);
	}

}
