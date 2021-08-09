package br.com.sembous.expertmodule.dto;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = ContentFormatValidator.class)
public @interface ContentFormat {
    String message() default "invalid fields and/or format";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
