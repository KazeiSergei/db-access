package com.courses.db.dto;


public class Mark {

    private int id;
    private int student_id;
    private int subject_id;
    private int mark;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getStudent_id() {
        return student_id;
    }

    public void setStudent_id(int student_id) {
        this.student_id = student_id;
    }

    public int getSubject_id() {
        return subject_id;
    }

    public void setSubject_id(int subject_id) {
        this.subject_id = subject_id;
    }

    public int getMark() {
        return mark;
    }

    public void setMark(int mark) {
        this.mark = mark;
    }
    public Mark (int id, int student_id, int subject_id, int mark) {
        this.setId(id);
        this.setStudent_id(student_id);
        this.setSubject_id(subject_id);
        this.setMark(mark);
    }
    public Mark ( int student_id, int subject_id, int mark) {
        this.setStudent_id(student_id);
        this.setSubject_id(subject_id);
        this.setMark(mark);
    }

    @Override
    public String toString() {
        return "Mark(" + "id=" + id + ", mark='" + mark + ", student_id='" + student_id + ", subject_id='" + subject_id + '\'' +  ')';
    }

}
