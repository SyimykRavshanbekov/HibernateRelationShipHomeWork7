package peaksoft.dao;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import peaksoft.Util.Util;
import peaksoft.entity.Course;
import peaksoft.entity.Instructor;
import peaksoft.service.CourseService;

import java.util.List;

public class CourseDao implements CourseService {
    private final SessionFactory sessionFactory = Util.getSessionFactory();

    @Override
    public void saveCourse(Course course) {
        try (Session session = sessionFactory.openSession()){
            session.beginTransaction();
            session.persist(new Course(course.getCourseName(), course.getDuration(), course.getCreateAt(), course.getImageLink(), course.getDescription()));
            session.getTransaction().commit();
        } catch (Exception exception) {
            throw new RuntimeException();
        }
    }

    @Override
    public Course getCourseById(Long id) {
        try(Session session = sessionFactory.openSession()){
            session.beginTransaction();
            Course course = session.get(Course.class, id);
            session.getTransaction().commit();
            return course;
        } catch (HibernateException e) {
            throw new RuntimeException();
        }
    }

    @Override
    public List<Course> getAllCourse() {
        try{
            Session session = sessionFactory.openSession();
            session.beginTransaction();
            List<Course> courses = session.createQuery("select u from Course u order by u.createAt").list();
            session.getTransaction().commit();
            session.close();
            return courses;
        } catch (HibernateException e) {
            throw new RuntimeException();
        }
    }

    @Override
    public void updateCourse(Long id, Course course) {
        try(Session session = sessionFactory.openSession()){
            session.beginTransaction();
            Course newCourse = session.get(Course.class, id);
            newCourse.setCourseName(course.getCourseName());
            newCourse.setDuration(course.getDuration());
            newCourse.setCreateAt(course.getCreateAt());
            newCourse.setImageLink(course.getImageLink());
            newCourse.setDescription(course.getDescription());
            session.saveOrUpdate(newCourse);
            session.getTransaction().commit();
        } catch (HibernateException e) {
            throw new RuntimeException();
        }
    }

    @Override
    public void deleteCourseById(Long id) {
        try(Session session = sessionFactory.openSession()){
            session.beginTransaction();
            Course course = session.get(Course.class, id);

            for (Instructor i: course.getInstructors()) {
                i.setCourses(null);
            }

            session.delete(course);
            session.getTransaction().commit();
        } catch (HibernateException e) {
            throw new RuntimeException();
        }
    }

    @Override
    public Course getCourseByName(String name) {
        try(Session session = sessionFactory.openSession();){
            session.beginTransaction();
            List<Course> courses = session.createQuery("select u from Course u").list();
            session.getTransaction().commit();
            for (Course i: courses) {
                if (i.getCourseName().equals(name)){
                    return i;
                }
            }
            return null;
        } catch (HibernateException e) {
            throw new RuntimeException();
        }
    }
}
