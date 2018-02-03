package com.ka.packer.test.unit;

import com.ka.packer.dao.ContainerLoader;
import com.ka.packer.dao.FileLoader;
import com.ka.packer.exception.APIException;
import org.junit.Test;

import java.util.UUID;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Description: Unit tests for file loader
 * Project: packer
 * Package: com.ka.packer.test.unit
 * Author: kakyurek
 * Date: 2018.02.03
 */
public class FileLoaderUnitTest {

    private static final String VALID_FILE_PATH = "valid-file.txt";
    private static final String EMPTY_FILE_PATH = "empty-file.txt";
    private static final String INVALID_FILE_PATH = "invalid-file.txt";

    private ContainerLoader dataLoader;

    @Test(expected = APIException.class)
    public void shouldUnknowFilePathThrowsException() {
        dataLoader = new FileLoader(UUID.randomUUID().toString(), true);
        dataLoader.load();
    }

    @Test
    public void shouldValidFileIsParsed() {
        dataLoader = new FileLoader(VALID_FILE_PATH, true);
        assertTrue(dataLoader.load().size() > 0);
    }

    @Test
    public void shouldEmptyFileReturnsEmptyList() {
        dataLoader = new FileLoader(EMPTY_FILE_PATH, true);
        assertEquals(0, dataLoader.load().size());
    }

    @Test(expected = APIException.class)
    public void shouldInvalidFileThrowsException() {
        dataLoader = new FileLoader(INVALID_FILE_PATH, true);
        dataLoader.load();
    }

}
