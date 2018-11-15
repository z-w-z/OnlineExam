package com.exam.web.exception;

public class ExamWebException extends Exception {
	
	public final ExamWebError examWebError;

    public ExamWebException(ExamWebError examWebError) {
        this.examWebError = examWebError;
    }

}
