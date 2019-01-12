package com.exam.exception;

public class ExamWebException extends ExamException {
	
	public final ExamWebError examWebError;

    public ExamWebException(ExamWebError examWebError) {
        this.examWebError = examWebError;
    }

}
