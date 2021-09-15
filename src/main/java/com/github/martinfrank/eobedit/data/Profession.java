package com.github.martinfrank.eobedit.data;

public enum Profession {

    FIGHTER("Fighter", 0, 1), RANGER("Ranger", 1, 1),
    PALADIN("Paladin", 2, 1), MAGE("Mage", 3, 1),
    CLERIC("Cleric", 4, 1), THIEF("Thief", 5, 1),
    FIGHTER_CLERIC("Fighter/Cleric", 6, 2),
    FIGHTER_THIEF("Fighter/Thief", 7, 2),
    FIGHTER_MAGE("Fighter/Mage", 8, 2),
    FIGHTER_MAGE_THIEF("Fighter/Mage/Thief", 9, 3),
    THIEF_MAGE("Thief/Mage", 10,2), CLERIC_THIEF("Cleric/Thief", 11,2),
    FIGHTER_CLERIC_MAGE("Fighter/Cleric/Mage", 12,3),
    RANGER_CLERIC("Ranger/Cleric", 13, 2), CLERIC_MAGE("Cleric/Mage", 14, 2);

    public final String description;
    public final int id;
    public final int amount ;

    Profession(String description, int id, int amount) {
        this.description = description;
        this.id = id;
        this.amount = amount;
    }

    @Override
    public String toString() {
        return description;
    }


}
