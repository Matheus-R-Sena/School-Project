/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import entidade.Professor;


public class ProfessorDAO implements Dao<Professor> {
    
    @Override
    public Professor get(int id) {
        Conexao conexao = new Conexao();
        Professor professor = new Professor();
        try {
            PreparedStatement sql = conexao.getConexao().prepareStatement("SELECT * FROM professores WHERE ID = ? ");
            sql.setInt(1, id);
            ResultSet resultado = sql.executeQuery();

            if (resultado != null) {
                while (resultado.next()) {
                    professor.setId(resultado.getInt("ID"));
                    professor.setNome(resultado.getString("NOME"));
                    professor.setEmail(resultado.getString("EMAIL"));
                    professor.setCpf(resultado.getString("CPF"));
                    professor.setSenha(resultado.getString("SENHA"));
                }
            }
        } catch (SQLException e) {
            System.err.println("Query de select (get professor) incorreta");
        } finally {
            conexao.closeConexao();
        }
        return professor;
    }
    
    @Override
    public void insert(Professor t) {

        Conexao conexao = new Conexao();
        try {
            PreparedStatement sql = conexao.getConexao().prepareStatement("INSERT INTO professores (nome, email, cpf, senha) VALUES (?, ?, ?, ?)");
            sql.setString(1, t.getNome());
            sql.setString(2, t.getEmail());
            sql.setString(3, t.getCpf());
            sql.setString(4, t.getSenha());
            sql.executeUpdate();

        } catch (SQLException e) {
            System.err.println("Query de insert (Professor) incorreta");
        } finally {
            conexao.closeConexao();
        }
    }
    
    @Override
    public void update(Professor t) {
        Conexao conexao = new Conexao();
        try {
            PreparedStatement sql = conexao.getConexao().prepareStatement("UPDATE professores SET nome = ?, email = ?, cpf = ?, senha = ? WHERE ID = ? ");
            sql.setString(1, t.getNome());
            sql.setString(2, t.getEmail());
            sql.setString(3, t.getCpf());
            sql.setString(4, t.getSenha());
            sql.setInt(5, t.getId());
            sql.executeUpdate();

        } catch (SQLException e) {
            System.err.println("Query de update (alterar Professor) incorreta");
        } finally {
            conexao.closeConexao();
        }
    }
    
     @Override
    public void delete(int id) {
        Conexao conexao = new Conexao();
        try {
            PreparedStatement sql = conexao.getConexao().prepareStatement("DELETE FROM professores WHERE ID = ? ");
            sql.setInt(1, id);
            sql.executeUpdate();

        } catch (SQLException e) {
            System.err.println("Query de delete (excluir Professor) incorreta");
        } finally {
            conexao.closeConexao();
        }
    }
    
     @Override
    public ArrayList<Professor> getAll() {

        ArrayList<Professor> meusProfessores = new ArrayList<>();
        Conexao conexao = new Conexao();
        try {
            String selectSQL = "SELECT * FROM professores";
            PreparedStatement preparedStatement;
            preparedStatement = conexao.getConexao().prepareStatement(selectSQL);
            ResultSet resultado = preparedStatement.executeQuery();
            if (resultado != null) {
                while (resultado.next()) {
                    Professor professor = new Professor(
                            resultado.getInt("ID"),
                            resultado.getString("NOME"),
                            resultado.getString("EMAIL"),
                            resultado.getString("CPF"),
                            resultado.getString("SENHA")
                    );
                    
                    meusProfessores.add(professor);
                }
            }
        } catch (SQLException e) {
            System.err.println("Query de select (GetAll - Professores) incorreta");
        } finally {
            conexao.closeConexao();
        }
        return meusProfessores;
    }
}

