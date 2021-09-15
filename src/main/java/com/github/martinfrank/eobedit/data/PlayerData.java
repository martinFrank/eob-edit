package com.github.martinfrank.eobedit.data;

import java.util.Arrays;

public class PlayerData {

    public static final int CHARACTER_NAME_OFFSET = 2;
    public static final int CHARACTER_NAME_LENGTH = 10;

    public static final int INVENTORY_OFFSET = 123;

    private final byte[] content;
    private final int slot;

    public PlayerData(int slot, byte[] content ){
        this.slot = slot;
        this.content = content;
    }


    public String getName(){
        byte[] name = ByteArrayTool.copy(content, CHARACTER_NAME_OFFSET, CHARACTER_NAME_LENGTH);
        return ByteArrayTool.asString(name);
    }

    public byte[] getContent() {
        return content;
    }

    public void setName(String name) {
        byte[] nameByte = name.getBytes();
        ByteArrayTool.set(content, nameByte, CHARACTER_NAME_OFFSET, CHARACTER_NAME_LENGTH);
    }

    public Item getInventory(int index){
        if (index < 0 || index > 12){
            throw new IllegalArgumentException("invalid inventory index (allowed is 0...11)");
        }

        byte[] data = ByteArrayTool.copy(content, INVENTORY_OFFSET, 2);

//        ItemItemType type = Items.ItemType.AMULETT;
//        return new Item(data);
        return null;
    }
}
