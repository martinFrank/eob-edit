package com.github.martinfrank.eobedit.data;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;

public class SavegameFile {

    public static final int AMOUNT_CHARACTERS = 6;
    public static final int CHARACTER_DATA_LENGTH = 243;


    private static final Logger LOGGER = LoggerFactory.getLogger(SavegameFile.class);

    private final File file;
    private byte[] content;
    private final PlayerData[] playerData = new PlayerData[AMOUNT_CHARACTERS];

    public SavegameFile(String filename) throws IOException {
        if(filename == null){
            throw new IllegalArgumentException("filename must be provided");
        }
        file = new File(filename);
        if(!file.exists()){
            throw new IllegalArgumentException("file "+filename+" does not exist");
        }
        reload();

    }

    public void reload() throws IOException {
        content = Files.readAllBytes(file.toPath());
        copyContentIntoPlayerData();
        LOGGER.debug("successfully loaded file {}", file.getName());
    }

    private void copyContentIntoPlayerData() {
        for(int i = 0; i < AMOUNT_CHARACTERS; i ++){
            int position = i * CHARACTER_DATA_LENGTH;
            playerData[i] = new PlayerData(ByteArrayTool.copy(content, position, CHARACTER_DATA_LENGTH));
        }
    }

    public void save() throws IOException {
        copyPlayerDataIntoContent();
        Files.write(file.toPath(), content, StandardOpenOption.WRITE);
        LOGGER.debug("successfully written file {}", file.getName());
    }

    private void copyPlayerDataIntoContent() {
        for(int i = 0; i < AMOUNT_CHARACTERS; i ++){
            int position = i * CHARACTER_DATA_LENGTH;
            System.arraycopy(playerData[i].getContent(), 0, content, position, CHARACTER_DATA_LENGTH);
        }
    }

    public PlayerData getPlayer(int i) {
        return playerData[i];
    }
}
