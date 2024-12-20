/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import entidade.Disciplina;


public class DisciplinaDAO implements Dao<Disciplina> {
    
    @Override
    public Disciplina get(int id) {
        Conexao conexao = new Conexao();
        Disciplina disciplina = new Disciplina();
        try {
            PreparedStatement sql = conexao.getConexao().prepareStatement("SELECT * FROM disciplina WHERE id = ? ");
            sql.setInt(1, id);
            ResultSet resultado = sql.executeQuery();

            if (resultado != null) {
                while (resultado.next()) {
                    disciplina.setId(resultado.getInt("id"));
                    disciplina.setNome(resultado.getString("nome"));
                    disciplina.setRequisito(resultado.getString("requisito"));
                    disciplina.setEmenta(resultado.getString("ementa"));
                    disciplina.setCargaHoraria(resultado.getInt("carga_horaria"));
                }
            }
        } catch (SQLException e) {
            System.err.println("Query de select (get Disciplina) incorreta");
        } finally {
            conexao.closeConexao();
        }
        return disciplina;
    }
    
    @Override
    public void insert(Disciplina t) {
        Conexao conexao = new Conexao();
        try {
            PreparedStatement sql = conexao.getConexao().prepareStatement("INSERT INTO disciplina (nome, requisito, ementa, carga_horaria) VALUES (?, ?, ?, ?)");
            sql.setString(1, t.getNome());
            sql.setString(2, t.getRequisito());
            sql.setString(3, t.getEmenta());
            sql.setInt(4, t.getCargaHoraria());
            sql.executeUpdate();

        } catch (SQLException e) {
            System.err.println("Query de insert (Disciplina) incorreta");
        } finally {
            conexao.closeConexao();
        }
    }
    
    @Override
    public void update(Disciplina t) {
        Conexao conexao = new Conexao();
        try {
            PreparedStatement sql = conexao.getConexao().prepareStatement("UPDATE disciplina SET nome = ?, requisito = ?, ementa = ?, carga_horaria = ? WHERE id = ? ");
            sql.setString(1, t.getNome());
            sql.setString(2, t.getRequisito());
            sql.setString(3, t.getEmenta());
            sql.setInt(4, t.getCargaHoraria());
            sql.setInt(5, t.getId());
            sql.executeUpdate();

        } catch (SQLException e) {
            System.err.println("Query de update (alterar Disciplina) incorreta");
        } finally {
            conexao.closeConexao();
        }
    }
    
    @Override
    public void delete(int id) {
        Conexao conexao = new Conexao();
        try {
            PreparedStatement sql = conexao.getConexao().prepareStatement("DELETE FROM disciplina WHERE id = ? ");
            sql.setInt(1, id);
            sql.executeUpdate();

        } catch (SQLException e) {
            System.err.println("Query de delete (excluir Disciplina) incorreta");
        } finally {
            conexao.closeConexao();
        }
    }
    
    @Override
    public ArrayList<Disciplina> getAll() {
        ArrayList<Disciplina> minhasDisciplinas = new ArrayList<>();
        Conexao conexao = new Conexao();
        try {
            String selectSQL = "SELECT * FROM disciplina";
            PreparedStatement preparedStatement = conexao.getConexao().prepareStatement(selectSQL);
            ResultSet resultado = preparedStatement.executeQuery();
            if (resultado != null) {
                while (resultado.next()) {
                    Disciplina disciplina = new Disciplina(
                            resultado.getInt("id"),
                            resultado.getString("nome"),
                            resultado.getString("requisito"),
                            resultado.getString("ementa"),
                            resultado.getInt("carga_horaria")
                    );
                    minhasDisciplinas.add(disciplina);
                }
            }
        } catch (SQLException e) {
            System.err.println("Query de select (GetAll - Disciplina) incorreta");
        } finally {
            conexao.closeConexao();
        }
        return minhasDisciplinas;
    }
}