package peaksoft.serviceImpl;

import peaksoft.dao.InstructorDao;
import peaksoft.entity.Course;
import peaksoft.entity.Instructor;
import peaksoft.service.InstructorService;

import java.util.List;

public class InstructorServiceImpl implements InstructorService {
    private InstructorDao instructorDao = new InstructorDao();

    @Override
    public void saveInstructor(Instructor instructor) {
        try{
            instructorDao.saveInstructor(instructor);
            System.out.println("Instructor with name: " + instructor.getFirstName() + " successfully saved!");
        } catch (Exception e) {
            System.out.println("Instructor not saved");
        }
    }

    @Override
    public void updateInstructor(Long id, Instructor instructor) {
        try{
            instructorDao.updateInstructor(id, instructor);
            System.out.println("Instructor with name: " + instructor.getFirstName() + " successfully updated!");
        } catch (Exception e) {
            System.out.println("Instructor wasn't updated");
        }
    }

    @Override
    public Instructor getInstructorById(Long id) {
        try{
            if (instructorDao.getInstructorById(id) != null){
                return instructorDao.getInstructorById(id);
            }else System.out.println("Instructor not found!");
        }catch (Exception e){
            System.out.println("Instructor not found!");
        }
        return null;
    }

    @Override
    public List<Instructor> getInstructorsByCourseId(Long id) {
        try{
            if (instructorDao.getInstructorsByCourseId(id).size() != 0){
                return instructorDao.getInstructorsByCourseId(id);
            }else System.out.println("There is no such instructors");
        }catch (Exception e){
            System.out.println("Instructors not found!");
        }
        return null;
    }

    @Override
    public void deleteInstructorById(Long id) {
        try{
            instructorDao.deleteInstructorById(id);
            System.out.println("Instructor successfully deleted");
        } catch (Exception e) {
            System.out.println("Instructor wasn't deleted");
        }
    }

    @Override
    public void assignInstructorToCourse(Long id, Long courseId) {
        try{
            instructorDao.assignInstructorToCourse(id, courseId);
            System.out.println("Instructor with id: " + id + "successfully assigned");
        } catch (Exception e) {
            System.out.println("Instructor or course not found in database");
        }
    }
}
