package com.ka.packer.validation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Description: Interface for container validations
 * Project: packer
 * Package: com.ka.packer.validation
 * Author: kakyurek
 * Date: 2018.02.04
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface ContainerValidator {
}