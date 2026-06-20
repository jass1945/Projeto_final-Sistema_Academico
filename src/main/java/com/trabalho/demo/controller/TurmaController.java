package com.trabalho.demo.controller;

import com.trabalho.demo.dto.TurmaDTO;
import com.trabalho.demo.model.Turma;
import com.trabalho.demo.service.TurmaService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/turmas")
public class TurmaController {

    private final TurmaService turmaRepository;

    public TurmaController(TurmaService turmaService) {
        this.turmaRepository = turmaService;
    }

    @PostMapping
    public ResponseEntity<TurmaDTO> criar(@Valid @RequestBody Turma turma) {
        return new ResponseEntity<>(turmaRepository.salvar(turma), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<Page<Turma>> listar(
            @RequestParam(required = false) String disciplina,
            @RequestParam(required = false) Double notaMaxima,
            @RequestParam(required = false) Double notaMinima,
            @RequestParam(required = false) Integer frequenciaMinima,
            Pageable pageable) {

        if (disciplina != null)
            return ResponseEntity.ok(turmaRepository.listarPorDisciplina(disciplina, pageable));
        if (notaMaxima != null)
            return ResponseEntity.ok(turmaRepository.listarPorNotaMenorQue(notaMaxima, pageable));
        if (frequenciaMinima != null)
            return ResponseEntity.ok(turmaRepository.listarPorFrequenciaMenorQue(frequenciaMinima, pageable));
        if (notaMinima != null)
            return ResponseEntity.ok(turmaRepository.listarPorNotaMaiorOuIgalA(notaMinima, pageable));

        return ResponseEntity.ok(turmaRepository.listarTodos(pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Turma> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(turmaRepository.buscarPorId(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<TurmaDTO> atualizar(@PathVariable Long id, @Valid @RequestBody Turma turma) {
        return ResponseEntity.ok(turmaRepository.atualizar(id, turma));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        turmaRepository.deletar(id);
        return ResponseEntity.noContent().build();
    }
}