<%@page import="entidade.Aluno"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8" %>

<!DOCTYPE html>
<html lang="pt-br">
    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="shortcut icon" href="#">
        <title>Lista Alunos</title>
         <link href="http://localhost:8080/aplicacaoMVC/views/bootstrap/bootstrap.min.css"  rel="stylesheet">

    </head>
    <body>
        <div class="container">
             <jsp:include page="../../comum/menu.jsp" />
            <div class="mt-5">

                <h1>Área Restrita</h1>
                <h2>Lista de Alunos/h2>

                <a href="/aplicacaoMVC/admin/AlunoController?acao=Incluir" class="mb-2 btn btn-primary">Incluir</a>
                <div class="table-responsive">
                    <table class="table table-hover">
                        <thead>
                            <tr>
                                <th scope="col">Id</th>
                                <th scope="col">nome</th>
                                <th scope="col">email</th>
                                <th scope="col">celular</th>
                                <th scope="col">cpf</th>
                                <th scope="col">senha</th>
                                <th scope="col">endereco</th>
                                <th scope="col">cidade</th>
                                <th scope="col">bairro</th>
                                <th scope="col">cep</th>       
                            </tr>
                        </thead>
                        <tbody>
                            <%
                                ArrayList<Aluno> listaAlunos = (ArrayList<Aluno>) request.getAttribute("listaAlunos");

                                for (Aluno aluno : listaAlunos) {
                                    out.println("<tr>");
                                    out.println("<th>" + aluno.getId() + "</th>");
                                    out.println("<td>" + aluno.getNome() + "</td>");
                                    out.println("<td>" + aluno.getEmail() + "</td>");
                                    out.println("<td>" + aluno.getCelular() + "</td>");
                                    out.println("<td>" + aluno.getCpf() + "</td>");
                                    out.println("<td>" + aluno.getSenha() + "</td>");
                                    out.println("<td>" + aluno.getEndereco() + "</td>");
                                    out.println("<td>" + aluno.getCidade() + "</td>");
                                    out.println("<td>" + aluno.getCep() + "</td>");
                                    %>
                            <td>
                            <a href="/aplicacaoMVC/admin/AlunoController?acao=Alterar&id=<%=aluno.getId()%>" class="btn btn-warning">Alterar</a>
                            <a href="/aplicacaoMVC/admin/AlunoController?acao=Excluir&id=<%=aluno.getId()%>" class="btn btn-danger">Excluir</a></td>
                            
                            <%   out.println("</tr>");
                                }
                            %>

                        </tbody>
                    </table>
                </div>
            </div>
        </div>

        <script src="http://localhost:8080/aplicacaoMVC/views/bootstrap/bootstrap.bundle.min.js"></script>

    </body>
</html>
