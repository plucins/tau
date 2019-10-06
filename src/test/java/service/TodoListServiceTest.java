package service;

import model.TodoTask;
import org.junit.*;
import repository.TodoListRepo;

import java.util.ArrayList;
import java.util.List;


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
    public void addTaskToList() {
        int taskCountBeforeAdd = repository.collectionAccess().size();
        todoListService.addTaskToList(new TodoTask(6, "kolejny"));
        Assert.assertEquals(taskCountBeforeAdd + 1, repository.collectionAccess().size());
    }

    @Test
    public void getTaskById() {
        TodoTask task = todoListService.getTaskById(1);
        Assert.assertEquals(task.getTaskName(), "pierwsza karta");
    }

    @Test
    public void deleteTaskById() {
        int elementNumbersBeforeTest = repository.collectionAccess().size();
        todoListService.deleteTaskById(1);
        Assert.assertEquals(elementNumbersBeforeTest, repository.collectionAccess().size() + 1);
    }

    @Test
    public void getAllTodoTasks() {
        List<TodoTask> tasks = todoListService.getAllTodoTasks();
        Assert.assertEquals(tasks.size(), 5);
    }

    @After
    public void clear() {
        repository.setTodoLists(new ArrayList<>());
    }
}
