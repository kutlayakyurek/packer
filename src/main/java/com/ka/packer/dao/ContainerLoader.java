package com.ka.packer.dao;

import com.ka.packer.model.Container;

import java.util.List;

/**
 * Description: Input data loader interface
 * Project: packer
 * Package: com.ka.packer.dao
 * Author: kakyurek
 * Date: 2018.02.03
 */
public interface ContainerLoader {

    /**
     * Loads containers to process
     *
     * @return Containers
     */
    List<Container> load();

}