package service;

import model.TaskOwner;
import model.TodoTask;
import model.TodoTaskTimeDTO;
import org.junit.*;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import repository.TodoListRepo;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class TodoListServiceTest {


    private TodoListRepo repository = TodoListRepo.getInstance();
    private TodoListService todoListService = new TodoListService();

    @Mock
    private TodoTaskTimeDTO todoTaskTimeDTOMock;
    @Mock
    private TodoListService todoListServiceMock;
    @Mock
    private TodoTask taskMock;

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
        task.setTaskOwner(new TaskOwner((long) 1,"Adam","Adamowicz",true));
        todoListService.updateTodoTask(1, task);

        Assert.assertEquals(taskToUpdate.getTaskName(), task.getTaskName());
        Assert.assertEquals(taskToUpdate.isDone(), task.isDone());
    }

    @Test(expected = NoSuchElementException.class)
    public void updateTodoTask_NoSuchElementException_expected() {

        TodoTask task = new TodoTask(99, "Title");
        task.setDone(true);
        task.setTaskOwner(new TaskOwner((long) 1,"Adam","Adamowicz",true));
        todoListService.updateTodoTask(98, task);

    }

    @Test
    public void readDataOnGetObject_correct_case() {
        LocalDateTime time = LocalDateTime.now();
        when(todoListServiceMock.getTaskById(1)).thenReturn(taskMock);
        when(todoListServiceMock.getTaskById(1).getLastReadTime()).thenReturn(time);

        Assert.assertEquals(todoListServiceMock.getTaskById(1).getLastReadTime(), time);
    }

    @Test
    public void addedDateDuringAddToCollection_correct_case() {
        todoListService.addTaskToList(TodoListFactory.create(55, "make a call"));
        LocalDateTime time = LocalDateTime.now();

        when(todoListServiceMock.getTaskById(55)).thenReturn(taskMock);

        when(todoListServiceMock.getTaskById(55).getLastReadTime()).thenReturn(time);
        Assert.assertEquals(todoListService.getTaskById(55).getLastReadTime(), LocalDateTime.now());
    }

    @Test
    public void updatedDateDuringUpdateObject_correct_case() {
        LocalDateTime time = LocalDateTime.now();
        when(todoTaskTimeDTOMock.getUpdatedTime()).thenReturn(time);

        TodoTask task = todoListService.updateTodoTask(1, todoListService.getTaskById(2));
        Mockito.timeout(300);
        Assert.assertEquals(todoTaskTimeDTOMock.getUpdatedTime(), time);
    }

    @Test
    public void getTimesByTaskId() {
        Assert.assertNotNull(todoListService.getTimesById(1));
    }

    @Test
    public void setTimesSaveToFalse_correct_case() {
        TodoTask taskWithFalse = repository.collectionAccess().get(1);
        taskWithFalse.setSaveTimes(false);
        todoListService.updateTodoTask(taskWithFalse.getId(), taskWithFalse);
        Mockito.timeout(300);

        List<TodoTask> allTodoTasks = todoListService.getAllTodoTasks();
        Assert.assertTrue(allTodoTasks.stream().anyMatch(u -> u.getLastReadTime() != taskWithFalse.getLastReadTime()));
        Assert.assertEquals(1, allTodoTasks.stream().filter(u -> u.getLastReadTime() == taskWithFalse.getLastReadTime()).count());
    }


    //http://szuflandia.pjwstk.edu.pl/~pantadeusz/zajecia/tau/2019_2020_zaoczne/#art/lab2.md

    @After
    public void clear() {
        repository.setTodoLists(new ArrayList<>());

    }
}
