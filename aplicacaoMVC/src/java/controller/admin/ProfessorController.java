package controller.admin;

import entidade.Professor;
import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.ProfessorDAO;

@WebServlet(name = "ProfessorController", urlPatterns = {"/admin/ProfessorController"})
public class ProfessorController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String acao = request.getParameter("acao");
        ProfessorDAO professorDAO = new ProfessorDAO();
        RequestDispatcher rd;

        switch (acao) {
            case "Listar":
                ArrayList<Professor> listaProfessores = professorDAO.getAll();
                request.setAttribute("listaProfessores", listaProfessores);
                rd = request.getRequestDispatcher("/views/admin/professor/listaProfessores.jsp");
                rd.forward(request, response);
                break;

            case "Alterar":
            case "Excluir":
                int id = Integer.parseInt(request.getParameter("id"));
                Professor professor = professorDAO.get(id);

                request.setAttribute("professor", professor);
                request.setAttribute("msgError", "");
                request.setAttribute("acao", acao);

                rd = request.getRequestDispatcher("/views/admin/professor/formProfessor.jsp");
                rd.forward(request, response);
                break;

            case "Incluir":
                request.setAttribute("professor", new Professor());
                request.setAttribute("msgError", "");
                request.setAttribute("acao", acao);

                rd = request.getRequestDispatcher("/views/admin/professor/formProfessor.jsp");
                rd.forward(request, response);
                break;

            default:
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Ação não reconhecida.");
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String btEnviar = request.getParameter("btEnviar");
        String nome = request.getParameter("nome");
        String email = request.getParameter("email");
        String cpf = request.getParameter("cpf");
        String senha = request.getParameter("senha");

        ProfessorDAO professorDAO = new ProfessorDAO();
        RequestDispatcher rd;

        // Verificar se os campos estão vazios
        if (nome.isEmpty() || email.isEmpty() || cpf.isEmpty() || senha.isEmpty()) {
            Professor professor = new Professor();
            if (!request.getParameter("id").isEmpty()) {
                professor.setId(Integer.parseInt(request.getParameter("id")));
            }
            professor.setNome(nome);
            professor.setEmail(email);
            professor.setCpf(cpf);
            professor.setSenha(senha);

            request.setAttribute("professor", professor);
            request.setAttribute("acao", btEnviar);
            request.setAttribute("msgError", "Todos os campos são obrigatórios.");

            rd = request.getRequestDispatcher("/views/admin/professor/formProfessor.jsp");
            rd.forward(request, response);

        } else {
            try {
                Professor professor = new Professor();
                if (!request.getParameter("id").isEmpty()) {
                    professor.setId(Integer.parseInt(request.getParameter("id")));
                }
                professor.setNome(nome);
                professor.setEmail(email);
                professor.setCpf(cpf);
                professor.setSenha(senha);

                switch (btEnviar) {
                    case "Incluir":
                        professorDAO.insert(professor);
                        request.setAttribute("msgOperacaoRealizada", "Inclusão realizada com sucesso");
                        break;
                    case "Alterar":
                        professorDAO.update(professor);
                        request.setAttribute("msgOperacaoRealizada", "Alteração realizada com sucesso");
                        break;
                    case "Excluir":
                        professorDAO.delete(professor.getId());
                        request.setAttribute("msgOperacaoRealizada", "Exclusão realizada com sucesso");
                        break;
                    default:
                        request.setAttribute("msgError", "Operação não reconhecida.");
                        break;
                }

                request.setAttribute("link", "/aplicacaoMVC/admin/ProfessorController?acao=Listar");
                rd = request.getRequestDispatcher("/views/comum/showMessage.jsp");
                rd.forward(request, response);

            } catch (Exception e) {
                System.out.println(e.getMessage());
                request.setAttribute("msgError", "Ocorreu um erro ao processar a operação.");
                rd = request.getRequestDispatcher("/views/admin/professor/formProfessor.jsp");
                rd.forward(request, response);
            }
        }
    }
}

