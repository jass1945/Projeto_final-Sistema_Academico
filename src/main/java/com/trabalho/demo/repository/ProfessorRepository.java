package com.trabalho.demo.repository;

import com.trabalho.demo.model.Professor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProfessorRepository extends JpaRepository<Professor, Long> {
    Page<Professor> findByNomeContainingIgnoreCase(String nome, Pageable pageable);
}