package bdd.jbehave;

import model.TaskOwner;
import model.TodoTask;
import org.hamcrest.MatcherAssert;
import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;
import repository.TodoListRepo;
import service.TodoListService;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.equalTo;

public class TaskCreationSteps {

    private TodoTask todoTask;
    private TodoListService todoListService = new TodoListService();
    private TodoListRepo repository = TodoListRepo.getInstance();

    private List<TodoTask> todoTasks = new ArrayList<>();
    private boolean isDeleted;

    @Given("a new task")
    public void taskIsCreated() {
        todoTask = new TodoTask();
    }

    @When("Task get own title - $name")
    public void setTaskName(String name) {
        todoTask.setTaskName(name);
    }

    @Then("user can show task $name name")
    public void taskNameIsCorrect(String name) {
        MatcherAssert.assertThat(todoTask.getTaskName(), equalTo(name));
    }

    @When("user set owner to task")
    public void setTodoTaskToDone(TaskOwner taskOwner) {
        todoTask.setTaskOwner(taskOwner);
    }

    @Then("task get new owner")
    public void taskOwnerIsPresent(TaskOwner taskOwner) {
        MatcherAssert.assertThat(todoTask.getTaskOwner(), equalTo(taskOwner));
    }

    @When("user wanna change task state")
    public void setTaskDoneState(boolean isDone) {
        todoTask.setDone(isDone);
    }

    @Then("task state will update")
    public void taskStateChanged(boolean isDone) {
        MatcherAssert.assertThat(todoTask.isDone(), equalTo(isDone));
    }

    @Given("init tasks list")
    public void initTaskList() {
        repository.collectionAccess().add(new TodoTask(1, "Call mom"));
        repository.collectionAccess().add(new TodoTask(2, "Feed dog"));
    }

    @When("user wanna tasks with $regex")
    public void getTasksByRegex(String regex) {
        todoTasks = todoListService.getTasksByRegex(regex);
    }

    @Then("user get all tasks with $regex ")
    public void tasksByRegexCorrect(String regex) {
        MatcherAssert.assertThat(todoTasks.size(), equalTo(1));
        MatcherAssert.assertThat(todoTasks.get(1).getTaskName(), containsString(regex) );
    }

    @Given("inits tasks list")
    public void initsTaskList() {
        repository.collectionAccess().add(new TodoTask(1, "Call mom"));
        repository.collectionAccess().add(new TodoTask(2, "Feed dog"));
    }

    @When("user wanna delete tasks with $regex")
    public void removeTasksByRegex(String regex) {
        isDeleted = todoListService.deleteTasksByRegex(regex);
    }

    @Then("user will remove all tasks contains $regex")
    public void tasksByRegexRemovedCorrect(String regex) {
        MatcherAssert.assertThat(isDeleted, equalTo(true) );
        MatcherAssert.assertThat(todoTasks.size(), equalTo(1));
    }
}
