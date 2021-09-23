package com.github.martinfrank.eobedit;

import com.github.martinfrank.eobedit.data.PlayerData;
import com.github.martinfrank.eobedit.data.SavegameFile;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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
        SavegameFile file = new SavegameFile(args[0]);
        PlayerData playerData = file.getPlayer(0);
        LOGGER.debug("Player 0 {}", playerData.getName());
    }

}
