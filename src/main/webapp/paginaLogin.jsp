<!DOCTYPE html>
<html lang="pt">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>P�gina de Login</title>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/estilo.css">
        <style>
            /* Estilos b�sicos para o formul�rio */
            body {
                font-family: Arial, sans-serif;
                display: flex;
                justify-content: center;
                align-items: center;
                height: 100vh;
                margin: 0;
                background-color: #f4f4f4;
            }
            .container-login {
                background: #fff;
                padding: 20px;
                border-radius: 8px;
                box-shadow: 0 0 10px rgba(0,0,0,0.1);
                width: 100%;
                max-width: 400px;
            }
            h1 {
                margin-bottom: 20px;
                font-size: 24px;
                color: #333;
                text-align: center; /* Centraliza o t�tulo */
            }
            .grupo-formulario {
                margin-bottom: 15px;
            }
            .grupo-formulario label {
                display: block;
                margin-bottom: 5px;
                font-weight: bold;
                color: #333;
            }
            .grupo-formulario input {
                width: 100%;
                padding: 10px;
                border: 1px solid #ddd;
                border-radius: 4px;
            }
            .grupo-formulario input[type="submit"] {
                background-color: rgb(27,77,227);
                color: #fff;
                border: none;
                cursor: pointer;
                font-size: 16px;
                padding: 10px;
            }
            .grupo-formulario input[type="submit"]:hover {
                background-color: rgb(37,87,237);
            }
            .link-restrito {
                display: block;
                margin-top: 10px;
                text-align: center;
                color: rgb(27,77,227);
                text-decoration: none;
            }
            .link-restrito:hover {
                text-decoration: underline;
            }
        </style>
    </head>
    <body>
        <div class="container-login">
            <h1>P�gina de Login</h1>
            <form action="${pageContext.request.contextPath}/login" method="post">
                <div class="grupo-formulario">
                    <label for="username">Nome de Usu�rio</label>
                    <input type="text" id="username" name="username" required placeholder="Digite seu nome de usu�rio">
                </div>
                <div class="grupo-formulario">
                    <label for="password">Senha</label>
                    <input type="password" id="password" name="password" required placeholder="Digite sua senha">
                </div>
                <div class="grupo-formulario">
                    <input type="submit" value="Entrar">
                </div>
                <a href="${pageContext.request.contextPath}/areaRestrita.jsp" class="link-restrito">�rea Restrita</a>
            </form>
        </div>
    </body>
</html>
