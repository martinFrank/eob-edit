package com.github.martinfrank.eobedit.generate;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

public class ItemListGenerator {

    private static final String TYPES = "src/main/resources/types.txt";
    private static final String CLASSES = "src/main/resources/classes.txt";
    private static final String ITEMS = "src/main/resources/items.txt";

    public static void main(String[] args) throws IOException {
        List<String> typeLines = Files.readAllLines(new File(TYPES).toPath());
        List<String> classLines = Files.readAllLines(new File(CLASSES).toPath());
        List<String> itemLines = Files.readAllLines(new File(ITEMS).toPath());

        List<String> itemsLines = new ArrayList<>();
        createItemListHeader(itemsLines);

        //types
        createItemTypesHeader(itemsLines);
        createItemTypes(itemsLines, typeLines);
        createItemTypesFooter(itemsLines);

        //classes
        createItemClassesHeader(itemsLines);
        createItemClasses(itemsLines, classLines);
        createItemClassesFooter(itemsLines);

        //items
        createItems(itemsLines, itemLines);

        createItemListFooter(itemsLines);
        for (String line : itemsLines) {
            System.out.println(line);
        }
    }

    private static void createItems(List<String> itemListLines, List<String> itemLines) {
        itemListLines.add("    ");
        for(String clazz: itemLines){
            String[] entries = clazz.split(",");
            itemListLines.add("    public static final Item "+entries[0]+" = new Item(\""+entries[1]+"\", \""+entries[2]+"\", getTypes(\""+entries[3]+"\"), getClasses(\""+entries[4]+"\"), \""+entries[5]+"\");");
        }

        itemListLines.add("    public static final Item[] ITEMS = new Item[]{ ");
        for(int i = 0; i < itemLines.size(); i ++){
            String[] entries = itemLines.get(i).split(",");
            itemListLines.add("        "+entries[0]+(i<itemLines.size()-1?",":""));
        }
        itemListLines.add("    };");
    }

    private static void createItemClassesHeader(List<String> itemListLines) {
        itemListLines.add("    public static class ItemClass{");
        itemListLines.add("        ");
    }

    private static void createItemClasses(List<String> itemListLines, List<String> clazzes) {
        for(String clazz: clazzes){
            String[] entries = clazz.split(",");
            itemListLines.add("        public static final ItemClass "+entries[0]+" = new ItemClass(\""+entries[1]+"\", \""+entries[2]+"\");");
        }

        itemListLines.add("        public static final ItemClass[] CLASSES = new ItemClass[]{ ");
        for(int i = 0; i < clazzes.size(); i ++){
            String[] entries = clazzes.get(i).split(",");
            itemListLines.add("            "+entries[0]+(i<clazzes.size()-1?",":""));
        }
        itemListLines.add("        };");

    }

    private static void createItemClassesFooter(List<String> itemListLines) {
        itemListLines.add("        ");
        itemListLines.add("        public final String classAbbreviation;");
        itemListLines.add("        public final String className;");
        itemListLines.add("        ");
        itemListLines.add("        public ItemClass(String classAbbreviation, String className){");
        itemListLines.add("            this.classAbbreviation = classAbbreviation;");
        itemListLines.add("            this.className = className;");
        itemListLines.add("        }");
        itemListLines.add("    }");
    }


    private static void createItemTypesHeader(List<String> itemListLines) {
        itemListLines.add("    public static class ItemType{");
        itemListLines.add("        ");
    }

    private static void createItemTypes(List<String> itemListLines, List<String> types) {
        for(String type: types){
            String[] entries = type.split(",");
            itemListLines.add("        public static final ItemType "+entries[0]+" = new ItemType(\""+entries[1]+"\", \""+entries[2]+"\", \""+entries[3]+"\");");
        }

        itemListLines.add("        public static final ItemType[] TYPES = new ItemType[]{ ");
        for(int i = 0; i < types.size(); i ++){
            String[] entries = types.get(i).split(",");
            itemListLines.add("            "+entries[0]+(i<types.size()-1?",":""));
        }
        itemListLines.add("        };");

    }

    private static void createItemTypesFooter(List<String> itemListLines) {
        itemListLines.add("        ");
        itemListLines.add("        public final String typeAbbreviation;");
        itemListLines.add("        public final String typeName;");
        itemListLines.add("        public final String typeDescription;");
        itemListLines.add("        ");
        itemListLines.add("        public ItemType(String typeAbbreviation, String typeName, String typeDescription){");
        itemListLines.add("            this.typeAbbreviation = typeAbbreviation;");
        itemListLines.add("            this.typeName = typeName;");
        itemListLines.add("            this.typeDescription = typeDescription;");
        itemListLines.add("        }");
        itemListLines.add("    }");
    }

    private static void createItemListFooter(List<String> itemListLines) {
        itemListLines.add("    ");
        itemListLines.add("    public static ItemType getType(String s){");
        itemListLines.add("        for(ItemType type: ItemType.TYPES){");
        itemListLines.add("            if(type.typeAbbreviation.equalsIgnoreCase(s)){");
        itemListLines.add("                return type;");
        itemListLines.add("            }");
        itemListLines.add("        }");
        itemListLines.add("        throw new IllegalArgumentException(\"unknown type \"+s);");
        itemListLines.add("    }");
        itemListLines.add("    ");
        itemListLines.add("    public static ItemClass getClass(String s){");
        itemListLines.add("        for(ItemClass clazz: ItemClass.CLASSES){");
        itemListLines.add("            if(clazz.classAbbreviation.equalsIgnoreCase(s)){");
        itemListLines.add("                return clazz;");
        itemListLines.add("            }");
        itemListLines.add("        }");
        itemListLines.add("        throw new IllegalArgumentException(\"unknown clazz \"+s);");
        itemListLines.add("    }");
        itemListLines.add("    ");
        itemListLines.add("    public static ItemClass[] getClasses(String str){");
        itemListLines.add("        ItemClass[] classes = new ItemClass[str.length()];");
        itemListLines.add("        for(int i = 0; i < str.length(); i++){");
        itemListLines.add("            classes[i] = getClass(\"\"+str.charAt(i));");
        itemListLines.add("        }");
        itemListLines.add("        return classes;");
        itemListLines.add("    }");
        itemListLines.add("    ");
        itemListLines.add("    public static ItemType[] getTypes(String str){");
        itemListLines.add("        ItemType[] types = new ItemType[str.length()];");
        itemListLines.add("        for(int i = 0; i < str.length(); i++){");
        itemListLines.add("            types[i] = getType(\"\"+str.charAt(i));");
        itemListLines.add("        }");
        itemListLines.add("        return types;");
        itemListLines.add("    }");
        itemListLines.add("    ");
        itemListLines.add("");
        itemListLines.add("}");
    }

    private static void createItemListHeader(List<String> itemListLines) {
        itemListLines.add("package com.github.martinfrank.eobedit.data;");
        itemListLines.add("");
        itemListLines.add("public class Items {");
        itemListLines.add("    ");
    }

}
