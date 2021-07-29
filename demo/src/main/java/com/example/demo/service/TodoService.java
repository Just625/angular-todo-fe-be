package com.example.demo.service;

import com.example.demo.model.ToDo;
import com.example.demo.repository.ITodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class TodoService implements ITodoService{
    @Autowired
    private ITodoRepository todoRepository;

    @Override
    public Iterable<ToDo> findAll() {
        return todoRepository.findAll();
    }

    @Override
    public Optional<ToDo> findById(Long id) {
        return todoRepository.findById(id);
    }

    @Override
    public ToDo save(ToDo todo) {
        return todoRepository.save(todo);
    }

    @Override
    public void deleteById(Long id) {
        todoRepository.deleteById(id);
    }
}
