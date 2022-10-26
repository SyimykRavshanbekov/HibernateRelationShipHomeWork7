package peaksoft.dao;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import peaksoft.Util.Util;
import peaksoft.entity.Course;
import peaksoft.entity.Instructor;
import peaksoft.entity.Lesson;
import peaksoft.entity.Task;
import peaksoft.service.TaskService;

import java.util.ArrayList;
import java.util.List;

public class TaskDao implements TaskService {
    private SessionFactory sessionFactory = Util.getSessionFactory();


    @Override
    public void saveTask(Task task) {
        try (Session session = sessionFactory.openSession();){
            session.beginTransaction();
            session.save(new Task(task.getName(), task.getDeadLine(), task.getTask(), task.getLesson().getId()));
            System.out.println("Task with name: " + task.getName() + " successfully saved!");
            session.getTransaction().commit();
        } catch (Exception exception) {
            throw new RuntimeException();
        }
    }

    @Override
    public void updateTask(Long id, Task task) {
        try(Session session = sessionFactory.openSession()){
            session.beginTransaction();
            Task newTask = session.get(Task.class, id);
            newTask.setName(task.getName());
            newTask.setTask(task.getTask());
            newTask.setDeadLine(task.getDeadLine());
            session.getTransaction().commit();
        } catch (HibernateException e) {
            throw new RuntimeException();
        }
    }

    @Override
    public List<Task> getAllTaskByLessonId(Long id) {
        try(Session session = sessionFactory.openSession()){
            session.beginTransaction();
            Lesson lesson = session.find(Lesson.class, id);
            List<Task> instructors = new ArrayList<>(lesson.getTaskList());
            session.getTransaction().commit();
            return instructors;
        } catch (HibernateException e) {
            throw new RuntimeException();
        }
    }

    @Override
    public void deleteTaskById(Long id) {
        try{
            Session session = sessionFactory.openSession();
            session.beginTransaction();
            Task task = session.get(Task.class, id);
            session.delete(task);
            session.getTransaction().commit();
            session.close();
        } catch (HibernateException e) {
            System.out.println(e.getMessage());
        }
    }
}
