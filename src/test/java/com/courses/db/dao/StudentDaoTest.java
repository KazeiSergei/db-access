package com.courses.db.dao;

import com.courses.db.dto.Mark;
import com.courses.db.dto.Student;
import com.courses.db.dto.Subject;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.List;

public class StudentDaoTest {
    private static StudentDao studentDao;
    private static MarkDao markDao;
    private static SubjectDao subjectDao;
    private int student_id1, student_id2, student_id3, subject_id, mark_id;

    @BeforeClass
    public static void initSubjectDaoBeforeClass() {
        studentDao = new StudentDao();
        subjectDao = new SubjectDao();
        markDao = new MarkDao();

    }

    @AfterClass
    public static void tearDownBeforeClass() {
        studentDao.close();
        markDao.close();
        subjectDao.close();
    }

    @Before
    public void setUp() throws Exception {
        studentDao.insertStudent(new Student("Test_Name_1", "Test_Name_1.1"));
        Student student = studentDao.getAll().stream().filter(x -> "Test_Name_1".equals(x.getFirstName())).findFirst().get();
        student_id1 = student.getId();

        studentDao.insertStudent(new Student("Test_Name_2", "Test_Name_2.2"));
        student = studentDao.getAll().stream().filter(x -> "Test_Name_2".equals(x.getFirstName())).findFirst().get();
        student_id2 = student.getId();

        studentDao.insertStudent(new Student("Test_Name_3", "Test_Name_3.3"));
        student = studentDao.getAll().stream().filter(x -> "Test_Name_3".equals(x.getFirstName())).findFirst().get();
        student_id3 = student.getId();

        subjectDao.insertSubject(new Subject("Test_1"));
        Subject subject = subjectDao.getAllSudject().stream().filter(x -> "Test_1".equals(x.getName())).findFirst().get();
        subject_id = subject.getId();

        markDao.insertMark(new Mark(student_id1, subject_id, 8));
        Mark mark = markDao.getAllMark().stream().filter(x -> (Integer.valueOf(8)).equals(x.getMark())).findFirst().get();
        mark_id = mark.getId();
    }

    @After
    public void tearDown() throws Exception {
        markDao.delateMark(mark_id);
        studentDao.delateStudent(student_id1);
        studentDao.delateStudent(student_id2);
        studentDao.delateStudent(student_id3);
        subjectDao.delateSubject(subject_id);
    }

    @Test
    public void testGetAll() throws Exception {
        List<Student> students = studentDao.getAll();
        Assert.assertEquals("Should be three subject in database", students.size(), 3);

    }

    @Test
    public void testGetStudentById() throws Exception {
        Assert.assertEquals(studentDao.getStudentById(student_id1).getFirstName(), "Test_Name_1");
        Assert.assertEquals(studentDao.getStudentById(student_id1).getSecondName(), "Test_Name_1.1");
        Assert.assertEquals(studentDao.getStudentById(student_id2).getFirstName(), "Test_Name_2");
        Assert.assertEquals("Second name is invalide", studentDao.getStudentById(student_id2).getSecondName(), "Test_Name_2.2");
        Assert.assertEquals(studentDao.getStudentById(student_id3).getFirstName(), "Test_Name_3");
        Assert.assertEquals(studentDao.getStudentById(student_id3).getSecondName(), "Test_Name_3.3");


    }

    @Test
    public void testGetStudentWithMark() throws Exception {
        List<Student> students = studentDao.getStudentWithMark(student_id1);
        for (Student s : students) {
            Assert.assertEquals("Fist name is invalide",s.getFirstName() , "Test_Name_1");
            Assert.assertEquals("Second name is invalide", s.getSecondName(), "Test_Name_1.1");
            Assert.assertEquals("Mark is invalide",s.getMark(),8);
            Assert.assertEquals("Subject is invalide",s.getSubject(),"Test_1");
        }

    }


    @Test
    public void testUpdateStudent() throws Exception {
        Student student = new Student("Test1111", "Test2222");
        studentDao.updateStudent(student, student_id1);
        Assert.assertEquals(studentDao.getStudentById(student_id1).getFirstName(), "Test1111");
        Assert.assertEquals(studentDao.getStudentById(student_id1).getSecondName(), "Test2222");
    }
}