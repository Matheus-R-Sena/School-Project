/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entidade;

/**
 *
 * @author mathe
 */
public class Turma {

    private int id;
    private int professorId;
    private int disciplinaId;
    private int alunoId;
    private String codigoTurma;
    private double nota;

    // Construtor completo
    public Turma(int professorId, int disciplinaId, int alunoId, String codigoTurma, double nota) {
        this.professorId = professorId;
        this.disciplinaId = disciplinaId;
        this.alunoId = alunoId;
        this.codigoTurma = codigoTurma;
        this.nota = nota;
    }

    // Construtor simplificado (sem nota)
    public Turma(int professorId, int disciplinaId, int alunoId, String codigoTurma) {
        this(professorId, disciplinaId, alunoId, codigoTurma, 0.0);
    }

    // Construtor padrão (sem parâmetros)
    public Turma() {
        this.id = 0;
        this.professorId = 0;
        this.disciplinaId = 0;
        this.alunoId = 0;
        this.codigoTurma = "";
        this.nota = 0.0;
    }

    // Getters e Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getProfessorId() {
        return professorId;
    }

    public void setProfessorId(int professorId) {
        this.professorId = professorId;
    }

    public int getDisciplinaId() {
        return disciplinaId;
    }

    public void setDisciplinaId(int disciplinaId) {
        this.disciplinaId = disciplinaId;
    }

    public int getAlunoId() {
        return alunoId;
    }

    public void setAlunoId(int alunoId) {
        this.alunoId = alunoId;
    }

    public String getCodigoTurma() {
        return codigoTurma;
    }

    public void setCodigoTurma(String codigoTurma) {
        this.codigoTurma = codigoTurma;
    }

    public double getNota() {
        return nota;
    }

    public void setNota(double nota) {
        this.nota = nota;
    }
}

