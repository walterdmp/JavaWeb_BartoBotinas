package com.mycompany.barto.controlador;

import com.mycompany.barto.modelo.dao.ClienteDao;
import com.mycompany.barto.modelo.entidade.Cliente;
import com.mycompany.barto.servico.WebConstantes;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(WebConstantes.BASE_PATH + "/ClienteControlador")
public class ClienteControlador extends HttpServlet {

    private ClienteDao clienteDao;
    private Cliente cliente;
    private String idCliente = "";
    private String nome = "";
    private String cpf = "";
    private String telefone = "";
    private String email = "";
    private String endereco = "";
    private String opcao = "";

    @Override
    public void init() throws ServletException {
        clienteDao = new ClienteDao();
        cliente = new Cliente();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            opcao = request.getParameter("opcao");
            idCliente = request.getParameter("idCliente");
            nome = request.getParameter("nome");
            cpf = request.getParameter("cpf");
            telefone = request.getParameter("telefone");
            email = request.getParameter("email");
            endereco = request.getParameter("endereco");

            if (opcao == null || opcao.isEmpty()) {
                opcao = "cadastrar";
            }

            switch (opcao) {
                case "cadastrar":
                    cadastrar(request, response);
                    break;
                case "editar":
                    editar(request, response);
                    break;
                case "confirmarEditar":
                    confirmarEditar(request, response);
                    break;
                case "excluir":
                    excluir(request, response);
                    break;
                case "confirmarExcluir":
                    confirmarExcluir(request, response);
                    break;
                case "cancelar":
                    cancelar(request, response);
                    break;
                case "encaminharParaPagina":
                    encaminharParaPagina(request, response);
                    break;
                default:
                    throw new IllegalArgumentException("Opção inválida " + opcao);
            }

        } catch (NumberFormatException e) {
            response.getWriter().println("Erro: um ou mais parâmetros não são números válidos");
        } catch (IllegalArgumentException e) {
            response.getWriter().println("Erro: " + e.getMessage());
        }
    }

    private void cadastrar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        validaCampos();
        cliente.setNome(nome);
        cliente.setCpf(cpf);
        cliente.setTelefone(telefone);
        cliente.setEmail(email);
        cliente.setEndereco(endereco);

        clienteDao.salvar(cliente);
        encaminharParaPagina(request, response);
    }

    private void editar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("idCliente", idCliente);
        request.setAttribute("opcao", "confirmarEditar");
        request.setAttribute("nome", nome);
        request.setAttribute("cpf", cpf);
        request.setAttribute("telefone", telefone);
        request.setAttribute("email", email);
        request.setAttribute("endereco", endereco);

        request.setAttribute("mensagem", "Edite os dados e clique em salvar");

        encaminharParaPagina(request, response);
    }

    private void excluir(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("idCliente", idCliente);
        request.setAttribute("opcao", "confirmarExcluir");
        request.setAttribute("nome", nome);
        request.setAttribute("cpf", cpf);
        request.setAttribute("telefone", telefone);
        request.setAttribute("email", email);
        request.setAttribute("endereco", endereco);
        request.setAttribute("mensagem", "Clique em salvar para confirmar a exclusão dos dados");
        encaminharParaPagina(request, response);
    }

    private void confirmarEditar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            validaCampos();

            Cliente clienteEditado = new Cliente();
            clienteEditado.setIdCliente(parseInt(request.getParameter("idCliente")));
            clienteEditado.setNome(request.getParameter("nome"));
            clienteEditado.setCpf(request.getParameter("cpf"));
            clienteEditado.setTelefone(request.getParameter("telefone"));
            clienteEditado.setEmail(request.getParameter("email"));
            clienteEditado.setEndereco(request.getParameter("endereco"));

            clienteDao.alterar(clienteEditado);

            // Redireciona para o método cancelar após a atualização
            cancelar(request, response);
        } catch (IllegalArgumentException e) {
            // Trata erros relacionados à validação de campos
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Erro na validação dos campos: " + e.getMessage());
        } catch (Exception e) {
            // Trata outros erros inesperados
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Erro inesperado: " + e.getMessage());
        }
    }

    // Método auxiliar para conversão de String para int
    private int parseInt(String value) {
        return (value != null && !value.isEmpty()) ? Integer.parseInt(value) : 0;
    }

    private void confirmarExcluir(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        cliente.setIdCliente(Integer.parseInt(request.getParameter("idCliente")));
        clienteDao.excluir(cliente);
        cancelar(request, response);
    }

    private void cancelar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("idCliente", "0");
        request.setAttribute("opcao", "cadastrar");
        request.setAttribute("nome", "");
        request.setAttribute("cpf", "");
        request.setAttribute("telefone", "");
        request.setAttribute("email", "");
        request.setAttribute("endereco", "");
        encaminharParaPagina(request, response);
    }

    private void encaminharParaPagina(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Cliente> clientes = clienteDao.buscarTodos();
        request.setAttribute("clientes", clientes);
        request.setAttribute(opcao, opcao);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/CadastroCliente.jsp");
        dispatcher.forward(request, response);
    }

    public void validaCampos() {
        if (nome == null || nome.isEmpty() || telefone == null || telefone.isEmpty() || cpf == null || cpf.isEmpty() || email == null || email.isEmpty() || endereco == null || endereco.isEmpty()) {
            throw new IllegalArgumentException("Um ou mais parâmetros estão ausentes");
        }
    }
}
