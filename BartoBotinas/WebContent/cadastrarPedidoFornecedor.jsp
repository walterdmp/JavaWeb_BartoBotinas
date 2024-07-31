<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="css/estilo.css">
<meta charset="UTF-8">
<title>Bartô Botinas - Pedido Fornecedor</title>
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
		<form action="cadastrarPedidoFornecedor" method="post"
			class="bartoForm">
			<div>
				<label for="fornecedor"><strong>Fornecedor:</strong></label> <select
					id="fornecedor" name="idFornecedor" required>
					<option value="">Selecione um fornecedor</option>
					<c:forEach var="fornecedor" items="${fornecedores}">
						<option value="${fornecedor[0]}">${fornecedor[1]}</option>
					</c:forEach>
				</select>
			</div>
			<div>
				<label for="data"><strong>Data:</strong></label> <input type="date"
					id="data" name="data" required>
			</div>
			<div>
				<label for="total"><strong>Total:</strong></label> <input
					type="number" step="0.01" id="total" name="total" required>
			</div>
			<br>
			<div>
				<input id="bartoButton" type="submit" name="salvar"
					value="Cadastrar Pedido Fornecedor">
			</div>
		</form>
	</main>
</body>
</html>
