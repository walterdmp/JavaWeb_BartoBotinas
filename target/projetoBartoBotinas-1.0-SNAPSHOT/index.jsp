<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html" pageEncoding="UTF-8" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Bartô Botinas - Página Inicial</title>
        <style>
            body {
                font-family: Arial, sans-serif;
                background-color: #f4f4f4;
                color: #333;
                margin: 0;
                padding: 0;
            }

            header {
                background-color: #FF9800; /* Laranja para o cabeçalho */
                color: #fff;
                padding: 40px 0; /* Aumentando o padding para aumentar o tamanho do cabeçalho */
                text-align: center;
                position: relative;
                overflow: hidden;
            }

            header .logo h1 {
                margin: 0;
                font-size: 42px; /* Aumentando o tamanho da fonte */
                text-transform: uppercase;
                letter-spacing: 2px;
                animation: zoomIn 0.6s ease-in-out;
            }

            @keyframes zoomIn {
                from {
                    transform: scale(0.5);
                    opacity: 0;
                }
                to {
                    transform: scale(1);
                    opacity: 1;
                }
            }

            .logo a {
                color: #fff;
                text-decoration: none;
            }

            .menu {
                text-align: right;
                padding: 10px 20px;
                position: absolute;
                top: 20px;
                right: 20px;
            }

            .menu a {
                color: #fff;
                text-decoration: none;
                font-weight: bold;
            }

            .container {
                max-width: 800px;
                margin: 40px auto;
                padding: 20px;
                background-color: #fff;
                border-radius: 8px;
                box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
                text-align: center;
            }

            h1 {
                font-size: 32px;
                margin-bottom: 20px;
            }

            p {
                font-size: 18px;
                line-height: 1.6;
                margin: 20px 0;
            }

            table {
                width: 100%;
                border-collapse: collapse;
                margin-top: 20px;
            }

            th, td {
                padding: 10px;
                border-bottom: 1px solid #ddd;
                text-align: left;
            }

            th {
                background-color: #f4f4f4;
                font-weight: bold;
            }
        </style>
    </head>
    <body>
        <header>
            <div class="center">
                <div class="logo">
                    <h1><a href="${pageContext.request.contextPath}/index.jsp">Bartô Botinas</a></h1>
                </div>
                <div class="menu">
                    <a href="${pageContext.request.contextPath}/paginaLogin.jsp" class="areaRestrita">Área Restrita</a>
                </div>
            </div>
        </header>

        <section class="container">
            <h1>Bem-vindo à Bartô Botinas</h1>
            <p>A Bartô Botinas é uma empresa renomada no setor de calçados, conhecida por sua dedicação à qualidade e ao conforto. Com anos de experiência, nos especializamos em oferecer produtos que aliam estilo e durabilidade, sempre priorizando a satisfação de nossos clientes.</p>
            <p>Explore nossa vasta gama de produtos e descubra por que somos a escolha preferida de tantos consumidores. Estamos comprometidos em proporcionar a você a melhor experiência de compra, desde a seleção de produtos até o atendimento pós-venda.</p>

            <table border="1">
                <tr>
                    <th>Nome</th>
                    <td>Bartô Botinas</td>
                </tr>
                <tr>
                    <th>CNPJ</th>
                    <td>12.345.678/0001-99</td>
                </tr>
                <tr>
                    <th>Telefone</th>
                    <td>(35) 3295-4457</td>
                </tr>
                <tr>
                    <th>Email</th>
                    <td>bartobotinas@hotmail.com</td>
                </tr>
                <tr>
                    <th>Endereço</th>
                    <td>Rua Dom Hugo 307 - Machado - MG</td>
                </tr>
            </table>
        </section>
    </body>
</html>
