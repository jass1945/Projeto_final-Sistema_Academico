package com.trabalho.demo.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record AlunoDTO(
        Long id,

        @NotBlank(message = "o nome e obrigatório.")
        String nome,

        @NotBlank(message = "O email é obrigatório.")
        @Email(message = "Informe um email válido.")
        String email,

        @NotBlank(message = "A matrícula é obrigatoria.")
        String matricula
) {}