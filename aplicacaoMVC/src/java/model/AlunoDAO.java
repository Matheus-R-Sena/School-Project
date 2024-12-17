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
import entidade.Categoria;
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
                    aluno.setNome(resultado.getString("NOME"));
                    aluno.setNome(resultado.getString("NOME"));
                    
                }
            }
        } catch (SQLException e) {
            System.err.println("Query de select (get categoria) incorreta");
        } finally {
            conexao.closeConexao();
        }
        return categoria;
    }
    
}


public class CategoriaDAO implements Dao<Categoria> {

    @Override
    public Categoria get(int id) {
        Conexao conexao = new Conexao();
        Categoria categoria = new Categoria();
        try {
            PreparedStatement sql = conexao.getConexao().prepareStatement("SELECT * FROM Categorias WHERE ID = ? ");
            sql.setInt(1, id);
            ResultSet resultado = sql.executeQuery();

            if (resultado != null) {
                while (resultado.next()) {
                    categoria.setId(Integer.parseInt(resultado.getString("ID")));
                    categoria.setDescricao(resultado.getString("DESCRICAO"));
                }
            }
        } catch (SQLException e) {
            System.err.println("Query de select (get categoria) incorreta");
        } finally {
            conexao.closeConexao();
        }
        return categoria;
    }

    @Override
    public void insert(Categoria t) {

        Conexao conexao = new Conexao();
        try {
            PreparedStatement sql = conexao.getConexao().prepareStatement("INSERT INTO Categorias (descricao) VALUES (?)");
            sql.setString(1, t.getDescricao());
            sql.executeUpdate();

        } catch (SQLException e) {
            System.err.println("Query de insert (categoria) incorreta");
        } finally {
            conexao.closeConexao();
        }
    }

    @Override
    public void update(Categoria t) {
        Conexao conexao = new Conexao();
        try {
            PreparedStatement sql = conexao.getConexao().prepareStatement("UPDATE Categorias SET descricao = ?  WHERE ID = ? ");
            sql.setString(1, t.getDescricao());
            sql.setInt(2, t.getId());
            sql.executeUpdate();

        } catch (SQLException e) {
            System.err.println("Query de update (alterar categoria) incorreta");
        } finally {
            conexao.closeConexao();
        }
    }

    @Override
    public void delete(int id) {
        Conexao conexao = new Conexao();
        try {
            PreparedStatement sql = conexao.getConexao().prepareStatement("DELETE FROM Categorias WHERE ID = ? ");
            sql.setInt(1, id);
            sql.executeUpdate();

        } catch (SQLException e) {
            System.err.println("Query de delete (excluir categoria) incorreta");
        } finally {
            conexao.closeConexao();
        }
    }
    
    @Override
    
    //Listar categorias 
    public ArrayList<Categoria> getAll() {

        ArrayList<Categoria> meusCategorias = new ArrayList();
        Conexao conexao = new Conexao();
        try {
            String selectSQL = "SELECT * FROM Categorias";
            PreparedStatement preparedStatement;
            preparedStatement = conexao.getConexao().prepareStatement(selectSQL);
            ResultSet resultado = preparedStatement.executeQuery();
            if (resultado != null) {
                while (resultado.next()) {
                    Categoria Categoria = new Categoria(
                            resultado.getInt("ID"),
                            resultado.getString("Descricao")
                    );
                    meusCategorias.add(Categoria);
                }
            }
        } catch (SQLException e) {
            System.err.println("Query de select (GetAll - categorias) incorreta");
        } finally {
            conexao.closeConexao();
        }
        return meusCategorias;
    }
}
