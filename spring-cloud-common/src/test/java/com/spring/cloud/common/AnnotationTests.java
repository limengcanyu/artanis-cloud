package com.spring.cloud.common;

import com.spring.cloud.common.controller.SampleController;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

@Slf4j
public class AnnotationTests {

    @Test
    public void test() {
        Class<SampleController> aClass = SampleController.class;
        Annotation[] annotations = aClass.getAnnotations();
        for (Annotation annotation : annotations) {
            log.debug("class annotationType: {}", annotation.annotationType());
        }

        // get annotation of method
        Method[] methods = aClass.getDeclaredMethods();
        for (Method method : methods) {
            Annotation[] methodAnnotations = method.getAnnotations();
            for (Annotation methodAnnotation : methodAnnotations) {
                log.debug("method: {} annotationType: {}", method.getName(), methodAnnotation.annotationType());
            }
        }

        // get annotation of field
        Field[] fields = aClass.getDeclaredFields();
        for (Field field : fields) {
            Annotation[] fieldAnnotations = field.getAnnotations();
            for (Annotation fieldAnnotation : fieldAnnotations) {
                log.debug("field: {} annotationType: {}", field.getName(), fieldAnnotation.annotationType());
            }
        }

    }

}
