package com.ka.packer.service;

import com.ka.packer.core.PropertyLoader;
import com.ka.packer.dao.ContainerLoader;
import com.ka.packer.model.Container;
import com.ka.packer.model.Item;
import com.ka.packer.model.Package;

import java.util.Comparator;
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
     * @param filePath File to be processed
     * @return Result string with format 4,7
     */
    public static String pack(String filePath) throws Exception {

        // Load configurations
        Properties properties = PropertyLoader.getInstance().loadProperties(PropertyLoader.APPLICATION_PROPERTIES);
        PackerAlgorithm packerAlgorithm = (PackerAlgorithm) Class.forName(properties.getProperty("packer.algorithm")).getDeclaredConstructor().newInstance();
        ContainerLoader containerLoader = (ContainerLoader) Class.forName(properties.getProperty("container.loader")).getDeclaredConstructor(new Class[]{String.class, boolean.class}).newInstance(filePath, false);
        List<Container> containers = containerLoader.load();

        // Execute the algorithm specified in configuration file
        List<Package> optimalPackages = packerAlgorithm.pack(containers);

        // Process and return result in String format
        StringBuilder resultBuilder = new StringBuilder();

        // Generate result text from optimal packages
        optimalPackages.forEach(p -> {
            if (p.getItems().isEmpty()) {
                resultBuilder.append("-");
            } else {
                List<Item> items = p.getItems();
                items.sort(Comparator.comparingInt(Item::getIndex));
                items.forEach(i -> {
                    resultBuilder.append(i.getIndex());

                    // Append ',' if item is not last one
                    if (items.indexOf(i) != items.size() - 1) {
                        resultBuilder.append(",");
                    }
                });
            }
            resultBuilder.append("\n");
        });

        return resultBuilder.toString();
    }

}
