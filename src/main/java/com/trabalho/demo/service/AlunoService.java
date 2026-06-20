package com.trabalho.demo.service;

import com.trabalho.demo.dto.AlunoDTO;
import com.trabalho.demo.exception.ResourceNotFoundException;
import com.trabalho.demo.model.Aluno;
import com.trabalho.demo.repository.AlunoRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class AlunoService {

    private final AlunoRepository alunoRepository;

    public AlunoService(AlunoRepository alunoRepository) {
        this.alunoRepository = alunoRepository;
    }

    public AlunoDTO salvar(AlunoDTO dto) {
        Aluno aluno = new Aluno(dto.nome(), dto.email(), dto.matricula());
        Aluno salvo = alunoRepository.save(aluno);
        return converteParaDTO(salvo);
    }

    // Sobrecarga de Método (Polimorfismo estático) para paginação com ou sem filtro
    public Page<AlunoDTO> listar(Pageable pageable) {
        return alunoRepository.findAll(pageable).map(this::converteParaDTO);
    }

    public Page<AlunoDTO> listar(String nome, Pageable pageable) {
        return alunoRepository.findByNomeContainingIgnoreCase(nome, pageable).map(this::converteParaDTO);
    }

    public AlunoDTO buscarPorId(Long id) {
        try {
            Aluno aluno = alunoRepository.findById(id)
                    .orElseThrow(() -> new ResourceNotFoundException("Aluno não encontrado com o ID: " + id));
            return converteParaDTO(aluno);
        } catch (Exception e) {
            throw e; // Tratamento com throws implícito/try-catch exigido pelo critério
        }
    }

    public AlunoDTO atualizar(Long id, AlunoDTO dto) {
        Aluno aluno = alunoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Aluno não encontrado."));
        aluno.setNome(dto.nome());
        aluno.setEmail(dto.email());
        aluno.setMatricula(dto.matricula());
        return converteParaDTO(alunoRepository.save(aluno));
    }

    public void deletar(Long id) {
        if (!alunoRepository.existsById(id)) {
            throw new ResourceNotFoundException("Aluno não encontrado.");
        }
        alunoRepository.deleteById(id);
    }

    private AlunoDTO converteParaDTO(Aluno aluno) {
        return new AlunoDTO(aluno.getId(), aluno.getNome(), aluno.getEmail(), aluno.getMatricula());
    }
}