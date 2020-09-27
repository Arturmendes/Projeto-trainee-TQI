<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="ISO-8859-1">
		<title>Cadastro Clientes</title>
		<link rel="stylesheet" type="text/css" href="resources/css/cadastroCliente.css">
		
		<!-- Adicionando JQuery -->
		    <script src="https://code.jquery.com/jquery-3.4.1.min.js"
		            integrity="sha256-CSXorXvZcTkaix6Yvo6HppcZGetbYMGWSFlBw8HfCJo="
		            crossorigin="anonymous">
		    </script>
		    <script type="text/javascript" src="resources/js/jquery.mask.min.js"/></script>
		    
		
	</head>
	<body>
	
		<header>
			<div class="container header">
			
				<div class="logomarca">
					
				</div>
				
				<div class=titulo>
					<h1>Cadastro de Cliente</h1>				
				</div>				
			
				<div class="menu">
					<a href="./index.jsp">
						<img width="40px" height="40px" title="Sair" alt="Sair" src="resources/img/sair.png">
					</a>	
				</div>
											
			</div>		
		</header>
		
		<section id="msg">
			<div class="container">
				<div class="divMsg">
					<h3 class="msg">${msg}</h3>	
				</div>			
			</div>				
		</section>
		
		
		<section id="form">
			<div class="container">
				<div class="divForm">				
					<form id="formUsuario" action="CadastroClienteServlet" method="post" class="formUsuario">
						<table>
							<tr>								
								<td><label>Login/CPF:</label></td>
								<td><input type="text" id="login" name="login" placeholder="Somente números" maxlength="11" value="${cliente.login}"></td>
								<td><label>CEP:</label></td>
								<td>
									<input type="text" id="cep" name="cep" placeholder="CEP"   
									   	   value="${cliente.cep}" maxlength="9" onblur="consultaCep(this.value);">
								</td>								
							</tr>
							<tr>
								<td><label>Senha:</label></td>
								<td><input type="password" id="senha" name="senha" placeholder="Senha" value="${cliente.senha}"></td>								
								<td><label>Endereço:</label></td>
								<td><input type="text" id="endereco" name="endereco" placeholder="Endereço" value="${cliente.endereco}"></td>								
							</tr>
							<tr>
								<td><label>Nome:</label></td>
								<td><input type="text" id="nome" name="nome" placeholder="Nome completo" value="${cliente.nome}"></td>								
								<td><label>Número:</label></td>
								<td><input type="number" id="numero" name="numero" placeholder="Número" value="${cliente.numero}"></td>								
							</tr>
							<tr>
								<td><label>RG:</label></td>
								<td><input type="text" id="rg" name="rg" placeholder="Somente números" value="${cliente.rg}"></td>								
								<td><label>Complemento:</label></td>
								<td><input type="text" id="complemento" name="complemento" placeholder="Complemento" value="${cliente.complemento}"></td>
								
							</tr>
							<tr>
								<td><label for="fone">Celular:</label></td>
								<td><input type="tel" id="fone" name="fone" placeholder="(xx) 9 8888-8888" value="${cliente.telefone}"></td>
								
								<td><label>Cidade:</label></td>
								<td><input type="text" id="cidade" name="cidade" placeholder="Cidade" value="${cliente.cidade}"></td>								
							</tr>		
							<tr>
								<td><label>Tipo:</label></td>					
								<td>
									<select id="tipo" name="tipo" required="required" >
										<option>Cliente</option>
										<option>Admin</option>							
									</select>
								</td>
								
								<td><label>Bairro:</label></td>
								<td><input type="text" id="bairro" name="bairro" placeholder="Bairro" value="${cliente.bairro}"></td>
							</tr>							
							<tr>								
								<td></td>
								<td><input type="text"  style="display: none" id="id" name="id" placeholder="ID" value="${cliente.id}"></td>
								<td><label>Estado:</label></td>
								<td><input type="text" id="estado" name="estado" placeholder="Estado" value="${cliente.estado}"></td>
								
							</tr>
							<tr>
								<td></td>
								<td style="text-align: center"><input class="submit" type="submit" value="Salvar"></td>
								<td></td>
								<td style="text-align: center"> <input class="submit" type="submit" value="Cancelar" onclick="cancelar()"></td>
								
							</tr>				
						</table>		
					</form>			
				</div>			
			</div>			
		</section>
		
		<section id="tableClientes">
			<div class="conteiner">
				<div class="tabela">
					<table class="responsive-table">			
						<tr>
							<th>Login</th>
							<th>Nome</th>
							<th>Telefone</th>							
							<th>Cpf</th>
							<th>Editar</th>	
							<th>Excluir</th>
							<th>Avaliar <br/> Emprestimos</th>				
						</tr>
						<c:forEach items="${clientes}" var="cliente">	
							<c:set var="tipo" value="${cliente.tipo}"/>	
							<c:set var="situacao" value="${cliente.statusEmprestimo}" />		
							<tr>
								<c:if test="${!tipo.equalsIgnoreCase('admin')}">
									<td><c:out value="${cliente.login}"></c:out></td>																
									<td><c:out value="${cliente.nome}"></c:out></td>
									<td><c:out value="${cliente.telefone}"></c:out></td>									
									<td><c:out value="${cliente.cpf}"></c:out></td>
									<td>
										<a href="CadastroClienteServlet?acao=editar&idCliente=${cliente.id}">
										<img width="15px" height="15px" title="Editar" alt="Editar" src="resources/img/editar.png"></a>
									</td>
									<td>
										<a href="CadastroClienteServlet?acao=delete&idCliente=${cliente.id}">
										<img width="15px" height="15px" title="Excluir" alt="Excluir" src="resources/img/excluir.png"></a>
									</td>
									<c:if test="${situacao.equalsIgnoreCase('analisar')}">	
										<td>
											<a href="AnaliseCreditoServlet?acao=carregar&idCliente=${cliente.id}">
											<img width="15px" height="15px" title="Avaliar" alt="Avaliar" src="resources/img/verificar.jpg"></a>
										</td>	
									</c:if>	
									<c:if test="${!situacao.equalsIgnoreCase('analisar')}">	
										<td>
											<a href="AnaliseCreditoServlet?acao=carregar&idCliente=${cliente.id}">
											<img width="15px" height="15px" title="Avaliado" alt="Avaliado" src="resources/img/avaliado.png"></a>
										</td>	
									</c:if>				
								</c:if>
															
							</tr>			
						</c:forEach>		
					</table>				
				</div>
							
			</div>		
		</section>

		
		
		
		<script type="text/javascript">
		
			$("#fone").mask("(00) 9 0000-0000");
		
			function cancelar(){
				document.getElementById("formUsuario").method="get";
				document.getElementById("formUsuario").action="CadastroClienteServlet?acao=reset" ;
				document.getElementById("formUsuario").onsubmit="";
				
			}
			
			function consultaCep(cep){				
				
                //Consulta o webservice viacep.com.br/
                $.getJSON("https://viacep.com.br/ws/"+ cep +"/json/?callback=?", function(dados) {

                    if (!("erro" in dados)) {
                        //Atualiza os campos com os valores da consulta.
                        $("#endereco").val(dados.logradouro);
                        $("#bairro").val(dados.bairro);
                        $("#cidade").val(dados.localidade);
                        $("#estado").val(dados.uf);                        
                    } //end if.
                    else {
                        //CEP pesquisado não foi encontrado.
                        alert("CEP não encontrado.");
                    }
                });           
				
			}
		</script>
			
	</body>
</html>