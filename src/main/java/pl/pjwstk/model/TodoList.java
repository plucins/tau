package pl.pjwstk.model;

import java.util.List;

public class TodoList {
    private Long id;
    private List<TodoTask> todoTasks;

    public TodoList() {
    }

    public TodoList(Long id, List<TodoTask> todoTasks) {
        this.id = id;
        this.todoTasks = todoTasks;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<TodoTask> getTodoTasks() {
        return todoTasks;
    }

    public void setTodoTasks(List<TodoTask> todoTasks) {
        this.todoTasks = todoTasks;
    }
}
