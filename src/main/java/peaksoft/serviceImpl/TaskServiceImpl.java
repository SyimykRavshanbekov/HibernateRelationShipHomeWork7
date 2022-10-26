package peaksoft.serviceImpl;

import peaksoft.dao.TaskDao;
import peaksoft.entity.Task;
import peaksoft.service.TaskService;

import java.util.List;

public class TaskServiceImpl implements TaskService {
    private TaskDao taskDao = new TaskDao();

    @Override
    public void saveTask(Task task) {
        try{
            taskDao.saveTask(task);
            System.out.println("Task with name: " + task.getName() + " successfully saved!");
        } catch (Exception e) {
            System.out.println("Task not saved");
        }
    }

    @Override
    public void updateTask(Long id, Task task) {
        try{
            taskDao.updateTask(id, task);
            System.out.println("Task with name: " + task.getName() + " successfully updated!");
        } catch (Exception e) {
            System.out.println("Task wasn't updated");
        }
    }

    @Override
    public List<Task> getAllTaskByLessonId(Long id) {
        try{
            if (taskDao.getAllTaskByLessonId(id).size() != 0){
                return taskDao.getAllTaskByLessonId(id);
            }else System.out.println("There is no such tasks");
        }catch (Exception e){
            System.out.println("Tasks not found!");
        }
        return null;
    }

    @Override
    public void deleteTaskById(Long id) {
        try{
            taskDao.deleteTaskById(id);
        }catch (Exception e){
            System.out.println("Task wasn't deleted!");
        }
    }
}
