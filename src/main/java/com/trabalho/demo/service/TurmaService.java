package com.trabalho.demo.service;

import com.trabalho.demo.dto.TurmaDTO;
import com.trabalho.demo.model.Aluno;
import com.trabalho.demo.model.Professor;
import com.trabalho.demo.model.Turma;
import com.trabalho.demo.repository.AlunoRepository;
import com.trabalho.demo.repository.ProfessorRepository;
import com.trabalho.demo.repository.TurmaRepository;
import com.trabalho.demo.exception.ResourceNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class TurmaService {

    private final TurmaRepository turmaRepository;
    private final ProfessorRepository professorRepository;
    private final AlunoRepository alunoRepository;

    public TurmaService(TurmaRepository turmaRepository,
                        ProfessorRepository professorRepository,
                        AlunoRepository alunoRepository) {
        this.turmaRepository = turmaRepository;
        this.professorRepository = professorRepository;
        this.alunoRepository = alunoRepository;
    }

    public TurmaDTO salvar(Turma turma) {
        Professor professor = professorRepository.findById(turma.getProfessor().getId())
                .orElseThrow(() -> new ResourceNotFoundException("Professor não encontrado."));
        Aluno aluno = alunoRepository.findById(turma.getAluno().getId())
                .orElseThrow(() -> new ResourceNotFoundException("Aluno não encontrado."));

        turma.setProfessor(professor);
        turma.setAluno(aluno);

        return converteParaDTO(turmaRepository.save(turma));
    }

    public TurmaDTO atualizar(Long id, Turma dadosNovos) {
        Turma turma = buscarPorId(id);

        Professor professor = professorRepository.findById(dadosNovos.getProfessor().getId())
                .orElseThrow(() -> new ResourceNotFoundException("Professor não encontrado."));
        Aluno aluno = alunoRepository.findById(dadosNovos.getAluno().getId())
                .orElseThrow(() -> new ResourceNotFoundException("Aluno não encontrado."));

        turma.setNomeDisciplina(dadosNovos.getNomeDisciplina());
        turma.setNota(dadosNovos.getNota());
        turma.setFrequencia(dadosNovos.getFrequencia());
        turma.setProfessor(professor);
        turma.setAluno(aluno);

        return converteParaDTO(turmaRepository.save(turma));
    }

    public Page<Turma> listarTodos(Pageable pageable) {
        return turmaRepository.findAll(pageable);
    }

    public Page<Turma> listarPorDisciplina(String disciplina, Pageable pageable) {
        return turmaRepository.findByNomeDisciplinaContainingIgnoreCase(disciplina, pageable);
    }

    public Page<Turma> listarPorNotaMenorQue(Double nota, Pageable pageable) {
        return turmaRepository.findByNotaLessThan(nota, pageable);
    }

    public Page<Turma> listarPorFrequenciaMenorQue(Integer frequencia, Pageable pageable) {
        return turmaRepository.findByFrequenciaLessThan(frequencia, pageable);
    }

    public Page<Turma> listarPorNotaMaiorOuIgalA(Double nota, Pageable pageable) {
        return turmaRepository.findByNotaGreaterThanEqual(nota, pageable);
    }

    public Turma buscarPorId(Long id) {
        return turmaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Registro de Turma não encontrado: " + id));
    }

    public void deletar(Long id) {
        Turma turma = buscarPorId(id);
        turmaRepository.delete(turma);
    }

    private TurmaDTO converteParaDTO(Turma turma) {
        return new TurmaDTO(
                turma.getId(),
                turma.getNomeDisciplina(),
                turma.getNota(),
                turma.getFrequencia(),
                turma.getProfessor() != null ? turma.getProfessor().getId() : null,
                turma.getProfessor() != null ? turma.getProfessor().getNome() : null,
                turma.getAluno() != null ? turma.getAluno().getId() : null,
                turma.getAluno() != null ? turma.getAluno().getNome() : null
        );
    }
}