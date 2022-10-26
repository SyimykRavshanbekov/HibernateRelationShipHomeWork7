package peaksoft.serviceImpl;

import peaksoft.dao.LessonDao;
import peaksoft.entity.Lesson;
import peaksoft.service.LessonService;

import java.util.List;

public class LessonServiceImpl implements LessonService {
    private LessonDao lessonDao = new LessonDao();

    @Override
    public void saveLesson(Lesson lesson) {
        try{
            lessonDao.saveLesson(lesson);
            System.out.println("Lesson with name: " + lesson.getName() + " successfully saved!");
        } catch (Exception e) {
            System.out.println("Lesson not saved");
        }
    }

    @Override
    public void updateLesson(Long id, Lesson lesson) {
        try{
            lessonDao.updateLesson(id, lesson);
            System.out.println("Lesson with name: " + lesson.getName() + " successfully updated!");
        } catch (Exception e) {
            System.out.println("Lesson wasn't updated");
        }
    }

    @Override
    public Lesson getLessonById(Long id) {
        try{
            if (lessonDao.getLessonById(id) != null){
                return lessonDao.getLessonById(id);
            }else System.out.println("Lesson not found!");
        }catch (Exception e){
            System.out.println("Lesson not found!");
        }
        return null;
    }

    @Override
    public List<Lesson> getLessonByCourseId(Long id) {
        try{
            if (lessonDao.getLessonByCourseId(id).size() != 0){
                return lessonDao.getLessonByCourseId(id);
            }else System.out.println("There is no such instructors");
        }catch (Exception e){
            System.out.println("Instructors not found!");
        }
        return null;
    }
}
