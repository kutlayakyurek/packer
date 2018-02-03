package com.ka.packer.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * Description: Item model which is part of a package
 * Project: packer
 * Package: com.ka.packer.model
 * Author: kakyurek
 * Date: 2018.02.03
 */
@Getter
@Setter
@ToString
@AllArgsConstructor
public class Item {

    private int index;
    private double weight;
    private int value;

}