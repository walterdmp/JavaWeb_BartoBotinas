<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ include file="areaRestrita.jsp" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Cadastro de Funcionário</title>
        <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/style.css" />

        <script>
            function submitForm(opcaoValue) {
                document.getElementById("opcao").value = opcaoValue;
                document.getElementById("cadastroForm").submit();
            }

            function validarNumero(evt) {
                var theEvent = evt || window.event;
                var key = theEvent.keyCode || theEvent.which;
                key = String.fromCharCode(key);
                var regex = /[0-9]|\./;
                if (!regex.test(key)) {
                    theEvent.returnValue = false;
                    if (theEvent.preventDefault)
                        theEvent.preventDefault();
                }
            }
        </script>
    </head>
    <body>
        <header>
            Cadastro de Funcionário
        </header>

        <div class="container">
            <form id="cadastroForm" name="cadastroForm" action="${pageContext.request.contextPath}${URL_BASE}/FuncionarioControlador" method="get">
                <input type="hidden" name="opcao" id="opcao" value="${opcao}" />
                <input type="hidden" name="idFuncionario" value="${idFuncionario}" />

                <p>
                    <label for="nome"><strong>Nome:</strong></label>
                    <input type="text" id="nome" name="nome" value="${nome}" required placeholder="Digite o nome do funcionário" />
                </p>

                <p>
                    <label for="telefone"><strong>Telefone:</strong></label>
                    <input type="text" id="telefone" name="telefone" value="${telefone}" required placeholder="Digite o telefone do funcionário" onkeypress="validarNumero(event)" />
                </p>

                <p>
                    <label for="cargo"><strong>Cargo:</strong></label>
                    <input type="text" id="cargo" name="cargo" value="${cargo}" required placeholder="Digite o cargo do funcionário" />
                </p>

                <p>
                    <label for="salario"><strong>Salário:</strong></label>
                    <input type="number" step="0.01" id="salario" name="salario" value="${salario}" required placeholder="Digite o salário do funcionário" onkeypress="validarNumero(event)" />
                </p>

                <p>
                    <label for="endereco"><strong>Endereço:</strong></label>
                    <input type="text" id="endereco" name="endereco" value="${endereco}" required placeholder="Digite o endereço do funcionário" />
                </p>

                <input type="submit" name="Salvar" value="Salvar" />
            </form>

            <form name="cancelarForm" action="${pageContext.request.contextPath}${URL_BASE}/FuncionarioControlador" method="get">
                <input type="hidden" name="opcao" value="cancelar" />
                <input type="submit" name="Cancelar" value="Cancelar" />
            </form>

            <c:if test="${not empty mensagem}">
                <p class="message">${mensagem}</p>
            </c:if>
        </div>

        <div class="container">
            <h2>Lista de Funcionários</h2>

            <table>
                <c:if test="${not empty funcionarios}">
                    <tr>
                        <th>Nome</th>
                        <th>Telefone</th>
                        <th>Cargo</th>
                        <th>Salário</th>
                        <th>Endereço</th>
                        <th>Alterar</th>
                        <th>Excluir</th>
                    </tr>
                </c:if>

                <c:forEach var="funcionario" items="${funcionarios}">
                    <tr>
                        <td>${funcionario.nome}</td>
                        <td>${funcionario.telefone}</td>
                        <td>${funcionario.cargo}</td>
                        <td>${funcionario.salario}</td>
                        <td>${funcionario.endereco}</td>
                        <td>
                            <form name="editarForm" action="${pageContext.request.contextPath}${URL_BASE}/FuncionarioControlador" method="get">
                                <input type="hidden" name="idFuncionario" value="${funcionario.idFuncionario}" />
                                <input type="hidden" name="nome" value="${funcionario.nome}" />
                                <input type="hidden" name="telefone" value="${funcionario.telefone}" />
                                <input type="hidden" name="cargo" value="${funcionario.cargo}" />
                                <input type="hidden" name="salario" value="${funcionario.salario}" />
                                <input type="hidden" name="endereco" value="${funcionario.endereco}" />
                                <input type="hidden" name="opcao" value="editar" />
                                <button type="submit">Editar</button>
                            </form>
                        </td>
                        <td>
                            <form name="excluirForm" action="${pageContext.request.contextPath}${URL_BASE}/FuncionarioControlador" method="get">
                                <input type="hidden" name="idFuncionario" value="${funcionario.idFuncionario}" />
                                <input type="hidden" name="nome" value="${funcionario.nome}" />
                                <input type="hidden" name="telefone" value="${funcionario.telefone}" />
                                <input type="hidden" name="cargo" value="${funcionario.cargo}" />
                                <input type="hidden" name="salario" value="${funcionario.salario}" />
                                <input type="hidden" name="endereco" value="${funcionario.endereco}" />
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
