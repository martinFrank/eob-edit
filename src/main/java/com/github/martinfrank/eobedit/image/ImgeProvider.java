package com.github.martinfrank.eobedit.image;

import com.github.martinfrank.eobedit.data.Item;
import com.github.martinfrank.eobedit.data.Portrait;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Locale;

public class ImgeProvider {

    private static final String ITEM_DIR = "src/main/resources/item";
    private static final String PORTRAIT_DIR = "src/main/resources/portrait";
    private static final String GUI_DIR = "src/main/resources/gui";

    private static final String PNG = ".PNG";
    private static final String GUI_PAGE_A = "PAGE_A";
    private static final String GUI_PAGE_B = "PAGE_B";
    private static final String PORT = "PORT_";


    public BufferedImage getItem(Item item) {
        return loadImageOrNull(ITEM_DIR, item.getId() + PNG);
    }

    public BufferedImage getPortrait(Portrait portrait) {
        String id = (portrait.id < 0x10 ? "0" : "") + Integer.toHexString(portrait.id).toUpperCase(Locale.ROOT);
        return loadImageOrNull(PORTRAIT_DIR, PORT + id + PNG);

    }

    public BufferedImage getGuiPageA() {
        return loadImageOrNull(GUI_DIR, GUI_PAGE_A + PNG);
    }

    public BufferedImage getGuiPageB() {
        return loadImageOrNull(GUI_DIR, GUI_PAGE_B + PNG);
    }

    private BufferedImage loadImageOrNull(String dir, String filename) {
        try {
            return ImageIO.read(new File(dir, filename));
        } catch (IOException e) {
            e.printStackTrace();
            ;
            return null;
        }
    }
}
