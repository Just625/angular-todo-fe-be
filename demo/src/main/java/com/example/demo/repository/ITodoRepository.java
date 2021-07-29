package com.example.demo.repository;

import com.example.demo.model.ToDo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ITodoRepository extends JpaRepository<ToDo, Long> {
}
