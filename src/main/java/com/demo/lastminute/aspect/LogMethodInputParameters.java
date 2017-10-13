package com.demo.lastminute.aspect;

import org.springframework.stereotype.Component;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Created by lcaspani on 13/10/17.
 */

@Component
@Retention(RetentionPolicy.RUNTIME)
public @interface LogMethodInputParameters {


}

