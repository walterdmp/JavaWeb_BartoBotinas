package barto.controller;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import barto.model.Fornecedor;
import barto.model.PedidoFornecedor;
import barto.model.Produto;

@WebServlet("/cadastrarPedidoFornecedorController")
public class cadastrarPedidoFornecedorController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public cadastrarPedidoFornecedorController() {
		super();
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doPost(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=UTF-8");

		String fornecedorIdStr = request.getParameter("fornecedorId");
		String dataPedidoStr = request.getParameter("dataPedido");
		String produtoIdStr = request.getParameter("produtoId");
		String quantidadeStr = request.getParameter("quantidade");

		int fornecedorId = 0;
		int produtoId = 0;
		int quantidade = 0;
		LocalDate dataPedido = null;

		try {
			fornecedorId = Integer.parseInt(fornecedorIdStr);
			produtoId = Integer.parseInt(produtoIdStr);
			quantidade = Integer.parseInt(quantidadeStr);
			dataPedido = LocalDate.parse(dataPedidoStr);
		} catch (NumberFormatException | DateTimeParseException e) {
			e.printStackTrace();
			String mensagem = "Erro ao converter dados. Por favor, verifique as informações e tente novamente.";
			request.setAttribute("mensagem", mensagem);
			RequestDispatcher dispatcher = request.getRequestDispatcher("cadastrarPedidoFornecedor.jsp");
			dispatcher.forward(request, response);
			return;
		}

		// Instanciar os objetos Fornecedor e Produto usando os IDs, você pode precisar
		// de uma DAO para isso
		Fornecedor fornecedor = new Fornecedor(); // Obter fornecedor usando fornecedorId
		Produto produto = new Produto(); // Obter produto usando produtoId

		PedidoFornecedor pedidoFornecedor = new PedidoFornecedor(fornecedor, dataPedido, produto, quantidade);
		pedidoFornecedor.salvar();

		String mensagem = "Pedido de fornecedor cadastrado com sucesso!";
		request.setAttribute("mensagem", mensagem);
		RequestDispatcher dispatcher = request.getRequestDispatcher("cadastrarPedidoFornecedor.jsp");
		dispatcher.forward(request, response);
	}
}
