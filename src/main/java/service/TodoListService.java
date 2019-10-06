package service;

import model.TodoTask;
import repository.TodoListRepo;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

public class TodoListService {


    public TodoListService() {
    }


    public boolean addTaskToList(TodoTask task) {
        if (task == null) throw new IllegalArgumentException("Object is null");

        TodoListRepo.getInstance().collectionAccess().add(task);
        return true;
    }

    public TodoTask getTaskById(long id) {
        if (TodoListRepo.getInstance().isPressentInRepoById(id)) {

            Optional<TodoTask> optionalTodoTask = TodoListRepo.getInstance().getObjectById(id);
            if (optionalTodoTask.isPresent()) {
                return optionalTodoTask.get();
            }
        }

        throw new NoSuchElementException("Element with pointed id doesnt exist");
    }

    public boolean deleteTaskById(long id) {
        if (TodoListRepo.getInstance().isPressentInRepoById(id)) {
            TodoListRepo.getInstance().collectionAccess().remove(getTaskById(id));
            return true;
        }
        return false;
    }

    public List<TodoTask> getAllTodoTasks() {
        return new ArrayList<>(TodoListRepo.getInstance().collectionAccess());
    }
}
