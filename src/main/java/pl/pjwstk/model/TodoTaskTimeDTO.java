package pl.pjwstk.model;

import java.time.LocalDateTime;

public class TodoTaskTimeDTO {
    private LocalDateTime creationTime;
    private LocalDateTime updatedTime;
    private LocalDateTime lastReadTime;

    public TodoTaskTimeDTO() {
    }

    public TodoTaskTimeDTO(LocalDateTime creationTime, LocalDateTime updatedTime, LocalDateTime lastReadTime) {
        this.creationTime = creationTime;
        this.updatedTime = updatedTime;
        this.lastReadTime = lastReadTime;
    }

    public TodoTaskTimeDTO create(TodoTask task) {
        return new TodoTaskTimeDTO(task.getCreationTime(), task.getUpdatedTime(), task.getLastReadTime());
    }

    public LocalDateTime getCreationTime() {
        return creationTime;
    }

    public LocalDateTime getUpdatedTime() {
        return updatedTime;
    }

    public LocalDateTime getLastReadTime() {
        return lastReadTime;
    }
}
