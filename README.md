# Sistema de Gerenciamento "Barto Botinas"

Este projeto é uma aplicação web desenvolvida para a empresa "Barto Botinas", especializada na venda de calçados. O objetivo principal do sistema é facilitar o gerenciamento de diversos aspectos do negócio, incluindo clientes, fornecedores, produtos, funcionários e pedidos. Além disso, o sistema oferece um módulo de controle financeiro para monitorar as entradas e saídas de caixa.

## Tecnologias Utilizadas

- **Java EE**: Utilizando Servlets e JSP para a camada de controle e visualização.
- **JSTL**: Para manipulação de dados e exibição dinâmica de conteúdo nas páginas JSP.
- **MySQL**: Banco de dados relacional para persistência de dados.
- **MVC**: Padrão de arquitetura Model-View-Controller.

## Funcionalidades

- **Gerenciamento de Clientes**: Cadastro, edição, exclusão e visualização de clientes.
- **Gerenciamento de Fornecedores**: Controle dos fornecedores da empresa.
- **Gerenciamento de Produtos**: Cadastro de novos produtos, atualizações de estoque, e visualização de informações detalhadas.
- **Gerenciamento de Funcionários**: Módulo para controlar as informações dos funcionários da empresa.
- **Gerenciamento de Pedidos**: Sistema para criação, acompanhamento e finalização de pedidos de clientes.
- **Controle Financeiro**: Monitoramento das entradas e saídas de caixa, com relatórios detalhados.
- **Autenticação de Usuário**: Sistema de login para proteger o acesso ao sistema, com diferentes níveis de permissão.

## Estrutura do Projeto

A organização do projeto segue o modelo MVC:

- **Model**: As classes Java que representam as tabelas do banco de dados.
- **View**: Páginas JSP que interagem com o usuário e apresentam os dados de forma dinâmica usando JSTL.
- **Controller**: Servlets que controlam o fluxo da aplicação, processam as solicitações do usuário e interagem com o banco de dados.
