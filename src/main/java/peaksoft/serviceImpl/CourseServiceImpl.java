package peaksoft.serviceImpl;

import peaksoft.dao.CourseDao;
import peaksoft.entity.Course;
import peaksoft.service.CourseService;

import java.util.List;

public class CourseServiceImpl implements CourseService {

    private CourseDao courseDao = new CourseDao();

    @Override
    public void saveCourse(Course course) {
        try{
            courseDao.saveCourse(course);
            System.out.println("Course with name: " + course.getCourseName() + " successfully saved!");
        }catch (Exception e){
            System.out.println("Course not saved");
        }
    }

    @Override
    public Course getCourseById(Long id) {
        try{
            if (courseDao.getCourseById(id) != null){
                return courseDao.getCourseById(id);
            }else System.out.println("Course not found!");
        }catch (Exception e){
            System.out.println("Course not found!");
        }
        return null;
    }

    @Override
    public List<Course> getAllCourse() {
        try{
            if (courseDao.getAllCourse().size() != 0){
                return courseDao.getAllCourse();
            }else System.out.println("There is no such courses");
        }catch (Exception e){
            System.out.println("Courses not found!");
        }
        return null;
    }

    @Override
    public void updateCourse(Long id, Course course) {
        try{
            courseDao.updateCourse(id, course);
        }catch (Exception e){
            System.out.println("Courses wasn't updated!");
        }
    }

    @Override
    public void deleteCourseById(Long id) {
        try{
            courseDao.deleteCourseById(id);
        }catch (Exception e){
            System.out.println("Courses wasn't deleted!");
        }
    }

    @Override
    public Course getCourseByName(String name) {
        try{
            if (courseDao.getCourseByName(name) != null){
                return courseDao.getCourseByName(name);
            }else System.out.println("Course with name: + " + name + " not found!");
        }catch (Exception e){
            System.out.println("Course with name: + " + name + " not found!");
        }
        return null;
    }
}
