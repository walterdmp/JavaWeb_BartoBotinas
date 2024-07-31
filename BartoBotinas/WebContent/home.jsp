<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="css/estilo.css">
<meta charset="UTF-8">
<title>Bartô Botinas - Home</title>
</head>
<body>
	<nav class="bartoNav">
		<ul>
			<li><a href="home.jsp">Home</a></li>
			<li><a href="cadastrarCliente.jsp">Cliente</a></li>
			<li><a href="cadastrarFornecedor.jsp">Fornecedor</a></li>
			<li><a href="cadastrarPedidoCliente.jsp">Pedido Cliente</a></li>
			<li><a href="cadastrarPedidoFornecedor.jsp">Pedido
					Fornecedor</a></li>
			<li><a href="cadastrarProdutos.jsp">Produtos</a></li>
		</ul>
	</nav>
	<main class="bartoMain">
		<div class="empresaInfo">
			<h1>Informações da Empresa</h1>
			<p>
				<strong>Nome:</strong> ${nome}
			</p>
			<p>
				<strong>CNPJ:</strong> ${cnpj}
			</p>
			<p>
				<strong>Telefone:</strong> ${telefone}
			</p>
			<p>
				<strong>Email:</strong> ${email}
			</p>
		</div>
	</main>
</body>
</html>
