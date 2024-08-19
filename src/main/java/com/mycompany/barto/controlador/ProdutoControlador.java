package com.mycompany.barto.controlador;

import com.mycompany.barto.modelo.dao.ProdutoDao;
import com.mycompany.barto.modelo.entidade.Produto;
import com.mycompany.barto.servico.WebConstantes;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(WebConstantes.BASE_PATH + "/ProdutoControlador")
public class ProdutoControlador extends HttpServlet {

    private ProdutoDao produtoDao;
    private Produto produto;
    private String idProduto = "";
    private String descricao = "";
    private String preco = "";
    private String quantidade = "";
    private String opcao = "";

    @Override
    public void init() throws ServletException {
        produtoDao = new ProdutoDao();
        produto = new Produto();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            opcao = request.getParameter("opcao");
            idProduto = request.getParameter("idProduto");
            descricao = request.getParameter("descricao");
            preco = request.getParameter("preco");
            quantidade = request.getParameter("quantidade");

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
        produto.setDescricao(descricao);
        produto.setPreco(Float.parseFloat(preco));
        produto.setQuantidade(Integer.parseInt(quantidade));
        
        produtoDao.salvar(produto);
        encaminharParaPagina(request, response);
    }

    private void editar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("idProduto", idProduto);
        request.setAttribute("opcao", "confirmarEditar");
        request.setAttribute("descricao", descricao);
        request.setAttribute("preco", preco);
        request.setAttribute("quantidade", quantidade);

        request.setAttribute("mensagem", "Edite os dados e clique em salvar");

        encaminharParaPagina(request, response);
    }

    private void excluir(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("idProduto", idProduto);
        request.setAttribute("opcao", "confirmarExcluir");
        request.setAttribute("descricao", descricao);
        request.setAttribute("preco", preco);
        request.setAttribute("quantidade", quantidade);
        request.setAttribute("mensagem", "Clique em salvar para confirmar a exclusão dos dados");
        encaminharParaPagina(request, response);
    }

    private void confirmarEditar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            validaCampos(); // Valida os campos recebidos

            // Criação e configuração do objeto Caixa
            Produto produtoEditado = new Produto();
            produtoEditado.setIdProduto(parseInt(request.getParameter("idProduto")));
            produtoEditado.setDescricao(request.getParameter("descricao"));
            produtoEditado.setPreco(Float.parseFloat(request.getParameter("preco")));
            produtoEditado.setQuantidade(parseInt(request.getParameter("quantidade")));

            // Atualização da caixa no banco de dados
            produtoDao.alterar(produtoEditado);

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
        produto.setIdProduto(Integer.parseInt(request.getParameter("idProduto")));
        produtoDao.excluir(produto);
        cancelar(request, response);
    }

    private void cancelar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("idProduto", "0");
        request.setAttribute("opcao", "cadastrar");
        request.setAttribute("descricao", "");
        request.setAttribute("preco", "");
        request.setAttribute("quantidade", "");
        encaminharParaPagina(request, response);
    }

    private void encaminharParaPagina(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Produto> produtos = produtoDao.buscarTodos();
        request.setAttribute("produtos", produtos);
        request.setAttribute(opcao, opcao);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/CadastroProdutos.jsp");
        dispatcher.forward(request, response);
    }

    public void validaCampos() {
        if (descricao == null || descricao.isEmpty() || preco == null || preco.isEmpty() || quantidade == null || quantidade.isEmpty()) {
            throw new IllegalArgumentException("Um ou mais parâmetros estão ausentes");
        }
    }
}
