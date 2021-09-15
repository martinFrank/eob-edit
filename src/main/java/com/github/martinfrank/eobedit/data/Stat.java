package com.github.martinfrank.eobedit.data;

public class Stat {

    public enum Stats {
        STR("Strength",13), INT("Intelligence",17), WIS("Wisdom",19),
        DEX("Dexterity",21), CON("Constitution",23), CHA("Charisma",25),
        HP("Hit points",27), AC("Armor class",29);

        public final String longName;
        public final int offset;

        Stats(String longName, int offset){
            this.longName = longName;
            this.offset = offset;
        }
    }

    private final byte[] content;
    private byte[] percent;
    private final Stats stats;

    public Stat(Stats stats, byte[] content) {
        this.stats = stats;
        this.content = content;
    }

    public Stat(Stats stats, byte[] content, byte[] percent) {
        this.stats = stats;
        this.content = content;
        this.percent = percent;
    }

    public int getCurrent(){
        return 0xFF & content[0];
    }
    public int getMax(){
        return 0xFF & content[1];
    }

    @Override
    public String toString() {
        if(stats == Stats.AC){
            return ""+stats.longName+":"+getCurrent();
        }
        if(stats == Stats.STR){
            return ""+stats.longName+":"+getCurrent()+"/"+getMax()+":"+getPercentCurrent()+"/"+getPercentMax();
        }
        return ""+stats.longName+":"+getCurrent()+"/"+getMax();
    }

    private int getPercentMax() {
        return 0xFF & percent[0];
    }

    private int getPercentCurrent() {
        return 0xFF & percent[1];
    }
}
