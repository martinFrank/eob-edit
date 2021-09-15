package com.github.martinfrank.eobedit.data;

import java.util.Arrays;

public class Item {

    public final byte[] id = new byte[2];
    public final Items.ItemType type;
    public final Items.ItemClass[] classes;
    public final String description;

    public Item(String firstHex, String secondHex, Items.ItemType type, Items.ItemClass[] classes, String description) {
        id[0] = ByteArrayTool.asByte(firstHex);
        id[1] = ByteArrayTool.asByte(secondHex);
        this.type = type;
        this.classes = classes;
        this.description = description;
    }

    @Override
    public String toString() {
        return "Item{" +
                "id=" + Arrays.toString(id) +
                ", type=" + type.typeDescription +
                ", classes=" + Arrays.toString(classes) +
                ", description='" + description + '\'' +
                '}';
    }
}
