package com.example.demo.controller;

import com.example.demo.model.ToDo;
import com.example.demo.service.ITodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/todos")
@CrossOrigin
public class TodoController {
    @Autowired
    private ITodoService todoService;

    @GetMapping("")
    public ResponseEntity<Iterable<ToDo>> findAll() {
        return new ResponseEntity<>(todoService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ToDo> findById(@PathVariable Long id) {
        Optional<ToDo> toDoOptional = todoService.findById(id);
        if (!toDoOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(toDoOptional.get(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ToDo> addItem(@RequestBody ToDo toDo) {
        return new ResponseEntity<>(todoService.save(toDo), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ToDo> toggleToDo(@PathVariable Long id) {
        Optional<ToDo> toDoOptional = todoService.findById(id);
        if(!toDoOptional.isPresent()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        toDoOptional.get().setComplete(!toDoOptional.get().isComplete());
        return new ResponseEntity<>(todoService.save(toDoOptional.get()), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ToDo> deleteItem(@PathVariable Long id) {
        Optional<ToDo> toDoOptional = todoService.findById(id);
        if(!toDoOptional.isPresent()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        todoService.deleteById(id);
        return new ResponseEntity<>(toDoOptional.get(),HttpStatus.OK);
    }
}
