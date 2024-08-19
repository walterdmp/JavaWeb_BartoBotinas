package com.mycompany.barto.controlador;

import com.mycompany.barto.modelo.dao.PagamentoDao;
import com.mycompany.barto.modelo.entidade.Pagamento;
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

@WebServlet(WebConstantes.BASE_PATH + "/PagamentoControlador")
public class PagamentoControlador extends HttpServlet {

    private PagamentoDao pagamentoDao;
    private Pagamento pagamento;
    private String idPagamento = "";
    private String dataPagamento = "";
    private String valor = "";
    private String pago = "";
    private String descricao = "";
    private String opcao = "";

    @Override
    public void init() throws ServletException {
        pagamentoDao = new PagamentoDao();
        pagamento = new Pagamento();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            opcao = request.getParameter("opcao");
            idPagamento = request.getParameter("idPagamento");
            dataPagamento = request.getParameter("dataPagamento");
            valor = request.getParameter("valor");
            descricao = request.getParameter("descricao");
            pago = request.getParameter("pago");
            if (pago == null) {
                pago = "false";
            }

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
        pagamento.setDataPagamento(Date.valueOf(dataPagamento));
        pagamento.setValor(Float.parseFloat(valor));
        pagamento.setPago(Boolean.parseBoolean(pago));
        pagamento.setDescricao(descricao);

        pagamentoDao.salvar(pagamento);
        encaminharParaPagina(request, response);
    }

    private void editar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("idPagamento", idPagamento);
        request.setAttribute("opcao", "confirmarEditar");
        request.setAttribute("dataPagamento", dataPagamento);
        request.setAttribute("valor", valor);
        request.setAttribute("pago", pago);
        request.setAttribute("descricao", descricao);

        request.setAttribute("mensagem", "Edite os dados e clique em salvar");

        encaminharParaPagina(request, response);
    }

    private void excluir(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("idPagamento", idPagamento);
        request.setAttribute("opcao", "confirmarExcluir");
        request.setAttribute("dataPagamento", dataPagamento);
        request.setAttribute("valor", valor);
        request.setAttribute("pago", pago);
        request.setAttribute("descricao", descricao);
        request.setAttribute("mensagem", "Clique em salvar para confirmar a exclusão dos dados");
        encaminharParaPagina(request, response);
    }

    private void confirmarEditar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            validaCampos(); // Valida os campos recebidos

            // Criação e configuração do objeto Caixa
            Pagamento pagamentoEditado = new Pagamento();
            pagamentoEditado.setIdPagamento(parseInt(request.getParameter("idPagamento")));
            pagamentoEditado.setDataPagamento(Date.valueOf(request.getParameter("dataPagamento")));
            pagamentoEditado.setValor(Float.parseFloat(request.getParameter("valor")));
            pagamentoEditado.setPago(Boolean.parseBoolean(request.getParameter("pago")));
            pagamentoEditado.setDescricao(request.getParameter("descricao"));

            // Atualização da caixa no banco de dados
            pagamentoDao.alterar(pagamentoEditado);

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
        pagamento.setIdPagamento(Integer.parseInt(request.getParameter("idPagamento")));
        pagamentoDao.excluir(pagamento);
        cancelar(request, response);
    }

    private void cancelar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("idPagamento", "0");
        request.setAttribute("opcao", "cadastrar");
        request.setAttribute("dataPagamento", "");
        request.setAttribute("valor", "");
        request.setAttribute("pago", "");
        request.setAttribute("descricao", "");
        encaminharParaPagina(request, response);
    }

    private void encaminharParaPagina(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Pagamento> pagamentos = pagamentoDao.buscarTodos();
        request.setAttribute("pagamentos", pagamentos);
        request.setAttribute(opcao, opcao);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/CadastroPagamento.jsp");
        dispatcher.forward(request, response);
    }

    public void validaCampos() {
        if (dataPagamento == null || dataPagamento.isEmpty() || valor == null || valor.isEmpty() || pago == null || pago.isEmpty() || descricao == null || descricao.isEmpty()) {
            throw new IllegalArgumentException("Um ou mais parâmetros estão ausentes");
        }
    }
}
