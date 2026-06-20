package com.trabalho.demo.repository;

import com.trabalho.demo.model.Aluno;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AlunoRepository extends JpaRepository<Aluno, Long> {
    Page<Aluno> findByNomeContainingIgnoreCase(String nome, Pageable pageable);
}