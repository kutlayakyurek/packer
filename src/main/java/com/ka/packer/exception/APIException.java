package com.ka.packer.exception;

/**
 * Description: Runtime exception for invalid input data
 * Project: packer
 * Package: com.ka.packer.exception
 * Author: kakyurek
 * Date: 2018.02.03
 */
public class APIException extends RuntimeException {

    public APIException(String message) {
        super(message);
    }

}
