package com.nofirst.spring.tdd.demo.exception;


import com.nofirst.spring.tdd.demo.common.ResultCode;

/**
 * The type Question not existed exception.
 */
public class QuestionNotExistedException extends ApiException {

    /**
     * Instantiates a new Question not existed exception.
     */
    public QuestionNotExistedException() {
        super(ResultCode.FAILED, "question not exist");
    }
}
