package service;

import model.TodoTask;

public class TodoListFactory {

    public static TodoTask create(long id, String taskName) {
        return new TodoTask(id, taskName);
    }
}
