<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ include file="areaRestrita.jsp" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Cadastro de Produto</title>
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
            Cadastro de Produto
        </header>

        <div class="container">
            <form id="cadastroForm" name="cadastroForm" action="${pageContext.request.contextPath}${URL_BASE}/ProdutoControlador" method="get">
                <input type="hidden" name="opcao" value="${opcao}" />
                <input type="hidden" name="idProduto" value="${idProduto}" />

                <p>
                    <label for="descricao"><strong>Descrição:</strong></label>
                    <input type="text" id="descricao" name="descricao" value="${descricao}" required placeholder="Digite a descrição" />
                </p>

                <p>
                    <label for="preco"><strong>Preço:</strong></label>
                    <input type="number" id="preco" name="preco" value="${preco}" required step="0.01" placeholder="Digite o preço" />
                </p>

                <p>
                    <label for="quantidade"><strong>Quantidade:</strong></label>
                    <input type="number" id="quantidade" name="quantidade" value="${quantidade}" required min="1" step="1" placeholder="Digite a quantidade" />
                </p>


                <input type="submit" name="Salvar" value="Salvar" />
            </form>

            <form name="cancelarForm" action="${pageContext.request.contextPath}${URL_BASE}/ProdutoControlador" method="get">
                <input type="hidden" name="opcao" value="cancelar" />
                <input type="submit" name="Cancelar" value="Cancelar" />
            </form>

            <c:if test="${not empty mensagem}">
                <p class="message">${mensagem}</p>
            </c:if>

            <h2>Lista de Produtos</h2>

            <table>
                <c:if test="${not empty produtos}">
                    <tr>
                        <th>Descrição</th>
                        <th>Preço</th>
                        <th>Quantidade</th>
                        <th>Alterar</th>
                        <th>Excluir</th>
                    </tr>
                </c:if>

                <c:forEach var="produto" items="${produtos}">
                    <tr>
                        <td>${produto.descricao}</td>
                        <td>${produto.preco}</td>
                        <td>${produto.quantidade}</td>
                        <td>
                            <form name="editarForm" action="${pageContext.request.contextPath}${URL_BASE}/ProdutoControlador" method="get">
                                <input type="hidden" name="idProduto" value="${produto.idProduto}" />
                                <input type="hidden" name="descricao" value="${produto.descricao}" />
                                <input type="hidden" name="preco" value="${produto.preco}" />
                                <input type="hidden" name="quantidade" value="${produto.quantidade}" />
                                <input type="hidden" name="opcao" value="editar" />
                                <button type="submit">Editar</button>
                            </form>
                        </td>
                        <td>
                            <form name="excluirForm" action="${pageContext.request.contextPath}${URL_BASE}/ProdutoControlador" method="get">
                                <input type="hidden" name="idProduto" value="${produto.idProduto}" />
                                <input type="hidden" name="descricao" value="${produto.descricao}" />
                                <input type="hidden" name="preco" value="${produto.preco}" />
                                <input type="hidden" name="quantidade" value="${produto.quantidade}" />
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
