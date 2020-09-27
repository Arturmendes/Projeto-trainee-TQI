<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="ISO-8859-1"><!--Funciona em jsp-->
		<!--<meta charset="UTF-8" />--><!--Funciona em html-->
		<meta name="viewport" content="width=device-width, initial-scale=1.0">
		<title>Login</title>
		<link rel="stylesheet" type="text/css" href="resources/css/index.css">
	</head>
	<body>
		<header>
			<div class="container">
				<div class="titulo">
					<h1>$$ Financeira Crédito Fácil $$</h1>
				</div>
				<div class="divMsg">
					<h3 class="msg">${msg}</h3>
				</div>
			</div>			
		</header>
		<section id=form>
			<div class="container">
				<div class="divForm">
					<p>Admin: 07652661641</p>
					<p>Senha: 123</p>
					<br/>
					<p>Cliente: 32307780653</p>					
					<p class="ultimo">Senha: 123</p>
					<form action="LoginServlet" method="post" class="form">
						<label>CPF/Login:</label>
						<input type="text" id="login" name="login" placeholder="Somente números">
						
						<label>Senha:</label>
						<input type="password" id="senha" name="senha">
						
						<input type="submit" value="Logar">		
					</form>
				</div>
			</div>			
		</section>
		
		<section id="primeiroAcesso">
			<div class="container">
				<div class="link">
					<a href="primeiroAcesso.jsp">Primeiro Acesso</a>
				</div>				
			</div>		
		</section>
	</body>
</html>