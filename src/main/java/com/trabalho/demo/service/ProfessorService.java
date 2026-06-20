package com.trabalho.demo.service;

import com.trabalho.demo.model.Professor;
import com.trabalho.demo.repository.ProfessorRepository;
import com.trabalho.demo.exception.ResourceNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ProfessorService {

    private final ProfessorRepository professorRepository;

    public ProfessorService(ProfessorRepository professorRepository) {
        this.professorRepository = professorRepository;
    }

    public Professor salvar(Professor professor) {
        return professorRepository.save(professor);
    }

    public Page<Professor> listar(Pageable pageable) {
        return professorRepository.findAll(pageable);
    }

    public Page<Professor> listar(String nome, Pageable pageable) {
        return professorRepository.findByNomeContainingIgnoreCase(nome, pageable);
    }

    public Professor buscarPorId(Long id) {
        return professorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Professor não encontrado com o ID: " + id));
    }

    public Professor atualizar(Long id, Professor dadosNovos) {
        Professor professor = buscarPorId(id);
        professor.setNome(dadosNovos.getNome());
        professor.setEmail(dadosNovos.getEmail());
        professor.setEspecialidade(dadosNovos.getEspecialidade());
        return professorRepository.save(professor);
    }

    public void deletar(Long id) {
        Professor professor = buscarPorId(id);
        professorRepository.delete(professor);
    }
}
