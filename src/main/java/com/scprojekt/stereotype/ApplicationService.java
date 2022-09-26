package com.scprojekt.stereotype;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * ApplicationService Stereotype, use for Application Layer
 */
@Target(TYPE)
@Retention(RUNTIME)
@Documented
public @interface ApplicationService {
}
