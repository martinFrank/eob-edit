package com.github.martinfrank.eobedit.data;

import java.util.Arrays;

public class Item {

    public final byte[] id = new byte[2];
    public final Items.ItemType type;
    public final Items.ItemClass[] classes;
    public final String description;
    public final String details;

    public Item(String firstHex, String secondHex, Items.ItemType type, Items.ItemClass[] classes, String description, String details) {
        id[0] = ByteArrayTool.asByte(firstHex);
        id[1] = ByteArrayTool.asByte(secondHex);
        this.type = type;
        this.classes = classes;
        this.description = description;
        this.details = details;
    }

    public Item(int high, int low) {
        id[0] = (byte)high;
        id[1] = (byte)low;
        this.type = null;
        this.classes = null;
        this.description = null;
        this.details = null;
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
