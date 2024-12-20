/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller.admin;

import entidade.Disciplina;
import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.DisciplinaDAO;

/**
 *
 * @author Geiziane
 */

@WebServlet(name = "DisciplinaController", urlPatterns = {"/admin/DisciplinaController"})
public class DisciplinaController extends HttpServlet {
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String acao = (String) request.getParameter("acao");
        Disciplina disciplina = new Disciplina();
        DisciplinaDAO disciplinaDAO = new DisciplinaDAO();
        RequestDispatcher rd;
        
        switch (acao) {
            case "Listar":
                
                ArrayList<Disciplina> listaDisciplinas = disciplinaDAO.getAll();
                request.setAttribute("listaDisciplinas", listaDisciplinas);
                rd = request.getRequestDispatcher("/views/admin/disciplina/listaDisciplinas.jsp");
                rd.forward(request, response);
                break;
            case "Alterar":
                int idAlterar = Integer.parseInt(request.getParameter("id"));
                disciplina = disciplinaDAO.get(idAlterar);
                
                if (disciplina != null) {
                    request.setAttribute("disciplina", disciplina);
                    request.setAttribute("msgError", "");
                    request.setAttribute("acao", "Alterar");
                    rd = request.getRequestDispatcher("/views/admin/disciplina/formDisciplina.jsp");
                    rd.forward(request, response);
                } else {
                    request.setAttribute("erro", "Disciplina não encontrada.");
                    rd = request.getRequestDispatcher("/views/admin/disciplina/listaDisciplinas.jsp");
                    rd.forward(request, response);        
                }
                break;
            case "Excluir":
                int id = Integer.parseInt(request.getParameter("id"));
                disciplina = disciplinaDAO.get(id);
                request.setAttribute("disciplina", disciplina);
                request.setAttribute("msgError", "");
                request.setAttribute("acao", "Excluir");
                rd = request.getRequestDispatcher("/views/admin/disciplina/formDisciplina.jsp");
                rd.forward(request, response);
                break;
            case "Incluir":
                request.setAttribute("disciplina", disciplina);
                request.setAttribute("msgError", "");
                request.setAttribute("acao", "Incluir");
                rd = request.getRequestDispatcher("/views/admin/disciplina/formDisciplina.jsp");
                rd.forward(request, response);
                break;
        }
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {

        String acao = request.getParameter("btEnviar");
        DisciplinaDAO disciplinaDAO = new DisciplinaDAO();
        Disciplina disciplina = new Disciplina();

        switch (acao) {
            case "Incluir":
                disciplina.setNome(request.getParameter("nome"));
                disciplina.setRequisito(request.getParameter("requisito"));
                disciplina.setEmenta(request.getParameter("ementa"));
                disciplina.setCargaHoraria(Integer.parseInt(request.getParameter("carga_horaria")));
                disciplinaDAO.insert(disciplina);
                response.sendRedirect("DisciplinaController?acao=Listar");
                break;
            case "Alterar":
                disciplina.setId(Integer.parseInt(request.getParameter("id")));
                disciplina.setNome(request.getParameter("nome"));
                disciplina.setRequisito(request.getParameter("requisito"));
                disciplina.setEmenta(request.getParameter("ementa"));
                disciplina.setCargaHoraria(Integer.parseInt(request.getParameter("carga_horaria")));
                disciplinaDAO.update(disciplina);
                response.sendRedirect("DisciplinaController?acao=Listar");
                break;
            case "Excluir":
                int id = Integer.parseInt(request.getParameter("id"));
                disciplinaDAO.delete(id);
                response.sendRedirect("DisciplinaController?acao=Listar");
                break;
            default:
                response.sendRedirect("DisciplinaController?acao=Listar");
                break;
        }
    }
}
