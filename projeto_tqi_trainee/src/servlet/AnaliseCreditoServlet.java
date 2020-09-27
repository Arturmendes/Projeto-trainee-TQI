package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.EmprestimoBean;
import beans.UsuarioBean;
import dao.ClienteDao;
import dao.EmprestimoDao;


@WebServlet("/AnaliseCreditoServlet")
public class AnaliseCreditoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	ClienteDao clienteDao = new ClienteDao();
	UsuarioBean cliente = new UsuarioBean();
	EmprestimoDao emprestimoDao = new EmprestimoDao();
	
       
    public AnaliseCreditoServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {		
		String acao = request.getParameter("acao");
		String id= request.getParameter("id");
		String idCliente = request.getParameter("idCliente");
		
		
		
		
		
		
		if(acao.equalsIgnoreCase("carregar")) {				
			cliente = clienteDao.consultar(Long.parseLong(idCliente));
			request.setAttribute("Emprestimos", emprestimoDao.listarTodos(Long.parseLong(idCliente)));				
			request.setAttribute("cliente", cliente);
			request.getRequestDispatcher("/analiseCredito.jsp").forward(request, response);	
		}else if(acao.equalsIgnoreCase("avaliar")) {						
			EmprestimoBean emprestimoConsultado = emprestimoDao.consultar(Long.parseLong(id));			
			request.setAttribute("cliente", cliente);
			request.setAttribute("emprestimo", emprestimoConsultado);	
			request.setAttribute("Emprestimos", emprestimoDao.listarTodos(Long.parseLong(idCliente)));
			request.getRequestDispatcher("/analiseCredito2.jsp").forward(request, response);	
		}else if(acao.equalsIgnoreCase("excluir")) {			
			emprestimoDao.delete(Long.parseLong(id));
			request.setAttribute("cliente", cliente);
			request.setAttribute("Emprestimos", emprestimoDao.listarTodos(Long.parseLong(idCliente)));	
			request.getRequestDispatcher("/analiseCredito.jsp").forward(request, response);	
		}
		
		
				
		
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String altSituacao = request.getParameter("altSituacao");
		String idCliente = request.getParameter("idcliente");
		String id = request.getParameter("id");
		
		if(!id.isEmpty() && id != null) {
			EmprestimoBean emprestimo = new EmprestimoBean();
			
			emprestimo.setId(Long.parseLong(id));
			emprestimo.setSituacao(altSituacao);
			
			emprestimoDao.update(emprestimo);
			
		}
		
		
		
		
		request.setAttribute("Emprestimos", emprestimoDao.listarTodos(Long.parseLong(idCliente)));				
		request.setAttribute("cliente", cliente);
		
		request.getRequestDispatcher("/analiseCredito.jsp").forward(request, response);	
		
	}

}
