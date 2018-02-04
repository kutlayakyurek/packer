package com.ka.packer.service;

import com.ka.packer.core.PropertyLoader;
import com.ka.packer.dao.ContainerLoader;
import com.ka.packer.model.Container;
import com.ka.packer.model.Package;

import java.util.List;
import java.util.Properties;

/**
 * Description: Packer service implementation
 * Project: packer
 * Package: com.ka.packer.service
 * Author: kakyurek
 * Date: 2018.02.03
 */
public class Packer {

    /**
     * File to be processed
     *
     * @param filePath
     * @return
     */
    public static String pack(String filePath) throws Exception {

        // Load configurations
        Properties properties = PropertyLoader.getInstance().loadProperties(PropertyLoader.APPLICATION_PROPERTIES);
        PackerAlgorithm packerAlgorithm = (PackerAlgorithm) Class.forName(properties.getProperty("packer.algorithm")).getDeclaredConstructor().newInstance();
        ContainerLoader containerLoader = (ContainerLoader) Class.forName(properties.getProperty("container.loader")).getDeclaredConstructor(new Class[]{String.class}).newInstance(filePath);
        List<Container> containers = containerLoader.load();

        // Execute the algorithm specified in configuration file
        List<Package> optimalPackages = packerAlgorithm.pack(containers);

        // Process and return result in String format
        StringBuilder resultBuilder = new StringBuilder();

        return resultBuilder.toString();
    }

}
