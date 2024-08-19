<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ include file="areaRestrita.jsp" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Cadastro de Pagamento</title>
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/style.css" />

    </head>
    <body>
        <header>
            Cadastro de Pagamento
        </header>

        <div class="container">
            <form id="cadastroForm" name="cadastroForm" action="${pageContext.request.contextPath}${URL_BASE}/PagamentoControlador" method="get">
                <input type="hidden" name="opcao" value="${opcao}" />
                <input type="hidden" name="idPagamento" value="${idPagamento}" />

                <p>
                    <label for="dataPagamento"><strong>Data de Pagamento:</strong></label>
                    <input type="date" id="dataPagamento" name="dataPagamento" value="${dataPagamento}" required />
                </p>

                <p>
                    <label for="descricao"><strong>Descrição:</strong></label>
                    <input type="text" id="descricao" name="descricao" value="${descricao}" maxlength="100" placeholder="Descrição do pagamento" required />
                </p>

                <p>
                    <label for="valor"><strong>Valor:</strong></label>
                    <input type="number" step="0.01" id="valor" name="valor" value="${valor}" required min="0" placeholder="Ex: 100.00" />
                </p>

                <p>
                    <label><strong>Status de Pagamento:</strong></label>
                    <input type="checkbox" id="pago" name="pago" value="true" ${pago ? 'checked' : ''} />
                    <label for="pago">Pago</label>
                </p>



                <input type="submit" name="Salvar" value="Salvar" />
            </form>

            <form name="cancelarForm" action="${pageContext.request.contextPath}${URL_BASE}/PagamentoControlador" method="get">
                <input type="hidden" name="opcao" value="cancelar" />
                <input type="submit" name="Cancelar" value="Cancelar" />
            </form>
            <c:if test="${not empty mensagem}">
                <p class="message">${mensagem}</p>
            </c:if>
        </div>

        <div class="container">
            <h2>Lista de Pagamentos</h2>

            <table>
                <c:if test="${not empty pagamentos}">
                    <tr>
                        <th>Data de Pagamento</th>
                        <th>Descrição</th>
                        <th>Valor</th>
                        <th>Status</th>
                        <th>Alterar</th>
                        <th>Excluir</th>
                    </tr>
                </c:if>

                <c:forEach var="pagamento" items="${pagamentos}">
                    <tr>
                        <td>${pagamento.dataPagamento}</td>
                        <td>${pagamento.descricao}</td>
                        <td>${pagamento.valor}</td>
                        <td>${pagamento.pago ? 'Pago' : 'Pendente'}</td>
                        <td>
                            <form name="editarForm" action="${pageContext.request.contextPath}${URL_BASE}/PagamentoControlador" method="get">
                                <input type="hidden" name="idPagamento" value="${pagamento.idPagamento}" />
                                <input type="hidden" name="dataPagamento" value="${pagamento.dataPagamento}" />
                                <input type="hidden" name="descricao" value="${pagamento.descricao}" />
                                <input type="hidden" name="valor" value="${pagamento.valor}" />
                                <input type="hidden" name="pago" value="${pagamento.pago}" />
                                <input type="hidden" name="opcao" value="editar" />
                                <button type="submit">Editar</button>
                            </form>
                        </td>
                        <td>
                            <form name="excluirForm" action="${pageContext.request.contextPath}${URL_BASE}/PagamentoControlador" method="get">
                                <input type="hidden" name="idPagamento" value="${pagamento.idPagamento}" />
                                <input type="hidden" name="dataPagamento" value="${pagamento.dataPagamento}" />
                                <input type="hidden" name="descricao" value="${pagamento.descricao}" />
                                <input type="hidden" name="valor" value="${pagamento.valor}" />
                                <input type="hidden" name="pago" value="${pagamento.pago}" />
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