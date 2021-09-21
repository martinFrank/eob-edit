package com.github.martinfrank.eobedit;

import com.github.martinfrank.eobedit.data.*;
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
        PlayerData p = file.getPlayer(0);
        int start = 9 * PlayerData.INVENTORY_SLOT_AMOUNT; int low = 1;
        for(int i = 0; i < PlayerData.INVENTORY_SLOT_AMOUNT; i++){
            int high = start + i;
            Item unknown = new Item(high, low);
            LOGGER.debug("setting 0x{} 0x{} at {}",Integer.toHexString(high),Integer.toHexString(low),i);
            p.setInventory(i, unknown);
        }


//        Profession profession = p.getProfession();
//        LOGGER.debug("current profession: {}",profession);
//        Profession newJob = Profession.FIGHTER_CLERIC_MAGE;
//        p.setProfession(newJob);
//        profession = p.getProfession();
//        Race newRace = Race.HALFLING_MALE;
//        p.setRace(newRace);
//        LOGGER.debug("upddated profession: {}",profession);

        Item it = Items.ADAMATITE_DART_A;


//        for(int i = 0; i < 4; i++){
//            PlayerData pd = file.getPlayer(i);
//            LOGGER.debug("Player {}, race {}, prof {}",pd.getName(), pd.getRace(), pd.getProfession());
//        }

        file.save();
    }

}
