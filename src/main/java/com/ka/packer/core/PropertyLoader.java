package com.ka.packer.core;

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
    public Properties loadProperties(final String configFilePath) throws Exception {
        Properties properties = new Properties();

        // Load properties file from class path
        try (InputStream is = PropertyLoader.class.getClassLoader().getResourceAsStream(configFilePath)) {
            properties.load(is);
        }

        return properties;
    }

}