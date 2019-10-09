package service;

import model.TaskOwener;
import model.TodoTask;
import org.junit.*;
import repository.TodoListRepo;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;


public class TodoListServiceTest {


    private TodoListRepo repository = TodoListRepo.getInstance();
    private TodoListService todoListService = new TodoListService();

    @BeforeClass
    public static void setup() {

    }

    @Before
    public void initList() {
        repository.collectionAccess().add(new TodoTask(1, "pierwsza karta"));
        repository.collectionAccess().add(new TodoTask(2, "druga karta"));
        repository.collectionAccess().add(new TodoTask(3, "trzecia karta"));
        repository.collectionAccess().add(new TodoTask(4, "czwarta karta"));
        repository.collectionAccess().add(new TodoTask(5, "piata karta"));
    }




    @Test
    public void addTaskToList_correct_case() {
        int taskCountBeforeAdd = repository.collectionAccess().size();
        todoListService.addTaskToList(new TodoTask(6, "kolejny"));
        Assert.assertEquals(taskCountBeforeAdd + 1, repository.collectionAccess().size());
    }

    @Test
    public void getTaskById_correct_case() {
        TodoTask task = todoListService.getTaskById(1);
        Assert.assertEquals(task.getTaskName(), "pierwsza karta");
    }

    @Test(expected = NoSuchElementException.class)
    public void getTaskById_NoSuchElementException_expected() {
        TodoTask task = todoListService.getTaskById(19);
    }

    @Test
    public void deleteTaskById_correct_case() {
        int elementNumbersBeforeTest = repository.collectionAccess().size();
        Assert.assertTrue(todoListService.deleteTaskById(1));
        Assert.assertEquals(elementNumbersBeforeTest, repository.collectionAccess().size() + 1);
    }

    @Test
    public void deleteTaskById_no_element_to_delete() {

        Assert.assertFalse(todoListService.deleteTaskById(99));
    }

    @Test
    public void getAllTodoTasks_correct_case() {
        List<TodoTask> tasks = todoListService.getAllTodoTasks();
        Assert.assertEquals(tasks.size(), 5);
    }

    @Test
    public void updateTodoTask_correct_case() {

        TodoTask task = new TodoTask(99, "Title");
        task.setDone(true);
        TodoTask taskToUpdate = todoListService.getTaskById(1);
        task.setTaskOwener(new TaskOwener((long) 1,"Adam","Adamowicz",true));
        todoListService.updateTodoTask(1, task);

        Assert.assertEquals(taskToUpdate.getTaskName(), task.getTaskName());
        Assert.assertEquals(taskToUpdate.isDone(), task.isDone());
    }

    @Test(expected = NoSuchElementException.class)
    public void updateTodoTask_NoSuchElementException_expected() {

        TodoTask task = new TodoTask(99, "Title");
        task.setDone(true);
        task.setTaskOwener(new TaskOwener((long) 1,"Adam","Adamowicz",true));
        todoListService.updateTodoTask(98, task);

    }

    @After
    public void clear() {
        repository.setTodoLists(new ArrayList<>());
    }
}
