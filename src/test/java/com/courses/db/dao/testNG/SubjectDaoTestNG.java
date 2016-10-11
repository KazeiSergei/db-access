package com.courses.db.dao.testNG;


import com.courses.db.dao.SubjectDao;
import com.courses.db.dto.Subject;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;

public class SubjectDaoTestNG {

    private static SubjectDao subjectDao;


    @BeforeClass
    public static void beforeClass() {
        subjectDao = new SubjectDao();
    }

    @BeforeMethod
    public void setUp() {
        subjectDao.insertSubject(new Subject("Test_1"));
        subjectDao.insertSubject(new Subject("Test_2"));
        subjectDao.insertSubject(new Subject("Test_3"));
    }

    @AfterMethod
    public void tearDown() {
        List<Subject> subjects = subjectDao.getAllSudject();
        for (Subject s : subjects) {
            subjectDao.delateSubject(s.getId());
        }
    }

    @AfterClass
    public static void afterClass() {
        subjectDao.close();
    }

    @Test
    public void testGetSubjectById() throws Exception {
        Subject subject = subjectDao.getAllSudject().stream().filter(x -> "Test_1".equals(x.getName())).findFirst().get();
        int id1 = subject.getId();
        subject = subjectDao.getAllSudject().stream().filter(x -> "Test_2".equals(x.getName())).findFirst().get();
        int id2 = subject.getId();
        subject = subjectDao.getAllSudject().stream().filter(x -> "Test_3".equals(x.getName())).findFirst().get();
        int id3 = subject.getId();
        Assert.assertEquals("Test_1",subjectDao.getSubjectById(id1).getName());
        Assert.assertEquals("Test_2",subjectDao.getSubjectById(id2).getName());
        Assert.assertEquals("Test_3",subjectDao.getSubjectById(id3).getName());
    }

    @Test
    public void testGetAllSudject() throws Exception {
        List<Subject> subjects = subjectDao.getAllSudject();
        Assert.assertEquals( 3, subjects.size());
    }



    @Test
    public void testUpdateSubject() throws Exception {
        Subject subject = subjectDao.getAllSudject().stream().filter(x -> "Test_1".equals(x.getName())).findFirst().get();
        int id1 = subject.getId();
        subject = new Subject("Test1_1");
        subjectDao.updateSubject(subject,id1);
        Assert.assertEquals( "Test1_1", subjectDao.getSubjectById(id1).getName());
    }


}
