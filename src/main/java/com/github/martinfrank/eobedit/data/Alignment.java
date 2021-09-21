package com.github.martinfrank.eobedit.data;

public enum Alignment {
    LAWFUL_GOOD(0, "lawful good"), NEUTRAL_GOOD(1, "neutral good"), CHAOTIC_GOOD(2, "chaotic good"),
    LAWFUL_NEUTRAL(3, "lawful neutral"), ABSOLUT_NEUTRAL(4, "true neutral"),CHAOTIC_NEUTRAL(5, "chaotic neutral"),
    LAWFUL_EVIL(6, "lawful evil"), NEUTRAL_EVIL(7, "neutral evil"),CHAOTIC_EVIL(9, "chaotic evil");

    public final int id;
    public final String desc;

    Alignment(int id, String desc){
        this.id = id;
        this.desc = desc;
    }

    @Override
    public String toString(){
        return desc;
    }
}
