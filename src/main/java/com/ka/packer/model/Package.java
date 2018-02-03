package com.ka.packer.model;

import lombok.*;

import java.util.List;

/**
 * Description: Package model holds selected items for each container
 * Project: packer
 * Package: com.ka.packer.model
 * Author: kakyurek
 * Date: 2018.02.03
 */
@Getter
@Setter
@ToString
@NoArgsConstructor
public class Package {

    @NonNull
    private List<Item> items;

}