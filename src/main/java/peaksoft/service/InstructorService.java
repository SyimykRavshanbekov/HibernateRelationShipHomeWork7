package peaksoft.service;

import peaksoft.entity.Course;
import peaksoft.entity.Instructor;

import java.util.List;

public interface InstructorService {
    void saveInstructor(Instructor instructor);
    void updateInstructor(Long id, Instructor instructor);
    Instructor getInstructorById(Long id);
    List<Instructor> getInstructorsByCourseId(Long id);
    void deleteInstructorById(Long id);
    void assignInstructorToCourse(Long id, Long courseId);
}
