package com.blog_platform.blog_platform_spring_boot.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class EnumValueValidator implements ConstraintValidator<EnumValueConstraint, String> {
    private EnumValueConstraint annotation;

    @Override
    public void initialize(EnumValueConstraint constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);

        this.annotation = constraintAnnotation;
    }

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        if (s == null) {
            return true;
        }

        Object[] enumValues = annotation.enumClass().getEnumConstants();
        if (enumValues != null) {
            for (Object enumValue : enumValues) {
                if (s.equals(enumValue.toString())) {
                    return true;
                }
            }
        }
        return false;
    }
}
