package com.trabalho.demo.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "alunos")
public class Aluno extends Pessoa {

    @NotBlank(message = "A matrícula é obrigatoria.")
    private String matricula;

    public Aluno() { super(); }

    public Aluno(String nome, String email, String matricula) {
        super(nome, email);
        this.matricula = matricula;
    }

    public String getMatricula() { return matricula; }
    public void setMatricula(String matricula) { this.matricula = matricula; }
}