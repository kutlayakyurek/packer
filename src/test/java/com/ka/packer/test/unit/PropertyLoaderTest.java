package com.ka.packer.test.unit;

import com.ka.packer.core.PropertyLoader;
import org.junit.Test;

import java.util.Properties;
import java.util.UUID;

import static junit.framework.TestCase.assertTrue;

/**
 * Description: Unit tests for property loader
 * Project: packer
 * Package: com.ka.packer.test.unit
 * Author: kakyurek
 * Date: 2018.02.03
 */
public class PropertyLoaderTest {

    private static final String PROPERTIES_FILE = "application.properties";

    @Test
    public void shouldLoadValidPropertiesFile() throws Exception {
        Properties properties = PropertyLoader.getInstance().loadProperties(PROPERTIES_FILE);
        assertTrue(properties.size() > 0);
    }

    @Test(expected = Exception.class)
    public void shouldInvalidPropertiesFileThrowsException() throws Exception {
        PropertyLoader.getInstance().loadProperties(UUID.randomUUID().toString());
    }

}
