package com.courses.db.dao.testNG;


import com.courses.db.dao.MarkDao;
import com.courses.db.dao.StudentDao;
import com.courses.db.dao.SubjectDao;
import com.courses.db.dto.Mark;
import com.courses.db.dto.Student;
import com.courses.db.dto.Subject;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;

public class StudentDaoTestNG {

    private static StudentDao studentDao;
    private static MarkDao markDao;
    private static SubjectDao subjectDao;

    @BeforeClass
    public static void beforeClass() {
        studentDao = new StudentDao();
        subjectDao = new SubjectDao();
        markDao = new MarkDao();

    }

    @AfterClass
    public static void afterClass() {
        studentDao.close();
        markDao.close();
        subjectDao.close();
    }

    @BeforeMethod
    public void setUp() throws Exception {
        studentDao.insertStudent(new Student("Test_Name_1", "Test_Name_1.1"));
        Student student = studentDao.getAll().stream().filter(x -> "Test_Name_1".equals(x.getFirstName())).findFirst().get();
        int student_id1 = student.getId();

        studentDao.insertStudent(new Student("Test_Name_2", "Test_Name_2.2"));

        studentDao.insertStudent(new Student("Test_Name_3", "Test_Name_3.3"));

        subjectDao.insertSubject(new Subject("Test_1"));
        Subject subject = subjectDao.getAllSudject().stream().filter(x -> "Test_1".equals(x.getName())).findFirst().get();
        int subject_id = subject.getId();

        markDao.insertMark(new Mark(student_id1, subject_id, 8));
    }

    @AfterMethod
    public void tearDown() throws Exception {
        List<Mark> marks = markDao.getAllMark();
        for (Mark m : marks) {
            markDao.delateMark(m.getId());
        }
        List<Subject> subjects = subjectDao.getAllSudject();
        for (Subject s : subjects) {
            subjectDao.delateSubject(s.getId());
        }
        List<Student> students = studentDao.getAll();
        for (Student s : students) {
            studentDao.delateStudent(s.getId());
        }
    }

    @Test
    public void testGetAll() throws Exception {
        List<Student> students = studentDao.getAll();
        Assert.assertEquals(3, students.size());

    }

    @Test
    public void testGetStudentById() throws Exception {
        Student student = studentDao.getAll().stream().filter(x -> "Test_Name_1".equals(x.getFirstName())).findFirst().get();
        int student_id1 = student.getId();
        Assert.assertEquals("Test_Name_1", studentDao.getStudentById(student_id1).getFirstName());
        Assert.assertEquals( "Test_Name_1.1", studentDao.getStudentById(student_id1).getSecondName());
        student = studentDao.getAll().stream().filter(x -> "Test_Name_2".equals(x.getFirstName())).findFirst().get();
        int student_id2 = student.getId();
        Assert.assertEquals("Test_Name_2", studentDao.getStudentById(student_id2).getFirstName());
        Assert.assertEquals("Test_Name_2.2", studentDao.getStudentById(student_id2).getSecondName() );
        student = studentDao.getAll().stream().filter(x -> "Test_Name_3".equals(x.getFirstName())).findFirst().get();
        int student_id3 = student.getId();
        Assert.assertEquals("Test_Name_3", studentDao.getStudentById(student_id3).getFirstName() );
        Assert.assertEquals("Test_Name_3.3", studentDao.getStudentById(student_id3).getSecondName());


    }

    @Test
    public void testGetStudentWithMark() throws Exception {
        Student student = studentDao.getAll().stream().filter(x -> "Test_Name_1".equals(x.getFirstName())).findFirst().get();
        int student_id1 = student.getId();
        List<Student> students = studentDao.getStudentWithMark(student_id1);
        for (Student s : students) {
            Assert.assertEquals("Test_Name_1", s.getFirstName());
            Assert.assertEquals( "Test_Name_1.1", s.getSecondName());
            Assert.assertEquals( 8, s.getMark());
            Assert.assertEquals("Test_1", s.getSubject());
        }

    }


    @Test
    public void testUpdateStudent() throws Exception {
        Student student = studentDao.getAll().stream().filter(x -> "Test_Name_1".equals(x.getFirstName())).findFirst().get();
        int student_id1 = student.getId();
        student = new Student("Test1111", "Test2222");
        studentDao.updateStudent(student, student_id1);
        Assert.assertEquals( "Test1111", studentDao.getStudentById(student_id1).getFirstName());
        Assert.assertEquals( "Test2222", studentDao.getStudentById(student_id1).getSecondName());
    }
}
