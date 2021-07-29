package com.example.demo.service;

import com.example.demo.model.ToDo;

import java.util.Optional;

public interface ITodoService {
    Iterable<ToDo> findAll();
    Optional<ToDo> findById(Long id);
    ToDo save(ToDo todo);
    void deleteById(Long id);
}
