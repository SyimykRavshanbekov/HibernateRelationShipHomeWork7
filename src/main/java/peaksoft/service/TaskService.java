package peaksoft.service;

import peaksoft.entity.Course;
import peaksoft.entity.Task;

import java.util.List;

public interface TaskService {
    void saveTask(Task task);
    void updateTask(Long id, Task task);
    List<Task> getAllTaskByLessonId(Long id);
    void deleteTaskById(Long id);
}
