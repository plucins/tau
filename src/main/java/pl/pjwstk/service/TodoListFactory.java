package pl.pjwstk.service;

import pl.pjwstk.model.TodoTask;

public class TodoListFactory {

    public static TodoTask create(long id, String taskName) {
        return new TodoTask(id, taskName);
    }
}
