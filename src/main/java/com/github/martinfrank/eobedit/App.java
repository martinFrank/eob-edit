package com.github.martinfrank.eobedit;

import com.github.martinfrank.eobedit.data.*;
import com.github.martinfrank.eobedit.image.ImageProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.awt.image.BufferedImage;
import java.io.IOException;

public class App {

    private static final Logger LOGGER = LoggerFactory.getLogger(App.class);

    public static void main(String[] args) throws IOException {
        App app = new App();
        app.start(args);
    }

    private void start(String[] args) throws IOException {
        if (args == null) {
            throw new IllegalArgumentException("you must provide a savegame file");
        }

        SavegameFile file = new SavegameFile();
        file.load(args[0]);
        PlayerData playerData = file.getPlayer(0);
//        LOGGER.debug("my alignment: {}",playerData.getAlignment());
//        playerData.setInventory(0, Items.FLICKA);
//        LOGGER.debug("item {}",file.getPlayer(2).getInventory(3));
//        BufferedImage image = new ImageProvider().getItem(file.getPlayer(2).getInventory(3));
//        LOGGER.debug("image: {}",image);
//        playerData.setName("Joshi");
//        playerData.setPortrait(Portrait.PORTRAIT_FEMALE_03);
//        file.save();
//        LOGGER.debug("Player 0 {}", playerData.getName());
        for(int i = 0; i < 14; i ++){
            byte[] id = new byte[]{(byte)(0x2a+i),0};
            Item item = Items.getItem(id);
            playerData.setInventory(i, item);
        }
        file.save();
    }

}
