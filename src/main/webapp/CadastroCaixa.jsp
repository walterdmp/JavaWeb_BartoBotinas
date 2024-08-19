<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ include file="areaRestrita.jsp" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Cadastro de Caixa</title>
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
            Cadastro do Caixa
        </header>

        <div class="container">
            <form id="cadastroForm" name="cadastroForm" action="${pageContext.request.contextPath}${URL_BASE}/CaixaControlador" method="get">
                <input type="hidden" name="opcao" value="${opcao}" />
                <input type="hidden" name="idCaixa" value="${idCaixa}" />

                <p>
                    <label for="tipo"><strong>Tipo:</strong></label>
                    <select id="tipo" name="tipo" required>
                        <option value="" disabled selected>Selecione um tipo</option>
                        <option value="Entrada" ${tipo == 'Entrada' ? 'selected' : ''}>Entrada</option>
                        <option value="Saida" ${tipo == 'Saida' ? 'selected' : ''}>Saída</option>
                    </select>
                </p>

                <p>
                    <label for="valor"><strong>Valor:</strong></label>
                    <input type="number" step="0.01" id="valor" name="valor" value="${valor}" required min="0" placeholder="Ex: 100.00" />
                </p>

                <p>
                    <label for="descricao"><strong>Descrição:</strong></label>
                    <input type="text" id="descricao" name="descricao" value="${descricao}" placeholder="Ex: Pagamento de conta" maxlength="100" />
                </p>

                <p>
                    <label for="data"><strong>Data:</strong></label>
                    <input type="date" id="data" name="data" value="${data}" required />
                </p>

                <input type="submit" name="Salvar" value="Salvar" />
            </form>

            <form name="cancelarForm" action="${pageContext.request.contextPath}${URL_BASE}/CaixaControlador" method="get">
                <input type="hidden" name="opcao" value="cancelar" />
                <input type="submit" name="Cancelar" value="Cancelar" />
            </form>
            <c:if test="${not empty mensagem}">
                <p class="message">${mensagem}</p>
            </c:if>
        </div>

        <div class="container">
            <h2>Lista do Caixa</h2>

            <table>
                <c:if test="${not empty caixas}">
                    <tr>
                        <th>Tipo</th>
                        <th>Valor</th>
                        <th>Descrição</th>
                        <th>Data</th>
                        <th>Alterar</th>
                        <th>Excluir</th>
                    </tr>
                </c:if>

                <c:forEach var="caixa" items="${caixas}">
                    <tr>
                        <td>${caixa.tipo}</td>
                        <td>${caixa.valor}</td>
                        <td>${caixa.descricao}</td>
                        <td>${caixa.data}</td>
                        <td>
                            <form name="editarForm" action="${pageContext.request.contextPath}${URL_BASE}/CaixaControlador" method="get">
                                <input type="hidden" name="idCaixa" value="${caixa.idCaixa}" />
                                <input type="hidden" name="tipo" value="${caixa.tipo}" />
                                <input type="hidden" name="valor" value="${caixa.valor}" />
                                <input type="hidden" name="descricao" value="${caixa.descricao}" />
                                <input type="hidden" name="data" value="${caixa.data}" />
                                <input type="hidden" name="opcao" value="editar" />
                                <button type="submit">Editar</button>
                            </form>
                        </td>
                        <td>
                            <form name="excluirForm" action="${pageContext.request.contextPath}${URL_BASE}/CaixaControlador" method="get">
                                <input type="hidden" name="idCaixa" value="${caixa.idCaixa}" />
                                <input type="hidden" name="tipo" value="${caixa.tipo}" />
                                <input type="hidden" name="valor" value="${caixa.valor}" />
                                <input type="hidden" name="descricao" value="${caixa.descricao}" />
                                <input type="hidden" name="data" value="${caixa.data}" />
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
