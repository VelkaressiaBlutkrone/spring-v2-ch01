package com.metacoding.springv2.core.handler;

import com.metacoding.springv2.core.handler.ex.Exception400;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;
import org.springframework.validation.*;
import java.util.List;

/**
 * 전역 유효성 검증 AOP 핸들러 클래스.
 * @Aspect를 통해 POST/PUT 요청 메서드 실행 전에 Bean Validation 결과를 검사한다.
 * Errors 객체에 유효성 오류가 있으면 Exception400을 발생시켜 GlobalExceptionHandler에서 처리하도록 한다.
 */
@Aspect
@Component
public class GlobalValidationHandler {

    // POST/PUT 메서드 실행 전 유효성 검증 오류 확인
    @Before("@annotation(org.springframework.web.bind.annotation.PostMapping) || @annotation(org.springframework.web.bind.annotation.PutMapping)")
    public void badRequestAdvice(JoinPoint jp) {
        Object[] args = jp.getArgs();
        for (Object arg : args) {

            if (arg instanceof Errors) {
                Errors errors = (Errors) arg;

                if (errors.hasErrors()) {
                    List<FieldError> fErrors = errors.getFieldErrors();

                    for (FieldError fieldError : fErrors) {
                        throw new Exception400(fieldError.getField() + ":" + fieldError.getDefaultMessage());
                    }
                }
            }
        }
    }
}