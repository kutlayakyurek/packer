package com.ka.packer.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

/**
 * Description: Container that includes items to process and form the package
 * Project: packer
 * Package: com.ka.packer.model
 * Author: kakyurek
 * Date: 2018.02.03
 */
@Getter
@Setter
@ToString
@NoArgsConstructor
public class Container {

    private List<Item> items = new ArrayList<>();

    private double limit;

}
