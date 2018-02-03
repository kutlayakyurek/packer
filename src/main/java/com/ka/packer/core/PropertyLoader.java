package com.ka.packer.core;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Description: Reads application configuration before start execution
 * Project: packer
 * Package: com.ka.packer.core
 * Author: kakyurek
 * Date: 2018.02.03
 */
public class PropertyLoader {

    private static final PropertyLoader instance = new PropertyLoader();

    private PropertyLoader() {
    }

    public static PropertyLoader getInstance() {
        return instance;
    }

    /**
     * Loads properties from configuration file of the application
     *
     * @param configFilePath Configuration file relative class path
     * @return Loaded properties
     */
    public Properties loadProperties(final String configFilePath) {
        Properties properties = new Properties();

        try (InputStream is = PropertyLoader.class.getResourceAsStream(configFilePath)) {
            properties.load(is);
        } catch (IOException ex) {
            System.err.println(ex.getMessage());
        }

        return properties;
    }

}