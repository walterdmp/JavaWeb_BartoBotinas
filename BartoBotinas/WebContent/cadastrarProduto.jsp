<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="css/estilo.css">
<meta charset="UTF-8">
<title>Bartô Botinas - Produtos</title>
</head>
<body>
	<nav class="bartoNav">
		<ul>
			<li><a href="home.jsp">Home</a></li>
			<li><a href="cadastrarCaixa.jsp">Caixa</a></li>
			<li><a href="cadastrarCliente.jsp">Cliente</a></li>
			<li><a href="cadastrarFornecedor.jsp">Fornecedor</a></li>
			<li><a href="cadastrarFuncionario.jsp">Funcionário</a></li>
			<li><a href="cadastrarPedidoCliente.jsp">Pedido Cliente</a></li>
			<li><a href="cadastrarPedidoFornecedor.jsp">Pedido
					Fornecedor</a></li>
			<li><a href="cadastrarProduto.jsp">Produtos</a></li>
		</ul>
	</nav>
	<main class="bartoMain">
		<form action="cadastrarProduto" method="post" class="bartoForm">
			<div>
				<label for="descricao"><strong>Descrição:</strong></label> <input
					type="text" id="descricao" name="descricao"
					placeholder="Descrição do produto" required>
			</div>
			<div>
				<label for="preco"><strong>Preço:</strong></label> <input
					type="number" step="0.01" id="preco" name="preco" min="0"
					placeholder="Preço do produto" required>
			</div>
			<div>
				<label for="quantidade"><strong>Quantidade:</strong></label> <input
					type="number" id="quantidade" name="quantidade"
					placeholder="Quantidade do produto" required>
			</div>
			<br>
			<div>
				<input id="bartoButton" type="submit" name="salvar"
					value="Cadastrar Produto">
			</div>
		</form>
	</main>
</body>
</html>
