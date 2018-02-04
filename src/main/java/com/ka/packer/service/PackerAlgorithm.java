package com.ka.packer.service;

import com.ka.packer.model.Container;
import com.ka.packer.model.Package;

import java.util.List;

/**
 * Description: Packer algorithm interface
 * Project: packer
 * Package: com.ka.packer.service
 * Author: kakyurek
 * Date: 2018.02.04
 */
public interface PackerAlgorithm {

    /**
     * Process containers and find optimal combinations and return package
     *
     * @param containers Containers that has different items in it
     * @return List of optimal packages for each container
     */
    List<Package> pack(List<Container> containers);

}