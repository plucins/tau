package service;

import model.TodoTask;
import model.TodoTaskTimeDTO;
import repository.TodoListRepo;

import java.time.LocalDateTime;
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
        if (TodoListRepo.getInstance().isPresentInRepoById(id)) {

            Optional<TodoTask> optionalTodoTask = TodoListRepo.getInstance().getObjectById(id);
            if (optionalTodoTask.isPresent()) {
                TodoTask todoTask = optionalTodoTask.get();
                if (todoTask.isSaveTimes()) {
                    todoTask.setLastReadTime(LocalDateTime.now());
                }
                return todoTask;
            }
        }

        throw new NoSuchElementException("Element with pointed id doesnt exist");
    }

    public boolean deleteTaskById(long id) {
        if (TodoListRepo.getInstance().isPresentInRepoById(id)) {
            TodoListRepo.getInstance().collectionAccess().remove(getTaskById(id));
            return true;
        }
        return false;
    }

    public List<TodoTask> getAllTodoTasks() {
        return new ArrayList<>(TodoListRepo.getInstance().collectionAccess());
    }

    public TodoTask updateTodoTask(long id, TodoTask task) {
        if (TodoListRepo.getInstance().isPresentInRepoById(id)) {
            TodoTask taskToUpdate = getTaskById(id);

            taskToUpdate.setTaskName(task.getTaskName());
            taskToUpdate.setDone(task.isDone());
            taskToUpdate.setTaskOwner(task.getTaskOwner());
            taskToUpdate.setSaveTimes(task.isSaveTimes());

            if (task.isSaveTimes()) {
                taskToUpdate.setUpdatedTime(LocalDateTime.now());
                taskToUpdate.setLastReadTime(task.getLastReadTime());
            }

            TodoListRepo.getInstance().collectionAccess().remove(getTaskById(id));
            TodoListRepo.getInstance().collectionAccess().add(taskToUpdate);

            return taskToUpdate;
        }

        throw new NoSuchElementException("Element with pointed id doesnt exist");
    }

    public TodoTaskTimeDTO getTimesById(long id) {
        return new TodoTaskTimeDTO().create(getTaskById(id));
    }
}
