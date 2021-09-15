package com.github.martinfrank.eobedit;

import com.github.martinfrank.eobedit.data.Items;
import com.github.martinfrank.eobedit.data.PlayerData;
import com.github.martinfrank.eobedit.data.SavegameFile;
import com.github.martinfrank.eobedit.data.Stat;
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
        if (args == null ){
            throw new IllegalArgumentException("you must provide a savegame file");
        }
        SavegameFile file = new SavegameFile(args[0]);
        for(int i = 0; i < 4; i ++){
            PlayerData p = file.getPlayer(i);
            p.setStat(Stat.Stats.STR, 18,18,100,100);
            p.setStat(Stat.Stats.DEX, 18);
            p.setStat(Stat.Stats.CON, 18);
            p.setStat(Stat.Stats.INT, 18);
            p.setStat(Stat.Stats.WIS, 18);
            p.setStat(Stat.Stats.CHA, 18);
            p.setStat(Stat.Stats.HP, 100);
        }

        file.save();
    }

}
