package com.trabalho.demo.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "professores")
public class Professor extends Pessoa {

    @NotBlank(message = "A especialidade é obrigatorio.")
    private String especialidade;

    public Professor() { super(); }

    public Professor(String nome, String email, String especialidade) {
        super(nome, email);
        this.especialidade = especialidade;
    }

    public String getEspecialidade() { return especialidade; }
    public void setEspecialidade(String especialidade) { this.especialidade = especialidade; }
}