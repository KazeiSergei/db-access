package com.courses.db.dao;

import com.courses.db.dto.Subject;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.List;


public class SubjectDaoTest {
    private static SubjectDao subjectDao;
    private int id1, id2, id3;

    @BeforeClass
    public static void initSubjectDaoBeforeClass() {
        subjectDao = new SubjectDao();
    }



    @Before
    public void initSubjectDao() {
        subjectDao.insertSubject(new Subject("Test_1"));
        subjectDao.insertSubject(new Subject("Test_2"));
        subjectDao.insertSubject(new Subject("Test_3"));
        Subject subject = subjectDao.getAllSudject().stream().filter(x -> "Test_1".equals(x.getName())).findFirst().get();
        id1 = subject.getId();
        subject = subjectDao.getAllSudject().stream().filter(x -> "Test_2".equals(x.getName())).findFirst().get();
        id2 = subject.getId();
        subject = subjectDao.getAllSudject().stream().filter(x -> "Test_3".equals(x.getName())).findFirst().get();
        id3 = subject.getId();
    }

    @After
    public void tearDown() {
        subjectDao.delateSubject(id1);
        subjectDao.delateSubject(id2);
        subjectDao.delateSubject(id3);

    }

    @AfterClass
    public static void tearDownBeforeClass() {
        subjectDao.close();
    }

    @Test
    public void testGetSubjectById() throws Exception {
        Assert.assertEquals(subjectDao.getSubjectById(id1).getName(), "Test_1");
        Assert.assertEquals(subjectDao.getSubjectById(id2).getName(), "Test_2");
        Assert.assertEquals(subjectDao.getSubjectById(id3).getName(), "Test_3");
    }

    @Test
    public void testGetAllSudject() throws Exception {
        List<Subject> subjects = subjectDao.getAllSudject();
        Assert.assertEquals("Should be three subject ib database", 3, subjects.size());
    }



    @Test
    public void testUpdateSubject() throws Exception {
        Subject subject = new Subject("Test1_1");
        subjectDao.updateSubject(subject,id1);
        Assert.assertEquals(subjectDao.getSubjectById(id1).getName(), "Test1_1");
    }
}