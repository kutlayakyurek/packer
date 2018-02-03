package com.ka.packer.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Description: Container that includes items to process and form the package
 * Project: packer
 * Package: com.ka.packer.model
 * Author: kakyurek
 * Date: 2018.02.03
 */
public class Container {

    private List<Item> items = new ArrayList<>();

    private double limit;

    public List<Item> getItems() {
        if (items == null) {
            items = new ArrayList<>();
        }

        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    public double getLimit() {
        return limit;
    }

    public void setLimit(double limit) {
        this.limit = limit;
    }

    @Override
    public String toString() {
        return "Container{" +
                "items=" + items +
                ", limit=" + limit +
                '}';
    }

}