/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import entidade.Aluno;
/**
 *
 * @author mathe
 */
public class AlunoDAO implements Dao<Aluno> {
    
    @Override
    public Aluno get(int id) {
        Conexao conexao = new Conexao();
        Aluno aluno = new Aluno();
        try {
            PreparedStatement sql = conexao.getConexao().prepareStatement("SELECT * FROM alunos WHERE ID = ? ");
            sql.setInt(1, id);
            ResultSet resultado = sql.executeQuery();

            if (resultado != null) {
                while (resultado.next()) {
                    aluno.setId(Integer.parseInt(resultado.getString("ID")));
                    aluno.setNome(resultado.getString("NOME"));
                    aluno.setEmail(resultado.getString("EMAIL"));
                    aluno.setCelular(resultado.getString("CELULAR"));
                    aluno.setCpf(resultado.getString("CPF"));
                    aluno.setSenha(resultado.getString("SENHA"));
                    aluno.setEndereco(resultado.getString("ENDERECO"));
                    aluno.setCidade(resultado.getString("CIDADE"));
                    aluno.setBairro(resultado.getString("BAIRRO"));
                    aluno.setCep(resultado.getString("CEP"));
                    
                }
            }
        } catch (SQLException e) {
            System.err.println("Query de select (get aluno) incorreta");
        } finally {
            conexao.closeConexao();
        }
        return aluno;
    }
    
    @Override
    public void insert(Aluno t) {

        Conexao conexao = new Conexao();
        try {
            PreparedStatement sql = conexao.getConexao().prepareStatement("INSERT INTO alunos (nome, email, celular, cpf, senha, endereco, cidade, bairro, cep) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)");
            sql.setString(1, t.getNome());
            sql.setString(2, t.getEmail());
            sql.setString(3, t.getCelular());
            sql.setString(4, t.getCpf());
            sql.setString(5, t.getSenha());
            sql.setString(6, t.getEndereco());
            sql.setString(7, t.getCidade());
            sql.setString(8, t.getBairro());
            sql.setString(9, t.getCep());
            sql.executeUpdate();

        } catch (SQLException e) {
            System.err.println("Query de insert (Aluno) incorreta");
        } finally {
            conexao.closeConexao();
        }
    }
    
    @Override
    public void update(Aluno t) {
        Conexao conexao = new Conexao();
        try {
            PreparedStatement sql = conexao.getConexao().prepareStatement("UPDATE alunos SET nome = ?, email = ?, celular = ?, cpf = ?, senha = ?, endereco = ?, cidade = ?, bairro = ?, cep = ? WHERE ID = ? ");
            sql.setString(1, t.getNome());
            sql.setString(2, t.getEmail());
            sql.setString(3, t.getCelular());
            sql.setString(4, t.getCpf());
            sql.setString(5, t.getSenha());
            sql.setString(6, t.getEndereco());
            sql.setString(7, t.getCidade());
            sql.setString(8, t.getBairro());
            sql.setString(9, t.getCep());
            sql.setInt(10, t.getId());
            sql.executeUpdate();

        } catch (SQLException e) {
            System.err.println("Query de update (alterar Aluno) incorreta");
        } finally {
            conexao.closeConexao();
        }
    }
    
    
     @Override
    public void delete(int id) {
        Conexao conexao = new Conexao();
        try {
            PreparedStatement sql = conexao.getConexao().prepareStatement("DELETE FROM alunos WHERE ID = ? ");
            sql.setInt(1, id);
            sql.executeUpdate();

        } catch (SQLException e) {
            System.err.println("Query de delete (excluir Aluno) incorreta");
        } finally {
            conexao.closeConexao();
        }
    }
    
     @Override
    
    //Listar categorias 
    public ArrayList<Aluno> getAll() {

        ArrayList<Aluno> meusAlunos = new ArrayList();
        Conexao conexao = new Conexao();
        try {
            String selectSQL = "SELECT * FROM alunos";
            PreparedStatement preparedStatement;
            preparedStatement = conexao.getConexao().prepareStatement(selectSQL);
            ResultSet resultado = preparedStatement.executeQuery();
            if (resultado != null) {
                while (resultado.next()) {
                    Aluno Aluno = new Aluno(
                            resultado.getInt("ID"),
                            resultado.getString("nome")
                    );
                    meusAlunos.add(Aluno);
                }
            }
        } catch (SQLException e) {
            System.err.println("Query de select (GetAll - Alunos) incorreta");
        } finally {
            conexao.closeConexao();
        }
        return meusAlunos;
    }
}


