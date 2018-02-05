package com.ka.packer.dao;

import com.ka.packer.model.Container;
import com.ka.packer.model.Item;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Stream;

/**
 * Description: File data loader that reads text based file and fills packages into list
 * Project: packer
 * Package: com.ka.packer.dao
 * Author: kakyurek
 * Date: 2018.02.03
 */
public class FileLoader implements ContainerLoader {

    /**
     * Absolute file path to load containers
     */
    private String filePath;

    /**
     * Whether file is read from classpath
     */
    private boolean classPathResource = false;

    public FileLoader(String filePath, boolean classPathResource) {
        this.filePath = filePath;
        this.classPathResource = classPathResource;
    }

    /**
     * Loads containers from file system
     *
     * @return Containers to process
     */
    public List<Container> load() {
        final List<Container> containers = new ArrayList<>();
        AtomicInteger containerIndex = new AtomicInteger(0);

        // Reading and parsing input file
        try (Stream<String> stream = Files.lines(!classPathResource ? Paths.get(filePath) : Paths.get(ClassLoader.getSystemResource(filePath).toURI()))) {
            stream.forEach(s -> {
                Container container = new Container();

                // Splitting parts by colon to get limit and items separately
                String[] containerParts = s.trim().split(":");
                int weightLimit = Integer.parseInt(containerParts[0].trim());
                container.setLimit(weightLimit);

                // Iterating and parsing items of container with format (1,53.38,€45) (2,88.62,€98)
                String[] itemParts;
                String[] items = containerParts[1].trim().split(" ");

                // Mapping item values within each item with format (1,53.38,€45)
                for (String containerItem : items) {
                    itemParts = containerItem.split(",");
                    container.getItems().add(new Item(Integer.parseInt(itemParts[0].substring(1)), Double.parseDouble(itemParts[1]), Integer.parseInt(itemParts[2].substring(1, itemParts[2].length() - 1))));
                }

                containers.add(container);
                containerIndex.getAndDecrement();
            });
        } catch (URISyntaxException | IOException e) {
            // Nothing to do
        }

        return containers;
    }

}