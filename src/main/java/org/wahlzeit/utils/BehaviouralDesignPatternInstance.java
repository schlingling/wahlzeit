package org.wahlzeit.utils;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)

public @interface BehaviouralDesignPatternInstance {
    String patternName();
    String[] metaParticipants();
    String[] participants();
    String roleOfAnnotatedMethod();
}
