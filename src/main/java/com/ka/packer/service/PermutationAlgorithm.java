package com.ka.packer.service;

import com.ka.packer.model.Container;
import com.ka.packer.model.Item;
import com.ka.packer.model.Package;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Description: Permutation algorithm to find best valued but less weighted items to pack
 * Project: packer
 * Package: com.ka.packer.service
 * Author: kakyurek
 * Date: 2018.02.04
 */
public class PermutationAlgorithm implements PackerAlgorithm {

    /**
     * Process containers and find optimal combinations using all permutations and return package
     *
     * @param containers Containers that has different items in it
     * @return List of optimal packages for each container
     */
    @Override
    public List<Package> pack(List<Container> containers) {
        final List<Package> optimalPackages = new ArrayList<>();

        // Generating all permutations
        containers.forEach(c -> {
            List<List<Item>> permutations = new ArrayList<>();
            createPermutations(permutations, Collections.emptyList(), c.getItems());
            int bestCost = 0;
            double minimalWeight = 0;
            int containerLimit = c.getLimit();
            Package optimalPackage = new Package();

            // Iterate over all permutations and find optimal combinations to add package
            for (List<Item> permutation : permutations) {
                double permutationWeight = permutation.stream().mapToDouble(Item::getWeight).sum();
                int permutationCost = permutation.stream().mapToInt(Item::getCost).sum();

                // Select optimal packages based on priorities (price > weight)
                if (permutationWeight < containerLimit) {

                    // Take best price first
                    if (permutationCost > bestCost) {
                        bestCost = permutationCost;
                        optimalPackage.setItems(permutation);
                        minimalWeight = permutationWeight;
                    } else if (permutationCost == bestCost && permutationWeight < minimalWeight) {

                        // If prices are same take the minimal weight as optimal package
                        minimalWeight = permutationWeight;
                        optimalPackage.setItems(permutation);
                    }
                }
            }

            // Add found optimal package for iterated container
            optimalPackages.add(optimalPackage);
        });

        return optimalPackages;
    }

    /**
     * Generates all item combinations
     *
     * @param items Items in container to be processed and calculate combinations
     */
    private static void createPermutations(List<List<Item>> permutations, List<Item> prefixItems, List<Item> items) {
        int n = items.size();
        if (n == 0) {
            permutations.add(prefixItems);
        } else {
            for (int i = 0; i < n; ++i) {
                List<Item> newPrefix = new ArrayList<>(prefixItems);
                newPrefix.add(items.get(i));
                List<Item> itemsLeft = new ArrayList<>(items);
                itemsLeft.remove(i);
                permutations.add(prefixItems);
                createPermutations(permutations, newPrefix, itemsLeft);
            }
        }
    }

}