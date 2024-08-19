<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ include file="areaRestrita.jsp" %>

<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Cadastro de Pedido de Fornecedor</title>
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/style.css" />

        <script>
            function submitForm(opcaoValue) {
                document.getElementById("opcao").value = opcaoValue;
                document.getElementById("cadastroForm").submit();
            }
        </script>
    </head>
    <body>
        <header>
            Cadastro de Pedido de Fornecedor
        </header>

        <div class="container">
            <form id="cadastroForm" name="cadastroForm" action="${pageContext.request.contextPath}${URL_BASE}/PedidoFornecedorControlador" method="get">
                <input type="hidden" name="opcao" id="opcao" value="${opcao}" />
                <input type="hidden" name="idPedidoFornecedor" value="${idPedidoFornecedor}" />

                <p>
                    <label for="dataPedido"><strong>Data do Pedido:</strong></label>
                    <input type="date" id="dataPedido" name="dataPedido" value="${dataPedido}" required />
                </p>

                <p>
                    <label for="quantidade"><strong>Quantidade:</strong></label>
                    <input type="number" id="quantidade" name="quantidade" min="1" step="1" value="${quantidade}" required placeholder="Digite a quantidade" />
                </p>


                <p>
                    <label for="fornecedorPedido"><strong>Fornecedor:</strong></label>
                    <select id="fornecedorPedido" name="fornecedorPedido" required>
                        <option value="">Selecione um Fornecedor</option>
                        <c:forEach var="fornecedor" items="${fornecedores}">
                            <option value="${fornecedor.idFornecedor}" ${fornecedor.idFornecedor == fornecedorPedido ? 'selected' : ''}>
                                ${fornecedor.nome}
                            </option>
                        </c:forEach>
                    </select>
                </p>

                <p>
                    <label for="produtoPedido"><strong>Produto:</strong></label>
                    <select id="produtoPedido" name="produtoPedido" required>
                        <option value="">Selecione um Produto</option>
                        <c:forEach var="produto" items="${produtos}">
                            <option value="${produto.idProduto}" ${produto.idProduto == produtoPedido ? 'selected' : ''}>
                                ${produto.descricao}
                            </option>
                        </c:forEach>
                    </select>
                </p>

                <input type="submit" name="Salvar" value="Salvar" />
            </form>

            <form name="cancelarForm" action="${pageContext.request.contextPath}${URL_BASE}/PedidoFornecedorControlador" method="get">
                <input type="hidden" name="opcao" value="cancelar" />
                <input type="submit" name="Cancelar" value="Cancelar" />
            </form>

            <c:if test="${not empty mensagem}">
                <p class="message">${mensagem}</p>
            </c:if>
        </div>   

        <div class="container">

            <h2>Lista de Pedidos de Fornecedor</h2>
            <table>
                <c:if test="${not empty pedidoFornecedores}">
                    <tr>
                        <th>ID</th>
                        <th>Data do Pedido</th>
                        <th>Quantidade</th>
                        <th>Fornecedor</th>
                        <th>Produto</th>
                        <th>Editar</th>
                        <th>Excluir</th>
                    </tr>
                </c:if>

                <c:forEach var="pedidoFornecedor" items="${pedidoFornecedores}">
                    <tr>
                        <td>${pedidoFornecedor.idPedidoFornecedor}</td>
                        <td>${pedidoFornecedor.dataPedido}</td>
                        <td>${pedidoFornecedor.quantidade}</td>
                        <td>${pedidoFornecedor.fornecedorPedidoFornecedor.nome}</td>
                        <td>${pedidoFornecedor.produtoPedidoFornecedor.descricao}</td>
                        <td>
                            <form name="editarForm" action="${pageContext.request.contextPath}${URL_BASE}/PedidoFornecedorControlador" method="get">
                                <input type="hidden" name="idPedidoFornecedor" value="${pedidoFornecedor.idPedidoFornecedor}" />
                                <input type="hidden" name="dataPedido" value="${pedidoFornecedor.dataPedido}" />
                                <input type="hidden" name="quantidade" value="${pedidoFornecedor.quantidade}" />
                                <input type="hidden" name="fornecedorPedido" value="${pedidoFornecedor.fornecedorPedidoFornecedor.idFornecedor}" />
                                <input type="hidden" name="produtoPedido" value="${pedidoFornecedor.produtoPedidoFornecedor.idProduto}" />
                                <input type="hidden" name="opcao" value="editar" />
                                <button type="submit">Editar</button>
                            </form>
                        </td>
                        <td>
                            <form name="excluirForm" action="${pageContext.request.contextPath}${URL_BASE}/PedidoFornecedorControlador" method="get">
                                <input type="hidden" name="idPedidoFornecedor" value="${pedidoFornecedor.idPedidoFornecedor}" />
                                <input type="hidden" name="dataPedido" value="${pedidoFornecedor.dataPedido}" />
                                <input type="hidden" name="quantidade" value="${pedidoFornecedor.quantidade}" />
                                <input type="hidden" name="fornecedorPedido" value="${pedidoFornecedor.fornecedorPedidoFornecedor.idFornecedor}" />
                                <input type="hidden" name="produtoPedido" value="${pedidoFornecedor.produtoPedidoFornecedor.idProduto}" />
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
