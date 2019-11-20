package pl.pjwstk.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.pjwstk.model.TodoTask;
import pl.pjwstk.service.TodoListService;

@RestController
@RequestMapping("/api")
public class TodoController {

    private TodoListService todoListService;

    @Autowired
    public TodoController(TodoListService todoListService) {
        this.todoListService = todoListService;
    }

    @PostMapping("/add")
    public ResponseEntity<Boolean> addTask(@RequestBody TodoTask todoTask) {
        return ResponseEntity.ok(todoListService.addTaskToList(todoTask));
    }

    @GetMapping("/{id}")
    public ResponseEntity<TodoTask> getTaskById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(todoListService.getTaskById(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> deleteById(@PathVariable("id") Long id) {
        return ResponseEntity.ok(todoListService.deleteTaskById(id));
    }

    @PutMapping
    public ResponseEntity<TodoTask> updateTaskById(@PathVariable("id") Long id, @RequestBody TodoTask todoTask) {
        return ResponseEntity.ok(todoListService.updateTodoTask(id, todoTask));
    }
}
