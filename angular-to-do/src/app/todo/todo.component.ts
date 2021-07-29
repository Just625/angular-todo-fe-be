import {Component, OnInit} from '@angular/core';
import {Todo} from '../model/todo';
import {FormControl} from '@angular/forms';
import {TodoService} from '../service/todo.service';

@Component({
  selector: 'app-todo',
  templateUrl: './todo.component.html',
  styleUrls: ['./todo.component.css']
})
export class TodoComponent implements OnInit {
  toDoList: Todo[] = [];
  content = new FormControl();

  constructor(private todoService: TodoService) {
  }

  ngOnInit() {
    this.getAll();
  }

  getAll() {
    this.todoService.getAll().subscribe(todoList => {
      this.toDoList = todoList;
    }, e => {
      console.log(e);
    });
  }

  change() {
    const value = this.content.value;
    if (value) {
      const todo: Todo = {
        content: value,
        complete: false
      };
      this.todoService.create(todo).subscribe(() => {
        this.content.reset();
        this.getAll();
      }, e => {
        console.log(e);
      });
    }
  }

  toggleToDo(id: number) {
    this.todoService.updateById(id).subscribe(() => {
      this.getAll();
    }, e => {
      console.log(e);
    });
  }

  deleteItem(id: number) {
    this.todoService.deleteById(id).subscribe(() => {
      this.getAll();
    }, e => {
      console.log(e);
    });
  }
}
