package barto.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import barto.dao.ClienteDao;
import barto.model.Cliente;
import barto.model.Endereco;

/**
 * Servlet implementation class cadastrarClienteController
 */
@WebServlet("/cadastrarClienteController")
public class cadastrarClienteController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public cadastrarClienteController() {
        super();
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        this.doPost(request, response);
    }

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=UTF-8");

        String nome = request.getParameter("nome");
        String cpf = request.getParameter("cpf");
        String telefone = request.getParameter("telefone");
        String email = request.getParameter("email");
        String enderecoStr = request.getParameter("endereco");

        // Supondo que o campo enderecoStr seja o ID do endereço. 
        // Você deve ajustar conforme a lógica de obtenção do endereço.
        int enderecoId = 0;

        try {
            enderecoId = Integer.parseInt(enderecoStr);
        } catch (NumberFormatException e) {
            e.printStackTrace();
            String mensagem = "Erro ao converter o ID do endereço. Por favor, insira um número válido.";
            request.setAttribute("mensagem", mensagem);
            RequestDispatcher dispatcher = request.getRequestDispatcher("cadastrarCliente.jsp");
            dispatcher.forward(request, response);
            return;
        }

        Endereco endereco = new Endereco();
        endereco.setIdEndereco(enderecoId);

        Cliente cliente = new Cliente(nome, cpf, telefone, email, endereco);
        ClienteDao clienteDao = new ClienteDao();

        try {
            clienteDao.cadastrarCliente(cliente);
        } catch (Exception e) {
            e.printStackTrace();
            String mensagem = "Erro ao cadastrar cliente. Por favor, tente novamente.";
            request.setAttribute("mensagem", mensagem);
            RequestDispatcher dispatcher = request.getRequestDispatcher("cadastrarCliente.jsp");
            dispatcher.forward(request, response);
            return;
        }

        String mensagem = "Cliente cadastrado com sucesso!";
        request.setAttribute("mensagem", mensagem);
        RequestDispatcher dispatcher = request.getRequestDispatcher("cadastrarCliente.jsp");
        dispatcher.forward(request, response);
    }
}
