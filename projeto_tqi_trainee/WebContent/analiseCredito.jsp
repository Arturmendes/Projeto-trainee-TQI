<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="ISO-8859-1">
		<title>Análise de Crédito</title>
		<link rel="stylesheet" type="text/css" href="resources/css/analiseCredito.css">		
	</head>
	<body>
		<header>		
			<div class="container">
				<div class="logomarca">
			
			
				</div>
				<div class="titulo">
					<h1>Análise de Crédito</h1>				
				</div>
				
				<div class="menu">
					<a href="CadastroClienteServlet?acao=voltar">
						<img width="40px" height="40px" title="Voltar" alt="Voltar" src="resources/img/voltar.jpg">
					</a>	
					<a href="./index.jsp">						
						<img width="40px" height="40px" title="Sair" alt="Sair" src="resources/img/sair.png">
					</a>	
				</div>				
						
			</div>		
		</header>
		
		
		<section id="header2">
			<div class="container">
				<div class="nomeCliente">
					<h4>Cliente: ${cliente.nome}</h4>
				</div>
			
				<div class="divMsg">
					<h3>${msg}</h3>	
				</div>			
			</div>					
		</section>
		
		<section id="body1">
			<div class="container">
				<form id="formUsuario" action="AnaliseCreditoServlet" method="post">
					<table>						
						<tr>
							<td><label>Nome:</label></td>
							<td><input type="text" id="nome" name="nome" placeholder="Nome completo" value="${cliente.nome}" readonly="readonly"></td>
						</tr>
						<tr>
							<td><label>CPF:</label></td>
							<td><input type="text" id="cpf" name="cpf" placeholder="CPF" value="${cliente.cpf}" readonly="readonly"></td>							
						</tr>												
						<tr>
							<td><label>Tipo:</label></td>
							<td><input type="text" id="tipo" name="tipo" placeholder="Admin/Cliente" value="${cliente.tipo}" readonly="readonly"></td>
							
						</tr>										
					</table>		
				</form>			
			</div>			
		</section>	
		
		<section id="titulo">
			<div class="container">
				<div class="titulo">
					<h3>Empréstimos</h3>
				</div>			
			</div>		
		</section>
		
		<section id="tabela">
			<div class="container">
				<table class="responsive-table">			
					<tr>			
						<td>ID</td>
						<td>Valor</td>
						<td>Quantidade Parcelas</td>
						<td>Situação</td>
						<td>Avaliar</td>
						<td>Excluir</td>							
					</tr>
					<c:forEach items="${Emprestimos}" var="emprestimo">							
						<tr>
								<td><c:out value="${emprestimo.id}"></c:out></td>
								<td><c:out value="${emprestimo.valor}"></c:out></td>
								<td><c:out value="${emprestimo.quantidadeParcelas}"></c:out></td>
								<td><c:out value="${emprestimo.situacao}"></c:out></td>	
								<td>
									<a href="AnaliseCreditoServlet?acao=avaliar&id=${emprestimo.id}&idCliente=${emprestimo.idCliente}">
									<img width="15px" height="15px" title="Avaliar" alt="Avaliar" src="resources/img/avaliar.png"></a>
								</td>	
								<td>
									<a href="AnaliseCreditoServlet?acao=excluir&id=${emprestimo.id}&idCliente=${emprestimo.idCliente}">
									<img width="15px" height="15px" title="Excluir" alt="Excluir" src="resources/img/excluir.png"></a>
								</td>											
						</tr>			
					</c:forEach>		
				</table>		
			</div>				
		</section>
		
		<section class="avaliarEmprestimo" style = "display: none;">
			<section id="titulo2">
				<div class="container">
					<div class="titulo">
						<h3>Avaliar Empréstimo</h3>
					</div>			
				</div>		
			</section>
		
			<section id="body2" >
				<div class="container">
					<form id="formEmprestimo" action="AnaliseCreditoServlet" method="post">
						<table>
							<tr>
								<td><label>ID Cliente:</label></td>
								<td><input type="text" id="idcliente" name="idcliente" placeholder="idcliente"  value="${cliente.id}" readonly="readonly"></td>											
							</tr>
							<tr>
								<td><label>ID:</label></td>
								<td><input type="text" id="id" name="id" placeholder="id"  value="${emprestimo.id}" readonly="readonly"></td>											
							</tr>				
							<tr>
								<td><label>Valor:</label></td>
								<td><input type="number" id="valor" name="valor" placeholder="" value="${emprestimo.valor}" readonly="readonly"></td>					
							</tr>
							<tr>
								<td><label>Quantidade de Parcelas</label></td>
								<td><input type="number" id="quantidade" name="quantidade" placeholder="Quantidade" value="${emprestimo.quantidadeParcelas}" readonly="readonly"></td>					
							</tr>
							<tr>
								<td><label>Situação:</label></td>
								<td><input type="text" id="situacao" name="situacao" value="${emprestimo.situacao}" readonly="readonly"></td>					
							</tr>
							<tr>
								<td><label>Alterar situação:</label></td>
								<td>
									<select id="altSituacao" name="altSituacao">
										<option>Aprovado</option>
										<option>Reprovado</option>
										<option>Em Análise</option>					
									</select>					
								</td>					
							</tr>
							<tr>					
								<td colspan="2" style="text-align: center"><input type="submit" value="Avaliar"></td>					
							</tr>				
						</table>		
					</form>				
				</div>
			</section>		
		</section>
		
		
		
		
		
		

		
		
		
		
		
		
		
		
		
		<script type="text/javascript">
			function cancelar(){
				document.getElementById("formUsuario").method="get";
				document.getElementById("formUsuario").action="AnaliseCreditoServlet?acao=reset" ;
				document.getElementById("formUsuario").onsubmit="";
				document.getElementById("acao").value="reset";
				
			}
		</script>
			
	</body>
</html>