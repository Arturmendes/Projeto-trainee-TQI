<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="ISO-8859-1">
		<title>Cadastro Clientes</title>
		<link rel="stylesheet" type="text/css" href="resources/css/paginaCliente.css">
		
		<!-- Adicionando JQuery -->
		    <script src="https://code.jquery.com/jquery-3.4.1.min.js"
		            integrity="sha256-CSXorXvZcTkaix6Yvo6HppcZGetbYMGWSFlBw8HfCJo="
		            crossorigin="anonymous">
		    </script>		
		    <script type="text/javascript" src="resources/js/jquery.mask.min.js"/></script>
		
	</head>
	<body>
		<header>		
			<div class="container">
				<div class="logomarca">
			
			
				</div>
				<div class="titulo">
					<h1>Cadastro de Empréstimos</h1>
				</div>
				
				<div class="menu">
					<a href="./index.jsp">
						<img width="40px" height="40px" title="Sair" alt="Sair" src="resources/img/sair.png">
					</a>	
				</div>
			</div>
		</header>
		
		<section id="header2">
			<div class="container">
				<div class="nomeCliente">
					<h3>Seja bem vindo: <span style="text-decoration: underline;">${cliente.nome}</span></h3>
				</div>			
				<div class="divMsg">
					<h3>${msg}</h3>	
				</div>			
			</div>					
		</section>
		
		<section id="body1">
			<div class="container">		
				<form id="formUsuario" action="PaginaClienteServlet" method="post">
					<table>
						<tr>
							<td><label>CPF/Login:</label></td>
							<td><input type="text" id="login" name="login" placeholder="Login" value="${cliente.login}" readonly="readonly"></td>
							
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
							<td><input type="text" id="nome" name="nome" placeholder="Nome completo" value="${cliente.nome}" readonly="readonly"></td>
							
							<td><label>Número:</label></td>
							<td><input type="number" id="numero" name="numero" placeholder="Número" value="${cliente.numero}"></td>
							
						</tr>
						<tr>
							<td><label>Rg:</label></td>
							<td><input type="text" id="rg" name="rg" placeholder="RG" value="${cliente.rg}" readonly="readonly"></td>
							
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
							<td></td>
							<td><input style="display: none;" type="text" id="idClienteFormUsuario" name="id" placeholder="id"  value="${cliente.id}" readonly="readonly"></td>
							
							<td><label>Bairro:</label></td>
							<td><input type="text" id="bairro" name="bairro" placeholder="Bairro" value="${cliente.bairro}"></td>
						</tr>							
						<tr>
							<td></td>
							<td><input style="display: none;" type="text" id="tipo" name="tipo" placeholder="Admin/Cliente" value="${cliente.tipo}" readonly="readonly"></td>
							<td><label>Estado:</label></td>
							<td><input type="text" id="estado" name="estado" placeholder="Estado" value="${cliente.estado}"></td>
						</tr>
						<tr>
							<td colspan="4" style="display: none;"><input type="text" id="acao" name="acao" value=""></td>
						</tr>
						
						<tr>
							<td></td>							
							<td style="text-align: center"><input type="submit" value="Editar"></td>
							<td></td>
							<td><input type="submit" value="Cancelar" onclick="cancelar()"></td>
							
						</tr>				
					</table>		
				</form>
			</div>
		</section>
		
		<section id="titulo">
			<div class="container">
				<div class="titulo">
					<h3>Solicitar Empréstimo</h3>
				</div>			
			</div>		
		</section>
		
		<section id="body2">
			<div class="container">
				<form id="formEmprestimo" action="EmprestimoClienteServlet" method="post">
					<table>
						<tr>
							<td></td>
							<td><input style="display: none;" type="text" id="idClienteFormEmprestimo" name="idcliente" placeholder="idcliente"  value="${cliente.id}" readonly="readonly"></td>											
						</tr>
						<tr>
							<td></td>
							<td><input style="display: none;" type="text" id="idEmprestimoFormEmprestimo" name="id" placeholder="id"  value="${emprestimo.id}" readonly="readonly"></td>											
						</tr>				
						<tr>
							<td><label>Valor:</label></td>
							<td><input type="number" step="0.01" id="valor" name="valor" placeholder="1000.00" value="${emprestimo.valor}"></td>					
						</tr>
						<tr>
							<td><label>Quantidade de Parcelas</label></td>
							<td><input type="number" min="1" max="12" id="quantidade" name="quantidade" placeholder="Quantidade" value="${emprestimo.quantidadeParcelas}" onblur="calcularParcelas(this.value)"></td>					
						</tr>
						<tr>
							<td><label>Valor das parcelas</label></td>
							<td><input type="number" id="parcelas" name="parcelas" placeholder="Valor Parcelas" value="" readonly="readonly"></td>					
						</tr>
						<tr>
							<td><label></label></td>
							<td style="display: none;"><input type="text" id="situacao" name="situacao" value="Em análise"></td>					
						</tr>
						<tr>					
							<td colspan="2" style="text-align: center;"><input type="submit" value="Solicitar"></td>					
						</tr>				
					</table>		
				</form>
			</div>
		</section>
		
		<section id="titulo2">
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
						<td>Valor</td>
						<td>Quantidade Parcelas</td>
						<td>Valor Parcelas</td>
						<td>Status</td>
						<td>Excluir</td>							
					</tr>
					<c:forEach items="${Emprestimos}" var="emprestimo">	
						<c:set var="status" value="${emprestimo.situacao}"/>							
						<tr>					
								
								<td><c:out value="${emprestimo.valor}"></c:out></td>
								<td><c:out value="${emprestimo.quantidadeParcelas}"></c:out></td>
								<td><c:out value="${emprestimo.parcelas}"></c:out></td>
								<td><c:out value="${emprestimo.situacao}"></c:out></td>
							<c:if test="${!status.equalsIgnoreCase('aprovado')}">	
								<td>
									<a href="EmprestimoClienteServlet?acao=excluir&id=${emprestimo.id}&idCliente=${emprestimo.idCliente}">
									<img width="15px" height="15px" title="Excluir" alt="Excluir" src="resources/img/excluir.png"></a>
								</td>	
							</c:if>			
						</tr>			
					</c:forEach>		
				</table>
			</div>
		</section>	
		
		
		
		
		
		<script type="text/javascript">
		
			$("#fone").mask("(00) 9 0000-0000");
		
			function cancelar(){
				document.getElementById("formUsuario").method="get";
				document.getElementById("formUsuario").action="PaginaClienteServlet?acao=reset" ;
				document.getElementById("formUsuario").onsubmit="";
				document.getElementById("acao").value="reset";				
			}
			function calcularParcelas(qtdParcelas){
				var valor = document.getElementById("valor").value;
				
				valor = parseFloat(valor);
				qtdParcelas = parseInt(qtdParcelas);
				
				if((qtdParcelas < 1) || (qtdParcelas > 12)){
					alert("Quantidade de parcelas 1 - 12");
					document.getElementById("quantidade").value = 12;
				}
				
				var valorParcelas = (valor * 1.15) / qtdParcelas;
				
				
				
				document.getElementById("parcelas").value = valorParcelas.toFixed(2);	
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