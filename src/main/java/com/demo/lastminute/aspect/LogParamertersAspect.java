package com.demo.lastminute.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * Created by lcaspani on 13/10/17.
 */

@Aspect
@Component
public class LogParamertersAspect {

    //@Around("@annotation(LogMethodInputParameters)")
    @Around("execution(* com.demo.lastminute.service..*.*(..))")
    public Object before(ProceedingJoinPoint caller) throws Throwable {

        MethodSignature signature = (MethodSignature) caller.getSignature();
        Method method = signature.getMethod();

        Object[] parameters = caller.getArgs();

        Logger log = LoggerFactory.getLogger(caller.getTarget().getClass());

        log.info("###### Class : {} ; Method : {} ", caller.getTarget().getClass().getName(), caller.getSignature().getName());
        String parametersString = "";
        for (int i = 0; i < parameters.length; i++) {
            Object parameterValue = parameters[i];
            parametersString += "value= " + parameterValue + ", ";
        }

        log.info("parameters: {} ", parametersString);

        return caller.proceed();

    }


}
