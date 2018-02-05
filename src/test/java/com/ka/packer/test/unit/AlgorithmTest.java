package com.ka.packer.test.unit;

import com.ka.packer.model.Container;
import com.ka.packer.model.Item;
import com.ka.packer.model.Package;
import com.ka.packer.service.PackerAlgorithm;
import com.ka.packer.service.PermutationAlgorithm;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertTrue;

/**
 * Description: Packer algorithms unit tests
 * Project: packer
 * Package: com.ka.packer.test.unit
 * Author: kakyurek
 * Date: 2018.02.04
 */
public class AlgorithmTest {

    private static final String VALID_FILE_PATH = "valid-file.txt";
    private static List<Container> containers;

    @BeforeClass
    public static void beforeTests() {
        containers = new ArrayList<>();
        Container container = new Container();
        container.setLimit(75);
        container.setItems(Arrays.asList(
                new Item(1, 85.31, 29),
                new Item(2, 14.55, 74),
                new Item(3, 3.98, 16),
                new Item(4, 26.24, 55),
                new Item(5, 63.69, 52),
                new Item(6, 76.25, 75),
                new Item(7, 60.02, 74),
                new Item(8, 93.18, 35),
                new Item(4, 89.95, 78)));
        containers.add(container);
    }

    @Test
    public void shouldPackerReturnTruePackageItems() {
        PackerAlgorithm algorithm = new PermutationAlgorithm();
        List<Package> packages = algorithm.pack(containers);
        assertTrue(packages.size() == 1 && packages.get(0).getItems().size() == 2);
        assertEquals(148, packages.get(0).getItems().get(0).getCost() + packages.get(0).getItems().get(1).getCost());
    }

}