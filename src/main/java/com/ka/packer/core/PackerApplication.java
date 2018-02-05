package com.ka.packer.core;

import com.ka.packer.exception.APIException;
import com.ka.packer.service.Packer;

/**
 * Description: Packer application for knapsacking optimal values inside the bounds of the weight limit
 * Project: packer
 * Package: com.ka.packer.core
 * Author: kakyurek
 * Date: 2018.02.03
 */
public class PackerApplication {

    private static final boolean MODE_TEST = false;

    public static void main(String[] args) throws Exception {
        String filePath;

        if (!MODE_TEST) {

            // Unless file is specified, throw API exception
            if (args == null || args.length == 0) {
                throw new APIException("Please provide absolute file path to be processed");
            }

            filePath = args[0];
        } else {
            filePath = "test-file.txt";
        }

        System.out.println(Packer.pack(filePath));
    }

}
