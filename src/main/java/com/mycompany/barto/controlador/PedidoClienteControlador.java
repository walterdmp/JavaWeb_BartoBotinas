package com.mycompany.barto.controlador;

import com.mycompany.barto.modelo.dao.ClienteDao;
import com.mycompany.barto.modelo.dao.PedidoClienteDao;
import com.mycompany.barto.modelo.dao.ProdutoDao;
import com.mycompany.barto.modelo.entidade.Cliente;
import com.mycompany.barto.modelo.entidade.PedidoCliente;
import com.mycompany.barto.modelo.entidade.Produto;
import com.mycompany.barto.servico.WebConstantes;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;
import java.util.List;

@WebServlet(WebConstantes.BASE_PATH + "/PedidoClienteControlador")
public class PedidoClienteControlador extends HttpServlet {

    private ClienteDao clienteDao;
    private Cliente cliente;
    private PedidoClienteDao pedidoClienteDao;
    private PedidoCliente pedidoCliente;
    private ProdutoDao produtoDao;
    private Produto produto;
    String idPedidoCliente = "";
    String dataPedido = "";
    String quantidade = "";
    String total = "";
    String clientePedido = "";
    String produtoPedido = "";
    String opcao = "";

    @Override
    public void init() throws ServletException {
        clienteDao = new ClienteDao();
        pedidoClienteDao = new PedidoClienteDao();
        produtoDao = new ProdutoDao();
        cliente = new Cliente();
        pedidoCliente = new PedidoCliente();
        produto = new Produto();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            opcao = request.getParameter("opcao");
            idPedidoCliente = request.getParameter("idPedidoCliente");
            dataPedido = request.getParameter("dataPedido");
            quantidade = request.getParameter("quantidade");
            total = request.getParameter("total");
            clientePedido = request.getParameter("clientePedido");
            produtoPedido = request.getParameter("produtoPedido");

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
                default:
                    throw new IllegalArgumentException("Opção inválida: " + opcao);
            }

        } catch (NumberFormatException e) {
            response.getWriter().println("Erro: um ou mais parâmetros não são números válidos");
        } catch (IllegalArgumentException e) {
            response.getWriter().println("Erro: " + e.getMessage());
        }
    }

    private void cadastrar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        validaCampos();

        pedidoCliente.setDataPedido(Date.valueOf(dataPedido));
        pedidoCliente.setQuantidade(Integer.parseInt(quantidade));
        pedidoCliente.setTotal(Float.parseFloat(total));

        pedidoCliente.getClientePedidoCliente().setIdCliente(Integer.parseInt(clientePedido));
        pedidoCliente.getProdutoPedidoCliente().setIdProduto(Integer.parseInt(produtoPedido));

        pedidoClienteDao.salvar(pedidoCliente);
        encaminharParaPagina(request, response);
    }

    private void editar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("idPedidoCliente", idPedidoCliente);
        request.setAttribute("opcao", "confirmarEditar");
        request.setAttribute("dataPedido", dataPedido);
        request.setAttribute("quantidade", quantidade);
        request.setAttribute("total", total);
        request.setAttribute("clientePedido", clientePedido);
        request.setAttribute("produtoPedido", produtoPedido);
        request.setAttribute("mensagem", "Edite os dados e clique em salvar");
        encaminharParaPagina(request, response);
    }

    private void excluir(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("idPedidoCliente", idPedidoCliente);
        request.setAttribute("opcao", "confirmarExcluir");
        request.setAttribute("dataPedido", dataPedido);
        request.setAttribute("quantidade", quantidade);
        request.setAttribute("total", total);
        request.setAttribute("clientePedido", clientePedido);
        request.setAttribute("produtoPedido", produtoPedido);
        request.setAttribute("mensagem", "Clique em salvar para excluir");
        encaminharParaPagina(request, response);
    }

    private void confirmarEditar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        validaCampos();

        pedidoCliente.setIdPedidoCliente(Integer.parseInt(idPedidoCliente));
        pedidoCliente.setDataPedido(Date.valueOf(dataPedido));
        pedidoCliente.setQuantidade(Integer.parseInt(quantidade));
        pedidoCliente.setTotal(Float.parseFloat(total));
        pedidoCliente.getClientePedidoCliente().setIdCliente(Integer.parseInt(clientePedido));
        pedidoCliente.getProdutoPedidoCliente().setIdProduto(Integer.parseInt(produtoPedido));
        pedidoClienteDao.alterar(pedidoCliente);
        cancelar(request, response);
    }

    private void confirmarExcluir(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        validaCampos();
        pedidoCliente.setIdPedidoCliente(Integer.parseInt(idPedidoCliente));
        pedidoCliente.setDataPedido(Date.valueOf(dataPedido));
        pedidoCliente.setQuantidade(Integer.parseInt(quantidade));
        pedidoCliente.setTotal(Float.parseFloat(total));
        pedidoCliente.getClientePedidoCliente().setIdCliente(Integer.parseInt(clientePedido));
        pedidoCliente.getProdutoPedidoCliente().setIdProduto(Integer.parseInt(produtoPedido));
        pedidoClienteDao.excluir(pedidoCliente);
        cancelar(request, response);
    }

    private void cancelar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("idPedidoCliente", "0");
        request.setAttribute("opcao", "cadastrar");
        request.setAttribute("dataPedido", "");
        request.setAttribute("quantidade", "");
        request.setAttribute("total", "");
        request.setAttribute("clientePedido", "");
        request.setAttribute("produtoPedido", "");
        encaminharParaPagina(request, response);
    }

    private void encaminharParaPagina(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        List<Cliente> clientes = clienteDao.buscarTodos();
        request.setAttribute("clientes", clientes);

        List<PedidoCliente> pedidoClientes = pedidoClienteDao.buscarTodos();
        request.setAttribute("pedidoClientes", pedidoClientes);

        List<Produto> produtos = produtoDao.buscarTodos();
        request.setAttribute("produtos", produtos);

        RequestDispatcher dispatcher = request.getRequestDispatcher("/CadastroPedidoCliente.jsp");
        dispatcher.forward(request, response);
    }

    public void validaCampos() {
        if (dataPedido == null || dataPedido.isEmpty()
                || quantidade == null || quantidade.isEmpty()
                || total == null || total.isEmpty()
                || clientePedido == null || clientePedido.isEmpty()
                || produtoPedido == null || produtoPedido.isEmpty()) {
            throw new IllegalArgumentException("Um ou mais parâmetros estão ausentes.");

        }
    }
}
