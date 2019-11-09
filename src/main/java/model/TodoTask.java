package model;

import java.time.LocalDateTime;

public class TodoTask {
    private Long id;
    private String taskName;
    private boolean isDone;
    private LocalDateTime creationTime;
    private LocalDateTime updatedTime;
    private LocalDateTime lastReadTime;
    private TaskOwner taskOwner;
    private boolean saveTimes;

    public TodoTask() {
    }

    public TodoTask(long id, String taskName) {
        this.id = id;
        this.taskName = taskName;
        this.isDone = false;
        this.taskOwner = new TaskOwner();
        this.creationTime = LocalDateTime.now();
        this.updatedTime = LocalDateTime.now();
        this.saveTimes = true;
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

    public TaskOwner getTaskOwner() {
        return taskOwner;
    }

    public void setTaskOwner(TaskOwner taskOwner) {
        this.taskOwner = taskOwner;
    }

    public LocalDateTime getCreationTime() {
        return creationTime;
    }

    public void setCreationTime(LocalDateTime creationTime) {
        this.creationTime = creationTime;
    }

    public LocalDateTime getUpdatedTime() {
        return updatedTime;
    }

    public void setUpdatedTime(LocalDateTime updatedTime) {
        this.updatedTime = updatedTime;
    }

    public LocalDateTime getLastReadTime() {
        return lastReadTime;
    }

    public void setLastReadTime(LocalDateTime lastReadTime) {
        this.lastReadTime = lastReadTime;
    }

    public boolean isSaveTimes() {
        return saveTimes;
    }

    public void setSaveTimes(boolean saveTimes) {
        this.saveTimes = saveTimes;
    }

    @Override
    public String toString() {
        return "TodoTask{" +
                "id=" + id +
                ", taskName='" + taskName + '\'' +
                ", isDone=" + isDone +
                ", creationTime=" + creationTime +
                ", updatedTime=" + updatedTime +
                ", lastReadTime=" + lastReadTime +
                ", taskOwner=" + taskOwner +
                ", saveTimes=" + saveTimes +
                '}';
    }
}
