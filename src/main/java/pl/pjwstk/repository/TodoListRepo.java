package pl.pjwstk.repository;

import pl.pjwstk.model.TodoTask;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class TodoListRepo {
    private static TodoListRepo ourInstance = new TodoListRepo();

    public static TodoListRepo getInstance() {
        return ourInstance;
    }

    private TodoListRepo() {
    }

    private List<TodoTask> todoLists = new ArrayList<>();


    public List<TodoTask> collectionAccess() {
        todoLists
                .stream()
                .filter(TodoTask::isSaveTimes)
                .forEach(u -> u.setLastReadTime(LocalDateTime.now()));
        return todoLists;
    }

    public void setTodoLists(List<TodoTask> todoLists) {
        this.todoLists = todoLists;
    }



    public Optional<TodoTask> getObjectById(Long id) {
        return todoLists.stream()
                .filter(u -> u.getId().equals(id))
                .findFirst();

    }

    public boolean isPresentInRepoById(final Long id) {
        return todoLists.stream().anyMatch(u -> u.getId().equals(id));
    }
}
