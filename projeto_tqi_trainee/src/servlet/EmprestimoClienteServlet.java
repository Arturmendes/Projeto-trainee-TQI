package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.EmprestimoBean;
import dao.EmprestimoDao;

@WebServlet("/EmprestimoClienteServlet")
public class EmprestimoClienteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	EmprestimoDao emprestimoDao = new EmprestimoDao();
       
    
    public EmprestimoClienteServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String acao = request.getParameter("acao");
		String id = request.getParameter("id");
		String idCliente = request.getParameter("idCliente");
		
		if(!id.isEmpty() || id != null) {
			if(acao.equalsIgnoreCase("excluir")) {
				EmprestimoBean emprestimo = emprestimoDao.consultar(Long.parseLong(id));
				if(emprestimo != null) {
					if(!emprestimo.getSituacao().equalsIgnoreCase("Aprovado")) {
						emprestimoDao.delete(Long.parseLong(id));				
					}	
				}						
			}			
		}
		
		request.setAttribute("cliente", emprestimoDao.consultarCliente(Long.parseLong(idCliente)));
		request.setAttribute("Emprestimos", emprestimoDao.listarTodos(Long.parseLong(idCliente)));
		
		RequestDispatcher pagina = request.getRequestDispatcher("/paginaCliente.jsp");		
		pagina.forward(request, response);		
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String idCliente = request.getParameter("idcliente");
		String id = request.getParameter("id");
		String valor = request.getParameter("valor");
		String qtdParcelas = request.getParameter("quantidade");
		String valorParcelas = request.getParameter("parcelas");
		String situacao = request.getParameter("situacao");
		
		EmprestimoBean emprestimo = new EmprestimoBean();
		emprestimo.setId(id.isEmpty() ? null : Long.parseLong(id));
		emprestimo.setIdCliente(Long.parseLong(idCliente));
		if(valor.isEmpty() || valor == null) {
			request.setAttribute("msg", "Valor em branco");			
		}else if(qtdParcelas.isEmpty() || qtdParcelas == null){
			request.setAttribute("msg", "Quantidade de parcelas obrigatório");			
		}else if(valorParcelas.isEmpty() || valorParcelas == null){
			request.setAttribute("msg", "Valor de parcelas obrigatório");			
		}else {
			emprestimo.setValor(Double.parseDouble(valor));
			emprestimo.setQuantidadeParcelas(Long.parseLong(qtdParcelas));
			emprestimo.setParcelas(Double.parseDouble(valorParcelas));
			emprestimo.setSituacao(situacao);	
			emprestimoDao.cadastrarEmprestimo(emprestimo);
		}	
		
		
		request.setAttribute("cliente", emprestimoDao.consultarCliente(Long.parseLong(idCliente)));
		request.setAttribute("Emprestimos", emprestimoDao.listarTodos(Long.parseLong(idCliente)));	
		RequestDispatcher pagina = request.getRequestDispatcher("/paginaCliente.jsp");		
		pagina.forward(request, response);
		
	}

}
