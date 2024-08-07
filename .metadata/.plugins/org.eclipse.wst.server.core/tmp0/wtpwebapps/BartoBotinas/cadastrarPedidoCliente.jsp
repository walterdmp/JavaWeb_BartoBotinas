<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="css/estilo.css">
<meta charset="UTF-8">
<title>Bartô Botinas - Pedido Cliente</title>
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
		<form action="cadastrarPedidoCliente" method="post" class="bartoForm">
			<div>
				<label for="cliente"><strong>Cliente:</strong></label> <select
					id="cliente" name="idCliente" required>
					<option value="">Selecione um cliente</option>
					<c:forEach var="cliente" items="${clientes}">
						<option value="${cliente[0]}">${cliente[1]}</option>
					</c:forEach>
				</select>
			</div>
			<div>
				<label for="data"><strong>Data:</strong></label> <input type="date"
					id="data" name="dataPedido" required>
			</div>
			<div>
				<label for="produto"><strong>Produto:</strong></label> <select
					id="produto" name="idProduto" required>
					<option value="">Selecione um produto</option>
					<c:forEach var="produto" items="${produtos}">
						<option value="${produto[0]}">${produto[1]}</option>
					</c:forEach>
				</select>
			</div>
			<div>
				<label for="quantidade"><strong>Quantidade:</strong></label> <input
					type="number" id="quantidade" name="quantidade" required>
			</div>
			<br>
			<div>
				<input id="bartoButton" type="submit" name="salvar"
					value="Cadastrar Pedido Cliente">
			</div>
		</form>
	</main>
</body>
</html>