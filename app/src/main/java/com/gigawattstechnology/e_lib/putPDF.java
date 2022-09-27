package com.gigawattstechnology.e_lib;

public class putPDF {
    String subjectname;
    String pdfurl;
    public putPDF(String subjectname, String pdfurl) {
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
