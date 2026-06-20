package com.trabalho.demo.repository;

import com.trabalho.demo.model.Turma;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TurmaRepository extends JpaRepository<Turma, Long> {

    Page<Turma> findByNomeDisciplinaContainingIgnoreCase(String disciplina, Pageable pageable);
    Page<Turma> findByNotaLessThan(Double nota, Pageable pageable);
    Page<Turma> findByFrequenciaLessThan(Integer frequencia, Pageable pageable);
    Page<Turma> findByNotaGreaterThanEqual(Double nota, Pageable pageable);
}