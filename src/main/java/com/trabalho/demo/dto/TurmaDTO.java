package com.trabalho.demo.dto;

public record TurmaDTO(
        Long id,
        String nomeDisciplina,
        Double nota,
        Integer frequencia,
        Long professorId,
        String nomeProfessor,
        Long alunoId,
        String nomeAluno
) {}