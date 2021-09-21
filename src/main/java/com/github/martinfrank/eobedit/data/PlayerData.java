package com.github.martinfrank.eobedit.data;

import java.util.Arrays;

public class PlayerData {

    public static final int CHARACTER_NAME_OFFSET = 2;
    public static final int CHARACTER_NAME_LENGTH = 10;

    public static final int INVENTORY_LENGTH = 2;
    public static final int INVENTORY_OFFSET = 123;
    public static final int INVENTORY_SLOT_AMOUNT = 14;

    public static final int STATS_LENGTH = 2;

    public static final int EXP_LVL1_OFFSET = 36;
    public static final int EXP_LVL2_OFFSET = 37;
    public static final int EXP_LVL3_OFFSET = 38;
    public static final int EXP_LVL_LENGTH = 1;
    public static final int EXP_PTS1_OFFSET = 47;
    public static final int EXP_PTS2_OFFSET = 47;
    public static final int EXP_PTS3_OFFSET = 47;
    public static final int EXP_PTS_LENGTH = 3;

    public static final int PROFESSION_OFFSET = 32;
    public static final int PROFESSION_LENGTH = 1;

    public static final int RACE_OFFSET = 31;
    public static final int RACE_LENGTH = 1;

    public static final int ALIGNMENT_OFFSET = 33;
    public static final int ALIGNMENT_LENGTH = 1;

    public static final int PORTRAIT_OFFSET = 34;
    public static final int PORTRAIT_LENGTH = 1;

    public static final int FOODLEVEL_OFFSET = 35;
    public static final int FOODLEVEL_LENGTH = 1;


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
        if (index < 0 || index > INVENTORY_SLOT_AMOUNT){
            throw new IllegalArgumentException("invalid inventory index (allowed is 0...13)");
        }
        int offset = INVENTORY_OFFSET + index * INVENTORY_LENGTH;
        byte[] data = ByteArrayTool.copy(content, offset, INVENTORY_LENGTH);
        return Items.getItem(data);
    }

    public void setInventory(int index, Item item){
        if (index < 0 || index > INVENTORY_SLOT_AMOUNT){
            throw new IllegalArgumentException("invalid inventory index (allowed is 0...13)");
        }
        int offset = INVENTORY_OFFSET + index * INVENTORY_LENGTH;
        ByteArrayTool.set(content, item.id, offset, INVENTORY_LENGTH);
    }

    public Stat getStat(Stat.Stats stats) {
        int offset = stats.offset;
        byte[] data = ByteArrayTool.copy(content, offset, STATS_LENGTH);
        if (stats == Stat.Stats.STR){
            byte[] percent = ByteArrayTool.copy(content, offset+STATS_LENGTH, STATS_LENGTH);
            return new Stat(stats, data, percent);
        }
        return new Stat(stats, data);
    }

    public void setStat(Stat.Stats stats, int current, int max, int percentCurrent, int percentMax) {
        if (stats == Stat.Stats.AC) {
            max = 0;
        }
        byte[] values = new byte[]{(byte) current, (byte) max};
        byte[] percent = new byte[]{(byte) percentCurrent, (byte) percentMax};
        ByteArrayTool.set(content, values, stats.offset, STATS_LENGTH);
        if(stats == Stat.Stats.STR){
            ByteArrayTool.set(content, percent, stats.offset+STATS_LENGTH, STATS_LENGTH);
        }
    }

    public void setStat(Stat.Stats stats, int current, int max) {
        setStat(stats, current, max,0,0);
    }
    public void setStat(Stat.Stats stats, int value) {
        setStat(stats, value, value,0,0);
    }

    public int getExpLevelFirst(){
        return 0xFF & ByteArrayTool.copy(content, EXP_LVL1_OFFSET, EXP_LVL_LENGTH)[0];
    }
    public int getExpLevelSecond(){
        return 0xFF & ByteArrayTool.copy(content, EXP_LVL2_OFFSET, EXP_LVL_LENGTH)[0];
    }
    public int getExpLevelThird(){
        return 0xFF & ByteArrayTool.copy(content, EXP_LVL3_OFFSET, EXP_LVL_LENGTH)[0];
    }

    public int getExpPointsFirst(){
        byte[] expData = ByteArrayTool.copy(content, EXP_PTS1_OFFSET, EXP_PTS_LENGTH);
        return ByteArrayTool.asInt(expData);
    }
    public int getExpPointsSecond(){
        byte[] expData = ByteArrayTool.copy(content, EXP_PTS2_OFFSET, EXP_PTS_LENGTH);
        return ByteArrayTool.asInt(expData);
    }
    public int getExpPointsThird(){
        byte[] expData = ByteArrayTool.copy(content, EXP_PTS3_OFFSET, EXP_PTS_LENGTH);
        return ByteArrayTool.asInt(expData);
    }

    public void setExpLevelFirst(int lvl){
        byte[] data = new byte[]{(byte)lvl};
        ByteArrayTool.set(content, data, EXP_LVL1_OFFSET, EXP_LVL_LENGTH);
    }
    public void setExpLevelSecond(int lvl){
        byte[] data = new byte[]{(byte)lvl};
        ByteArrayTool.set(content, data, EXP_LVL2_OFFSET, EXP_LVL_LENGTH);
    }
    public void setExpLevelThird(int lvl){
        byte[] data = new byte[]{(byte)lvl};
        ByteArrayTool.set(content, data, EXP_LVL3_OFFSET, EXP_LVL_LENGTH);
    }

    public void setExpPointsFirst(int expPoints){
        byte[] data = ByteArrayTool.fromInt(expPoints,EXP_PTS_LENGTH);
        ByteArrayTool.set(content, data, EXP_PTS1_OFFSET,EXP_PTS_LENGTH);
    }
    public void setExpPointsSecond(int expPoints){
        byte[] data = ByteArrayTool.fromInt(expPoints,EXP_PTS_LENGTH);
        ByteArrayTool.set(content, data, EXP_PTS2_OFFSET,EXP_PTS_LENGTH);
    }
    public void setExpPointsThird(int expPoints){
        byte[] data = ByteArrayTool.fromInt(expPoints,EXP_PTS_LENGTH);
        ByteArrayTool.set(content, data, EXP_PTS3_OFFSET,EXP_PTS_LENGTH);
    }

    public Profession getProfession() {
        byte[] data = ByteArrayTool.copy(content, PROFESSION_OFFSET, PROFESSION_LENGTH);
        for (Profession prof: Profession.values()){
            if (data[0] == prof.id){
                return prof;
            }
        }
        return null;
    }

    public void setProfession(Profession profession) {
        byte[] prof = new byte[]{profession.id};
        ByteArrayTool.set(content, prof, PROFESSION_OFFSET, PROFESSION_LENGTH);
        int firstLevel = getExpLevelFirst();
        if (profession.amount == 1){
            setExpLevelSecond(0);
            setExpLevelThird(0);
        }
        if (profession.amount == 2) {
            if( getExpLevelSecond() == 0){
                setExpLevelSecond(firstLevel);
            }
            if(getExpLevelThird() != 0){
                setExpLevelThird(0);
            }
        }
        if (profession.amount == 3) {
            if( getExpLevelSecond() == 0){
                setExpLevelSecond(firstLevel);
            }
            if(getExpLevelThird() == 0)
                setExpLevelThird(firstLevel);
        }
    }

    public Race getRace() {
        byte[] data = ByteArrayTool.copy(content, RACE_OFFSET, RACE_LENGTH);
        for (Race race: Race.values()){
            if (data[0] == race.id){
                return race;
            }
        }
        return null;
    }

    public void setRace(Race race) {
        byte[] rc = new byte[]{race.id};
        ByteArrayTool.set(content, rc, RACE_OFFSET, RACE_LENGTH);
    }

    public void setAlignment(Alignment alignment){
        byte[] data = ByteArrayTool.fromInt(alignment.id,ALIGNMENT_LENGTH);
        ByteArrayTool.set(content, data, ALIGNMENT_OFFSET,ALIGNMENT_LENGTH);
    }
    public Alignment getAlignment(){
        int id = 0xFF & ByteArrayTool.copy(content, ALIGNMENT_OFFSET, ALIGNMENT_LENGTH)[0];
        for(Alignment alignment: Alignment.values()){
            if(alignment.id == id){
                return alignment;
            }
        }
        return null;
    }

    public void setFoodLevelPercent(int percent){
        if(percent < 0 || percent > 100){
            throw new IllegalArgumentException("percent must be 0..100, but is: "+percent);
        }
        byte[] data = ByteArrayTool.fromInt(percent,FOODLEVEL_LENGTH);
        ByteArrayTool.set(content, data, FOODLEVEL_OFFSET,FOODLEVEL_LENGTH);
    }
    public int getFoodLevelPercent(){
        return 0xFF & ByteArrayTool.copy(content, FOODLEVEL_OFFSET, FOODLEVEL_LENGTH)[0];
    }

    public void setPortrait(Portrait portrait){
        byte[] data = ByteArrayTool.fromInt(portrait.id,PORTRAIT_LENGTH);
        ByteArrayTool.set(content, data, PORTRAIT_OFFSET,PORTRAIT_LENGTH);
    }
    public Portrait getPortrait(){
        int id = 0xFF & ByteArrayTool.copy(content, PORTRAIT_OFFSET, PORTRAIT_LENGTH)[0];
        for(Portrait portrait: Portrait.values()){
            if(portrait.id == id){
                return portrait;
            }
        }
        return null;
    }

}
