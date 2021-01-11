package org.wahlzeit.utils;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface DesignPatternInstance {
    String patternName();
    String[] participants();
    String type();
    String roleOfAnnotatedClass();

}
