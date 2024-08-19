<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ include file="areaRestrita.jsp" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Cadastro de Fornecedor</title>
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
            Cadastro de Fornecedor
        </header>

        <div class="container">
            <form id="cadastroForm" name="cadastroForm" action="${pageContext.request.contextPath}${URL_BASE}/FornecedorControlador" method="get">
                <input type="hidden" name="opcao" id="opcao" value="${opcao}" />
                <input type="hidden" name="idFornecedor" value="${idFornecedor}" />

                <p>
                    <label for="nome"><strong>Nome:</strong></label>
                    <input type="text" id="nome" name="nome" value="${nome}" required placeholder="Digite o nome do fornecedor" />
                </p>

                <p>
                    <label for="cnpj"><strong>CNPJ:</strong></label>
                    <input type="text" id="cnpj" name="cnpj" value="${cnpj}" required placeholder="Digite o CNPJ do fornecedor" oninput="allowOnlyNumbers(this)" />
                </p>

                <p>
                    <label for="telefone"><strong>Telefone:</strong></label>
                    <input type="text" id="telefone" name="telefone" value="${telefone}" required placeholder="Digite o telefone do fornecedor" oninput="allowOnlyNumbers(this)" />
                </p>

                <p>
                    <label for="email"><strong>Email:</strong></label>
                    <input type="email" id="email" name="email" value="${email}" required placeholder="Digite o email do fornecedor" />
                </p>

                <p>
                    <label for="endereco"><strong>Endereço:</strong></label>
                    <input type="text" id="endereco" name="endereco" value="${endereco}" required placeholder="Digite o endereço do fornecedor" maxlength="200" />
                </p>

                <input type="submit" name="Salvar" value="Salvar" />
            </form>

            <form name="cancelarForm" action="${pageContext.request.contextPath}${URL_BASE}/FornecedorControlador" method="get">
                <input type="hidden" name="opcao" value="cancelar" />
                <input type="submit" name="Cancelar" value="Cancelar" />
            </form>
            <c:if test="${not empty mensagem}">
                <p class="message">${mensagem}</p>
            </c:if>
        </div>

        <div class="container">
            <h2>Lista de Fornecedores</h2>

            <table>
                <c:if test="${not empty fornecedores}">
                    <tr>
                        <th>Nome</th>
                        <th>CNPJ</th>
                        <th>Telefone</th>
                        <th>Email</th>
                        <th>Endereço</th>
                        <th>Alterar</th>
                        <th>Excluir</th>
                    </tr>
                </c:if>

                <c:forEach var="fornecedor" items="${fornecedores}">
                    <tr>
                        <td>${fornecedor.nome}</td>
                        <td>${fornecedor.cnpj}</td>
                        <td>${fornecedor.telefone}</td>
                        <td>${fornecedor.email}</td>
                        <td>${fornecedor.endereco}</td>
                        <td>
                            <form name="editarForm" action="${pageContext.request.contextPath}${URL_BASE}/FornecedorControlador" method="get">
                                <input type="hidden" name="idFornecedor" value="${fornecedor.idFornecedor}" />
                                <input type="hidden" name="nome" value="${fornecedor.nome}" />
                                <input type="hidden" name="cnpj" value="${fornecedor.cnpj}" />
                                <input type="hidden" name="telefone" value="${fornecedor.telefone}" />
                                <input type="hidden" name="email" value="${fornecedor.email}" />
                                <input type="hidden" name="endereco" value="${fornecedor.endereco}" />
                                <input type="hidden" name="opcao" value="editar" />
                                <button type="submit">Editar</button>
                            </form>
                        </td>
                        <td>
                            <form name="excluirForm" action="${pageContext.request.contextPath}${URL_BASE}/FornecedorControlador" method="get">
                                <input type="hidden" name="idFornecedor" value="${fornecedor.idFornecedor}" />
                                <input type="hidden" name="nome" value="${fornecedor.nome}" />
                                <input type="hidden" name="cnpj" value="${fornecedor.cnpj}" />
                                <input type="hidden" name="telefone" value="${fornecedor.telefone}" />
                                <input type="hidden" name="email" value="${fornecedor.email}" />
                                <input type="hidden" name="endereco" value="${fornecedor.endereco}" />
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
