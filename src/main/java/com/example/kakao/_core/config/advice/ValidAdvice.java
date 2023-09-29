package com.example.kakao._core.config.advice;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;

import com.example.kakao._core.errors.exception.Exception400;

@Aspect
@Component
public class ValidAdvice {

    @Pointcut("@annotation(org.springframework.web.bind.annotation.PostMapping)")
    public void PostMapping() {

    }


    @Before("PostMapping()")
    public void validationAdvice(JoinPoint jp) throws Throwable {
        Object[] args = jp.getArgs();
        for (Object arg : args) {
            if(arg instanceof Errors) {
                Errors errors = (Errors) arg;

                if(errors.hasErrors()) {
                    List<FieldError> filedErrors = errors.getFieldErrors();
                    throw new Exception400(
                        filedErrors.get(0).getDefaultMessage() + " : " + filedErrors.get(0).getField()
                    );
                }
            }
        }
        
    }

}