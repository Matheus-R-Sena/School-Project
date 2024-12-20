/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entidade;

/**
 *
 * @author mathe
 */
public class Professor {

    private int id;
    private String nome;
    private String email;
    private String cpf;
    private String senha;

    // Construtor completo
    public Professor(int id, String nome, String email, String cpf, String senha) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.cpf = cpf;
        this.senha = senha;
    }

    // Construtor simplificado (sem email e senha)
    public Professor(int id, String nome, String cpf) {
        this(id, nome, "", cpf, "");
    }

    // Construtor padrão (sem parâmetros)
    public Professor() {
        this.id = 0;
        this.nome = "";
        this.email = "";
        this.cpf = "";
        this.senha = "";
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
}

