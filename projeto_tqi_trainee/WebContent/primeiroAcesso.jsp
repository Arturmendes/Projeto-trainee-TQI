<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="ISO-8859-1">
		<title>Primeiro Acesso</title>
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
						<img width="40px" height="40px" title="Login" alt="Login" src="resources/img/voltar.jpg">
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
					<form id="formUsuario" action="PrimeiroAcessoServlet" method="post" class="formUsuario">
						<table>
							<tr>
								<td><label>Login/CPF:</label></td>
								<td><input type="text" id="login" name="login" placeholder="Somente números" maxlength="11" value="${cliente.cpf}"></td>
								
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
								<td><input type="text" id="fone" name="fone" placeholder="(xx) 9 8888-8888" value="${cliente.telefone}"></td>
								
								<td><label>Cidade:</label></td>
								<td><input type="text" id="cidade" name="cidade" placeholder="Cidade" value="${cliente.cidade}"></td>								
							</tr>		
							<tr>
								<td></td>
								<td><input style="display: none;" type="text" id="id" name="id" placeholder="id"  value="${cliente.id}" readonly="readonly"></td>
								
								<td><label>Bairro:</label></td>
								<td><input type="text" id="bairro" name="bairro" placeholder="Bairro" value="${cliente.bairro}"></td>
							</tr>							
							<tr>
								<td></td>	
								<td><input style="display: none;" type="text" id="acao" name="acao" placeholder="acao"  value="cancelar" readonly="readonly"></td>						
								
								<td><label>Estado:</label></td>
								<td><input type="text" id="estado" name="estado" placeholder="Estado" value="${cliente.estado}"></td>
								
							</tr>
							<tr>
								<td></td>
								<td style="text-align: center"><input class="submit" type="submit" value="Cadastrar"></td>
								<td></td>
								<td style="text-align: center"> <input class="submit" type="submit" value="Cancelar" onclick="cancelar()"></td>								
							</tr>				
						</table>		
					</form>			
				</div>			
			</div>			
		</section>
		


		
		
		
		<script type="text/javascript">
		
			$("#fone").mask("(00) 00000-0000");
		
			function cancelar(){
				document.getElementById("formUsuario").method="get";
				document.getElementById("formUsuario").action="LoginServlet" ;
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