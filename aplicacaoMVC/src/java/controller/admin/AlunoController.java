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
                
                //usando o comando listar 
                ArrayList<Aluno> listaAlunos = alunoDAO.getAll();
                request.setAttribute("listaAlunos", listaAlunos);
                
                //enviando para o front-end
                rd = request.getRequestDispatcher("/views/admin/aluno/listaAlunos.jsp");
                rd.forward(request, response);

                break;
            case "Alterar":
            {
                //pega o id do aluno selecionado
                int idAlterar = Integer.parseInt(request.getParameter("id"));

                // Busca o aluno pelo ID e coloca na instância atual do objeto aluno os dados do aluno
                aluno = alunoDAO.get(idAlterar);
                
                // Verifica se o aluno foi encontrado
                if (aluno != null) {
                    // Encaminha o aluno para o formulário de edição
                    request.setAttribute("aluno", aluno);
                    request.setAttribute("msgError", "");
                    request.setAttribute("acao", "Alterar");
                    
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

                // get parametro ação indicando sobre qual aluno será a ação
                int id = Integer.parseInt(request.getParameter("id"));
                aluno = alunoDAO.get(id);

                request.setAttribute("aluno", aluno);
                request.setAttribute("msgError", "");
                request.setAttribute("acao", "Excluir");

                rd = request.getRequestDispatcher("/views/admin/aluno/formAluno.jsp");
                rd.forward(request, response);
                break;
                
            case "Incluir":
                request.setAttribute("aluno", aluno);
                request.setAttribute("msgError", "");
                request.setAttribute("acao", "Incluir");

                rd = request.getRequestDispatcher("/views/admin/aluno/formAluno.jsp");
                rd.forward(request, response);
        }

    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {

    // Obtém a ação enviada pelo formulário
        String acao = request.getParameter("btEnviar");

        AlunoDAO alunoDAO = new AlunoDAO();
        Aluno aluno = new Aluno();

        switch (acao) {
            case "Incluir":
                // Preenche o objeto Aluno com os dados do formulário
                aluno.setNome(request.getParameter("nome"));
                aluno.setEmail(request.getParameter("email"));
                aluno.setCelular(request.getParameter("celular"));
                aluno.setCpf(request.getParameter("cpf"));
                aluno.setSenha(request.getParameter("senha"));
                aluno.setEndereco(request.getParameter("endereco"));
                aluno.setCidade(request.getParameter("cidade"));
                aluno.setBairro(request.getParameter("bairro"));
                aluno.setCep(request.getParameter("cep"));

                // Insere o aluno no banco de dados
                alunoDAO.insert(aluno);

                // Redireciona para a lista de alunos
                response.sendRedirect("AlunoController?acao=Listar");
                break;

            case "Alterar":
                // Preenche o objeto Aluno com os dados do formulário
                aluno.setId(Integer.parseInt(request.getParameter("id")));
                aluno.setNome(request.getParameter("nome"));
                aluno.setEmail(request.getParameter("email"));
                aluno.setCelular(request.getParameter("celular"));
                aluno.setCpf(request.getParameter("cpf"));
                aluno.setSenha(request.getParameter("senha"));
                aluno.setEndereco(request.getParameter("endereco"));
                aluno.setCidade(request.getParameter("cidade"));
                aluno.setBairro(request.getParameter("bairro"));
                aluno.setCep(request.getParameter("cep"));

                // Atualiza os dados do aluno no banco de dados
                alunoDAO.update(aluno);

                // Redireciona para a lista de alunos
                response.sendRedirect("AlunoController?acao=Listar");
                break;

            case "Excluir":
                // Obtém o ID do aluno a ser excluído
                int id = Integer.parseInt(request.getParameter("id"));

                // Remove o aluno do banco de dados
                alunoDAO.delete(id);

                // Redireciona para a lista de alunos
                response.sendRedirect("AlunoController?acao=Listar");
                break;

            default:
                // Redireciona para a página inicial se a ação não for reconhecida
                response.sendRedirect("AlunoController?acao=Listar");
                break;
        }
}
    
    
    
    
}
