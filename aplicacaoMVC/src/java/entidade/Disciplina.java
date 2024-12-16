/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entidade;

/**
 *
 * @author mathe
 */
public class Disciplina {

    private int id;
    private String nome;
    private String requisito;
    private String ementa;
    private int cargaHoraria;

    // Construtor completo
    public Disciplina(String nome, String requisito, String ementa, int cargaHoraria) {
        this.nome = nome;
        this.requisito = requisito;
        this.ementa = ementa;
        this.cargaHoraria = cargaHoraria;
    }

    // Construtor simplificado (sem requisito, ementa e carga horária)
    public Disciplina(String nome) {
        this(nome, null, null, 0);
    }

    // Construtor padrão (sem parâmetros)
    public Disciplina() {
        this.id = 0;
        this.nome = "";
        this.requisito = "";
        this.ementa = "";
        this.cargaHoraria = 0;
    }

    // Getters e setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getRequisito() {
        return requisito;
    }

    public void setRequisito(String requisito) {
        this.requisito = requisito;
    }

    public String getEmenta() {
        return ementa;
    }

    public void setEmenta(String ementa) {
        this.ementa = ementa;
    }

    public int getCargaHoraria() {
        return cargaHoraria;
    }

    public void setCargaHoraria(int cargaHoraria) {
        this.cargaHoraria = cargaHoraria;
    }
}

