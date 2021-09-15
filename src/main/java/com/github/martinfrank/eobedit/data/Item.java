package com.github.martinfrank.eobedit.data;

public class Item {

    private final byte firstHex;
    private final byte secondHex;
    private final Items.ItemType[] types;
    private final Items.ItemClass[] classes;
    private final String description;

    public Item(String firstHex, String secondHex, Items.ItemType[] types, Items.ItemClass[] classes, String description) {
        this.firstHex = ByteArrayTool.asByte(firstHex);
        this.secondHex = ByteArrayTool.asByte(secondHex);
        this.types = types;
        this.classes = classes;
        this.description = description;
    }
}
