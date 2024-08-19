package com.mycompany.barto.controlador;

import com.mycompany.barto.modelo.dao.CaixaDao;
import com.mycompany.barto.modelo.entidade.Caixa;
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

@WebServlet(WebConstantes.BASE_PATH + "/CaixaControlador")
public class CaixaControlador extends HttpServlet {

    private CaixaDao caixaDao;
    private Caixa caixa;
    private String idCaixa = "";
    private String tipo = "";
    private String valor = "";
    private String descricao = "";
    private String data = "";
    private String opcao = "";

    @Override
    public void init() throws ServletException {
        caixaDao = new CaixaDao();
        caixa = new Caixa();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            opcao = request.getParameter("opcao");
            idCaixa = request.getParameter("idCaixa");
            tipo = request.getParameter("tipo");
            valor = request.getParameter("valor");
            descricao = request.getParameter("descricao");
            data = request.getParameter("data");

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
        caixa.setTipo(tipo);
        caixa.setValor(Float.parseFloat(valor));
        caixa.setDescricao(descricao);
        caixa.setData(Date.valueOf(data));
        
        caixaDao.salvar(caixa);
        encaminharParaPagina(request, response);
    }

    private void editar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("idCaixa", idCaixa);
        request.setAttribute("opcao", "confirmarEditar");
        request.setAttribute("tipo", tipo);
        request.setAttribute("valor", valor);
        request.setAttribute("descricao", descricao);
        request.setAttribute("data", data);

        request.setAttribute("mensagem", "Edite os dados e clique em salvar");

        encaminharParaPagina(request, response);
    }

    private void excluir(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("idCaixa", idCaixa);
        request.setAttribute("opcao", "confirmarExcluir");
        request.setAttribute("tipo", tipo);
        request.setAttribute("valor", valor);
        request.setAttribute("descricao", descricao);
        request.setAttribute("data", data);
        request.setAttribute("mensagem", "Clique em salvar para confirmar a exclusão dos dados");
        encaminharParaPagina(request, response);
    }

    private void confirmarEditar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            validaCampos(); // Valida os campos recebidos

            // Criação e configuração do objeto Caixa
            Caixa caixaEditado = new Caixa();
            caixaEditado.setIdCaixa(parseInt(request.getParameter("idCaixa")));
            caixaEditado.setTipo(request.getParameter("tipo"));
            caixaEditado.setValor(Float.parseFloat(request.getParameter("valor")));
            caixaEditado.setDescricao(request.getParameter("descricao"));
            caixaEditado.setData(Date.valueOf(request.getParameter("data")));

            // Atualização da caixa no banco de dados
            caixaDao.alterar(caixaEditado);

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
        caixa.setIdCaixa(Integer.parseInt(request.getParameter("idCaixa")));
        caixaDao.excluir(caixa);
        cancelar(request, response);
    }

    private void cancelar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("idCaixa", "0");
        request.setAttribute("opcao", "cadastrar");
        request.setAttribute("tipo", "");
        request.setAttribute("valor", "");
        request.setAttribute("descricao", "");
        request.setAttribute("data", "");
        encaminharParaPagina(request, response);
    }

    private void encaminharParaPagina(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Caixa> caixas = caixaDao.buscarTodas();
        request.setAttribute("caixas", caixas);
        request.setAttribute(opcao, opcao);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/CadastroCaixa.jsp");
        dispatcher.forward(request, response);
    }

    public void validaCampos() {
        if (tipo == null || tipo.isEmpty() || descricao == null || descricao.isEmpty() || data == null || data.isEmpty()) {
            throw new IllegalArgumentException("Um ou mais parâmetros estão ausentes");
        }
    }
}
