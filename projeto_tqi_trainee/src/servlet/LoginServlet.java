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
import dao.LoginDao;



@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private LoginDao loginDao = new LoginDao();
       
    
    public LoginServlet() {
        super();        
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String acao = request.getParameter("acao");		
		
		
		if(!acao.isEmpty() && acao != null) {
			RequestDispatcher pagina = request.getRequestDispatcher("/index.jsp");
			pagina.forward(request, response);
		}
		
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
				
		
		String login = request.getParameter("login");
		String senha = request.getParameter("senha");
		
		
		EmprestimoDao emprestimoDao = new EmprestimoDao();
		ClienteDao clienteDao = new ClienteDao();
		
		if(ValidaCPF.isCPF(login)) {
			//login = ValidaCPF.imprimeCPF(login);
			if(loginDao.validarLoginSenha(login, senha)) {
				//acesso ok
				UsuarioBean usuario = loginDao.consultar(login);
				
				
				if(usuario.getTipo().equalsIgnoreCase("admin")) {
					request.setAttribute("clientes", clienteDao.listarTodos());	
					RequestDispatcher pagina = request.getRequestDispatcher("/cadastroCliente.jsp");
					pagina.forward(request, response);				
				}else {
					
					request.setAttribute("cliente", usuario);
					request.setAttribute("Emprestimos", emprestimoDao.listarTodos(usuario.getId()));	
					RequestDispatcher pagina = request.getRequestDispatcher("/paginaCliente.jsp");
					
					pagina.forward(request, response);				
				}		
				
			}else {			
				request.setAttribute("msg", "Campo Usuário ou Senha: Inválido!");
				RequestDispatcher pagina = request.getRequestDispatcher("/index.jsp");
				pagina.forward(request, response);				
			}			
		}else {			
			request.setAttribute("msg", "Campo Usuário ou Senha: Inválido!");
			RequestDispatcher pagina = request.getRequestDispatcher("/index.jsp");
			pagina.forward(request, response);
			
		}		
	}

}
