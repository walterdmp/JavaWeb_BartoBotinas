<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ include file="areaRestrita.jsp" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Cadastro de Cliente</title>
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/style.css" />
        <script>
            function submitForm(opcaoValue) {
                document.getElementById("opcao").value = opcaoValue;
                document.getElementById("cadastroForm").submit();
            }
            function allowOnlyNumbers(input) {
                input.value = input.value.replace(/[^0-9]/g, '');
            }
        </script>
    </head>
    <body>
        <header>
            Cadastro de Cliente
        </header>

        <div class="container">
            <form id="cadastroForm" name="cadastroForm" action="${pageContext.request.contextPath}${URL_BASE}/ClienteControlador" method="get">
                <input type="hidden" name="opcao" id="opcao" value="${opcao}" />
                <input type="hidden" name="idCliente" value="${idCliente}" />

                <p>
                    <label for="nome"><strong>Nome:</strong></label>
                    <input type="text" id="nome" name="nome" value="${nome}" required placeholder="Digite o nome do cliente" />
                </p>

                <p>
                    <label for="cpf"><strong>CPF:</strong></label>
                    <input type="text" id="cpf" name="cpf" value="${cpf}" required placeholder="Digite o CPF do cliente" oninput="allowOnlyNumbers(this)" />
                </p>


                <p>
                    <label for="telefone"><strong>Telefone:</strong></label>
                    <input type="text" id="telefone" name="telefone" value="${telefone}" required placeholder="Digite o telefone do cliente" oninput="allowOnlyNumbers(this)" />
                </p>

                <p>
                    <label for="email"><strong>Email:</strong></label>
                    <input type="email" id="email" name="email" value="${email}" required placeholder="Digite o email do cliente" />
                </p>

                <p>
                    <label for="endereco"><strong>Endereço:</strong></label>
                    <input type="text" id="endereco" name="endereco" value="${endereco}" required placeholder="Digite o endereço do cliente" maxlength="200" />
                </p>

                <input type="submit" name="Salvar" value="Salvar" />
            </form>

            <form name="cancelarForm" action="${pageContext.request.contextPath}${URL_BASE}/ClienteControlador" method="get">
                <input type="hidden" name="opcao" value="cancelar" />
                <input type="submit" name="Cancelar" value="Cancelar" />
            </form>
            <c:if test="${not empty mensagem}">
                <p class="message">${mensagem}</p>
            </c:if>
        </div>

        <div class="container">
            <h2>Lista de Clientes</h2>

            <table>
                <c:if test="${not empty clientes}">
                    <tr>
                        <th>Nome</th>
                        <th>CPF</th>
                        <th>Telefone</th>
                        <th>Email</th>
                        <th>Endereço</th>
                        <th>Alterar</th>
                        <th>Excluir</th>
                    </tr>
                </c:if>

                <c:forEach var="cliente" items="${clientes}">
                    <tr>
                        <td>${cliente.nome}</td>
                        <td>${cliente.cpf}</td>
                        <td>${cliente.telefone}</td>
                        <td>${cliente.email}</td>
                        <td>${cliente.endereco}</td>
                        <td>
                            <form name="editarForm" action="${pageContext.request.contextPath}${URL_BASE}/ClienteControlador" method="get">
                                <input type="hidden" name="idCliente" value="${cliente.idCliente}" />
                                <input type="hidden" name="nome" value="${cliente.nome}" />
                                <input type="hidden" name="cpf" value="${cliente.cpf}" />
                                <input type="hidden" name="telefone" value="${cliente.telefone}" />
                                <input type="hidden" name="email" value="${cliente.email}" />
                                <input type="hidden" name="endereco" value="${cliente.endereco}" />
                                <input type="hidden" name="opcao" value="editar" />
                                <button type="submit">Editar</button>
                            </form>
                        </td>
                        <td>
                            <form name="excluirForm" action="${pageContext.request.contextPath}${URL_BASE}/ClienteControlador" method="get">
                                <input type="hidden" name="idCliente" value="${cliente.idCliente}" />
                                <input type="hidden" name="nome" value="${cliente.nome}" />
                                <input type="hidden" name="cpf" value="${cliente.cpf}" />
                                <input type="hidden" name="telefone" value="${cliente.telefone}" />
                                <input type="hidden" name="email" value="${cliente.email}" />
                                <input type="hidden" name="endereco" value="${cliente.endereco}" />
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
