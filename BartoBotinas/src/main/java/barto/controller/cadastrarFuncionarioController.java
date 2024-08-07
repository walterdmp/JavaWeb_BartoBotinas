package barto.controller;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import barto.model.Funcionario;

@WebServlet("/cadastrarFuncionarioController")
public class cadastrarFuncionarioController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public cadastrarFuncionarioController() {
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

		String nome = request.getParameter("nome");
		String telefone = request.getParameter("telefone");
		String cargo = request.getParameter("cargo");
		String salarioStr = request.getParameter("salario");

		float salario = 0;

		try {
			salario = Float.parseFloat(salarioStr);
		} catch (NumberFormatException e) {
			e.printStackTrace();
			String mensagem = "Erro ao converter o salário. Por favor, insira um valor numérico válido.";
			request.setAttribute("mensagem", mensagem);
			RequestDispatcher dispatcher = request.getRequestDispatcher("cadastrarFuncionario.jsp");
			dispatcher.forward(request, response);
			return;
		}

		Funcionario funcionario = new Funcionario(nome, telefone, cargo, salario);
		funcionario.salvar();

		String mensagem = "Funcionário cadastrado com sucesso!";
		request.setAttribute("mensagem", mensagem);
		RequestDispatcher dispatcher = request.getRequestDispatcher("cadastrarFuncionario.jsp");
		dispatcher.forward(request, response);
	}
}
