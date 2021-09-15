package com.github.martinfrank.eobedit.data;

import java.util.Arrays;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Items {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(Items.class);
    
    public static class ItemType{
        
        public static final ItemType ITEM = new ItemType("IT", "Item", "any other item");
        public static final ItemType DAGGER = new ItemType("DA", "Dagger", "Dagger or Dart");
        public static final ItemType PRIMARY = new ItemType("PR", "Primary", "Primary Weapon");
        public static final ItemType SECONDARY = new ItemType("SE", "Secondary", "Secondary Weapon");
        public static final ItemType SHIELD = new ItemType("SH", "Shield", "Shield");
        public static final ItemType TWOHANDED = new ItemType("TW", "TwoHanded", "Two handed Weapon");
        public static final ItemType RING = new ItemType("RI", "Ring", "Ring");
        public static final ItemType AMULETT = new ItemType("AM", "Amulett", "Amulett");
        public static final ItemType ARMOR = new ItemType("AR", "Armor", "Body Armor");
        public static final ItemType BRACER = new ItemType("BR", "Bracers", "Bracers or Arm Protections");
        public static final ItemType HELMET = new ItemType("HE", "Helmet", "Helmet");
        public static final ItemType BOOTS = new ItemType("BO", "Boots", "Boots or Foot wear");
        public static final ItemType[] TYPES = new ItemType[]{ 
            ITEM,
            DAGGER,
            PRIMARY,
            SECONDARY,
            SHIELD,
            TWOHANDED,
            RING,
            AMULETT,
            ARMOR,
            BRACER,
            HELMET,
            BOOTS
        };
        
        public final String typeAbbreviation;
        public final String typeName;
        public final String typeDescription;
        
        public ItemType(String typeAbbreviation, String typeName, String typeDescription){
            this.typeAbbreviation = typeAbbreviation;
            this.typeName = typeName;
            this.typeDescription = typeDescription;
        }
        
        @Override
        public String toString(){
            return typeName;
        }
        
    }
    public static class ItemClass{
        
        public static final ItemClass FIGHTER = new ItemClass("F", "Fighter");
        public static final ItemClass RANGER = new ItemClass("R", "Ranger");
        public static final ItemClass PALADIN = new ItemClass("P", "Paladin");
        public static final ItemClass MAGE = new ItemClass("M", "Mage");
        public static final ItemClass CLERIC = new ItemClass("C", "Cleric");
        public static final ItemClass THIEF = new ItemClass("T", "Thief");
        public static final ItemClass[] CLASSES = new ItemClass[]{ 
            FIGHTER,
            RANGER,
            PALADIN,
            MAGE,
            CLERIC,
            THIEF
        };
        
        public final String classAbbreviation;
        public final String className;
        
        public ItemClass(String classAbbreviation, String className){
            this.classAbbreviation = classAbbreviation;
            this.className = className;
        }
        
        @Override
        public String toString(){
            return className;
        }
        
    }
    
    public static final Item COMMISION_LETTER_A = new Item("22", "00", getType("IT"), getClasses("FRPMCT"), "Commision and letter of Marque");
    public static final Item COMMISION_LETTER_B = new Item("A6", "00", getType("IT"), getClasses("FRPMCT"), "Commision and letter of Marque");
    public static final Item DAGGER_A = new Item("04", "00", getType("DA"), getClasses("FRPMCT"), "Dagger");
    public static final Item IRON_RATION_A = new Item("4E", "01", getType("IT"), getClasses("FRPMCT"), "Iron Rations");
    public static final Item LOCK_PICKS_A = new Item("06", "00", getType("IT"), getClasses("RT"), "Lock Picks");
    public static final Item NOTHING = new Item("00", "00", getType("IT"), getClasses("FRPMCT"), "nothing");
    public static final Item ROBE_A = new Item("02", "00", getType("AR"), getClasses("FRPMCT"), "Robe");
    public static final Item[] ITEMS = new Item[]{ 
        COMMISION_LETTER_A,
        COMMISION_LETTER_B,
        DAGGER_A,
        IRON_RATION_A,
        LOCK_PICKS_A,
        NOTHING,
        ROBE_A
    };
    
    public static ItemType getType(String s){
        for(ItemType type: ItemType.TYPES){
            if(type.typeAbbreviation.equalsIgnoreCase(s)){
                return type;
            }
        }
        throw new IllegalArgumentException("unknown type "+s);
    }
    
    public static ItemClass getClass(String s){
        for(ItemClass clazz: ItemClass.CLASSES){
            if(clazz.classAbbreviation.equalsIgnoreCase(s)){
                return clazz;
            }
        }
        throw new IllegalArgumentException("unknown clazz "+s);
    }
    
    public static ItemClass[] getClasses(String str){
        ItemClass[] classes = new ItemClass[str.length()];
        for(int i = 0; i < str.length(); i++){
            classes[i] = getClass(""+str.charAt(i));
        }
        return classes;
    }
    public static Item getItem(byte[] data) {
        for (Item item: ITEMS){
            if (Arrays.equals(data, item.id)){
                return item;
            }
        }
        LOGGER.debug("no item found for id {}", Arrays.toString(data));
        return null;
    }
    

}
