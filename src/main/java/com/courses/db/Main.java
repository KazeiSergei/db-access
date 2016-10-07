package com.courses.db;


import com.courses.db.dao.MarkDao;
import com.courses.db.dao.StudentDao;
import com.courses.db.dao.SubjectDao;
import com.courses.db.dto.Mark;
import com.courses.db.dto.Student;
import com.courses.db.dto.Subject;

import java.sql.SQLException;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
    public static void main(String[] args) throws DaoException, SQLException {

        MarkDao markDao = new MarkDao();

        //markDao.insertMark(new Mark(132,206,3));
        //markDao.updateMark(new Mark(108,192,5),25);
        List<Mark> marks = markDao.getAllMark();
        for(Mark m : marks) {
            System.out.println(m);
            //markDao.delateMark(m.getId());

        }

        StudentDao studentDao = new StudentDao();
        //studentDao.insertStudent(new Student(9,"qqqq","444"));
        List<Student> students = studentDao.getAll();
        for(Student s : students){
            System.out.println(s);
            studentDao.delateStudent(s.getId());


        }

        SubjectDao subjectDao = new SubjectDao();
        //subjectDao.insertSubject(new Subject("rrr"));
        List<Subject> all = subjectDao.getAllSudject();
        for(Subject s : all){
            System.out.println(s);
          //  subjectDao.delateSubject(s.getId());
        }



        /*List<Student> getStudent = studentDao.getAll();
        studentDao.closeConnection();
        for(Student s : getStudent){
            System.out.println(s);
        }
/*
        studentDao.delateStudent(6);
        studentDao.updateStudent("Iryna", "Kazei" , 5);
        List<Student> student = studentDao.getAll();
        for(Student s : student){
            System.out.println(s);
        }
*/
       /* StudentDao studentDao = new StudentDao();
        List<Student> students = studentDao.getStudentWithMark(1);
        for (Student s: students) {
            System.out.println(s.getMarkId());
        }*/
        //System.out.println(studentDao.getAll());*/

        //studentDao.insertStudent(student);
        /*SubjectDao subjectDao = new SubjectDao();
        List<Subject> subjects = subjectDao.getAllSudject();
        for (Subject s: subjects) {
            System.out.println(s);
         }*/
        /*Subject subject = new Subject("English");
        subjectDao.insertSubject(subject);*/
        /*String str = "  Va   ";
        System.out.println(checkWithRegExp(str.trim()));*/

    }
    /*public static boolean checkWithRegExp(String string){
        Pattern p = Pattern.compile("^[A-Z][a-z]{1,255}$");
        Matcher m = p.matcher(string);
        return m.matches();
    }*/


}
