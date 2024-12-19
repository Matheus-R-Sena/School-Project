/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller.admin;

import entidade.Aluno;
import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.AlunoDAO;

/**
 *
 * @author mathe
 */

//Método de servlet
@WebServlet(name = "AlunoController", urlPatterns = {"/admin/AlunoController"})
public class AlunoController extends HttpServlet {
    
    @Override
    //adicionar parâmetros de validação
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // get parametro ação indicando o que fazer
        String acao = (String) request.getParameter("acao");
        Aluno aluno = new Aluno();
        AlunoDAO alunoDAO = new AlunoDAO();
        RequestDispatcher rd;
        
        switch (acao) {
            case "Listar":
                ArrayList<Aluno> listaAlunos = alunoDAO.getAll();
                request.setAttribute("listaAlunos", listaAlunos);
                rd = request.getRequestDispatcher("/views/admin/aluno/listaAlunos.jsp");
                rd.forward(request, response);

                break;
            case "Alterar":
            {
                //pega o id do aluno selecionado
                int idAlterar = Integer.parseInt(request.getParameter("id"));

                // Busca o aluno pelo ID
                aluno = alunoDAO.get(idAlterar);
                
                // Verifica se o aluno foi encontrado
                if (aluno != null) {
                    // Encaminha o aluno para o formulário de edição
                    request.setAttribute("aluno", aluno);
                    rd = request.getRequestDispatcher("/views/admin/aluno/formAluno.jsp");
                    rd.forward(request, response);
                } else {
                    // Redireciona para a lista com uma mensagem de erro
                    request.setAttribute("erro", "Aluno não encontrado.");
                    rd = request.getRequestDispatcher("/views/admin/aluno/listaAlunos.jsp");
                    rd.forward(request, response);
                    
                
              }
              break;
            }
            case "Excluir":

                // get parametro ação indicando sobre qual categoria será a ação
                int id = Integer.parseInt(request.getParameter("id"));
                aluno = alunoDAO.get(id);

                request.setAttribute("categoria", aluno);
                request.setAttribute("msgError", "");
                request.setAttribute("acao", acao);

                rd = request.getRequestDispatcher("/views/admin/categoria/formCategoria.jsp");
                rd.forward(request, response);
                break;
                
            case "Incluir":
                request.setAttribute("aluno", aluno);
                request.setAttribute("msgError", "");
                request.setAttribute("acao", acao);

                rd = request.getRequestDispatcher("/views/admin/categoria/formCategoria.jsp");
                rd.forward(request, response);
        }

    }
}
