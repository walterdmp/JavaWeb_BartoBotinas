<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ include file="areaRestrita.jsp" %>

<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Cadastro de Pedido de Cliente</title>
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/style.css" />

        <script>
            function submitForm(opcaoValue) {
                document.getElementById("opcao").value = opcaoValue;
                document.getElementById("cadastroPedidoForm").submit();
            }

            function calcularTotal() {
                const quantidade = document.getElementById("quantidade").value;
                const preco = document.getElementById("produtoPedido").selectedOptions[0].getAttribute("data-preco");
                const total = quantidade * preco;
                document.getElementById("total").value = total.toFixed(2);
            }
        </script>
    </head>
    <body>
        <header>
            Cadastro de Pedido de Cliente
        </header>

        <div class="container">
            <form id="cadastroPedidoForm" name="cadastroPedidoForm" action="${pageContext.request.contextPath}${URL_BASE}/PedidoClienteControlador" method="get">
                <input type="hidden" name="opcao" id="opcao" value="${opcao}" />
                <input type="hidden" name="idPedidoCliente" value="${idPedidoCliente}" />
                <input type="hidden" id="total" name="total" value="${total}" />

                <p>
                    <label for="dataPedido"><strong>Data do Pedido:</strong></label>
                    <input type="date" id="dataPedido" name="dataPedido" value="${dataPedido}" required />
                </p>

                <p>
                    <label for="quantidade"><strong>Quantidade:</strong></label>
                    <input type="number" id="quantidade" name="quantidade" min="1" step="1" value="${quantidade}" required onchange="calcularTotal()" oninput="validarQuantidade(this)" placeholder="Digite a quantidade" />
                </p>



                <p>
                    <label for="clientePedido"><strong>Cliente:</strong></label>
                    <select id="clientePedido" name="clientePedido" required>
                        <option value="">Selecione um Cliente</option>
                        <c:forEach var="cliente" items="${clientes}">
                            <option value="${cliente.idCliente}" ${cliente.idCliente == clientePedido ? 'selected' : ''}>
                                ${cliente.nome}
                            </option>
                        </c:forEach>
                    </select>
                </p>

                <p>
                    <label for="produtoPedido"><strong>Produto:</strong></label>
                    <select id="produtoPedido" name="produtoPedido" required onchange="calcularTotal()">
                        <option value="">Selecione um Produto</option>
                        <c:forEach var="produto" items="${produtos}">
                            <option value="${produto.idProduto}" data-preco="${produto.preco}" ${produto.idProduto == produtoPedido ? 'selected' : ''}>
                                ${produto.descricao}
                            </option>
                        </c:forEach>
                    </select>
                </p>

                <input type="submit" name="Salvar" value="Salvar" />
            </form>

            <form name="cancelarForm" action="${pageContext.request.contextPath}${URL_BASE}/PedidoClienteControlador" method="get">
                <input type="hidden" name="opcao" value="cancelar" />
                <input type="submit" name="Cancelar" value="Cancelar" />
            </form>

            <div class="message">${mensagem}</div>

        </div>

        <div class="container">

            <h2>Lista de Pedidos de Clientes</h2>

            <table>
                <c:if test="${not empty pedidoClientes}">
                    <tr>
                        <th>ID</th>
                        <th>Data do Pedido</th>
                        <th>Quantidade</th>
                        <th>Total</th>
                        <th>Cliente</th>
                        <th>Produto</th>
                        <th>Alterar</th>
                        <th>Excluir</th>
                    </tr>
                </c:if>

                <c:forEach var="pedidoCliente" items="${pedidoClientes}">
                    <tr>
                        <td>${pedidoCliente.idPedidoCliente}</td>
                        <td>${pedidoCliente.dataPedido}</td>
                        <td>${pedidoCliente.quantidade}</td>
                        <td>${pedidoCliente.total}</td>
                        <td>${pedidoCliente.clientePedidoCliente.nome}</td>
                        <td>${pedidoCliente.produtoPedidoCliente.descricao}</td>
                        <td>
                            <form name="editarForm" action="${pageContext.request.contextPath}${URL_BASE}/PedidoClienteControlador" method="get">
                                <input type="hidden" name="idPedidoCliente" value="${pedidoCliente.idPedidoCliente}" />
                                <input type="hidden" name="dataPedido" value="${pedidoCliente.dataPedido}" />
                                <input type="hidden" name="quantidade" value="${pedidoCliente.quantidade}" />
                                <input type="hidden" name="total" value="${pedidoCliente.total}" />
                                <input type="hidden" name="clientePedido" value="${pedidoCliente.clientePedidoCliente.idCliente}" />
                                <input type="hidden" name="produtoPedido" value="${pedidoCliente.produtoPedidoCliente.idProduto}" />
                                <input type="hidden" name="opcao" value="editar" />
                                <button type="submit">Editar</button>
                            </form>
                        </td>
                        <td>
                            <form name="excluirForm" action="${pageContext.request.contextPath}${URL_BASE}/PedidoClienteControlador" method="get">
                                <input type="hidden" name="idPedidoCliente" value="${pedidoCliente.idPedidoCliente}" />
                                <input type="hidden" name="dataPedido" value="${pedidoCliente.dataPedido}" />
                                <input type="hidden" name="quantidade" value="${pedidoCliente.quantidade}" />
                                <input type="hidden" name="total" value="${pedidoCliente.total}" />
                                <input type="hidden" name="clientePedido" value="${pedidoCliente.clientePedidoCliente.idCliente}" />
                                <input type="hidden" name="produtoPedido" value="${pedidoCliente.produtoPedidoCliente.idProduto}" />
                                <input type="hidden" name="opcao" value="excluir" />
                                <button type="submit">Excluir</button>
                            </form>
                        </td>
                    </tr>
                </c:forEach>
            </table>
        </div>
    </body>
</html>
