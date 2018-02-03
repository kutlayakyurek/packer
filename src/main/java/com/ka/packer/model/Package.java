package com.ka.packer.model;

import java.util.List;

/**
 * Description: Package model holds selected items for each container
 * Project: packer
 * Package: com.ka.packer.model
 * Author: kakyurek
 * Date: 2018.02.03
 */
public class Package {

    private List<Item> items;

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    @Override
    public String toString() {
        return "Package{" +
                "items=" + items +
                '}';
    }

}