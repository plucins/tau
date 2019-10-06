package model;

public class TodoTask {
    private Long id;
    private String taskName;
    private boolean isDone;
    private TaskOwener taskOwener;

    public TodoTask() {
    }

    public TodoTask(Long id, String taskName, boolean isDone, TaskOwener taskOwener) {
        this.id = id;
        this.taskName = taskName;
        this.isDone = isDone;
        this.taskOwener = taskOwener;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public boolean isDone() {
        return isDone;
    }

    public void setDone(boolean done) {
        isDone = done;
    }

    public TaskOwener getTaskOwener() {
        return taskOwener;
    }

    public void setTaskOwener(TaskOwener taskOwener) {
        this.taskOwener = taskOwener;
    }
}
