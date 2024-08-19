<!DOCTYPE html>
<html lang="pt">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Área Restrita</title>

        <style>
            * {
                font-family: sans-serif, Verdana;
            }
            body {
                margin: 0;
                background-color: #f4f4f4;
            }

            nav {
                background-color: #ff8c00;
                padding: 15px 20px;
                position: sticky;
                top: 0;
                z-index: 1000;
            }

            nav ul {
                font-size: 16px;
                list-style-type: none;
                padding: 0;
                margin: 0;
                display: flex;
                flex-wrap: wrap;
                justify-content: center;
            }

            nav ul li {
                margin: 0 15px;
            }

            nav ul li a {
                color: #fff;
                text-decoration: none;
                font-weight: bold;
                padding: 12px 18px;
                display: block;
                border-radius: 5px;
                transition: background-color 0.1s ease, transform 0.3s ease; 
            }

            nav ul li a:hover {
                background-color: #e67e00;
                color: #ddd;
                transform: scale(1.1); 
            }

        </style>
    </head>
    <body>
        <nav>
            <ul>
                <li><a href="${pageContext.request.contextPath}${URL_BASE}/CaixaControlador?opcao=cancelar">Caixa</a></li>
                <li><a href="${pageContext.request.contextPath}${URL_BASE}/ClienteControlador?opcao=cancelar">Cliente</a></li>
                <li><a href="${pageContext.request.contextPath}${URL_BASE}/FornecedorControlador?opcao=cancelar">Fornecedor</a></li>
                <li><a href="${pageContext.request.contextPath}${URL_BASE}/FuncionarioControlador?opcao=cancelar">Funcionário</a></li>
                <li><a href="${pageContext.request.contextPath}${URL_BASE}/PagamentoControlador?opcao=cancelar">Pagamento</a></li>
                <li><a href="${pageContext.request.contextPath}${URL_BASE}/PedidoClienteControlador?opcao=cancelar">Pedido Cliente</a></li>
                <li><a href="${pageContext.request.contextPath}${URL_BASE}/PedidoFornecedorControlador?opcao=cancelar">Pedido Fornecedor</a></li>
                <li><a href="${pageContext.request.contextPath}${URL_BASE}/ProdutoControlador?opcao=cancelar">Produtos</a></li>
            </ul>
        </nav>
    </body>
</html>
