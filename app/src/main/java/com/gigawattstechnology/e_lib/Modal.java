package com.gigawattstechnology.e_lib;

public class Modal {
    private String subject;
    private int subjecticon;

    public Modal(String subject, int subjecticon) {
        this.subject = subject;
        this.subjecticon = subjecticon;
    }

    public String getSubject() {
        return subject;
    }

    public int getSubjecticon() {
        return subjecticon;
    }
}
