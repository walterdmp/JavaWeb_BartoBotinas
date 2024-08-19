package com.mycompany.barto.controlador;

import com.mycompany.barto.modelo.dao.FornecedorDao;
import com.mycompany.barto.modelo.dao.PedidoFornecedorDao;
import com.mycompany.barto.modelo.dao.ProdutoDao;
import com.mycompany.barto.modelo.entidade.Fornecedor;
import com.mycompany.barto.modelo.entidade.PedidoFornecedor;
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

@WebServlet(WebConstantes.BASE_PATH + "/PedidoFornecedorControlador")
public class PedidoFornecedorControlador extends HttpServlet {

    private FornecedorDao fornecedorDao;
    private Fornecedor fornecedor;
    private PedidoFornecedorDao pedidoFornecedorDao;
    private PedidoFornecedor pedidoFornecedor;
    private ProdutoDao produtoDao;
    private Produto produto;
    String idPedidoFornecedor = "";
    String dataPedido = "";
    String quantidade = "";
    String fornecedorPedido = "";
    String produtoPedido = "";
    String opcao = "";

    @Override
    public void init() throws ServletException {
        fornecedorDao = new FornecedorDao();
        pedidoFornecedorDao = new PedidoFornecedorDao();
        produtoDao = new ProdutoDao();
        fornecedor = new Fornecedor();
        pedidoFornecedor = new PedidoFornecedor();
        produto = new Produto();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            opcao = request.getParameter("opcao");
            idPedidoFornecedor = request.getParameter("idPedidoFornecedor");
            dataPedido = request.getParameter("dataPedido");
            quantidade = request.getParameter("quantidade");
            fornecedorPedido = request.getParameter("fornecedorPedido");
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

        pedidoFornecedor.setDataPedido(Date.valueOf(dataPedido));
        pedidoFornecedor.setQuantidade(Integer.parseInt(quantidade));

        pedidoFornecedor.getFornecedorPedidoFornecedor().setIdFornecedor(Integer.parseInt(fornecedorPedido));
        pedidoFornecedor.getProdutoPedidoFornecedor().setIdProduto(Integer.parseInt(produtoPedido));

        pedidoFornecedorDao.salvar(pedidoFornecedor);
        encaminharParaPagina(request, response);
    }

    private void editar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("idPedidoFornecedor", idPedidoFornecedor);
        request.setAttribute("opcao", "confirmarEditar");
        request.setAttribute("dataPedido", dataPedido);
        request.setAttribute("quantidade", quantidade);
        request.setAttribute("fornecedorPedido", fornecedorPedido);
        request.setAttribute("produtoPedido", produtoPedido);
        request.setAttribute("mensagem", "Edite os dados e clique em salvar");
        encaminharParaPagina(request, response);
    }

    private void excluir(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("idPedidoFornecedor", idPedidoFornecedor);
        request.setAttribute("opcao", "confirmarExcluir");
        request.setAttribute("dataPedido", dataPedido);
        request.setAttribute("quantidade", quantidade);
        request.setAttribute("fornecedorPedido", fornecedorPedido);
        request.setAttribute("produtoPedido", produtoPedido);
        request.setAttribute("mensagem", "Clique em salvar para excluir");
        encaminharParaPagina(request, response);
    }

    private void confirmarEditar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        validaCampos();

        pedidoFornecedor.setIdPedidoFornecedor(Integer.parseInt(idPedidoFornecedor));
        pedidoFornecedor.setDataPedido(Date.valueOf(dataPedido));
        pedidoFornecedor.setQuantidade(Integer.parseInt(quantidade));
        pedidoFornecedor.getFornecedorPedidoFornecedor().setIdFornecedor(Integer.parseInt(fornecedorPedido));
        pedidoFornecedor.getProdutoPedidoFornecedor().setIdProduto(Integer.parseInt(produtoPedido));
        pedidoFornecedorDao.alterar(pedidoFornecedor);
        cancelar(request, response);
    }

    private void confirmarExcluir(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        validaCampos();
        pedidoFornecedor.setIdPedidoFornecedor(Integer.parseInt(idPedidoFornecedor));
        pedidoFornecedor.setDataPedido(Date.valueOf(dataPedido));
        pedidoFornecedor.setQuantidade(Integer.parseInt(quantidade));
        pedidoFornecedor.getFornecedorPedidoFornecedor().setIdFornecedor(Integer.parseInt(fornecedorPedido));
        pedidoFornecedor.getProdutoPedidoFornecedor().setIdProduto(Integer.parseInt(produtoPedido));
        pedidoFornecedorDao.excluir(pedidoFornecedor);
        cancelar(request, response);
    }

    private void cancelar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("idPedidoFornecedor", "0");
        request.setAttribute("opcao", "cadastrar");
        request.setAttribute("dataPedido", "");
        request.setAttribute("quantidade", "");
        request.setAttribute("fornecedorPedido", "");
        request.setAttribute("produtoPedido", "");
        encaminharParaPagina(request, response);
    }

    private void encaminharParaPagina(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        List<Fornecedor> fornecedores = fornecedorDao.buscarTodos();
        request.setAttribute("fornecedores", fornecedores);

        List<PedidoFornecedor> pedidoFornecedores = pedidoFornecedorDao.buscarTodos();
        request.setAttribute("pedidoFornecedores", pedidoFornecedores);

        List<Produto> produtos = produtoDao.buscarTodos();
        request.setAttribute("produtos", produtos);

        RequestDispatcher dispatcher = request.getRequestDispatcher("/CadastroPedidoFornecedor.jsp");
        dispatcher.forward(request, response);
    }

    public void validaCampos() {
        if (dataPedido == null || dataPedido.isEmpty()
                || quantidade == null || quantidade.isEmpty()
                || fornecedorPedido == null || fornecedorPedido.isEmpty()
                || produtoPedido == null || produtoPedido.isEmpty()) {
            throw new IllegalArgumentException("Um ou mais parâmetros estão ausentes.");

        }
    }
}
