package com.bp.learnblog.validators;

import javax.validation.Constraint;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import javax.validation.Payload;
import java.lang.annotation.*;

/**
 * 加班超时校验
 *
 * @author DH
 */
@Constraint(validatedBy = {WorkOverTimeValidator.class})
@Documented
@Target({ElementType.ANNOTATION_TYPE, ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface WorkOverTime {
    String message() default "加班时间过长, 不能超过{max}小时";
    int max() default 5;
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}

class WorkOverTimeValidator implements ConstraintValidator<WorkOverTime, Integer> {

    WorkOverTime work;
    int max;

    @Override
    public void initialize(WorkOverTime constraintAnnotation) {
        // 获取注解定义
        this.work = constraintAnnotation;
        max = constraintAnnotation.max();
    }

    @Override
    public boolean isValid(Integer value, ConstraintValidatorContext constraintValidatorContext) {
        // 校验注解
        if (value == null) {
            return true;
        }
        return value < max;
    }
}
