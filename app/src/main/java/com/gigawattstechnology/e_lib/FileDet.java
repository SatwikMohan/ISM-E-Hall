package com.gigawattstechnology.e_lib;

public class FileDet {
    String subjectname;
    String pdfurl;

    public FileDet(String subjectname, String pdfurl) {
        this.subjectname = subjectname;
        this.pdfurl = pdfurl;
    }

    public String getSubjectname() {
        return subjectname;
    }

    public String getPdfurl() {
        return pdfurl;
    }
}
