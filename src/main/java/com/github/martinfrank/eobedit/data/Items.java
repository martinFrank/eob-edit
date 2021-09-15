package com.github.martinfrank.eobedit.data;

public class Items {

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
    }

    public static final Item NOTHING = new Item("00", "00", getTypes("IT"), getClasses("FRPMCT"), "nothing");
    public static final Item ROBE_A = new Item("02", "00", getTypes("AR"), getClasses("FRPMCT"), "Robe");
    public static final Item DAGGER_A = new Item("04", "00", getTypes("DA"), getClasses("FRPMCT"), "Dagger");
    public static final Item LOCK_PICKS_A = new Item("06", "00", getTypes("IT"), getClasses("RT"), "Lock Picks");
    public static final Item[] ITEMS = new Item[]{
            NOTHING,
            ROBE_A,
            DAGGER_A,
            LOCK_PICKS_A
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

    public static ItemType[] getTypes(String str){
        ItemType[] types = new ItemType[str.length()];
        for(int i = 0; i < str.length(); i++){
            types[i] = getType(""+str.charAt(i));
        }
        return types;
    }


}
