package barto.controller;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import barto.model.Produto;

@WebServlet("/cadastrarProdutoController")
public class cadastrarProdutoController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public cadastrarProdutoController() {
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

		String descricao = request.getParameter("descricao");
		String precoStr = request.getParameter("preco");
		String quantidadeStr = request.getParameter("quantidade");

		float preco = 0;
		int quantidade = 0;

		try {
			preco = Float.parseFloat(precoStr);
			quantidade = Integer.parseInt(quantidadeStr);
		} catch (NumberFormatException e) {
			e.printStackTrace();
			String mensagem = "Erro ao converter os valores. Por favor, insira números válidos.";
			request.setAttribute("mensagem", mensagem);
			RequestDispatcher dispatcher = request.getRequestDispatcher("cadastrarProduto.jsp");
			dispatcher.forward(request, response);
			return;
		}

		Produto produto = new Produto(descricao, preco, quantidade);
		produto.salvar();

		String mensagem = "Produto cadastrado com sucesso!";
		request.setAttribute("mensagem", mensagem);
		RequestDispatcher dispatcher = request.getRequestDispatcher("cadastrarProduto.jsp");
		dispatcher.forward(request, response);
	}
}
