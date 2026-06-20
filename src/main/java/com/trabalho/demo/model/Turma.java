package com.trabalho.demo.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

@Entity
@Table(name = "turmas")
public class Turma {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "O nome da disciplina é obrigatório.")
    private String nomeDisciplina;

    @NotNull(message = "A nota é obrigatória.")
    @DecimalMin(value = "0.0", message = "A nota não pode ser menor que 0.")
    @DecimalMax(value = "10.0", message = "A nota não pode ser maior que 10.")
    private Double nota;

    @NotNull(message = "A frequencia é obrigatória.")
    @Min(value = 0, message = "A frequência não pode ser menor que 0.")
    @Max(value = 100, message = "A frequência não pode ser maior que 100.")
    private Integer frequencia;

    @NotNull(message = "O professor é obrigatório.")
    @ManyToOne
    @JoinColumn(name = "professor_id")
    private Professor professor;

    @NotNull(message = "O aluno é obrigatório.")
    @ManyToOne
    @JoinColumn(name = "aluno_id")
    private Aluno aluno;

    public Turma() {}

    public Turma(String nomeDisciplina, Double nota, Integer frequencia, Professor professor, Aluno aluno) {
        this.nomeDisciplina = nomeDisciplina;
        this.nota = nota;
        this.frequencia = frequencia;
        this.professor = professor;
        this.aluno = aluno;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getNomeDisciplina() { return nomeDisciplina; }
    public void setNomeDisciplina(String nomeDisciplina) { this.nomeDisciplina = nomeDisciplina; }
    public Double getNota() { return nota; }
    public void setNota(Double nota) { this.nota = nota; }
    public Integer getFrequencia() { return frequencia; }
    public void setFrequencia(Integer frequencia) { this.frequencia = frequencia; }
    public Professor getProfessor() { return professor; }
    public void setProfessor(Professor professor) { this.professor = professor; }
    public Aluno getAluno() { return aluno; }
    public void setAluno(Aluno aluno) { this.aluno = aluno; }
}