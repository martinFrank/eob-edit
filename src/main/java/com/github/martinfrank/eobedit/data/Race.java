package com.github.martinfrank.eobedit.data;

public enum Race {

    HUMAN_MALE("Human","male", 0), HUMAN_FEMALE("Human", "female", 1),
    ELF_MALE("Elf", "male",2), ELF_FEMALE("Elf", "female", 3),
    HALF_ELF_MALE("Half Elf", "male", 4), HALF_ELF_FEMALE("Half Elf", "female", 5),
    DWARF_MALE("Dwarf", "male", 6),DWARF_FEMALE("Dwarf", "female", 7),
    GNOME_MALE("Gnome", "male", 8),GNOME_FEMALE("Gnome", "female", 9),
    HALFLING_MALE("Halfling", "male", 10),HALFLING_FEMALE("Halfling", "female", 11);

    public final String description;
    private final String sex;
    public final byte id;

    Race(String description, String sex, int id ) {
        this.description = description;
        this.sex = sex;
        this.id = (byte)id;
    }

    @Override
    public String toString() {
        return description+" "+sex;
    }

    }
